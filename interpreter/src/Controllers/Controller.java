package Controllers;

import Exceptions.EmptyCollectionException;
import Models.ADTs.MyStack.MyStack;
import Models.ProgramState;
import Models.Statements.IStatement;
import Repositories.IProgramRepository;
import Repositories.ProgramRepository;
import Services.Logger.ILogger;

public class Controller {
    private final IProgramRepository repository;
    private final ILogger logger;

    public Controller(IProgramRepository repository, ILogger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    public void executeCurrentProgram() throws RuntimeException {
        // TODO: make this currentProgram function work properly.
        ProgramState currentState = this.repository.getCurrentProgram();
        this.runProgram(currentState);
    }

    public void executeProgram(int index) {
        ProgramState currentState = ((ProgramRepository) this.repository).getProgram(index);
        this.runProgram(currentState);
    }

    private void runProgram(ProgramState state) throws RuntimeException {
        while (!state.getExecutionStack().isEmpty()) {
            logger.append(state.toString());
            state = this.executeOneInstruction(state);
        }

        logger.log();
    }

    private ProgramState executeOneInstruction(ProgramState state) throws RuntimeException {
        MyStack<IStatement> executionStack = state.getExecutionStack();

        if (executionStack.isEmpty()) {
            throw new EmptyCollectionException("Execution stack is empty");
        }

        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(state);
    }
}
