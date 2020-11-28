//Stack Implementation with LinkedList

public class Stack<T> implements Iterable<T> {
    
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();
    
    public Stack() {}
    
    public Stack(T firstelem) {
        push(firstelem);
    }
    
    int size() {
        return list.size();
    }
    
    boolean isEmpty() {
        return size()==0;
    }
    
    void push(T elem) {
        list.addLast(elem);
    }
    
    T pop() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        return list.removeLast();
    }
    
    T peek() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        return list.peekLast();
    }
    
    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
