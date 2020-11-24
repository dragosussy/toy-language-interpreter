package Models.Statements.Heap;

import Exceptions.InvalidTypeException;
import Models.Expressions.IExpression;
import Models.ProgramState;
import Models.Statements.IStatement;
import Models.Types.IType;
import Models.Types.ReferenceType;
import Models.Values.IValue;
import Models.Values.ReferenceValue;

public class NewAllocationStatement implements IStatement {
    private final String variableName;
    private final IExpression expression;

    public NewAllocationStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        var variableType = state.getSymbolTable().lookup(this.variableName).getType();
        if (!(variableType instanceof ReferenceType)) {
            throw new InvalidTypeException(""); //TODO: complete this
        }
        var variable = (ReferenceValue) state.getSymbolTable().lookup(this.variableName);

        IValue evaluatedExpression = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        IType evaluatedExpressionType = this.expression.evaluate(state.getSymbolTable(), state.getHeap()).getType();
        if (!evaluatedExpressionType.equals(variable.getReferencedType())) {
            throw new InvalidTypeException(""); //TODO: complete this
        }

        int newAddress = state.getHeap().allocate(evaluatedExpression);
        variable.setAddress(newAddress);
        variable.setReferencedType(evaluatedExpressionType);
        state.getSymbolTable().update(this.variableName, variable);

        return state;
    }

    @Override
    public String toString() {
        return "New(" + this.variableName + ", " + this.expression.toString() + ")";
    }
}
