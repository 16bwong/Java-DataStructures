/**
 * A abstract stack of values of type <span><</span>T<span>></span>
 * @param <T> A generic parameter representing the data object stored in the stack
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-24
 */
public abstract class Stack<T> {

    protected int size;

    public Stack() {
        size = 0;
    }

    /**
     * Checks if the stack contains values
     * @return <code>True</code> if the stack is empty or <code>False</code> if the stack contains values
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Retrieves the size of the stack
     * @return Number of values stored in stack
     */
    public int getSize() { return size; }

    public String toString() {
        return String.format("%s: size=%s", this.getClass(), size);
    }

    // Abstract Methods

    /**
     * Push a value to the stack
     * @param value Value to add to the stack
     */
    public abstract void push(T value);

    /**
     * Retrieves the value stored at the top of the stack and removes it from the stack
     * @return Value at the top of the stack
     */
    public abstract T pop();

    /**
     * Retrieves the value stored at the top of the stack
     * @return Value at the top of the stack
     */
    public abstract T peek();

}
