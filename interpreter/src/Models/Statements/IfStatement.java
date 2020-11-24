package Models.Statements;

import Exceptions.InvalidTypeException;
import Models.ADTs.MyStack.MyStack;
import Models.Expressions.IExpression;
import Models.ProgramState;
import Models.Types.BoolType;
import Models.Values.BoolValue;
import Models.Values.IValue;

public class IfStatement implements IStatement {
    private final IExpression condition;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(IExpression exp, IStatement thenStatement, IStatement elseStatement) {
        this.condition = exp;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "(if (" + this.condition.toString() + ")" +
                " then (" + this.thenStatement.toString() + ")" +
                " else (" + this.elseStatement.toString() + "))";
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        IValue conditionResult = this.condition.evaluate(state.getSymbolTable(), state.getHeap());

        if (!conditionResult.getType().equals(new BoolType())) {
            throw new InvalidTypeException("Condition in the if statement isn't a boolean value.");
        }

        MyStack<IStatement> stack = state.getExecutionStack();
        BoolValue result = (BoolValue) conditionResult;
        if (result.getValue()) {
            stack.push(thenStatement);
        } else {
            stack.push(elseStatement);
        }

        return state;
    }
}
