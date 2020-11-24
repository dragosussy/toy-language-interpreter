package Models.Expressions;

import Exceptions.InvalidTypeException;
import Models.ADTs.MyDictionary.MyDictionary;
import Models.Heap.IHeap;
import Models.Values.IValue;
import Models.Values.ReferenceValue;

public class ReadHeapExpression implements IExpression {
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> variablesTable, IHeap<IValue> heap) throws RuntimeException {
        IValue evaluatedExpression = this.expression.evaluate(variablesTable, heap);
        if (!(evaluatedExpression instanceof ReferenceValue)) {
            throw new InvalidTypeException("Can not read the heap for this expression.");
        }

        ReferenceValue variableReference = (ReferenceValue) evaluatedExpression;
        return heap.getValueAtAddress(variableReference.getAddress());
    }

    @Override
    public String toString() {
        return "ReadHeap(" + this.expression.toString() + ")";
    }
}
