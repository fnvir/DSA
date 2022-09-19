//stanford slide: http://web.stanford.edu/class/archive/cs/cs166/cs166.1166/lectures/02/Slides02.pdf

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public
class AhoCorasick {
    int maxC;
    int[] suffl,outl,trie[];
    String end[];
    public AhoCorasick(String s[],int mxs,int mxc) {
        maxC=mxc;
        suffl=new int[mxs];
        outl=new int[mxs];
        trie=new int[mxs][mxc];
        end=new String[mxs];
        Arrays.fill(suffl,-1);
        Arrays.fill(outl,-1);
        for(int i=0;i<mxs;i++)
            Arrays.fill(trie[i],-1);
        makeTrie(s);
    }
    private void makeTrie(String s[]) {
        int states=1;
        for(String x:s) {
            int curr=0;
            for(int j=0;j<x.length();j++) {
                int c=x.charAt(j)-'a';
                if(trie[curr][c]==-1)
                    trie[curr][c]=states++;
                curr=trie[curr][c];
            }
            end[curr]=x; //marks leaf states and stores the string
        }
//        trie=Arrays.copyOf(trie,states); //resize(not needed)
        makeLinks();
    }
    // generate suffix-links and output-links (see stanford slide pg. 331)
    private void makeLinks() {
        // nodes from root not having edges point to root
        for(int i=0;i<maxC;i++) 
            if(trie[0][i]==-1)
                trie[0][i]=0;
        Queue<Integer> q=new ArrayDeque<>();
        for(int c=0;c<maxC;c++) {
            if(trie[0][c]!=0) {
                suffl[trie[0][c]]=0;
                q.add(trie[0][c]);
            }
        }
        while(!q.isEmpty()) {
            int prnt=q.poll(), curr;
            for(int c=0;c<maxC;c++) {
                curr=trie[prnt][c];
                if(curr!=-1) {
                    int pSuffx=suffl[prnt];
                    while(trie[pSuffx][c]==-1)
                        pSuffx=suffl[pSuffx];
                    suffl[curr]=trie[pSuffx][c];
                    q.add(curr);
                    makeOutputLink(curr);
                }
            }
        }
    }
    private void makeOutputLink(int v) {
        int u=suffl[v];
        outl[v]=end[u]!=null?u:outl[u]; //stanford slide pg.440
    }
    private int goTo(int curr,char ch) {
        int c=ch-'a';
        while(trie[curr][c]==-1)
            curr=suffl[curr];
        return trie[curr][c];
    }
    // stanford slide pg.270
    public void findAll(String text) {
        int state=0;
        for(char c:text.toCharArray()) {
            state=goTo(state,c);
            if(end[state]!=null)
                System.out.println("Match -> "+end[state]);
            for(int s=outl[state];s!=-1;s=outl[s]) {
                System.out.println("Match -> "+end[s]);
            }
        }
    }
    public static void main(String[] args) {
        String s[]="a aa ab".split(" ");
        AhoCorasick a=new AhoCorasick(s,12,26);
        a.findAll("aaab");
    }
}
