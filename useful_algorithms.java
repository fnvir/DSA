
public class useful_algorithms {
    //Sieve of Eratosthenes 
    static void sieve() {
        int n=1000005;
        boolean prime[]=new boolean[n+1];
        for(int i=0;i<n;i++) prime[i]=true; 
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
        for(int i=0;i<n*n;i++) {
            for(int j=0;j<n;j++)
                if((i&(1<<j))>0)
                    System.out.print(a[j]+" ");
            System.out.println();
        }
    }
    //No. of Divisors
    static int divisors(int n) {
        int c=0;
        for(int i=1;i<=Math.sqrt(n);i++) {
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
    static int lcm(int a, int b) {
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
    static long powmod(long a, long b,long m) {
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
        StringBuilder sb=new StringBuilder("+");
        int cl[]=new int[m];
        for(int j=0;j<m;j++) {
            for(int i=0;i<n;i++)
                cl[j]=Math.max(cl[j],(table[i][j]+"").length()+2);
            sb.append("-".repeat(cl[j])).append("+");
        }
        System.out.println(sb);
        StringBuilder pr=new StringBuilder();
        for(int i=0;i<n;i++) {
            pr.append("|");
            for(int j=0;j<m;j++) {
                String s=" "+table[i][j];
                int x=cl[j]-s.length();
                pr.append(" ".repeat(x/2)).append(s).append(" ".repeat(x-x/2)).append("|"); //center
//                pr.append(s).append(" ".repeat(x)).append("|");
            }
            pr.append('\n').append(sb);
            System.out.println(pr);
            pr.setLength(0);
        }
    }
    public static void main(String[] args) {
        Object a[][]= {{0.0001,2.54,-3.6656},{3001,4,-65},{-2.2,4,-65566556565565l}};
        printTable(a);
    }
    
}