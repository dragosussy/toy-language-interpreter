package Models.Statements.File;

import Exceptions.FileException;
import Models.Expressions.IExpression;
import Models.ProgramState;
import Models.Statements.IStatement;
import Models.Types.StringType;
import Models.Values.IValue;
import Models.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    private final IExpression expression;

    public OpenReadFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        IValue expressionValue = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!expressionValue.getType().equals(new StringType())) {
            throw new FileException("Invalid file path.");
        }

        StringValue path = (StringValue) expressionValue;
        if (state.getFileTable().isDefined(path)) {
            throw new FileException("File already opened.");
        }

        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(path.getValue()));
        } catch (Exception e) {
            throw new FileException("File not found.");
        }

        state.getFileTable().put(path, file);

        return state;
    }

    @Override
    public String toString() {
        return "OpenReadFile(" + this.expression + ")";
    }
}
