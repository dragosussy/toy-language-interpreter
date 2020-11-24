package Models.Heap;

public interface IHeap<T> {
    boolean isAvailableSpot(int address);

    int allocate(T value);

    T getValueAtAddress(int address);

    void update(int address, T newValue);
}
