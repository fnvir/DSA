import java.util.HashMap;
import java.util.PriorityQueue;

class node implements Comparable<node> {
    Character c;
    int val;
    node left,right;
    node(char c,int v) {
        this.c=c;
        val=v;
    }
    node(node l,node r) {
        val=l.val+r.val;
        if(getHeight(l)<getHeight(r)) {
            left=l;
            right=r;
        } else {
            left=r;
            right=l;
        }
    }
    public int getHeight(node root) {
        if(root==null) return 0;
        int left=getHeight(root.left)+1;
        int right=getHeight(root.right)+1;
        return Math.max(left, right);
    }
    public int compareTo(node o) {
        return Integer.compare(this.val, o.val);
    }
    public String toString() {
        return c+" : "+val;
    }
}
public class huffman {
    
    private String encoded,original;
    private HashMap<Character ,String> codes=new HashMap<>();
    private node tree;
    public huffman(String s) {
        original=s;
        tree=generateTree(s.toCharArray());
        generateCodes(tree,"");
        compress();
    }
    
    private node generateTree(char s[]) {
        int f[]=new int[256];
        for(char c:s) f[c]++;
        PriorityQueue<node> pq=new PriorityQueue<>();
        for(int i=0;i<f.length;i++)
            if(f[i]>0)
                pq.add(new node((char)i,f[i]));
        System.out.println("Nodes: "+pq.toString());
        while(pq.size()!=1) 
            pq.add(new node(pq.poll(),pq.poll()));
        return pq.poll();
    }
    
    private void generateCodes(node root,String s) {
        if(root.c!=null)
            codes.put(root.c,s);
        if(root.left!=null)
            generateCodes(root.left,s+"0");
        if(root.right!=null)
            generateCodes(root.right,s+"1");
    }
    
    public void printCodes() {
        System.out.println("Codes ("+(codes.size())+"): ");
        for(var x:codes.entrySet()) {
            System.out.println(x.getKey()+" : "+x.getValue());
        }
    }

    public String getCode(char c) {
        return codes.get(c);
    }
    
    private void compress() {
//        encoded=original;
//        for(var x:codes.entrySet())
//            encoded=encoded.replaceAll(x.getKey()+"",x.getValue());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<original.length();i++)
            sb.append(getCode(original.charAt(i)));
        encoded=sb.toString();
    }
    
    public String getEncoded() {
        return encoded;
    }
    
    public String getEncoded(String s) {
        StringBuilder sb=new StringBuilder();
        for(char c:s.toCharArray()) {
            sb.append(getCode(c));
        }
        return sb.toString();
    }
    
    public String getDecoded() {
        return getDecoded(encoded);
    }
    
    public String getDecoded(String str) {
        String decoded="";
        node curr=tree;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='0')
                curr=curr.left;
            else curr=curr.right;
            if(curr.c!=null) {
                decoded+=curr.c;
                curr=tree;
            }
        }
        return decoded;
    }
    
    int code=-1,len=0;
    public void getCanonical() {
        System.out.println("Canonical: ");
        codes.entrySet().stream().sorted(
            (k1,k2) -> {
                int x=Integer.compare(k1.getValue().length(), k2.getValue().length());
                if(x==0) return Integer.compare(k1.getKey(), k2.getKey());
                return x;
            }
        ).forEach(e->{
            code=(code+1)<<(e.getValue().length()-len);
            len=e.getValue().length();
            System.out.println(e.getKey()+
                    " : "+String.format("%"+len+"s",Integer.toBinaryString(code)).replace(' ','0'));
        });
    }
    
    public void showComparison() {
        int x=original.length(),y=encoded.length();
//        StringBuilder sb=new StringBuilder();
//        for(int i=0;i<original.length();i++)
//            sb.append(String.format("%8s", Integer.toBinaryString(original.charAt(i))).replaceAll(" ", "0"));
//        System.out.println("Original data: "+sb);
//        System.out.println(this);
        System.out.println("Original data size: "+(original.length()*8)+" bits = "+x+" bytes");
        System.out.println("Compressed data size: "+y+" bits = "+(int)Math.ceil(y/8.0)+" bytes");
    }
    
    public void stats() {
        int maxlen=0;
        for(String s:codes.values()) {
            maxlen=Math.max(maxlen, s.length());
        }
        System.out.println("MAX LEN: "+maxlen);
    }
    
    public String toString() {
        return "Compressed Data: "+getEncoded();
    }
    
    
    public static void main(String[] args) {
        String s="abccdddeeeee"+"f".repeat(8)+"g".repeat(13)+"h".repeat(21);
        huffman f=new huffman(s);
        f.printCodes();
//        System.out.println(f.getCode((char)109));
//        System.out.println(f); 
        System.out.println("Decoded Data: "+f.getDecoded("1101111001110101111110"));
        System.out.println(f.getEncoded("chegg"));
//        f.showComparison();
//        f.stats();
//        f.getCanonical();
    }
}
