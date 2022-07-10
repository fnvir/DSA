import java.io.PrintWriter;
import java.util.Scanner;

class tt{
    char tbl[][];
    int n,m;
    static final Scanner sc=new Scanner(System.in);
    static final PrintWriter out=new PrintWriter(System.out);
            
    tt(){
        n=m=3;
        tbl=new char[n][m];
    }
    char[][] action(char[][] state, int i,int j,boolean human){
        char[][] cpy=new char[n][m];
        for(int k=0;k<3;k++)
            System.arraycopy(state[k],0,cpy[k],0,state[k].length);
        cpy[i][j]=human?'x':'o';
        return cpy;
    }
    int maxV(char[][] state,int alpha,int beta,int[] nxt) {
        int end=isEnd(state);
        if(end<2) return end;
        int mx=-2;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(state[i][j]==0) {
                    int z=minV(action(state,i,j,true),alpha,beta,new int[]{0,0});
                    if(z>mx) {
                        mx=z;
                        nxt[0]=i;nxt[1]=j;
                    }
                    alpha=Math.max(alpha,mx);
                    if(alpha>=beta) return mx;
                }
        return mx;
    }
    int minV(char[][] state,int alpha,int beta,int[] nxt) {
        int end=isEnd(state);
        if(end<2) return end;
        int mn=3;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(state[i][j]==0) {
                    int z=maxV(action(state,i,j,false),alpha,beta,new int[]{0,0});
                    if(z<=mn) {
                        mn=z;
                        nxt[0]=i;nxt[1]=j;
                    }
                    beta=Math.min(beta,mn);
                    if(alpha>=beta) return mn;
                }
        return mn;
    }
    void play() {
        out.println("How to Play:\nKeep entering the row and col no. you want to place move");
        show();
        int x,y;
        while(isEnd(tbl)==2) {
            x=sc.nextInt();y=sc.nextInt();
            while(tbl[x][y]!=0){
                out.println("Invalid Move !");out.flush();
                x=sc.nextInt();y=sc.nextInt();
            }
            tbl[x][y]='x';
            out.println("Your move: "+"("+x+","+y+")");
            show();
            
            int nxt[]={0,0};
            minV(tbl,-10,10,nxt);
            x=nxt[0];y=nxt[1];
            tbl[x][y]='o';
            out.println("Opponent's move: "+"("+x+","+y+")");
            show();
        }
        out.println((x=isEnd(tbl))==0?"It's a draw":((x==1?"You":"Opponent")+" Won")+"!!!!!!");
        out.close();
    }
    boolean equal(char a,char b,char c) {
        return a==b&&b==c&&a>0;
    }
    int utility(char c) {
        return c=='x'?1:-1;
    }
    int isEnd(char[][] state) { // 1: x wins, -1: o wins, 0: draw, 2: not ended
        for(int i=0;i<n;i++)
            if(equal(state[i][0],state[i][1],state[i][2]))
                return utility(state[i][0]);
        for(int j=0;j<m;j++)
            if(equal(state[0][j],state[1][j],state[2][j]))
                return utility(state[0][j]);
        if(equal(state[0][0],state[1][1],state[2][2])||equal(state[2][0],state[1][1],state[0][2]))
            return utility(state[1][1]);
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(state[i][j]==0)
                    return 2;
        return 0;
    }
    void show() {
        StringBuilder sb=new StringBuilder();
        sb.append("+");
        int cl[]=new int[m];
        for(int j=0;j<m;j++) {
            for(int i=0;i<n;i++)
                cl[j]=Math.max(cl[j],(tbl[i][j]+"").length()+2);
            sb.append("-".repeat(cl[j])).append('+');
        }
        out.println(sb);
        for(int i=0;i<n;i++) {
            out.print("|");
            for(int j=0;j<m;j++) {
                String s=" "+(tbl[i][j]==0?"":tbl[i][j]);
                out.print(s);
                out.print(" ".repeat(cl[j]-s.length()));
                out.print("|");
            }
            out.println();
            out.println(sb);
        }
        out.flush();
    }
}

public class tictactoe {
    public static void main(String[] args) {
        new tt().play();
    }
}