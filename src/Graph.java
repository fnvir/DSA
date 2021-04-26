import java.util.ArrayList;

public class Graph {
    static int n=4,m=4;//n-> No. of Vertex | m-> No. of Edges
    static ArrayList<Integer>[] adjList=new ArrayList[n];
    static boolean visited[]=new boolean[n];
    
    static void createGraph() {
        for(int i=0;i<adjList.length;i++) adjList[i]=new ArrayList<Integer>();
    }
    
    public static void main(String[] args) {
        createGraph();
        dfs(0);
        visited=new boolean[n];
        System.out.println();
        visited=new boolean[n];
        dfs(1);
        visited=new boolean[n];
        System.out.println();
        dfs(2);
    }
    
    static void dfs(int node) {
        if(visited[node]) return;
        visited[node]=true;
        System.out.print(node+1+" ");
        for(var neighbours:adjList[node]) {
            dfs(neighbours);
        }
    }
}

//class pair{
//    int x,y;
//    pair(int a,int b){x=a;y=b;}
//}
