public class stackk {
    class node {
        int data;
        node next;
        node(int d,node n){data=d;next=n;}
    }
    node head;
    int size=0;
    void add(int data) {
        head=new node(data,head);
        size++;
    }
    void pop() {
        head=head.next;
        size--;
    }
    int peek() {return head.data;}
    boolean empty() {return size==0;}
    
    public static void main(String[] args) {
        stackk s=new stackk();
        for(int i=0;i<10;i++) s.add(i);
        while(s.size>0) {System.out.println(s.peek());s.pop();}
    }
}