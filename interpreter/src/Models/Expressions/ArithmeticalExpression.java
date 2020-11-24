package Models.Expressions;

import Exceptions.DivisionByZeroException;
import Exceptions.InvalidTypeException;
import Models.ADTs.MyDictionary.MyDictionary;
import Models.Expressions.Operations.ArithmeticalOperation;
import Models.Heap.IHeap;
import Models.Types.IntType;
import Models.Values.IValue;
import Models.Values.IntValue;

public class ArithmeticalExpression implements IExpression {
    private final IExpression leftHandSide;
    private final IExpression rightHandSide;
    private final ArithmeticalOperation sign;

    public ArithmeticalExpression(IExpression lhs, ArithmeticalOperation sign, IExpression rhs) {
        this.leftHandSide = lhs;
        this.rightHandSide = rhs;
        this.sign = sign;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> variablesTable, IHeap<IValue> heap) throws RuntimeException {
        IValue lhs, rhs;

        lhs = leftHandSide.evaluate(variablesTable, heap);
        if (!lhs.getType().equals(new IntType())) {
            throw new InvalidTypeException("Left hand side is not an integer.");
        }

        rhs = rightHandSide.evaluate(variablesTable, heap);
        if (!rhs.getType().equals(new IntType())) {
            throw new InvalidTypeException("Right hand side is not an integer.");
        }

        int i1 = ((IntValue) lhs).getValue();
        int i2 = ((IntValue) rhs).getValue();

        return this.getValueAfterOperation(i1, this.sign, i2);
    }

    private IValue getValueAfterOperation(int operand1, ArithmeticalOperation sign, int operand2) throws RuntimeException {
        if (operand2 == 0 && sign == ArithmeticalOperation.Division) {
            throw new DivisionByZeroException("Division by zero.");
        }

        return switch (this.sign) {
            case Addition -> new IntValue(operand1 + operand2);
            case Subtraction -> new IntValue(operand1 - operand2);
            case Multiplication -> new IntValue(operand1 * operand2);
            case Division -> new IntValue(operand1 / operand2);
        };
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", this.leftHandSide, this.sign.getMathematicalSign(), this.rightHandSide);
    }
}
