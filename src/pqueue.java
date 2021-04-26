import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class pqueue<T extends Comparable<T>> {
    private int heapsize=0,heapcapacity=0;
    private ArrayList<T> heap;
    private HashMap<T, TreeSet<Integer>> map;
    
    public pqueue() {
        this(1);
    }
    
    public pqueue(int size) {
        heap=new ArrayList<>(size);
        map=new HashMap<>();
    }
    
    public pqueue(T[] elems) {
        heapsize=heapcapacity=elems.length;
        heap=new ArrayList<>(heapcapacity);
        
        for(int i=0;i<heapsize;i++) {
            mapAdd(elems[i],i);
            heap.add(elems[i]);
        }
        
        //heapify
        for(int i=Math.max(0, heapsize/2-1);i>-1;i--) {
            sink(i);
        }
    }
    
    public pqueue(Collection<T> elem) {
        this(elem.size());
        for(T e:elem) add(e);
    }
    
    private void mapAdd(T elem,int inx) {
        TreeSet<Integer> indices=map.get(elem);
        if(indices==null) {
            indices=new TreeSet<>();
            indices.add(inx);
            map.put(elem,indices);
        }else 
            indices.add(inx);
    }
    
    private void mapSwap(T a,T b, int i, int j) {
        Set<Integer> x=map.get(a);
        Set<Integer> y=map.get(b);
        x.remove(i);
        y.remove(j);
        x.add(j);
        y.add(i);
    }
    
    private Integer mapGet(T e) {
        TreeSet<Integer> s=map.get(e);
        if(s==null) return null;
        return s.last();
    }
    
    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index); // TreeSets take O(log(n)) removal time
        if (set.size() == 0) map.remove(value);
    }
    
    private boolean less(int i, int j) {
        return heap.get(i).compareTo(heap.get(j))<=0;
    }
    
    public boolean isEmpty() {
        return heapsize==0;
    }
    
    public void clear() {
        heap.clear();
        heapsize=0;
        map.clear();
    }
    
    public int size() {
        return heapsize;
    }

    public boolean contains(T elem) {
        return elem==null?false:map.containsKey(elem);
    }
    
    
    private void swim(int k) {
        int parent=(k-1)/2;
        while(k>0 && less(k,parent)) {
            swap(parent,k);
            k=parent;
            parent=(k-1)/2;
        }
    }
    
    private void sink(int k) {
        while(true) {
            int left=2*k+1,right=2*k+2;
            int smallest=left;
            if(right<heapsize&&less(right,left)) smallest=right;
            if(left>=heapsize||less(k,smallest)) break;
            swap(smallest,k);
            k=smallest;
        }
    }
    
    private void swap(int i,int j) {
        T a=heap.get(i), b=heap.get(j);
        heap.set(i,b);
        heap.set(j, a);
        mapSwap(a,b,i,j);
    }
    
    public void add(T e) {
        if(e==null) throw new IllegalArgumentException();
        if(heapsize<heapcapacity) {
            heap.set(heapsize,e);
        }else {
            heap.add(e);
            heapcapacity++;
        }
        mapAdd(e,heapsize);
        swim(heapsize);
        heapsize++;
    }
    
    public T peek() {
        if(isEmpty()) return null;
        return heap.get(0);
    }
    
    public T poll() {
        return removeAt(0);
    }
    
    public boolean remove(T e) {
        if(e==null) return false;
        
        //linear remove
//        for(int i=0;i<heapsize;i++) {
//            if(heap.get(i).equals(e)) {
//                removeAt(i);
//                return true;
//            }
//        }
        
        Integer index=mapGet(e);
        if(index!=null) removeAt(index);
        return index!=null;
    }
    
    private T removeAt(int i) {
        if(isEmpty()) return null;
        heapsize--;
        T data=heap.get(i);
        swap(i,heapsize);
        
        heap.set(heapsize,null);
        mapRemove(data,heapsize);
        
        if(i==heapsize) return data;
        
        T elem=heap.get(i);
        sink(i);
        
        if(heap.get(i).equals(elem))
            swim(i);
        return data;
    }
    
    private boolean isMinPQ(int start) {
        if(start>=heapsize) return true;
        
        int left=2*start+1,right=2*start+2;
        if(left<heapsize && !less(start,left)) return false;
        if(right<heapsize && !less(start,right)) return false;
        return isMinPQ(left) && isMinPQ(right);
    }

    public String toString() {
        return heap.toString();
    }
    
    public static void main(String[] args) {
        var pq=new pqueue<Integer>();
        pq.add(0);
        pq.add(1000);
        pq.add(2);
        pq.add(-1);
        while(!pq.isEmpty()) System.out.println(pq.poll());
        System.out.println(pq.isMinPQ(0));
    }
}
