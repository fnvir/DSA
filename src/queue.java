public class queue<T> implements Iterable<T>{
    private java.util.LinkedList<T> ll=new java.util.LinkedList<T>();
    
    public queue() {}
    
    public queue(T first) {
        add(first);
    }
    
    public int size() {
        return ll.size();
    }
    
    public boolean isEmpty() {
        return size()==0;
    }
    
    public void add(T elem) {
        ll.add(elem);
    }
    
    public T remove() {
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        return ll.removeFirst();
    }
    
    public T peek() {
        return ll.peekFirst();
    }
    
    @Override
    public java.util.Iterator<T> iterator() {
        return ll.iterator();
    }

}