public class QueuebyArray {

    private static int a[] = new int[10000000];
    static int i=0;
    
    public QueuebyArray() {}
    
    public QueuebyArray(int first) {
        enque(first);
    }
    
    public int size() {
        return i;
    }
    
    public boolean isEmpty() {
        return size()==0;
    }
    
    public void enque(int elem) {
        a[i++]=elem;
    }
    
    public int deque() {
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        int x=a[0];
        for(int j=0;j<i-1;j++) a[j]=a[j+1];
        i--;
        return x;
    }
    
    public int peek() {
        return a[0];
    }
    
    public void printq() {
        for(int j=0;j<i;j++) System.out.print(a[j]+" ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        QueuebyArray q=new QueuebyArray();
        for(int i=1;i<11;i++) q.enque(i);
        q.printq();
        q.deque();
        q.deque();
        q.printq();
    }
}