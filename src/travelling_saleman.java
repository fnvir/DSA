import java.util.ArrayList;

public class travelling_saleman {
    
    static int n,start,dis[][];
    static long dp[][];
    
    public static void main(String[] args) {
        var tst=new tsp_test();
        n=tst.n;
        dis=tst.dis;
        start=tst.start;
//        n=10;
//        dis=new int[n][n];
//        start=0;
        int endS=(1<<n)-1; //end state (all bits 1)
        
        var combinations=precalc(n);
        dp=new long[n][1<<n]; //stores best distances of tours ending at row 'r' with visited cities as bits of col 'c'
        for(int end=0;end<n;end++) {
            if(end==start) continue;
            dp[end][(1<<start)|(1<<end)]=dis[start][end];
        }
        
        for(int l=3;l<=n;l++) { // l= no. of cities visited till now
            for(int tour:combinations[l]) { // for each tour where 'l' no. of cities are visited
                if(!set(tour,start)) continue; //if start is not in visited paths -> invalid
                for(int next=0;next<n;next++) { //for each bit in tour, if this city will be visited next i.e. last city
                    if(next==start|| !set(tour,next)) continue;
                    int pathWithoutNext=tour^(1<<next); // remove the city to visit next from the tour
                    long mn=Long.MAX_VALUE;
                    for(int prevEnd=0;prevEnd<n;prevEnd++) { //if this was the last city visited before 'next' i.e. 2nd last city
                        if(prevEnd==start||prevEnd==next|| !set(tour,prevEnd)) continue;
                        long dist=dp[prevEnd][pathWithoutNext]+dis[prevEnd][next];
                        if(dist<mn) mn=dist;
                    }
                    dp[next][tour]=mn;
                }
            }
        }
        
        long minCost=Long.MAX_VALUE;
        
        //now, to return back to the starting city
        for(int i=0;i<n;i++) {
            if(i==start) continue;
            long x=dp[i][endS]+dis[i][start];
            if(x<minCost) minCost=x;
        }
        
        System.out.println("Minimum cost for tsp: "+ minCost);
        System.out.println("Path: "+java.util.Arrays.toString(recreatePath()));
        
    }
    
    static int[] recreatePath() {
        int path[]=new int[n+1];
        
        int lastCity=start; //cause i returned to it...
        int state=(1<<n)-1; // all visited
        path[n]=lastCity;
        
        for(int i=n-1;i>0;i--) {
            long mn=Long.MAX_VALUE;
            int sLast=-1; //second last city visited
            
            for(int last=0;last<n;last++) {
                if(last==start|| !set(state,last)) continue;
                long dist=dp[last][state]+dis[last][lastCity];
                if(dist<mn) {
                    mn=dist;
                    sLast=last;
                }
            }
            lastCity=sLast;
            path[i]=lastCity;
            state^=(1<<lastCity); //remove the bit from that state
        }
        path[0]=start; //cause it was the first one
        
        return path;
    }
    
    
    //if the ith bit is set in 'N' ; 0-based index
    static boolean set(int N,int i) {
        return (N&(1<<i))>0;
    }
    
    static ArrayList<Integer>[] precalc(int n) {
        ArrayList<Integer> comb[]=new ArrayList[n+1];
        for(int i=0;i<=n;i++) comb[i]=new ArrayList<>();
        
        for(int i=0;i<1<<n;i++) comb[Integer.bitCount(i)].add(i);
        return comb;
    }
}


class tsp_test {
    int n,dis[][],start;
    tsp_test(){
        n=randInt(5,10);
        dis=new int[n][n];
        start=randInt(0,n);
        System.out.println("Nodes: "+n);
        System.out.println("Start: "+start);
        System.out.println("Distances (i<-->j) ");
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++) {
                dis[i][j]=dis[j][i]=randInt(1,50);
                System.out.println(i+" <-> "+j+" : "+dis[i][j]);
            }
        System.out.println();
    }
    
    public int randInt(int mn,int mx) {
        return (int)(Math.random()*(mx-mn)+mn);
    }
    
}


