package Repositories;

import Models.ProgramState;

public interface IProgramRepository {
    ProgramState getCurrentProgram();

    void addProgram(ProgramState state);
}
