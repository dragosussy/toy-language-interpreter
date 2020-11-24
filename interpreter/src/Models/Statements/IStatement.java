package Models.Statements;

import Models.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws RuntimeException;
}
