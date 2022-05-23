import java.util.Collection;
import java.util.Comparator;

/**
 * A abstract linked list of nodes of type <span><</span>T<span>></span>
 * @param <T> A generic parameter representing the data object stored in each node
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-23
 */
public abstract class LinkedList<T> {

    protected Node<T> head;

    /**
     * A protected inner-class used to create linked nodes of the generic type <span><</span>T<span>></span>
     * @param <T> A generic parameter representing the data object stored in each node
     */
    protected abstract class Node<T> {

        protected T data;
        protected Node<T> next;

        public Node(T value) {
            instantiate(value);
        }

        /**
         * Sets the current value of the node stored in the node's data variable to the value passed
         * in the parameter
         * @param value Value to store in the node
         */
        public void setValue(T value) { data = value; }

        /** @return The node's stored data value*/
        public T getValue() { return this.data; }

        public String toString() {
            return String.format("[%s]", data.toString());
        }

        /**
         * Links the current node to the address of the node passed in the parameter
         * @param node Target node to link
         */
        public void setNext(Node<T> node) { next = node; }

        /**
         * Retrieves the next node in the list
         * @return The next linked node in the list
         */
        public Node<T> getNext() { return this.next; }

        // Abstract Methods
        /**
         * Initializes the node
         * @param value Value to store in the node
         */
        protected abstract void instantiate(T value);

    }

    /** Defines the insertion strategy options for normal insertion: {@link #HEAD}, {@link #END} */
    public enum Insert {HEAD, END}

    /** Defines the sorting direction: {@link #ASC}, {@link #DESC} */
    public enum SortOrder { ASC, DESC }

    public LinkedList() { head = null; }

    /**
     * Inserts a node with the <code>value</code> of type <code>T</code> at the end of the linked list
     * @param value Value to insert into linked list as a node
     */
    public void insert(T value) {
        insert(value, Insert.END);
    }

    /**
     * Inserts a node with the <code>value</code> of type <code>T</code> into the linked list
     * @param value Value to insert into linked list as a node
     * @param pos Insertions strategy to use
     */
    public void insert(T value, Insert pos) {
        if (pos == Insert.HEAD) { insertStart(value); }
        if (pos == Insert.END) { insertEnd(value); }
    }

    /** Inserts a collection of values using the default <code>Insert.END</code> insertion strategy
     * @param values Collection of values to insert
     * */
    public void insert(Collection<T> values) {
        for (T v : values) {
            insert(v);
        }
    }

    /** Inserts an array of values using the default <code>Insert.END</code> insertion strategy
     * @param values Array of values to insert
     * */
    public void insert(T[] values) {
        for (T v : values) {
            insert(v);
        }
    }

    /** Inserts a collection of values
     * @param values Collection of values to insert
     * @param pos Insertion strategy to use
     * */
    public void insert(Collection<T> values, Insert pos) {
        for (T v : values) {
            insert(v, pos);
        }
    }

    /** Inserts an array of values
     * @param values Array of values to insert
     * @param pos Insertion strategy to use
     * */
    public void insert(T[] values, Insert pos) {
        for (T v : values) {
            insert(v, pos);
        }
    }

    /**
     * Deletes the first node in a linked list matching each <code>value</code> contained in the collection
     * @param values Node values to remove from linked list
     */
    public void delete(Collection<T> values) {
        for (T v : values) { delete(v); }
    }

    /**
     * Deletes the first node in a linked list matching each <code>value</code> contained in the array
     * @param values Node values to remove from linked list
     */
    public void delete(T[] values) {
        for (T v : values) { delete(v); }
    }

    /**
     * Sorts a linked list using the methods defined in the arguments
     * @param comparator Comparator object used to define how the generic object is evaluated relative to another
     * @param order Defines the order in which to sort the nodes
     */
    public void sort(Comparator<T> comparator, SortOrder order) {
        if (order == SortOrder.ASC) { sortAscending(comparator); }
        if (order == SortOrder.DESC) { sortDescending(comparator); }
    }

    // Abstract Methods

    /** Defines the insertion logic for the <code>Insert.HEAD</code> insertion strategy which
     * inserts the value passed at the start of the linked list
     * @param value Value to insert into linked list as a node
     * */
    protected abstract void insertStart(T value);

    /** Defines the insertion logic for the <code>Insert.END</code> insertion strategy which
     * inserts the value passed at the end of the linked list
     * @param value Value to insert into linked list as a node
     * */
    protected abstract void insertEnd(T value);

    /**
     * Inserts a node with the <code>value</code> of type <code>T</code> into the linked list following the
     * node with the matching <code>searchValue</code>
     * @param value Value to insert into linked list as a node
     * @param searchValue Value to insert the node after
     * @exception NullPointerException If a node matching the <code>searchValue</code> passed as a parameter
     * cannot be found, a null exception will be thrown
     */
    public abstract void insertAfter(T value, T searchValue);

    /**
     * Deletes the first node in a linked list matching the <code>value</code> passed as a parameter
     * @param value Node value to remove from linked list
     */
    public abstract void delete(T value);

    /**
     * @param value Node value to search for within linked list
     * @return The first node matching the <code>value</code> passed in the parameter or <code>NULL</code> if there
     * were no nodes containing the matching value
     */
    public abstract Node<T> getNode(T value);

    /**
     * Appends the <code>list</code> passed as a parameter to the current object
     * @param list The list to append to the end of the calling object
     */
    public abstract void concatenate(LinkedList<T> list);

    /** Reverses the order of nodes in a linked list */
    public abstract void reverse();

    /**
     * Sorts the list in Ascending order
     * @param comparator Comparator object used to define how the generic object is evaluated relative to another
     */
    protected abstract void sortAscending(Comparator<T> comparator);

    /**
     * Sorts the list in Descending order
     * @param comparator Comparator object used to define how the generic object is evaluated relative to another
     */
    protected abstract void sortDescending(Comparator<T> comparator);

}
