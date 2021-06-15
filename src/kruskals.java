public class kruskals {
    public static void main(String[] args) {
        
    }
    static long kruskalMST(edge[] edgelist,int n) {
        long sum=0;
        java.util.Arrays.sort(edgelist);
        dsu d=new dsu(n);
        for(edge e:edgelist) {
            if(d.connected(e.u, e.v)) continue;
            d.unite(e.u, e.v);
            sum+=e.w;
            if(d.componentSize(0)==n) break; //if all nodes are connected -> break
        }
        return sum;
    }
    static class dsu{
        int sz,components,size[],id[];
        dsu(int sz) {
            this.sz=components=sz;
            size=new int[sz];
            id=new int[sz];
            for(int i=0;i<sz;i++) {
                size[i]=1;
                id[i]=i;
            }
        }
        int find(int p) {
            int root=p;
            while(root!=id[root]) root=id[root];
            while(p!=root) {
                int nxt=id[p];
                id[p]=root;
                p=nxt;
            }
            return root;
        }
        boolean connected(int a,int b) {
            return find(a)==find(b);
        }
        void unite(int a,int b) {
            if(connected(a,b)) return;
            int root1=find(a),root2=find(b);
            if(size[root1]<size[root2]) {
                size[root2]+=size[root1];
                id[root1]=root2;
            }else {
                size[root1]+=size[root2];
                id[root2]=root1;
            }
            components--;
        }
        int size() {
            return sz;
        }
        int components() {
            return components;
        }
        int componentSize(int a) {
            return size[find(a)];
        }
    }
    static class edge implements Comparable<edge>{
        int u,v,w;
        edge(int... a){u=a[0];v=a[1];w=a[2];}
        public int compareTo(edge o) {
            return Integer.compare(w,o.w);
        }
    }
}
