//Fenwick tree with range updates
//Range sum not possible / maybe
public class fenwicktreeUpdate {
    
    private long[] originalTree,tree2;
    private int n;
    public fenwicktreeUpdate(long[] val) {
        //val must be 1 based
        n=val.length;
        originalTree=val.clone();
        
        for(int i=1;i<n;i++) {
            int j=i+lsb(i);
            if(j<n) originalTree[j]+=originalTree[i];
        }
        tree2=originalTree.clone();
    }
    
    private int lsb(int x) {
        return x&-x; //Alternative-> Integer.lowestOneBit(x)
    }
    
    //adds x in the interval [l,r]
    public void updateRange(int l,int r,long x) {
        add(l,x);
        add(r+1,-x);
    }
    //adds x to the index i
    public void add(int i,long x) {
        while(i<n) {
            tree2[i]+=x;
            i+=lsb(i);
        }
    }
    //Get the value at a specific index.
    public long get(int i) {
        return preSum(i, tree2) - preSum(i - 1, originalTree);
    }
    private long preSum(int i,long[] tree) {
        long sum = 0;
        while (i!=0) {
          sum += tree[i];
          i &= ~lsb(i); // Equivalently, i-=lsb(i);
        }
        return sum;
    }
}
