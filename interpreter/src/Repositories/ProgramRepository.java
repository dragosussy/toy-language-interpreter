package Repositories;

import Models.ADTs.MyList.MyList;
import Models.ProgramState;
import Services.Logger.ILogger;

import java.util.Arrays;

public class ProgramRepository implements IProgramRepository {
    private final MyList<ProgramState> programs = new MyList<>();

    public ProgramRepository(ProgramState... programs) {
        this.programs.addAll(Arrays.asList(programs));
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programs.remove(0);
    }

    public ProgramState getProgram(int index) {
        return programs.get(index);
    }

    @Override
    public void addProgram(ProgramState state) {
        this.programs.add(state);
    }
}
