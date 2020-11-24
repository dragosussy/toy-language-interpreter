package Models.Expressions;

import Exceptions.InvalidTypeException;
import Models.ADTs.MyDictionary.MyDictionary;
import Models.Expressions.Operations.LogicalOperation;
import Models.Heap.IHeap;
import Models.Types.BoolType;
import Models.Values.BoolValue;
import Models.Values.IValue;

public class LogicalExpression implements IExpression {
    private final IExpression leftHandSide;
    private final IExpression rightHandSide;
    private final LogicalOperation sign;

    public LogicalExpression(IExpression lhs, IExpression rhs, LogicalOperation sign) {
        this.leftHandSide = lhs;
        this.rightHandSide = rhs;
        this.sign = sign;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> variablesTable, IHeap<IValue> heap) throws RuntimeException {
        IValue lhs, rhs;

        lhs = this.leftHandSide.evaluate(variablesTable, heap);
        if (!lhs.getType().equals(new BoolType())) {
            throw new InvalidTypeException("Left hand side is not a boolean value.");
        }

        rhs = this.rightHandSide.evaluate(variablesTable, heap);
        if (!rhs.getType().equals(new BoolType())) {
            throw new InvalidTypeException("Right hand side is not a boolean value");
        }

        boolean b1 = ((BoolValue) lhs).getValue();
        boolean b2 = ((BoolValue) rhs).getValue();

        return this.getValueAfterOperation(b1, this.sign, b2);
    }

    private IValue getValueAfterOperation(boolean lhs, LogicalOperation sign, boolean rhs) {
        return switch (sign) {
            case And -> new BoolValue(lhs && rhs);
            case Or -> new BoolValue(rhs || lhs);
        };
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.leftHandSide, this.sign.getMathematicalSign(), this.leftHandSide);
    }
}
