package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {
    private class Node<T> {
        T item;
         Node next;
         Node prev;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;

    private Node<T> head;
    private Node<T> tail;


    public LinkedListDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item, tail, null);
        if (tail == null) {
            head = newNode;  // If list was empty, head also points to newNode
        } else {
            tail.next = newNode;  // Link old tail(new "before last" item) to newNode
        }
        tail = newNode;  // Update tail
        size++;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode=new Node<>(item, null ,head);
        if (head == null) {
            tail = newNode;  // If the list was empty, tail also points to newNode (new last item)
        } else {
            head.prev = newNode;  // Link old head (new "second" item) to newNode (new first element)
        }
        head = newNode;  // Update head
        size++;
    }

    @Override
    public T removeFirst() {
        if (head == null) return null;  // Empty list

        T first = head.item;  // Get value
        head = head.next;  // Move head pointer

        if (head == null) {
            tail = null;  // If list is now empty, update tail
        } else {
            head.prev = null;  // Remove old head reference
        }
        size--;
        return first;
    }

    @Override
    public T removeLast() {
        if (tail == null) return null;  // Empty list

        T item = tail.item;  // Get value
        tail = tail.prev;  // Move tail pointer

        if (tail == null) {
            head = null;  // If list is now empty, update head
        } else {
            tail.next = null;  // Remove old tail reference
        }
        size--;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size) return null;
        Node<T> current = head;
        while (index > 0){
            index--;
            current = current.next;
        }
        return current.item;
    }

    /*
     helper method
    */

    public T getRecurcive(int index){
        if(index < 0 || index >= size) return null;
        return Recurcive(index, head);
    }
    private T Recurcive(int index, Node<T> pointer){
       if(index == 0)
        return pointer.item;
       return (T)Recurcive(index - 1, pointer.next);
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {
        int pos;

        LinkedListDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size();
        }

        public T next() {
            T x = (T) get(pos);
            pos++;
            return x;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator<>();
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Deque){
         Deque<T> temp= (Deque) obj;
         if (temp == this) return true;
         if(size()!=temp.size())
             return false;
         for (int i =0;i < size() ; i++){
             if(!(get(i).equals(temp.get(i)))){
                 return false;
             }
         }
         return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void printDeque() {
        if (head == null) return;
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

}
