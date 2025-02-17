package deque;

public interface Deque<T> {

   void addLast(T item);
   void addFirst(T item);
   T removeFirst();
   T removeLast();
   int size();
   default boolean isEmpty()
   {
        return (size() == 0);
   };
   T get(int index);
   void printDeque();


}
