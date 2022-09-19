
public class lcs {
    int dp[][];
    String x,y;
    String lcs;
    public lcs(String s1,String s2) {
        int n=s1.length();
        int m=s2.length();
        x=s1;
        y=s2;
        dp=new int[n+1][m+1];
        computeLCS(s1, n, s2, m);
    }
    private void computeLCS(String s1,int n,String s2,int m) {
        for(int i=1;i<n+1;i++) {
            for(int j=1;j<m+1;j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        lcs="";
        int i=m,j=n;
        while (i>0 && j>0) {
            if (s1.charAt(i-1)==s2.charAt(j-1)) {
                lcs=s1.charAt(i-1)+lcs;
                i--;
                j--;
            } else if (dp[i-1][j]>dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
    }

    
    public void printTable() {
        for(char c:(" 0"+y).toCharArray()) System.out.print(c+" ");
        System.out.println();
        for(int i=0;i<dp.length;i++) {
            System.out.print((i==0?"0":x.charAt(i-1))+" ");
            for(int j=0;j<dp[i].length;j++)
                System.out.print(dp[i][j]+" ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        lcs x=new lcs("ACGATCGT","CGATCGTA");   // wrong code...doesn't work on unequal lengths
        x.printTable();
        System.out.println(x.lcs);
//        x=new lcs("ATTATCGT","TTTTCGAA");
//        x.printTable();
//        System.out.println(x.lcs);
    }
}
