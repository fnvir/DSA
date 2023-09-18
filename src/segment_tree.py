class segtree:
    def __init__(self,a,l,r,f):
        self.l=l #starting range
        self.r=r #end range
        self.f=f # function to be execute e.g: sum,min,max etc.
        self.val=None # value of function 'f' executed on range [l,r]
        self.lazy=0 # lazy propagation
        self.lflag=False # to mark if lazy prop is lazy assignment or update
        self.leftChild=self.rightChild=None
        if self.l==self.r:
            self.val=a[l]
        else:
            m=(l+r)//2
            self.leftChild=segtree(a,l,m,f)
            self.rightChild=segtree(a,m+1,r,f)
            self.recalc()

    def recalc(self):
        self.val=self.f(self.leftChild.val,self.rightChild.val)
    
    def __lazyProp(self): #lazy propagation
        if self.lflag:
            self.val=self.lazy*(self.r-self.l+1)
            if self.l!=self.r: # non leaf
                self.leftChild.lazy=self.rightChild.lazy=self.lazy
                self.leftChild.lflag=self.rightChild.lflag=True
            self.lazy=0
            self.lflag=False

        if self.lazy==0: return
        self.val+=self.lazy*(self.r-self.l+1)
        if self.l!=self.r: # non leaf
            self.leftChild.lazy+=self.lazy
            self.rightChild.lazy+=self.lazy
        self.lazy=0

    def point_update(self,inx,add): # O(logN)
        '''add 'e' to element at index'''
        self.__lazyProp()
        if inx<self.l or self.r<inx: return
        if self.l==self.r:
            self.val+=add
            return
        self.leftChild.point_update(inx,add)
        self.rightChild.point_update(inx,add)
        self.recalc()

    def point_assign(self,inx,v): # O(logN)
        '''set index to val'''
        self.__lazyProp()
        if inx<self.l or self.r<inx: return
        if self.l==self.r:
            self.val=v
            return
        self.leftChild.point_assign(inx,v)
        self.rightChild.point_assign(inx,v)
        self.recalc()

    def range_update(self,l,r,e): # O(logN)
        '''add 'e' to all elements from index l to r '''
        self.__lazyProp()
        if l>self.r or r<self.l: return
        if l<=self.l and self.r<=r:
            self.lazy+=e
            self.__lazyProp()
            return
        self.leftChild.range_update(l,r,e)
        self.rightChild.range_update(l,r,e)
        self.recalc()
    
    def range_assign(self,l,r,e): # O(logN)
        '''set all elements from index [l,r] to "e"'''
        self.__lazyProp()
        if l>self.r or r<self.l: return
        if l<=self.l and self.r<=r:
            self.lazy=e
            self.lflag=True
            self.__lazyProp()
            return
        self.leftChild.range_assign(l,r,e)
        self.rightChild.range_assign(l,r,e)
        self.recalc()

    def range_query(self,l,r): # O(logN)
        '''get the value of the range [l,r]'''
        self.__lazyProp()
        if l>self.r or r<self.l: # update return vals acc. to func
            # if self.f==min: return float('inf')
            # if self.f==max: return -float('inf')
            # if self.f==lcm: return 1
            # if self.f==gcd: return 0
            return 0 # xor, sum, gcd etc.
        if l<=self.l and r>=self.r:
            return self.val
        return self.f(self.leftChild.range_query(l,r),self.rightChild.range_query(l,r))

    def point_query(self,inx): # O(logN)
        '''get value at index'''
        self.__lazyProp()
        if self.l==self.r:
            return self.val
        if inx<=self.leftChild.r:
            return self.leftChild.point_query(inx)
        return self.rightChild.point_query(inx)
    
