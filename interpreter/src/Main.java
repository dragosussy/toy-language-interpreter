import Controllers.Controller;
import Models.ADTs.MyDictionary.MyDictionary;
import Models.ADTs.MyList.MyList;
import Models.ADTs.MyStack.MyStack;
import Models.Expressions.*;
import Models.Expressions.Operations.ArithmeticalOperation;
import Models.Expressions.Operations.RelationalOperation;
import Models.Heap.Heap;
import Models.Heap.IHeap;
import Models.ProgramState;
import Models.Statements.*;
import Models.Statements.File.CloseReadFile;
import Models.Statements.File.OpenReadFileStatement;
import Models.Statements.File.ReadFileStatement;
import Models.Statements.Heap.NewAllocationStatement;
import Models.Statements.Heap.WriteToHeapStatement;
import Models.Types.BoolType;
import Models.Types.IntType;
import Models.Types.ReferenceType;
import Models.Types.StringType;
import Models.Values.BoolValue;
import Models.Values.IValue;
import Models.Values.IntValue;
import Models.Values.StringValue;
import Repositories.ProgramRepository;
import Services.Logger.FileLogger;
import Views.*;
import Views.MenuView.Commands.ExitCommand;
import Views.MenuView.Commands.RunCommand;
import Views.MenuView.TextMenuView;
import com.sun.jdi.Value;

import java.io.BufferedReader;

/*TODO: Major refactoring.*/

public class Main {
    public static void main(String[] args) {
        var logger = new FileLogger("output.log");
        var repository = new ProgramRepository();
        var controller = new Controller(repository, logger);

        /* int v; v = 2; Print(v) */
        MyStack<IStatement> executionStack1 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable1 = new MyDictionary<>();
        MyList<IValue> outputList1 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        IHeap<IValue> heap1 = new Heap<>();
        IStatement ex1 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("v", new IntType()),
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                }
        );
        executionStack1.push(ex1);
        ProgramState ex1_state = new ProgramState(executionStack1, symbolTable1, outputList1, fileTable1, heap1);
        repository.addProgram(ex1_state);


        /* int a;int b; a=2+3*5;b=a+1;Print(b) */
        MyStack<IStatement> executionStack2 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable2 = new MyDictionary<>();
        MyList<IValue> outputList2 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        IHeap<IValue> heap2 = new Heap<>();
        IStatement ex2 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("a", new IntType()),
                        new VariableDeclarationStatement("b", new IntType()),
                        new AssignmentStatement("a",
                                new ArithmeticalExpression(new ValueExpression(new IntValue(2)), ArithmeticalOperation.Addition,
                                        new ArithmeticalExpression(new ValueExpression(new IntValue(3)), ArithmeticalOperation.Multiplication, new ValueExpression(new IntValue(5))))),
                        new AssignmentStatement("b",
                                new ArithmeticalExpression(new VariableExpression("a"), ArithmeticalOperation.Addition, new ValueExpression(new IntValue(1)))),
                        new PrintStatement(new VariableExpression("b"))
                }
        );
        executionStack2.push(ex2);
        ProgramState ex2_state = new ProgramState(executionStack2, symbolTable2, outputList2, fileTable2, heap2);
        repository.addProgram(ex2_state);


        /*bool a; int v; a = true; (If a Then v = 2 Else v = 3); Print(v)*/
        MyStack<IStatement> executionStack3 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable3 = new MyDictionary<>();
        MyList<IValue> outputList3 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        IHeap<IValue> heap3 = new Heap<>();
        IStatement ex3 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("a", new BoolType()),
                        new VariableDeclarationStatement("v", new IntType()),
                        new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new IfStatement(
                                new VariableExpression("a"), // condition
                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))), // then
                                new AssignmentStatement("v", new ValueExpression(new IntValue(3))) // else
                        ),
                        new PrintStatement(new VariableExpression("v"))
                }
        );
        executionStack3.push(ex3);
        ProgramState ex3_state = new ProgramState(executionStack3, symbolTable3, outputList3, fileTable3, heap3);
        repository.addProgram(ex3_state);

        /*int a; a=false*/
        MyStack<IStatement> executionStack4 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable4 = new MyDictionary<>();
        MyList<IValue> outputList4 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        IHeap<IValue> heap4 = new Heap<>();
        IStatement ex4 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("a", new IntType()),
                        new AssignmentStatement("a", new ValueExpression(new BoolValue(false)))
                }
        );
        executionStack4.push(ex4);
        ProgramState ex4_state = new ProgramState(executionStack4, symbolTable4, outputList4, fileTable4, heap4);
        repository.addProgram(ex4_state);


        /*string f;
        f="test.in";
        OpenReadFile(f);
        int c;
        ReadFile(f, c); print(c);
        ReadFile(f, c)l print(c);
        CloseReadFile(f)*/
        MyStack<IStatement> executionStack5 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable5 = new MyDictionary<>();
        MyList<IValue> outputList5 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        IHeap<IValue> heap5 = new Heap<>();
        IStatement ex5 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("f", new StringType()),
                        new AssignmentStatement("f", new ValueExpression(new StringValue("test.in"))),
                        new OpenReadFileStatement(new VariableExpression("f")),
                        new VariableDeclarationStatement("c", new IntType()),
                        new ReadFileStatement(new VariableExpression("f"), "c"),
                        new PrintStatement(new VariableExpression("c")),
                        new ReadFileStatement(new VariableExpression("f"), "c"),
                        new PrintStatement(new VariableExpression("c")),
                        new CloseReadFile(new VariableExpression("f"))
                }
        );
        executionStack5.push(ex5);
        ProgramState ex5_state = new ProgramState(executionStack5, symbolTable5, outputList5, fileTable5, heap5);
        repository.addProgram(ex5_state);

        /*int a; int b=a+1; print(a>b)*/
        MyStack<IStatement> executionStack6 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable6 = new MyDictionary<>();
        MyList<IValue> outputList6 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        IHeap<IValue> heap6 = new Heap<>();
        IStatement ex6 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("a", new IntType()),
                        new VariableDeclarationStatement("b", new IntType()),
                        new AssignmentStatement("b",
                                new ArithmeticalExpression(
                                        new VariableExpression("a"),
                                        ArithmeticalOperation.Addition,
                                        new ValueExpression(new IntValue(1))
                                )
                        ),
                        new PrintStatement(
                                new RelationalExpression(
                                        new VariableExpression("a"),
                                        RelationalOperation.LessThan,
                                        new VariableExpression("b")
                                )
                        )
                }
        );
        executionStack6.push(ex6);
        ProgramState ex6_state = new ProgramState(executionStack6, symbolTable6, outputList6, fileTable6, heap6);
        repository.addProgram(ex6_state);

        /*
         * Reference int v;
         * new(v,20);
         * Reference Reference int a;
         * new(a, v);
         * print(v);
         * WriteToHeap(v, 30)
         * print(ReadHeap(ReadHeap(a)) + 5)
         */
        MyStack<IStatement> executionStack7 = new MyStack<>();
        MyDictionary<String, IValue> symbolTable7 = new MyDictionary<>();
        MyList<IValue> outputList7 = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        IHeap<IValue> heap7 = new Heap<>();
        IStatement ex7 = CompoundStatement.fromArray(new IStatement[]{
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new NewAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                        new NewAllocationStatement("a", new VariableExpression("v")),
                        new PrintStatement(new VariableExpression("v")),
                        new WriteToHeapStatement("v", new ValueExpression(new IntValue(30))),
                        new PrintStatement(new ArithmeticalExpression(
                                new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                ArithmeticalOperation.Addition,
                                new ValueExpression(new IntValue(5))
                            )
                        )
                }
        );
        executionStack7.push(ex7);
        ProgramState ex7_state = new ProgramState(executionStack7, symbolTable7, outputList7, fileTable7, heap7);
        repository.addProgram(ex7_state);

        IView view = new TextMenuView()
                .addCommand(new RunCommand("1", ex1.toString(), controller))
                .addCommand(new RunCommand("2", ex2.toString(), controller))
                .addCommand(new RunCommand("3", ex3.toString(), controller))
                .addCommand(new RunCommand("4", ex4.toString(), controller))
                .addCommand(new RunCommand("5", ex5.toString(), controller))
                .addCommand(new RunCommand("6", ex6.toString(), controller))
                .addCommand(new RunCommand("7", ex7.toString(), controller))
                .addCommand(new ExitCommand("x", "Exit"));
        view.run();
    }
}
