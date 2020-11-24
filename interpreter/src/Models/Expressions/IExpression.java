package Models.Expressions;

import Models.ADTs.MyDictionary.MyDictionary;
import Models.Heap.IHeap;
import Models.Values.IValue;

public interface IExpression {
    IValue evaluate(MyDictionary<String, IValue> variablesTable, IHeap<IValue> heap) throws RuntimeException;
}
