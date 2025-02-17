package deque;

public interface Deque<T> {

   void addLast(T item);
   void addFirst(T item);
   T removeFirst();
   T removeLast();
   int Size();
   default boolean isEmpty()
   {
        return (Size() == 0);
   };
   T get(int index);
   void printDeque();


}
