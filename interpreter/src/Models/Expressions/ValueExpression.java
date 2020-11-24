package Models.Expressions;

import Models.ADTs.MyDictionary.MyDictionary;
import Models.Heap.IHeap;
import Models.Values.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> variablesTable, IHeap<IValue> heap) {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("%s", this.value);
    }
}
