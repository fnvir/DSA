import java.lang.reflect.Array;
import java.util.Arrays;

class pyList {

    private Object a[];
    private int len;
    private double growth=1.75;
    
    public pyList() {
        this(8);
    }
    
    public pyList(int size) {
        a=new Object[size];
    }

    private void resize() {
        Object b[]=new Object[(int)(len*growth)];
        System.arraycopy(a,0,b,0,len);
        a=b;
    }
    
    public boolean set(int inx,Object o) {
        if(inx<0||inx>=len) throw new IndexOutOfBoundsException();
        a[inx]=o;
        return true;
    }
    
    public Object get(int inx) {
        if(inx<0||inx>=len) throw new IndexOutOfBoundsException();
        return a[inx];
    }
    
    public void add(Object x) {
        if(x.getClass().equals(pyList.class)) {
            concat((pyList)x);
            return;
        }
        if(len+1>=a.length)
            resize();
        a[len++]=x;
    }
    
    public void concat(pyList x) {
        for(int i=0;i<x.length();i++)
            add(x.get(i));
    }
    
    public Object remove(int index) {
        if(index<0||index>=len) throw new IndexOutOfBoundsException();
        Object x=a[index];
        len--;
        if(index!=len) 
            System.arraycopy(a,index+1,a,index,len-index);
        return x;
    }
    
    public Object remove(Object o) {
        for(int i=0;i<len;i++)
            if(o.equals(a[i]))
                return remove(i);
        return null;
    }
    
    public Object pop() {
        return remove(len-1);
    }
    
    public int length() {
        return len;
    }
    
    public String toString() {
        StringBuilder s=new StringBuilder("[ ");
        for(int i=0;i<len;i++) {
            if(a[i].getClass().isArray())
                s.append(Arrays.toString(toObjArray(a[i])));
            else
                s.append(a[i].toString());
            s.append(", ");
        }
        s.setLength(s.length()-2);
        s.append(" ]");
        return s.toString();
    }
    
    public String allClassNames() {
        StringBuilder s=new StringBuilder("[ ");
        for(int i=0;i<len;i++) {
            s.append(a[i].getClass().getCanonicalName()+", ");
        }
        s.setLength(s.length()-2);
        s.append(" ]");
        return s.toString();
    }
    
    private static Object[] toObjArray(Object array) {
        Class<?> ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            int length = Array.getLength(array);
            Object ar[] = new Object[length];
            for (int i = 0; i < length; i++)
                ar[i]=Array.get(array, i);
            return ar;
        }
        return (Object[])array;
    }
    
}
