package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {

    private T[] a;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        a=(T[])new Object[10];
        size = 0;
        capacity = 8;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize(int beforeDoubling) {
        T[] temp= (T[]) new Object[capacity];
        for (int oldArrIdx = nextFirst + 1,newArrIdx = 0; newArrIdx < size; oldArrIdx++, newArrIdx++) {
            oldArrIdx %= beforeDoubling;
            temp[newArrIdx] = a[oldArrIdx ];
        }
        a = temp;
        nextFirst = capacity - 1 ;
        nextLast = size;
    }


    @Override
    public void addLast(T item) {
        if(size == capacity){
            int beforeDoubling = capacity;
            capacity *= 2;
            resize( beforeDoubling );
        }
        size++;
        a[nextLast] = item;
        nextLast++;
        nextLast %= capacity;
    }

    @Override
    public void addFirst(T item) {
        int beforeDoubling = capacity;
        if(size == capacity){
            capacity *= 2;
            resize(beforeDoubling);
        }
        size++;
        a[nextFirst] = item;
        nextFirst = ( nextFirst - 1 + capacity) % capacity;
    }


    @Override
    public T removeFirst() {
       if(size == 0 ){
           return null;
       }
       nextFirst++;
       nextFirst %= capacity;
       T returnValue = a[nextFirst];
       a[nextFirst] = null;
       size--;
       if((100.0 * size)/capacity < 25 && capacity / 2 >= 8)
       {
           capacity /= 2;
           resize(capacity * 2);
       }
       return returnValue;
    }

    @Override
    public T removeLast() {
        if(size==0){
            return null;
        }
        nextLast--;
        nextLast += capacity; //avoid negative values
        nextLast %= capacity;
        T returnValue = a[nextLast];
        a[nextLast] = null;
        size--;
        if((100.0 * size )/capacity <25 && capacity / 2 >= 8)
        {
            capacity /= 2;
            resize(capacity * 2);
        }
        return returnValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return a[(nextFirst + index + 1) % capacity];
    }

    @Override
    public void printDeque() {
        int InsideIdx = nextFirst + 1;
        for (int numOfElement = 0; numOfElement < size; numOfElement++, InsideIdx++) {
            InsideIdx %= capacity;
            System.out.print(a[InsideIdx] + " ");
        }
        System.out.println();
    }
    private class ArrayDequeIterator<T> implements Iterator<T> {
        private int pos = 0;

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
        return new ArrayDequeIterator<>();
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Deque){
            Deque<T> temp = (Deque) obj;
            if (temp == this) return true;
            if(size() != temp.size())
                return false;
            for (int i =0; i < size() ; i++){
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
}
