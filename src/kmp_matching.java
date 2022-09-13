//alternate implementation of kmp algo from gfg
public class kmp_matching {
    
    //find all occurrences of string 'pat' in 'txt' in O(len(txt))
    static int search(String txt,String pat) {
        int N=txt.length(),M=pat.length();
        int pi[]=computePi(pat);
        int i=0,j=0; // i=inx for txt, j=index for pat
        int found=0;
        while(N-i>=M-j) {
            if(pat.charAt(j)==txt.charAt(i)) {
                i++;
                j++;
            }
            if(j==M) {
//                System.out.println("Found pattern at index " + (i-j));
                found++;
                j=pi[j-1];
            } else if (i<N && pat.charAt(j)!=txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j!=0) j=pi[j-1];
                else i++;
            }
        }
        return found;
    }
    
    private static int[] computePi(String P) {
        int m=P.length();
        int pi[]=new int[m];
        int i=1, len=0; // length of the previous longest prefix suffix
        pi[0]=0;
        while (i<m) {
            if (P.charAt(i)==P.charAt(len)) {
                len++;
                pi[i]=len;
                i++;
            } else {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len!=0) len=pi[len-1];
                else {
                    pi[i]=len;
                    i++;
                }
            }
        }
        return pi;
    }
}
