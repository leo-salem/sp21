package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {
    private class Node<T> {
        T item;
         Node next;
         Node prev;
        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
        public Node() {}
    }

    private int size;

    private Node<T> sentinel;


    public LinkedListDeque() {
        sentinel = new Node<>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addLast(T item) {
        Node<T> temp = new Node<>(item , sentinel , sentinel.prev);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    @Override
    public void addFirst(T item) {
        Node<T> temp=new Node<>( item , sentinel.next , sentinel );
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return returnValue;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return returnValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size) return null;
        Node<T> current = sentinel.next;
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
        return (T) Recurcive(index, sentinel.next);
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
        Node<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

}
