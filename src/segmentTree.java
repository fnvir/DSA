//see cses
class segmentTree{
    int l,r;
    segmentTree lchild,rchild;
    long sum;
    public segmentTree(int l,int r,int a[]) {
        this.l=l;
        this.r=r;
        if(l==r) sum=a[l];
        else {
            //split into half: [l,mid]+[mid+1,r] 
            int mid=(l+r)/2;
            lchild=new segmentTree(l, mid, a);
            rchild=new segmentTree(mid+1, r, a);
            recalc();
        }
    }
    private void recalc() {
        if(l==r) return;
        sum=lchild.sum+rchild.sum;
    }
    public void pointUpd(int inx,int val) {
        if(l==r) {
            sum=val;
            return;
        }
        if(inx<=lchild.r) lchild.pointUpd(inx, val);
        else rchild.pointUpd(inx, val);
        recalc();
    }
    public long rangeSum(int lft, int rght) {
        //disjoint
        if(lft>r||rght<l) {
            return 0;
        }
        //covers entirely
        if(lft<=l&&rght>=r) return sum;
        
        //else
        return lchild.rangeSum(lft, rght)+rchild.rangeSum(lft, rght);
    }
}


class segTreeRMQ {
    int n, tree[];
    final int INF = 1000000000;

    // v=vertex/node
    public segTreeRMQ(int v, int l, int r, int a[]) {
        n = a.length;
        tree = new int[4 * n];
        build(l, r, v, a);
    }

    void build(int tl, int tr, int v, int a[]) {
        if (tl == tr)
            tree[v] = a[tl]; // has no child
        else {
            // split into two halves
            int tm=(tl+tr)/2;
            build(tl,tm,2*v,a); // lchild
            build(tm+1,tr,2*v+1,a); // rchild
            tree[v] = Math.min(tree[2 * v], tree[v * 2 + 1]); // parent=lchild+rchild
        }
    }

    int min(int v,int tl,int tr,int l,int r) {
        if (l>r)
            return INF;
        if (l==tl&&r==tr)
            return tree[v];
        int tm=(tl+tr)/2;
        return Math.min(min(2*v,tl,tm,l,Math.min(r,tm)),min(v*2+1,tm+1,tr,Math.max(tm+1,l),r));
    }

    void update(int v, int tl, int tr, int pos, int val) {
        if (tl==tr)
            tree[v]=val;
        else {
            int tm=(tl+tr)/2;
            if (pos <= tm)
                update(v*2,tl,tm,pos,val);
            else
                update(v*2+1,tm+1,tr,pos,val);
            tree[v]=Math.min(tree[v*2],tree[v*2+1]);
        }
    }
}
class segTreeUpdate{
    int n;
    long tree[];
    public segTreeUpdate(int v,int l,int r,int a[]) {
        n=a.length;
        tree=new long[4*n];
        build(v,l,r,a);
    }
    void build(int v,int tl,int tr,int a[]) {
        if(tl==tr) tree[v]=a[tl];
        else {
            int tm=(tl+tr)/2;
            build(2*v,tl, tm, a);
            build(2*v+1,tm+1,tr,a);
            tree[v]=0;
        }
    }
    void rangeUpdate(int v, int tl, int tr, int l, int r, int add) {
        if(l>r) return;
        if(l==tl&&r==tr) {
            tree[v]+=add;
        }else {
            int tm=(tl+tr)/2;
            rangeUpdate(v*2, tl, tm, l, Math.min(r, tm), add);
            rangeUpdate(v*2+1, tm+1, tr, Math.max(l, tm+1), r, add);
        }
    }
    long pointQuery(int v,int tl,int tr,int inx) {
        if(tl==tr) return tree[v];
        int tm=(tl+tr)/2;
        if(inx<=tm) return tree[v]+pointQuery(v*2,tl,tm,inx);
        return tree[v]+pointQuery(v*2+1,tm+1,tr,inx);
    }
}