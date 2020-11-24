package Models.Heap;

import Exceptions.HeapException;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Heap<T> implements IHeap<T> {
    private final HashMap<Integer, T> allocations;
    private final AtomicInteger firstAvailableLocation;

    public Heap() {
        this.firstAvailableLocation = new AtomicInteger(1);
        this.allocations = new HashMap<>();
    }

    @Override
    public boolean isAvailableSpot(int address) {
        return !this.allocations.containsKey(address);
    }

    @Override
    public int allocate(T value) {
        int newLocation = this.firstAvailableLocation.incrementAndGet();
        this.allocations.put(newLocation, value);
        return newLocation;
    }

    @Override
    public T getValueAtAddress(int address) {
        return this.allocations.get(address);
    }

    @Override
    public void update(int address, T newValue) {
        if (!this.allocations.containsKey(address)) {
            throw new HeapException("Can't update an address that hasn't been allocated yet.");
        }
        this.allocations.put(address, newValue);
    }

    @Override
    public String toString() {
        StringBuilder printedHeap = new StringBuilder();
        printedHeap.append("{");
        this.allocations.forEach((key, value) -> {
            printedHeap
                    .append(key)
                    .append("->")
                    .append(value)
                    .append(" ");
        });
        printedHeap.append("}");
        return printedHeap.toString();
    }
}
