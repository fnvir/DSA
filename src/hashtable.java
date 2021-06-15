import java.util.Iterator;
import java.util.LinkedList;

public class hashtable<K,V> implements Iterable<K>{
    static class Map<K,V>{
        K key;
        V value;
        int hash;
        public Map(K key, V val) {
            this.key=key;
            value=val;
            hash=key.hashCode();
        }
        public boolean equals(Map<K,V> other) {
            if(this.hash!=other.hash) return false;
            return key.equals(other.key);
        }
        public String toString() {
            return key+" : "+value;
        }
    }

    private static final int defaultCapacity=3;
    private static final double defloadfactor=0.75;
    
    private double currLoad;
    private int capacity,threshold,size=0;
    private LinkedList<Map<K,V>> table[];
    
    public hashtable() {
        this(defaultCapacity,defloadfactor);
    }
    public hashtable(int capcty) {
        this(capcty,defloadfactor);
    }
    public hashtable(int cpct,double loadfactor) {
        if(cpct<0) throw new IllegalArgumentException();
        if (loadfactor <= 0 || Double.isNaN(loadfactor) || Double.isInfinite(loadfactor))
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        currLoad=loadfactor;
        this.capacity=cpct;
        threshold=(int) (capacity*loadfactor);
        table=new LinkedList[capacity];
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    private int capacityHash(int hash) {
        return (hash&0x7FFFFFFF)%capacity; //& 0x7FFFFFFF converts the number to positive
    }
    
    public void clear() {
        for(int i=0;i<table.length;i++) table[i]=null;
        size=0;
    }
    
    private Map<K, V> seekEntry(int inx,K key) {
        if(key==null) return null;
        LinkedList<Map<K, V>> list=table[inx];
        if(list==null) return null;
        for(Map<K,V> x:list)
            if(x.key.equals(key))
                return x;
        return null;
    }
    
    private V insertEntry(int inx,Map<K,V> map) {
        LinkedList<Map<K,V>> list=table[inx];
        if(list==null) table[inx]=list=new LinkedList<>();
        Map<K,V> m = seekEntry(inx,map.key);
        if(m==null) {
            list.add(map);
            size++;
            if(size>threshold) resize();
            return null;
        }else {
            V oldval=m.value;
            m.value=map.value;
            return oldval;
        }
    }
    
    private V removeEntry(int inx,K key) {
        Map<K,V> m=seekEntry(inx,key);
        if(m!=null) {
            LinkedList<Map<K,V>> links=table[inx];
            links.remove(m);
            size--;
            return m.value;
        }
        return null;
    }
    
    public boolean containsKey(K key) {
        int inx=capacityHash(key.hashCode());
        return seekEntry(inx,key)!=null;
    }
    
    public V insert(K key,V value) {
        if(key==null) throw new IllegalArgumentException();
        Map<K,V> m=new Map<>(key,value);
        int inx=capacityHash(m.hash);
        return insertEntry(inx,m);
    }
    
    public V get(K key) {
        if(key==null) return null;
        int inx=capacityHash(key.hashCode());
        Map<K,V> m=seekEntry(inx,key);
        return m.value;
    }
    
    public V remove(K key) {
        if(key==null) return null;
        int inx=capacityHash(key.hashCode());
        return removeEntry(inx,key);
    }
    
    private void resize() {
        capacity*=2;
        threshold=(int)(capacity*currLoad);
        LinkedList<Map<K, V>> newTable[]=new LinkedList[capacity];
        for(int i=0;i<table.length;i++) {
            if(table[i]!=null) {
                for(Map<K, V> x:table[i]) {
                    int inx=capacityHash(x.hash);
                    LinkedList<Map<K, V>> bucket = newTable[inx];
                    if (bucket == null) newTable[inx] = bucket = new LinkedList<>();
                    bucket.add(x);
                }
                // Avoid memory leak
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }
    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        return null;
    }
}




















