public 
class fenwicktree {
    private long tree[]; //1-based index
    
    public fenwicktree(int n) {
        tree=new long[n+1];
    }
    public fenwicktree(long[] val) {
        //val array must be 1 based
        tree=val.clone();
        for(int i=1;i<tree.length;i++) {
            int j=i+lsb(i);
            if(j<tree.length) tree[j]+=tree[i];
        }
    }
    private int lsb(int x) {
        return x&-x; //Alternative-> Integer.lowestOneBit(x)
    }
    
    public long preSum(int i) {
        long sum=0;
        while(i!=0) {
            sum+=tree[i];
            i&=~lsb(i); //i-=lsb(i)
        }
        return sum;
    }
    //returns the sum in range [l,r]
    public long sum(int l,int r) {
        if(l>r) throw new IllegalArgumentException();
        return preSum(r)-preSum(l-1); //like normal prefix sum
    }
    
    //adds x to the index i
    public void add(long x,int i) {
        while(i<tree.length) {
            tree[i]+=x;
            i+=lsb(i);
        }
    }
    //set index i to x
    public void set(int i,long x) {
        long val=sum(i,i);
        add(x-val,i);
    }
}