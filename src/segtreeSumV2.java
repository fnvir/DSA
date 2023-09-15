/* Only for sum queries
 * Cannot do range assignments
*/
class segTreeSumV2{
    int n;
    long tree[];
    int lazy[];
    
    public segTreeSumV2(int a[]) {
        n=a.length;
        int z=1<<(32-Integer.numberOfLeadingZeros(n));
        tree=new long[2*z]; // if power of 2, then 2n size is enough
        lazy=new int[2*z];
        build(0, n-1, 1, a);
    }
    
    void build(int tl,int tr,int v,int a[]) {
        if(tl==tr) tree[v]=a[tl];
        else {
            int tm=(tl+tr)/2;
            build(tl, tm, 2*v, a);
            build(tm+1,tr,2*v+1,a);
            recalc(v);
        }
    }
    void recalc(int v) {
        tree[v]=tree[2*v]+tree[2*v+1];
    }
    void lazy(int v,int tl,int tr) {
        if(lazy[v]==0) return;
        tree[v]+=lazy[v]*(tr-tl+1);
        if(tr!=tl) { // not leaf
            lazy[2*v]+=lazy[v];
            lazy[2*v+1]+=lazy[v];
        }
        lazy[v]=0;
    }
    
    void point_update(int inx,long add) {
        pointUpdate(1,0,n-1,inx,add);
    }
    void range_update(int l,int r,int add) {
        rangeUpdate(1,0,n-1,l,r,add);
    }
    void point_set(int inx,int val) {
//          point_update(inx,val-point_query(inx)); // 2log(n) = log(n^2)
        pointSet(1,0,n-1,inx,val); // O(log(n))
    }
    long point_query(int inx) {
        return pointQuery(1,0,n-1,inx);
    }
    long range_query(int l,int r) {
        return rangeQuery(1,0,n-1,l,r);
    }
    
    long pointSet(int v,int tl,int tr, int inx,int val) {
        lazy(v,tl,tr);
        long z;
        if(tl==tr) {
            z=val-tree[v];
            tree[v]=val;
            return z;
        }
        int tm=(tl+tr)/2;
        if(inx<=tm) z=pointSet(2*v,tl,tm,inx,val);
        else z=pointSet(2*v+1,tm+1,tr,inx,val);
        tree[v]+=z;
        return z;
    }

    void pointUpdate(int v,int tl,int tr, int inx,long add) {
        tree[v]+=add;
        if(tl==tr) return;
        int tm=(tl+tr)/2;
        if(inx<=tm) pointUpdate(2*v,tl,tm,inx,add);
        else pointUpdate(2*v+1,tm+1,tr,inx,add);
    }
    
    void rangeUpdate(int v, int tl, int tr, int l, int r, long add) {
        if(l>r) return;
        tree[v]+=add*(r-l+1);
        if(l==tl&&r==tr) {
            if(tl!=tr) {
                lazy[2*v]+=add;
                lazy[2*v+1]+=add;
            }
            return;
        }
        int tm=(tl+tr)/2;
        rangeUpdate(v*2, tl, tm, l, Math.min(r, tm), add);
        rangeUpdate(v*2+1, tm+1, tr, Math.max(l, tm+1), r, add);
    }
    long pointQuery(int v,int tl,int tr,int inx) {
        lazy(v,tl,tr);
        if(tl==tr) return tree[v];
        int tm=(tl+tr)/2;
        if(inx<=tm) return pointQuery(v*2,tl,tm,inx);
        return pointQuery(v*2+1,tm+1,tr,inx);
    }
    long rangeQuery(int v,int tl,int tr, int l,int r) {
        lazy(v,tl,tr);
        if(l>tr || r<tl) return 0;
        if(l<=tl && r>=tr) return tree[v];
        int tm=(tl+tr)/2;
        return rangeQuery(2*v,tl,tm,l,r)+rangeQuery(2*v+1,tm+1,tr,l,r);
    }
}
