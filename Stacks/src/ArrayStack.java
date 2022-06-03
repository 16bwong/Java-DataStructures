import java.lang.reflect.Array;

/**
 * A stack of values of type <span><</span>T<span>></span> stored as an array requiring a set capacity
 * @param <T> A generic parameter representing the data object stored in the stack
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-24
 */
public class ArrayStack<T> extends Stack<T> {

    private Object[] stack;

    public ArrayStack(int size) {
        super();
        stack = new Object[size];
    }

    /**
     * Number of values that can be stored in the stack
     * @return The total available capacity of the stack
     */
    public int getCapacity() { return stack.length; }

    public String toString() {
        return super.toString() + ", capacity=" + getCapacity();
    }

    @Override
    public void push(T value) {
        if (getSize() >= getCapacity()) { throw new IndexOutOfBoundsException("Stack Overflow: Maximum stack capacity has been met"); }
        stack[size] = value;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) { throw new IndexOutOfBoundsException("There are no values stored in the current stack"); }

        T value = (T) stack[size-1];
        stack[size-1] = null;
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) { throw new IndexOutOfBoundsException("There are no values stored in the current stack"); }

        return (T) stack[size-1];
    }

}
