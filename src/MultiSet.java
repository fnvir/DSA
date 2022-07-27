import java.util.HashMap;
import java.util.HashSet;

public
class MultiSet<T> {
    private HashMap<T,Integer> freq;
    private HashSet<T> hs;
    private int size;
    public MultiSet() {
        freq = new HashMap<>();
        hs=new HashSet<>();
    }
    public boolean add(T t) {
        freq.put(t, freq.getOrDefault(t, 0) + 1);
        size++;
        return hs.add(t);
    }
    public boolean remove(T o) {
        if (!freq.containsKey(o))
            return false;
        freq.put(o,freq.get(o)-1);
        if (freq.get(o) == 0) {
            freq.remove(o);
            hs.remove(o);
        }
        size--;
        return true;
    }
    public int size() {
        return size;
    }
    public T[] toArray(T[] a) {
        int i=0;
        for(T z:freq.keySet())
            for(int c=freq.get(z);c-->0;)
                a[i++]=z;
        return a;
    }
    public String toString() {
        StringBuilder s=new StringBuilder("{");
        for(T x:freq.keySet()) {
            int y=freq.get(x);
            while(y-->0) s.append(x+", ");
        }
        s.replace(s.length()-2, s.length(), "}");
        return s.toString();
    }
}

//public
//class MultiSet<T> extends TreeSet<T> {
//    HashMap<T,Integer> frequency;
//    public MultiSet() {
//        frequency = new HashMap<>();
//    }
//    public boolean add(T t) {
//        frequency.put(t, frequency.getOrDefault(t, 0) + 1);
//        return super.add(t);
//    }
//    public boolean remove(Object o) {
//        if (!frequency.containsKey(o))
//            return false;
//        frequency.put((T) o, frequency.getOrDefault(o, 1) - 1);
//        if (frequency.get(o) == 0) {
//            frequency.remove(o);
//            super.remove(o);
//        }
//        return true;
//    }
//    public String toString() {
//        StringBuilder s=new StringBuilder("{");
//        for(T x:frequency.keySet()) {
//            int y=frequency.get(x);
//            while(y-->0) s.append(x+", ");
//        }
//        s.replace(s.length()-2, s.length(), "}");
//        return s.toString();
//    }
//}