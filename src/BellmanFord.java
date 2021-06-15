import java.util.LinkedList;

public class BellmanFord {
    static LinkedList<tuple> edges=new LinkedList<>(); //Edge list with weights
    public static void main(String[] args) {
        createGraph();
        int n=5; //Number of nodes/vertices
        final int INF=(int)1e9;
        //Main algorithm starts here
        int distance[]=new int[n+1];
        for(int i=0;i<=n;i++) distance[i]=INF;
        int start=1;
        distance[start]=0;
        for(int i=1;i<n;i++) {
            for(var e:edges) {
                int a=e.a,b=e.b,w=e.w;
                distance[b]=Math.min(distance[b],distance[a]+w);
            }
        }
        //Doesn't work
        //Check for negative cycles
        for(int i=0;i<edges.size();i++) {
            int a=edges.get(i).a,b=edges.get(i).b,w=edges.get(i).w;
            if(distance[a]+w<distance[b]) {
                System.out.println("Negative cycle detected");
                break;
            }
        }
        System.out.println(distance[5]);
    }
    static void createGraph() {
        //Directed Graph - from book BellmanFord topic
        edges.add(new tuple(1,2,5));
        edges.add(new tuple(1,3,3));
        edges.add(new tuple(1,4,7));
//        edges.add(new tuple(2,1,5));
//        edges.add(new tuple(2,4,3));
        edges.add(new tuple(2,5,2));
//        edges.add(new tuple(5,2,2));
//        edges.add(new tuple(5,4,2));
        edges.add(new tuple(4,2,3));
        edges.add(new tuple(4,5,2));
//        edges.add(new tuple(4,1,7));
//        edges.add(new tuple(4,3,1));
//        edges.add(new tuple(3,1,3));
        edges.add(new tuple(3,4,1));
    }
    static class tuple{
        int a,b,w;
        tuple(int a,int b,int w){
            this.a=a;
            this.b=b;
            this.w=w;
        }
    }
}