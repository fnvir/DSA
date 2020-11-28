//Doubly Linked List practice
public class linkedlistdoubly {
    node head;

    private static class node {
        private int data;
        private node next;
        private node prev;

        node(int d) {
            data = d;
        }
    }

    public void addfirst(int data) {
        node n = new node(data);
        n.next = head;
        n.prev = null;
        if (head != null)
            head.prev = n;
        head = n;
    }

    public void insertafter(node pre, int data) {
        if(pre==null) {System.out.println("Error. Null node"); return;}
        node n=new node(data);
        n.next=pre.next;
        pre.next=n;
        n.prev=pre;
        if(n.next!=null) n.next.prev=n;
    }
    
    public void addlast(int data) {
        node n=new node(data),curr=head;
//        n.next=null;
        if(head==null) {
//            n.prev=null;
            head=n;;
        }else{
        while(curr.next!=null)
            curr=curr.next;
        curr.next=n;
        n.prev=curr;
        }
    }
    
    public void insertbefore(node nxt,int d){
        node n=new node(d);
        if(nxt==null)
            System.out.println("Null node");
        else {
            n.prev=nxt.prev;
            nxt.prev=n;
            n.next=nxt;
            if(n.prev!=null)
                n.prev.next=n;
            else head=n;
        }
    }
    
    public void printlist() {
        node curr=head;
        while(curr!=null) {
            System.out.println(curr.data);
            curr=curr.next;
        }
    }
    
    public void printlistReversed() {
        node last=head;
        while(last.next!=null) last=last.next;
        while(last!=null) {
            System.out.println(last.data);
            last=last.prev;
        }
    }
    
    void delete(node del){
        if(head==null || del==null) {
            System.out.println("Null node");return;
        }
        if(head==del)
            head=del.next;
        if(del.next!=null) 
            del.next.prev=del.prev;
        if (del.prev != null)
            del.prev.next = del.next;
    }
    void reverse() {
        node tmp=null,curr=head;
        while(curr!=null) {
            tmp=curr.prev;
            curr.prev=curr.next;
            curr.next=tmp;
            curr=curr.prev;
        }
        if(tmp!=null) head=tmp.prev;
    }
    
    public static void main(String[] args) {
        linkedlistdoubly dll=new linkedlistdoubly();
        for(int i=0;i<5;i++) dll.addlast(i);
        dll.insertbefore(dll.head.next.next.next,10);
        dll.printlistReversed();
//        dll.delete(dll.head.next.next);
    }
}
