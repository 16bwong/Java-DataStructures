import java.util.Collection;
import java.util.Comparator;

/**
 * A generic uni-directional linked list of nodes of type <span><</span>T<span>></span>
 * @param <T> A generic parameter representing the data object stored in each node
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-23
 */
public class SinglyLinkedList<T> extends LinkedList<T> {

    private class Node<T> extends LinkedList<T>.Node<T> {

        public Node<T> next;

        public Node(T value) {
            super(value);
        }

        /**
         * Links the current node to the address of the node passed in the parameter
         * @param node Target node to link
         */
        public void setNext(Node<T> node) {
            next = null;
        }

        @Override
        public Node<T> getNext() { return this.next; }

        @Override
        protected void instantiate(T value) {
            setValue(value);
            next = null;
        }

    }

    /** <code>SinglyLinkedList</code> Constructor */
    public SinglyLinkedList() {
        head = null;
    }

    @Override
    protected void insertStart(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> node = new Node<>(value);
            node.setNext(head);
            head = node;
        }
    }

    @Override
    protected void insertEnd(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> node = (Node<T>) head;
            while (node.next != null) {
                node = node.next;
            }
            node.setNext(new Node<>(value));
        }
    }

    @Override
    public void insertAfter(T value, T searchValue) {
        Node<T> node = getNode(searchValue);
        if (node == null) { throw new NullPointerException("A node with the corresponding search value could not be found in list."); }

        if (node.next == null) {
            node.setNext(new Node<>(value));
        } else {
            Node<T> temp = node.next;
            node.setNext(new Node<>(value));
            node.next.setNext(temp);
        }
    }

    @Override
    public void delete(T value) {
        if (value.equals(head.getValue())) {
            head = head.next;
            return;
        }
        Node<T> node = (Node<T>) head;
        while (node.next != null) {
            if (value.equals(node.next.getValue())) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

    @Override
    public Node<T> getNode(T value) {
        Node<T> node = (Node<T>) head;
        while (node != null) {
            if(value.equals(node.getValue())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public void concatenate(LinkedList<T> list) {
        if (head == null) { return; }

        Node<T> node = (Node<T>) head;
        while (node.next != null) { node = node.next; }

        if (list instanceof SinglyLinkedList) {
            node.setNext((Node<T>) list.head);
        } else {
            LinkedList<T>.Node<T> n = list.head;
            while (n != null) {
                node.setNext(new Node<>(n.getValue()));
                node = node.next;
                n = n.getNext();
            }
        }
    }

    @Override
    public void reverse() {
        Node<T> node = (Node<T>) head;
        Node<T> prev = null;
        while (node != null) {
            Node<T> newNode = node.next;
            node.setNext(prev);
            prev = node;
            node = newNode;
        }
        head = prev;
    }

    @Override
    protected void sortAscending(Comparator<T> comparator) {
        Node<T> node = (Node<T>) head;
        Node<T> prev = null;

        while (node != null) {
            // Find Min Value
            Node<T> min = node;
            Node<T> next = node.next;
            Node<T> minPrev = prev;
            Node<T> tPrev = node;
            while (next != null) {
                if (comparator.compare(next.getValue(), min.getValue()) < 0) {
                    min = next;
                    minPrev = tPrev;
                }
                tPrev = next;
                next = next.next;
            }

            // Swap
            Node<T> newNode = node.next;
            if (comparator.compare(min.getValue(), node.getValue()) < 0) {
                node.setNext(min.next);
                if (node.equals(minPrev)) {  // Swap for nodes next to each other
                    min.setNext(node);
                } else {  // Swap for nodes spread apart
                    minPrev.setNext(node);
                    min.setNext(newNode);
                }

                // Check if the first value in list is replaced
                if (prev != null) { prev.setNext(min); }
                else { head = min; }
            }

            // Iterator
            prev = min;
            node = prev.next;
        }
    }

    @Override
    protected void sortDescending(Comparator<T> comparator) {
        Node<T> node = (Node<T>) head;
        Node<T> prev = null;

        while (node != null) {
            // Find Max Value
            Node<T> max = node;
            Node<T> next = node.next;
            Node<T> maxPrev = prev;
            Node<T> tPrev = node;
            while (next != null) {
                if (comparator.compare(next.getValue(), max.getValue()) > 0) {
                    max = next;
                    maxPrev = tPrev;
                }
                tPrev = next;
                next = next.next;
            }

            // Swap
            Node<T> newNode = node.next;
            if (comparator.compare(max.getValue(), node.getValue()) > 0) {
                node.setNext(max.next);
                if (node.equals(maxPrev)) {  // Swap for nodes next to each other
                    max.setNext(node);
                } else {  // Swap for nodes spread apart
                    maxPrev.setNext(node);
                    max.setNext(newNode);
                }

                // Check if the first value in list is replaced
                if (prev != null) { prev.setNext(max); }
                else { head = max; }
            }

            // Iterator
            prev = max;
            node = prev.next;
        }
    }

    @Override
    public String toString() {
        if (head == null) { return null; }

        String s = "";
        Node<T> node = (Node<T>) head;
        while (node.next != null) {
            s += node + "->";
            node = node.next;
        }
        s += node;
        return s;
    }

}
