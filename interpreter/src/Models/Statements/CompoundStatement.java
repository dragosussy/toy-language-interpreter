package Models.Statements;

import Models.ADTs.MyStack.MyStack;
import Models.ProgramState;

import java.util.Arrays;

public class CompoundStatement implements IStatement {
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return this.first.toString() + "; " + this.second.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) {
        MyStack<IStatement> stack = state.getExecutionStack();
        stack.push(this.second);
        stack.push(this.first);
        return state;
    }

    @Deprecated
    public static CompoundStatement fromArray(IStatement[] statements) {
        if (statements.length == 0) {
            return new CompoundStatement(new NopStatement(), new NopStatement());
        } else {
            var remainingStatements = Arrays.copyOfRange(statements, 1, statements.length);
            return new CompoundStatement(statements[0], fromArray(remainingStatements));
        }
    }
}
