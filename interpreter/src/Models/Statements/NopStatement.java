package Models.Statements;

import Models.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public String toString() {
        return "Nop";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }
}
