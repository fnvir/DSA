public class doublyLinkedList<T> implements Iterable<T> {

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	private class Node<T> {
		T data;
		Node<T> prev, next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public String toString() {
			return data.toString();
		}
	}

	public void clear() {
		Node<T> trav=head;
		while (trav!=null) {
			Node<T> next=trav.next;
			trav.prev=trav.next=null;
			trav.data=null;
			trav=next;
		}
		head=tail=trav=null;
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public void add(T elem) {
		addLast(elem);
	}

	public void addFirst(T elem) {
		if(isEmpty()) head=tail=new Node<T>(elem,null,null);
		else {
			head.prev=new Node<T>(elem,null,head);
			head=head.prev;
		}
		size++;
	}

	public void addLast(T elem) {
		if (isEmpty()) head=tail=new Node<T>(elem,null,null);
		else {
			tail.next=new Node<T>(elem,tail,null);
			tail=tail.next;
		}
		size++;
	}
	
	public void addAt(int index, T data) {
	    if(index<0) throw new RuntimeException("Illegal Index");

	    if(index==0) {
	      addFirst(data);
	      return;
	    }

	    if(index==size) {
	      addLast(data);
	      return;
	    }
	    
	    Node<T> pre=head;
	    
	    for(int i=0;i<index-1;i++) 
	      pre=pre.next;
	    
	    Node<T> newNode = new Node<>(data, pre, pre.next);
	    pre.next.prev = newNode;
	    pre.next = newNode;

	    size++;
	  }
	
	public T peekFirst() {
		if(isEmpty()) throw new RuntimeException("Empty List");
		return head.data;
	}
	
	public T peekLast() {
		if(isEmpty()) throw new RuntimeException("Empty List");
		return tail.data;
	}
	
	public T removeFirst() {
		if(isEmpty()) throw new RuntimeException("Empty List");
		T data= head.data;
		head=head.next;
		--size;
		
		if(isEmpty()) tail=null;
		else head.prev=null;
		
		return data;
	}
	
	public T removeLast() {
	    if(isEmpty()) throw new RuntimeException("Emptylist");
	    T data=tail.data;
	    tail=tail.prev;
	    size--;
	    if(isEmpty()) head=null;
	    else tail.next=null;
	    return data;
	}

	public T removeAt(int index) {
	    if(index<0||index>=size()) throw new IllegalArgumentException();
	    int i;
	    Node<T> trav;
	    
	    if(index<size/2)
	        for(i=0,trav=head;i!=index;i++) trav=trav.next;
        else
            for(i=size-1,trav=tail;i!=index;i--) trav=trav.prev;
	    
	    return remove(trav);
	}
	
	private T remove(Node<T> node) {
	    if(node.prev==null) return removeFirst();
	    if(node.next==null) return removeLast();
	    
	    node.next.prev=node.prev;
	    node.prev.next=node.next;
	    size--;
	    
	    T data=node.data;
	    node.data=null;
	    node=node.prev=node.next=null;
	    
	    return data;
	}
	
	public boolean remove(Object elem) {
	    Node<T> curr=head;
	    if(elem==null) {
	        for(;curr!=null;curr=curr.next)
	            if(curr.data==null) {
	                remove(curr);
	                return true;
	            }
	    } else {
	        for(;curr!=null;curr=curr.next)
                if(curr.data.equals(elem)) {
                    remove(curr);
                    return true;
                }
	    }
	    return false;
	}
	
	public void reverse() {
        Node<T> tmp=null,curr=head;
        while(curr!=null) {
            tmp=curr.prev;
            curr.prev=curr.next;
            curr.next=tmp;
            curr=curr.prev;
        }
        if(tmp!=null) head=tmp.prev;
    }
	
	public int indexOf(Object elem) {
	    Node<T> curr=head;
	    int i=0;
	    
        if(elem==null) {
            for(;curr!=null;curr=curr.next,i++) {
                if(curr.data==null) {
                    remove(curr);
                    return i;
                }
            }
        } 
        else {
            for(;curr!=null;curr=curr.next,i++) {
                if(curr.data.equals(elem)) {
                    remove(curr);
                    return i;
                }
            }
        }
        return -1;
	}
	
	public boolean contains(Object obj) {
	    return indexOf(obj) != -1;
	}

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> curr=head;
            
            @Override
            public boolean hasNext() {
              return curr!=null;
            }
            
            @Override
            public T next() {
              T data = curr.data;
              curr=curr.next;
              return data;
            }
            
            @Override
            public void remove() {
              throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("[");
      Node<T> curr=head;

      for(;curr.next!=null;curr=curr.next) sb.append(curr.data+", ");
      sb.append(curr.data+"]");
      
      return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if ((!(obj instanceof doublyLinkedList))) return false;
        if(this==obj) return true;
        
        doublyLinkedList other=(doublyLinkedList) obj;
        
        if(other.size()!=this.size()) return false;
        Node<T> h=head;
        for(Object x:other) {
            if(!x.equals(h.data)) return false;
            h=h.next;
        }
        return true;
    }
    
    public doublyLinkedList<T> clone() {
        doublyLinkedList<T> cl=new doublyLinkedList<>();
        for(Node<T> x=head;x!=null;x=x.next) cl.add(x.data);
        return cl;
    }
    
    public static void main(String[] args) {
        doublyLinkedList<Integer> ll=new doublyLinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        doublyLinkedList<Integer> ll2=ll.clone();
        ll.reverse();
        System.out.println(ll2);
        System.out.println(ll);
    }
}
