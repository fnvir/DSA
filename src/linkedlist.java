//Singly Linked List
class linkedlist {

    public static void main(String[] args) {
        linkedlist ll = new linkedlist();
        ll.head = new node(1);
        node second = new node(2);
        node third = new node(3);
        ll.head.next = second;
        second.next = third;
//        ll.addfirst(50);
//        ll.insertafter(ll.head.next, 10);
//        ll.addlast(99);
//        ll.printlist();
//        ll.delete(3);
//        ll.reverse();
        ll.printlist();
    }
    
    node head;

    static class node {
        int data;
        node next;

        public node(int data) {
            this.data = data;
            next = null;
        }

    }

    void addfirst(int newdata) {
        node newnode = new node(newdata);
        newnode.next = head;
        head = newnode;
    }

    void insertafter(node prev, int data) {
        if(prev==null) System.out.println("Error. node null");
        else {
            node newnode=new node(data);
            newnode.next=prev.next;
            prev.next=newnode;
        }
    }
    
    void addlast(int data) {
        node newnode=new node(data);
        if(head==null) head=newnode;
        else {
            node current=head;
            while(current.next!=null) 
                current=current.next;
            current.next=newnode;
        }
    }
    
    void delete(int key) {
        node current=head,prev=null;
        if(head != null && head.data==key)
            head=current.next;
        else {
            while(current!=null && current.data!=key) {
                prev=current;
                current=current.next;
            }
            if (current == null) System.out.println("No such values found");
            else prev.next=current.next;
        }
    }
    
    void reverse() {
        node prev=null,curr=head,next=null;
        while(curr!=null) {
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head = prev;
    }

    void printlist() {
        node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

}