public class QueuebyArray {

    private int a[];
    int pos=0,maxSize=1000000;
    
    public QueuebyArray() {
        a=new int[maxSize];
    }
    
    public QueuebyArray(int size) {
        maxSize=size;
    }
    
    public int length() {
        return pos;
    }
    
    public boolean isEmpty() {
        return length()==0;
    }
    
    public boolean isFull() {
        return length()==maxSize;
    }
    
    public void enque(int elem) {
        if(length()==maxSize) throw new RuntimeException("Size Full");
        a[pos++]=elem;
    }
    
    public int deque() {
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        int x=a[0];
        for(int j=0;j<pos-1;j++) a[j]=a[j+1];
        pos--;
        return x;
    }
    
    public int peek() {
        return a[0];
    }
    
    public void printQueue() {
        for(int j=0;j<pos;j++) System.out.print(a[j]+" ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        QueuebyArray q=new QueuebyArray();
        for(int i=1;i<11;i++) q.enque(i);
        q.printQueue();
        q.deque();
        q.deque();
        q.printQueue();
    }
}