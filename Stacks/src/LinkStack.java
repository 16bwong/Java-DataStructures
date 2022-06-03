/**
 * A stack of values of type <span><</span>T<span>></span> stored as a linked list
 * @param <T> A generic parameter representing the data object stored in the stack
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-24
 */
public class LinkStack<T> extends Stack<T> {

    private SinglyLinkedList<T> stack;

    public LinkStack() {
        super();
        stack = new SinglyLinkedList<>();
    }

    @Override
    public void push(T value) {
        stack.insertStart(value);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty() || stack.getHead() == null) { throw new NullPointerException("There are no values stored in the current stack"); }

        T value = stack.getHead().getValue();
        stack.delete(value);
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty() || stack.getHead() == null) { throw new NullPointerException("There are no values stored in the current stack"); }

        T value = stack.getHead().getValue();
        return value;
    }
}
