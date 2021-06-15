import java.util.HashMap;
import java.util.TreeSet;

public
class MultiSet<T> extends TreeSet<T> {
    HashMap<T,Integer> frequency;
    public MultiSet() {
        frequency = new HashMap<>();
    }
    public boolean add(T t) {
        frequency.put(t, frequency.getOrDefault(t, 0) + 1);
        return super.add(t);
    }
    public boolean remove(Object o) {
        if (!frequency.containsKey(o))
            return false;
        frequency.put((T) o, frequency.getOrDefault(o, 1) - 1);
        if (frequency.get(o) == 0) {
            frequency.remove(o);
            super.remove(o);
        }
        return true;
    }
    public String toString() {
        StringBuilder s=new StringBuilder("{");
        for(T x:frequency.keySet()) {
            int y=frequency.get(x);
            while(y-->0) s.append(x+", ");
        }
        s.replace(s.length()-2, s.length(), "}");
        return s.toString();
    }
}