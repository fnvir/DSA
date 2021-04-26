//UVa-532
import java.util.LinkedList;
import java.util.Scanner;
public class grid3D_BFS {
    static int L,R,C;
    static LinkedList<tuple> list;
    static char[][][]grid;
    static boolean visited[][][];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true) {
            L=sc.nextInt();R=sc.nextInt();C=sc.nextInt();
            list=new LinkedList<>();
            grid=new char[L][R][C];
            visited=new boolean[L][R][C];
            if(R+C+L==0) break;
            int sx=0,sy=0,sz=0;
            for(int i=0;i<L;i++) {
                for(int j=0;j<R;j++) {
                    grid[i][j]=sc.next().toCharArray();
                    for(int k=0;k<C;k++) if(grid[i][j][k]=='S') {sx=i;sy=j;sz=k;break;}
                }
            }
            int ans=bfs(sx,sy,sz);
            System.out.println(ans==-1?"Trapped!":"Escaped in "+ans+" minute(s).");
        }
    }
    static int bfs(int sx,int sy,int sz) {
        visited[sx][sy][sz]=true;
        list.add(new tuple(sx,sy,sz,0));
        while(!list.isEmpty()) {
            tuple curr=list.poll();
            int x=curr.x,y=curr.y,z=curr.z,steps=curr.steps; 
            if(grid[x][y][z]=='E') return steps;
            visitNeighbours(x,y,z,steps);
        }
        return -1;
    }
    static void visitNeighbours(int x,int y,int z,int steps) {
        int[] dx={-1,0,1,0,0,0},dy={0,1,0,-1,0,0},dz={0,0,0,0,1,-1};
        for(int i=0;i<6;i++) {
            int a=x+dx[i],b=y+dy[i],c=z+dz[i];
            if(a<0||b<0||c<0||a>=L||b>=R||c>=C||grid[a][b][c]=='#'||visited[a][b][c]) continue;
            visited[a][b][c]=true;
            list.add(new tuple(a,b,c,steps+1));
        }
    }
    static class tuple{
        int x,y,z,steps;
        tuple(int x, int y, int z, int steps) {
            this.x = x;
            this.y =y;
            this.z=z;
            this.steps=steps;
        }
    }
}

/*
Sample Input: 
5 10 20
S.##.####.#...##..#.
...#..##.###.#......
#..#.#.#.#.###.###.#
......#.###.###.##.#
####...##..###..##.#
#.##.#..#..#.#.#####
.....#..##..##..#.##
..#.#...#.#.##...##.
..#.###.#..#...#....
###...#.....#.##.##.

#..#.####.#.##.#.##.
..#.....#####.##....
.#.#.#......#..#....
..#.###..#..#####.#.
#.#..##.##..##..#...
...#...####.#..#..#.
######.#.....#.#####
#.##.###.#.###.#.#.#
......#..#.###...#..
###.##.###...#..##..

#...##.......#.#.#.#
.###...##.##..##....
.###.#..##.####.####
.######.....#..##...
#..#.....###...#..#.
.#..######.##......#
.#...#...##.#....#..
###..##......#.####.
#..#...##.#..##...##
#....#######..#.#.##

.......####..##.##..
....##.####....##.##
.#########.#.##...##
#..###.#..###.#...##
##.#.#...###.#...#..
.#.##....#..####...#
.#..###.##.#######..
..##..###.##..####..
.#....#.....##.#.#..
.#.#.#..#...######.#

#....#....#.##..##..
..#..#.#.####.#####.
....###..#....##.###
##...##...#.###.####
.#..#..####.#.####.#
#..##.#.##...#.#..##
.##..#..##.#.###....
##.#..#.##.#..##.##.
####...###.#.#.###.#
#.###...##.##..###.E

0 0 0

Output:
Escaped in 40 minute(s).
*/