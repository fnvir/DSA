//Shortest Path in Grid using BFS
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class grid_BFS {
    static int R,C;
    static int movecount,nodesNext,nodesLeft=1;
    static Queue<Integer> rq=new LinkedList<>(),cq=new LinkedList<>();
    static char m[][];
    static boolean end,visited[][];
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        R=scan.nextInt();C=scan.nextInt();
        m=new char[R][C];
        for(int i=0;i<R;i++) m[i]=scan.next().toCharArray();
        int sr=0,sc=0;
        for(int i=0;i<R;i++) for(int j=0;j<C;j++) if(m[i][j]=='S') {sr=i;sc=j;break;}

        visited=new boolean[R][C];
        
        System.out.println(solve(sr,sc)+" minutes taken");
    }
    static int solve(int sr,int sc) {
        rq.add(sr);
        cq.add(sc);
        while(!rq.isEmpty()) {
            int r=rq.poll(),c=cq.poll();
            if(m[r][c]=='E') {end=true;break;}
            exploreNeighbours(r,c);
            nodesLeft--;
            if(nodesLeft==0) {
                nodesLeft=nodesNext;
                nodesNext=0;
                movecount++;
            }
        }
        if(end) return movecount;
        return -1;
    }
    
    static void exploreNeighbours(int r,int c) {
        int dr[]= {-1,1,0,0},dc[]= {0,0,1,-1};
        for(int i=0;i<4;i++) {
            int rr=r+dr[i],cc=c+dc[i];
            if(rr<0||cc<0||rr>=R||cc>=C||m[rr][cc]=='#'||visited[rr][cc]) continue;
            rq.add(rr);
            cq.add(cc);
            visited[rr][cc]=true;
            nodesNext++;
        }
    }
}
/*

Sample Input:

5 7
S..#...
.#...#.
.#.....
..##...
#.#E.#.

Output: 9

10 10
S.........
..........
..........
..........
..........
..........
..........
..........
..........
.........E

*/
