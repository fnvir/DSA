class pairr implements Comparable<pairr>{
    int f,s;
    pairr(int... x){f=x[0];s=x[1];}
    public int compareTo(pairr o) {
        int x=Integer.compare(f,o.f);
        if(x==0) return Integer.compare(s,o.s);
        return x;
    }
}