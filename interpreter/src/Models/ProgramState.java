package Models;

import Models.ADTs.MyDictionary.MyDictionary;
import Models.ADTs.MyList.MyList;
import Models.ADTs.MyStack.MyStack;
import Models.Heap.IHeap;
import Models.Statements.IStatement;
import Models.Values.IValue;
import Models.Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private final MyStack<IStatement> executionStack;
    private final MyDictionary<String, IValue> symbolTable;
    private final MyList<IValue> outputList;
    private final MyDictionary<StringValue, BufferedReader> fileTable;
    private final IHeap<IValue> heap;

    private final IStatement originalProgram;

    public ProgramState(MyStack<IStatement> stack,
                        MyDictionary<String, IValue> table,
                        MyList<IValue> output,
                        MyDictionary<StringValue, BufferedReader> fileTable,
                        IHeap<IValue> heap,
                        IStatement program) {
        this.executionStack = stack;
        this.symbolTable = table;
        this.outputList = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = program; //TODO: make a deep copy of the program
        this.executionStack.push(program);
    }

    public ProgramState(MyStack<IStatement> stack,
                        MyDictionary<String, IValue> table,
                        MyList<IValue> output,
                        MyDictionary<StringValue, BufferedReader> fileTable,
                        IHeap<IValue> heap) {
        this.executionStack = stack;
        this.symbolTable = table;
        this.outputList = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = null;
    }

    public MyDictionary<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }

    public MyStack<IStatement> getExecutionStack() {
        return this.executionStack;
    }

    public MyList<IValue> getOutputList() {
        return this.outputList;
    }

    public MyDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IHeap<IValue> getHeap() {
        return this.heap;
    }

    @Override
    public String toString() {
        return "========== Execution stack ==========\n" + this.executionStack + "\n" +
                "========== Symbol table ==========\n" + this.symbolTable + "\n" +
                "========== File table ==========\n" + this.fileTable + "\n" +
                "========== Output list ==========\n" + this.outputList + "\n" +
                "========== Heap ==========\n" + this.heap + "\n\n";
    }
}
