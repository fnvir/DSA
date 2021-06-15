import java.util.ArrayList;
import java.util.PriorityQueue;

public class Djikstra {
    static ArrayList<pair> list[];
    static PriorityQueue<pair> q=new PriorityQueue<>();
    static boolean visited[];
    static int distance[];
    public static void main(String[] args) {
        int n=5; //number of nodes
        distance=new int[n+1];
        visited=new boolean[n+1];
        createGraph(n+1);
        dijkstra(1);
        System.out.println(distance[3]);
    }
    static void dijkstra(int start) {
        int INF=(int)1e9;
        for(int i=0;i<distance.length;i++) distance[i]=INF;
        distance[start]=0;
        q.add(new pair(0,start));
        while(!q.isEmpty()) {
            int a=q.poll().node;
            if(visited[a]) continue;
            visited[a]=true;
            for(pair u:list[a]) {
                int b=u.distance,w=u.node;
                if(distance[a]+w<distance[b]) {
                    distance[b]=distance[a]+w;
                    q.add(new pair(distance[b],b));
                }
            }
        }
    }
    static void createGraph(int n) {
        list=new ArrayList[n];
        for(int i=0;i<n;i++) list[i]=new ArrayList<pair>();
        list[1].add(new pair(4,9));
        list[1].add(new pair(5,1));
        list[1].add(new pair(2,5));
        list[5].add(new pair(4,2));
        list[4].add(new pair(3,6));
        list[2].add(new pair(3,2));
    }
    static class pair implements Comparable<pair>{
        int distance,node;
        pair(int d,int n){
            distance=d;
            node=n;
        }
        public int compareTo(pair o) {
            int x=Integer.compare(distance,o.distance);
            if(x==0) return Integer.compare(node,o.node);
            return x;
//            if(this.distance-o.distance>0) return 1;
//            else if(this.distance-o.distance<0) return -1;
//            else if(this.node-o.node>0) return -1;
//            else if(this.node-o.node<0) return 1;
//            else return 0;
        }
    }
}
