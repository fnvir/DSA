class Pair<FIRST extends Comparable<FIRST>, SECOND extends Comparable<SECOND>> implements Comparable<Pair<FIRST, SECOND>> {
 
    public FIRST first;
    public SECOND second;
 
    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }
 
    public static <FIRST extends Comparable<FIRST>, SECOND extends Comparable<SECOND>>
        Pair<FIRST, SECOND> of(FIRST first, SECOND second) {
        return new Pair<>(first, second);
    }
 
    public int compareTo(Pair<FIRST, SECOND> o) {
        int cmp = compare(first, o.first);
        return cmp == 0 ? compare(second, o.second) : cmp;
    }
 
    private <T extends Comparable<T>> int compare(T o1, T o2) {
        if(o1 == null) {
            if(o2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if(o2 == null) {
            return +1;
        } else {
            return o1.compareTo(o2);
        }
    }
 
    public int hashCode() {
        return 31 * hashcode(first) + hashcode(second);
    }
 
    private static int hashcode(Object o) {
        return o == null ? 0 : o.hashCode();
    }
 
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return equal(first,((Pair)obj).first)&&equal(second,((Pair)obj).second);
    }
 
    private boolean equal(Object o1, Object o2) {
        return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
    }
 
    public String toString() {
        return "(" + first + ", " + second + ')';
    }
}
