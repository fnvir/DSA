//Knuth–Morris–Pratt algorithm
//ref: stanford cs97si slides (https://web.stanford.edu/class/cs97si/10-string-algorithms.pdf)

public class kmp {
    int[] pi;
    char[] T,P; //a text T and a pattern P
    public kmp(String text,String ptrn) {
        T=text.toCharArray();
        P=ptrn.toCharArray();
        pi=new int[P.length+1];
        calcPi();
    }
    private void calcPi() { //slide pg.22
        pi[0]=-1;
        int k=-1;
        for(int i=1;i<=P.length;i++) {
            while(k>=0 && P[k]!=P[i-1])//1-based index
                k=pi[k];
            pi[i]= ++k;
        }
    }
    //find all occurrences of P within T
    public int findAllOccurrences() { //slide pg.23
        int k=0,c=0;
        for(int i=0;i<T.length;i++) {
            while(k>=0 && P[k]!=T[i])
                k = pi[k];
            k++;
            if(k == P.length) {
                // P matches T[i-m+1 ... i]
                c++;
                k = pi[k];
            }
        }
        return c;
    }
    //check if 'a' is a substring of 's'
    public boolean isSubStr() {
        int k=0;
        for(int i=0;i<T.length;i++) {
            while(k>=0 && P[k]!=T[i])
                k = pi[k];
            k++;
            if(k == P.length)
                return true;
        }
        return false;
    }
}
