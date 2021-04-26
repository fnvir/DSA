public class SparseTableRMQ{
    int n,P,log2[],inx[][];
    long dp[][];
    
    SparseTableRMQ(long[] values){
        n=values.length;
        P=log2(n);
        dp=new long[P+1][n];
        inx=new int[P+1][n];
        log2=new int[n+1];
 
        for(int i=0;i<n;i++) {
            dp[0][i]=values[i];
            inx[0][i]=i;
        }
        for(int i=2;i<=n;i++) {
            log2[i]=log2[i/2]+1;
        }
        
        for(int t=1;t<=P;t++) {
            for(int i=0;i+(1<<t)<=n;i++) {
                long l=dp[t-1][i],r=dp[t-1][i+(1<<(t-1))];
                dp[t][i]=Math.min(l, r);
                if(l<=r) {
                    inx[t][i]=inx[t-1][i];
                }else {
                    inx[t][i]=inx[t-1][i+(1<<(t-1))];
                }
            }
        }
    }
    int log2(int n) {
        return Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
    }
    long queryMin(int l,int r) {
        int len=r-l+1;
        int p=log2[len];
        int k=1<<p;
        long left=dp[p][l],right=dp[p][r-k+1];
        return Math.min(left, right);
    }
    
    int queryMinIndex(int l,int r) {
        int len=r-l+1;
        int p=log2[len];
        int k=1<<p;
        long left=dp[p][l],right=dp[p][r-k+1];
        if(left<=right) return inx[p][l];
        return inx[p][r-k+1];
    }
}