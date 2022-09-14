import java.math.BigInteger;

public class useful_algorithms {
    //Sieve of Eratosthenes 
    static void sieve() {
        int n=1000005;
        boolean prime[]=new boolean[n+1];
//        prime[0]=prime[1]=false;
        for(int i=2;i<n;i++) prime[i]=true; 
        for(int p=2;p*p<=n;p++){ 
            if(prime[p]) { 
                for(int i=p*p;i<=n;i+=p)
                    prime[i] = false; 
            }
        }
    }
    //C++ lowerbound
    static int lowerBound(int[] a, int low, int high, int element) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (element > a[middle]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }
    //Alternatively:
    /***
    C++, lower_bound() should return an index to the first element in the sorted container that is equal to or above
    the number being looked up, and -1 if there is no such element.

    C++, upper_bound() should return an index to the first element in the sorted container that is above the number 
    being looked up, and -1 if there is no such element.

    In Java, you can use Collections.binarySearch to find the lower bound of the equal range in a 
    sorted list (Arrays.binarySearch provides a similar capability for arrays). 
    Then you continue iterating linearly until you hit to the end of the equal range.
    ***/
    //C++ upperbound
    static int upperBound(int[] a, int low, int high, int element) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (a[middle] > element) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return low;
    }
    static String randStr(int n) {
        StringBuilder sb=new StringBuilder();
        char[] start= {'A','a','1'}, end= {'Z','z','9'};
        for(int i=0;i<n;i++) {
            int z=randint(0,start.length-1);
            sb.append((char)randint(start[z],end[z]));
        }
        return sb.toString();
    }
    static int randint(int mn,int mx) {
        return (int)Math.round(Math.random()*(mx-mn)+mn);
    }
    //print all subarrays
    //exact complexity: n*(n+1)*(n+2)/6
    public static void subarrays(int a[]) {
        int n=a.length;
        for(int i=0;i<n;i++)
            for(int j=i;j<n;j++){
                for(int k=i;k<=j;k++)
                    System.out.print(a[k]+" ");
                System.out.println();
            }
    }
    // all subsequences a.k.a. powerset
    // complexity: O(2^n * n)
    static void powerset(int a[]) {
        int n=a.length;
        for(int i=0;i<1<<n;i++) {
            for(int j=0;j<n;j++)
                if((i&(1<<j))>0)
                    System.out.print(a[j]+" ");
            System.out.println();
        }
    }
    //No. of Divisors
    static int divisors(int n) {
        int c=0;
        for(int i=1;i*i<=n;i++) {
            if(n%i==0) {
                if(n/i==i) {
                    c++;
                    System.out.print(i+" ");
                }
                else {
                    c+=2;
                    System.out.print(i+" "+n/i+" ");
                }
            }
        }
        System.out.println();
        return c;
    }
    // ceil(log2(n))
    int log2(int n) {
        return 31-Integer.numberOfLeadingZeros(n);
        //return 63-Long.numberOfLeadingZeros(n); //for Long
//        return Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
    }
    //fast ceil
    int ceil(int a,int b) {
        return (a+b-1)/b;
    }
    //GCD
    static int gcd(int a,int b) {
        return b==0?a:gcd(b,a%b);
    }
    //LCM
    static int lcm(int a,int b) {
        return (a/gcd(a,b))*b;
    }
    //Computes a^b using binary exponentiation
    static long binpow(long a,long b) {
        long res=1;
        while (b>0) {
            if ((b&1)==1)
                res = res * a;
            a = a * a;
            b >>= 1;
        }
        return res;
    }
    // a^b % m
    // using binary exponentiation
    static long powmod(long a, long b,int m) {
        a %= m;
        long res = 1;
        while (b > 0) {
            if ((b&1)>0)
                res = res * a % m;
            a = a * a % m;
            b >>= 1;
        }
        return res;
    }
    // (a+b) % mod
    //Constraints: 0<=a,b<MOD
    //If both a and b are in range [0,MOD-1] and MOD is less than INT_MAX/2,
    // which is usually the case, then it's correct.
    int MOD=9;
    int addmod(int a,int b){
        a+=b;
        if(a>=MOD)
            a-=MOD;
        return a;
    }
    // Diff % mod
    //Constraints: 0<=a,b<MOD
    int submod(int a,int b){
        a-=b;
        if(a<0)
            a+=MOD;
        return a;
    }
    //Fast Inverse Square root (1/sqrt(x))
    //Not 100% accurate
    public static float invSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x *= (1.5f - xhalf * x * x);
        return x;
    }
    //For double
    public static double invSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }
    static void primefact(int n) {
        for(int i=2;i<=Math.sqrt(n);i++) {
            while(n%i==0) {
                System.out.print(i+" ");
                n/=i;
            }
        }
        if(n>1) System.out.print(n+" ");
        System.out.println();
    }
//    static void primeFactorization(int n,HashSet<Integer> hs) { 
//        if(n%2==0) {
//            hs.add(2);
//            for(;n%2==0;n/=2);
//        }
//        for(int i=3,j=(int)Math.sqrt(n);i<=j;i+=2) {
//            if(n%i==0) {
//                hs.add(i);
//                for(;n%i==0;n/=i);
//            }
//        }
//        if(n>2) hs.add((int)n);
//    }

    
    
    
    static int read(){int z=-1;try{z=System.in.read();}catch(Throwable e){}return z;} 
    static char nextChar(){char c=0;do c=(char)read();while(c<=32);return c;}
    static int nextInt(){
        int x=0,p=1,c=nextChar();if(c=='-'){p=-1;c=read();}
        for(;c>='0'&&c<='9';c=read())x=x*10+c-'0'; return x*p;
    }
    static long nextLong(){
        long x=0,p=1,c=nextChar();if(c=='-'){p=-1;c=read();}
        for(;c>='0'&&c<='9';c=read())x=x*10+c-'0'; return x*p;
    }
    static String next(){
        StringBuilder s=new StringBuilder();
        for(int c=nextChar();c>32;c=read())s.append((char)c);
        return s.toString();
    }
    static String nextLine(){
        StringBuilder s=new StringBuilder();
        for(int c=nextChar();c>=32;c=read())s.append((char)c);
        return s.toString();
    }
    
    
    
    public static void memoryStats() {
        int kb = 1024;
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory: "+rt.totalMemory()/kb);
        System.out.println("Free Memory: " +rt.freeMemory()/kb);
        System.out.println("Used Memory: "+(rt.totalMemory()-rt.freeMemory())/kb);
        System.out.println("Max Memory: " +rt.maxMemory()/kb);
    }
    

    // prints 2d array as table .......
    static void printTable(Object table[][]) {
        int n=table.length,m=table[0].length;
        StringBuilder div=new StringBuilder("+");
        int cl[]=new int[m];
        for(int j=0;j<m;j++) {
            for(int i=0;i<n;i++)
                cl[j]=Math.max(cl[j],(table[i][j]+"").length()+2);
            div.append("-".repeat(cl[j])).append("+");
        }
        System.out.println(div);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++) {
            sb.append("|");
            for(int j=0;j<m;j++) {
                String s=" "+table[i][j];
                int x=cl[j]-s.length();
                sb.append(" ".repeat(x/2)).append(s).append(" ".repeat(x-x/2)).append("|"); //center
//                sb.append(s).append(" ".repeat(x)).append("|");
            }
            sb.append('\n').append(div);
            System.out.println(sb);
            sb.setLength(0);
        }
    }
    static long nPr(int n,int r) {
        long x=1;
        for(int i=n-r+1;i<=n;i++) x*=i;
        return x;
    }
    static long nCr(int n,int r) { //possibility of overflow
        long x=1;
        for(int i=1;i<=r;i++) x=(x*(n-r+i))/i; //the product of k consecutive integers is divisible by k!
        return x;
    }
    
//    static BigInteger npr(int n,int r) {
//        BigInteger x=BigInteger.ONE;
//        for(int i=1;i<=r;i++) x=x.multiply(new BigInteger(n-r+i+""));
//        return x;
//    }
//    static BigInteger ncr(int n,int r) {
//        BigInteger x=BigInteger.ONE;
//        for(int i=1;i<=r;i++) x=x.multiply(new BigInteger(n-r+i+"")).divide(new BigInteger(i+""));
//        return x;
//    }
    
    
    
    public static void main(String[] args) {
//        Object a[][]= {{0.0001,2.54,-3.6656},{"hello",4,-65},{2e-90,4,-6556656565565l}};
//        printTable(a);
    }
    
}