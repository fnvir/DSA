/* Incomplete */
import java.util.Iterator;

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

		@Override
		public String toString() {
			return data.toString();
		}
	}

	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void add(T elem) {
		addLast(elem);
	}

	public void addFirst(T elem) {
		if (isEmpty())
			head = tail = new Node<T>(elem, null, null);
		else {
			head.prev = new Node<T>(elem, null, head);
			head = head.prev;
		}
		size++;
	}

	public void addLast(T elem) {
		if (isEmpty())
			head = tail = new Node<T>(elem, null, null);
		else {
			tail.next = new Node<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	public void addAt(int index, T data) throws Exception {
	    if (index < 0) {
	      throw new Exception("Illegal Index");
	    }
	    if (index == 0) {
	      addFirst(data);
	      return;
	    }

	    if (index == size) {
	      addLast(data);
	      return;
	    }
	    
	    Node<T> temp = head;
	    for (int i = 0; i < index - 1; i++) {
	      temp = temp.next;
	    }
	    Node<T> newNode = new Node<>(data, temp, temp.next);
	    temp.next.prev = newNode;
	    temp.next = newNode;

	    size++;
	  }
	
	public T peekFirst() {
		if(isEmpty()) throw new RuntimeException("Empty List");
		return head.data;
	}
	
	public T peekLAst() {
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

	public Iterator<T> iterator() {
		return null;
	}

}
