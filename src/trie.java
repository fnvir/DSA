// check if a string 's' exists in a String[] in O(n) ; n=len(s)
// or find the longest prefix of 's' and the String[]
public class trie {
    char trie[][];
    trie() {this(100000,26);}
    trie(int n,int k) {
        // n=max length of any string in the set
        // k=size of alphabet (len of set of all chars from all the strings)
        trie=new char[n][k];
    }
//    trie(String s[]) {
//        boolean b[]=new boolean[256];
//        int n=0,k=0;
//        for(String x:s) {
//            n=Math.max(n,x.length());
//            for(int i=0;i<x.length();i++)
//                b[x.charAt(i)]=true;
//        }
//        for(boolean f:b) k+=(f?1:0);
//        trie=new char[n][k];
//        insert(s);
//    }
    void insert(String s[]) {
        for(String x:s) insert(x);
    }
    void insert(String s) {
        // s[i]==0: nonexistent; s[i]==1: exists; s[i]==3: exists and end of string
        int i=0;
        for(char c:s.toCharArray()) trie[i++][c-'a']|=1;
        trie[--i][s.charAt(i)-'a']=3;
    }
    boolean find(String s) {
        int i=0;
        for(;i<s.length();i++)
            if(trie[i][s.charAt(i)-'a']==0)
                return false;
        return trie[--i][s.charAt(i)-'a']==3;
    }
    String findLongestPrefix(String s) {
        int i=0;
        for(;i<s.length();i++)
            if(trie[i][s.charAt(i)-'a']==0)
                break;
        return s.substring(0,i);
    }
    public static void main(String[] args) {
        trie t=new trie(5,4);
        t.insert("abc"); t.insert("caca");
        System.out.println(t.find("aca"));
    }
}
