import java.util.ArrayList;
/**
    lowest common ancestor of a tree with sparse table
    O(NlogN) build, O(1) per query, O(NlogN) memory
    use seg/fenwick tree for O(N) build+memory but O(logN) per query
*/
class LCA {
    
    int[] lvl,first,eulertour,dp[];
    int m;
    
    public LCA(ArrayList<Integer>[] adj) {
        int n=adj.length;
        eulertour=new int[n*2];
        lvl=new int[n];
        first=new int[n];
        dfs(0,-1,0,adj);
        sparsetbl();
    }
    void dfs(int u,int p,int h,ArrayList<Integer>[] adj) {
        eulertour[m]=u;
        first[u]=m++;
        lvl[u]=h;
        for(int v:adj[u]) {
            if(v!=p) {
                dfs(v,u,h+1,adj);
                eulertour[m++]=u;
            }
        }
    }
    void sparsetbl() { //O(nlogn)
        int p=31-Integer.numberOfLeadingZeros(m);
        dp=new int[p+1][m];
        dp[0]=eulertour;
        for(int i=1;i<=p;i++)
            for(int j=0;j+(1<<i)<=m;j++) {
                int l=dp[i-1][j],r=dp[i-1][j+(1<<(i-1))];
                dp[i][j]=lvl[l]<lvl[r]?l:r;
            }
    }
    int lca(int u,int v) { //get lca of node u and v -> O(1)
        int l=first[u],r=first[v];
        if(l>r) {l^=r;r^=l;l^=r;}
        int k=31-Integer.numberOfLeadingZeros(r-l+1),x,y; // log2
        return lvl[x=dp[k][l]]<lvl[y=dp[k][r-(1<<k)+1]]?x:y;
    }
}