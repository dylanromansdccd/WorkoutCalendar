package edu.sdccd.cisc191.workoutcalendar;

public class DoublyLinkedList<T> {
    class Node
    {
        T data;
        Node next;
        Node prev;

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    Node head;
    Node tail;

    public int getLength() {
        int count = 0;

        Node node = head;
        while(node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    /**
     * Adds data to the end of the linked list, if head is null data becomes head,
     * if tail is null data becomes tail, otherwise data is added to the tail
     * @param data
     */
    public void add(T data) {
        if(head == null) {
            head = new Node(data, null, null);
        }
        else if(tail == null) {
            tail = new Node(data, head, null);
            head.next = tail;
        }
        else {
            Node node = new Node(data, tail, null);
            tail.next = node;
            tail = node;
        }
    }

    /**
     * Adds node to linked list at requested index, calls a method not available to the user
     * @param data
     * @param index
     */
    public void add(T data, int index) {
        add(data, index, head);
    }

    /**
     * Method called by "add" method with data and index parameters,
     * recursively iterates through the linked list and inserts a node at specified spot
     * @param data
     * @param index
     * @param node
     */
    private void add(T data, int index, Node node) {
        try {
            if(index == 0) {
                Node new_node = new Node(data, node.prev, node);
                node.prev = new_node;
                node.prev.prev.next = new_node;
            } else {
                add(data, --index, node.next);
            }
        } catch (NullPointerException e) {
            System.err.println("Index is out of bounds");
        }
    }

    /**
     * Iterates through given array and adds it to the end of the linked list
     * @param array
     */
    public void add(T[] array) {
        for(int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    /**
     * Deletes node in linked list at requested index, calls a method not available to the user
     * @param index
     */
    public void remove(int index) {
        remove(index, head);
    }

    /**
     * Called by user callable "remove" method with index parameter,
     * recursively iterates through list and deletes node at given requested index
     * @param index
     * @param node
     */
    private void remove(int index, Node node) {
        try {
            if (index == 0) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                remove(--index, node.next);
            }
        } catch (NullPointerException e) {
            System.err.println("Index is out of bounds");
        }
    }

    /**
     * searches for given data in linked list, removes node data exists in
     * @param data
     */
    public void remove(T data) {
        remove(data, head);
    }

    /**
     * Called by user callable "remove" method with data parameter,
     * recursively iterates through list and deletes node containing given data
     * @param data
     * @param node
     */
    private void remove(T data, Node node) {
        try {
            if (data == node.data) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                remove(data, node.next);
            }
        } catch (NullPointerException e) {
            System.err.println("Node not found");
        }
    }

    /**
     * Gets data form node at given index, calls a method not available to the user
     * @param index
     * @return
     */
    public T get(int index) {
        Node node = head;

        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.data;
    }
}
