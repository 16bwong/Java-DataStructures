import java.util.Comparator;

/**
 * A generic bi-directional linked list of nodes of type <span><</span>T<span>></span>
 * @param <T> A generic parameter representing the data object stored in each node
 *
 * @author Benjamin Wong
 * @version 1.0
 * @since 2022-05-23
 */
public class DoublyLinkedList<T> extends LinkedList<T> {

    private class Node<T> extends LinkedList<T>.Node<T> {

        public Node<T> next;
        public Node<T> prev;

        public Node(T value) {
            super(value);
        }

        /**
         * Links the current node to the address of node passed in the parameter as the preceding node in the link
         * and links the current node to the node passed in the parameter as the next node in the link
         * @param node Source node to link
         */
        public void setPrev(Node<T> node) {
            if (node == null) {
                prev = null;
            } else {
                prev = node;
                node.next = this;
            }
        }

        /**
         * Retrieves the preceding node in the list
         * @return The previous linked node in the list
         */
        public Node<T> getPrev() { return this.prev; }

        /**
         * Sets the node's linkages
         * @param prv Preceding node in linked list
         * @param nxt Next node in linked list
         */
        public void setLinks(Node<T> prv, Node<T> nxt) {
            setPrev(prv);
            setNext(nxt);
        }

        /**
         * Links the current node to the address of node passed in the parameter as the next node in the link
         * and links the current node to the node passed in the parameter as the preceding node in the link
         * @param node Source node to link
         */
        public void setNext(Node<T> node) {
            if (node == null) {
                next = null;
            } else {
                next = node;
                node.prev = this;
            }
        }

        @Override
        protected void instantiate(T value) {
            setValue(value);
            setLinks(null, null);
        }

        @Override
        public Node<T> getNext() {
            return this.next;
        }

    }

    public DoublyLinkedList() {
        head = null;
    }

    @Override
    protected void insertStart(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> node = (Node<T>) head;
            head = new Node<>(value);
            head.setNext(node);
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

    /**
     * Inserts a node with the <code>value</code> of type <code>T</code> into the linked list preceding the
     * node with the matching <code>searchValue</code>
     * @param value Value to insert into linked list as a node
     * @param searchValue Value to insert the node before
     * @exception NullPointerException If a node matching the <code>searchValue</code> passed as a parameter
     * cannot be found, a null exception will be thrown
     */
    public void insertBefore(T value, T searchValue) {
        Node<T> node = getNode(searchValue);
        if (node == null) { throw new NullPointerException("A node with the corresponding search value could not be found in list."); }

        if (node.prev == null) {
            head = new Node<>(value);
            head.setNext(node);
        } else {
            Node<T> temp = node.prev;
            node.setPrev(new Node<>(value));
            node.prev.setPrev(temp);
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
        Node<T> node = getNode(value);
        if (node == null) { return; }
        if (node.prev == null) {
            head = node.next;
            ((Node<T>) head).prev = null;
        } else {
            node.prev.setNext(node.next);
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

        if (list instanceof DoublyLinkedList) {
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
        Node<T> prev = node.prev;
        while (node != null) {
            // Find Min Value
            Node<T> min = node;
            Node<T> next = node.next;
            while (next != null) {
                if (comparator.compare(next.getValue(), min.getValue()) < 0) {
                    min = next;
                }
                next = next.next;
            }

            // Swap Nodes
            Node<T> newNode = node.next;
            if (comparator.compare(min.getValue(), node.getValue()) < 0) {
                node.setNext(min.next);

                if (!node.getValue().equals(min.prev.getValue())) {
                    node.setPrev(min.prev);
                    min.setNext(newNode);
                } else {
                    node.setPrev(min);
                    min.setNext(node);
                }

                if (prev == null) {
                    min.setPrev(null);
                    head = min;
                } else {
                    min.setPrev(prev);
                }
            }

            // Iterator
            prev = min;
            node = prev.next;
        }
    }

    @Override
    protected void sortDescending(Comparator<T> comparator) {
        Node<T> node = (Node<T>) head;
        Node<T> prev = node.prev;
        while (node != null) {
            // Find Max Value
            Node<T> max = node;
            Node<T> next = node.next;
            while (next != null) {
                if (comparator.compare(next.getValue(), max.getValue()) > 0) {
                    max = next;
                }
                next = next.next;
            }

            // Swap Nodes
            Node<T> newNode = node.next;
            if (comparator.compare(max.getValue(), node.getValue()) > 0) {
                node.setNext(max.next);

                if (!node.getValue().equals(max.prev.getValue())) {
                    node.setPrev(max.prev);
                    max.setNext(newNode);
                } else {
                    node.setPrev(max);
                    max.setNext(node);
                }

                if (prev == null) {
                    max.setPrev(null);
                    head = max;
                } else {
                    max.setPrev(prev);
                }
            }

            // Iterator
            prev = max;
            node = prev.next;
        }
    }

    public String toString() {
        if (head == null) { return null; }
        String s = "";
        Node<T> node = (Node<T>) head;
        while (node.next != null) {
            s += node + "<->";
            node = node.next;
        }
        s += node;
        return s;
    }

}
