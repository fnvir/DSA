class dsu{
    private int sz,components;
    private int[] size,id;
    
    public dsu(int sz) {
        this.sz=components=sz;
        size=new int[sz];
        id=new int[sz];
        for(int i=0;i<sz;i++) {
            size[i]=1;
            id[i]=i; // initially, every elem is its own root
        }
    }
    
    public int find(int p) {
        int root=p;
        while(root!=id[root]) root=id[root];
        //path compression
        while(p!=root) {
            int nxt=id[p];
            id[p]=root;
            p=nxt;
        }
        return root;
    }
    public boolean connected(int a,int b) {
        return find(a)==find(b);
    }
    public void unify(int a,int b) {
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
    public int size() {
        return sz;
    }
    public int components() {
        return components;
    }
    public int componentSize(int a) {
        return size[find(a)];
    }
}