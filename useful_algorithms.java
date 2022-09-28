import java.math.BigInteger;

public class useful_algorithms {
    //Sieve of Eratosthenes 
    static void sieve() {
        int n=1000005;
        boolean prime[]=new boolean[n+1];
//        prime[0]=prime[1]=false;
        for(int i=2;i<n;i++) prime[i]=true; 
        for(int p=2;(long)p*p<=n;p++){ 
            if(prime[p]) { 
                for(int i=p*p;i<=n;i+=p)
                    prime[i] = false; 
            }
        }
    }
    static int lowerBound(int[] a,int l,int r,int elem) {
        while(l<r) {
            int m=l+(r-l)/2;
            if(elem>a[m]) l=m+1;
            else r=m;
        }
        return l;
    }
    /***
    C++, lower_bound() should return an index to the first element in the sorted container that is equal to or above
    the number being looked up, and -1 if there is no such element.

    C++, upper_bound() should return an index to the first element in the sorted container that is above the number 
    being looked up, and -1 if there is no such element.

    In Java, you can use Collections.binarySearch to find the lower bound of the equal range in a 
    sorted list (Arrays.binarySearch provides a similar capability for arrays). 
    Then you continue iterating linearly until you hit to the end of the equal range.
    ***/
    static int upperBound(int[] a,int l,int r,int elem) {
        while(l<r) {
            int m=l+(r-l)/2;
            if(a[m]>elem) r=m;
            else l=m+1;
        }
        return l;
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
    // floor(log2(n))
    static int log2(int n) {
        return 31-Integer.numberOfLeadingZeros(n);
        //return 63-Long.numberOfLeadingZeros(n); //for Long
//        return Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
    }
    // log base x of n
    static double logx(int x, double n) {
        return Math.log(n)/Math.log(x);
    }
    //fast ceil(a/b)
    int ceilDiv(int a,int b) {
        return (a+b-1)/b;
    }
//    //GCD
//    // almost fast as iterative due to tail recursion optimization
//    static int gcd(int a,int b) {
//        return b==0?a:gcd(b,a%b);
//    }
    static int gcd(int a,int b) {
        int c;
        while(b!=0) {
            c=a;a=b;
            b=c%b;
        }
        return a;
    }
    //LCM
    static int lcm(int a,int b) {
        return (a/gcd(a,b))*b;
    }
    
    //Computes a^b using binary exponentiation
    // simply: a^n = a^(n/2) * a^(n/2)
    // O(log_2(n))
    static long binpow(long a,long b) {
        long res=1;
        while(b>0) {
            if((b&1)==1) res*=a;
            a*=a;
            b>>=1;
        }
        return res;
    }
    // a^b % m
    // using binary exponentiation
    static long powmod(long a, long b,int m) {
        a%=m;
        long res=1;
        while(b>0) {
            if((b&1)>0)
                res=res*a%m;
            a=a*a%m;
            b>>=1;
        }
        return res;
    }
    
    // x^(y^e) % m
    // https://cp-algorithms.com/algebra/phi-function.html#generalization
    static long powmod_large(long x,int y,int e, int m) {
        int z=phi(m);
        if(e>=logx(y,logx(2,m))) // let, n=y^e;  if n > log2(m) :
            return powmod(x,z+powmod(y,e,z),m); // x^n%m = [x^(phi(m)+(n%phi(m)))]%m
        return powmod(x,(long)Math.pow(y,e),m); // else traditional way
    }
    
    // euler's totient function O(sqrt(n))
    // total number of co-prime numbers between 1 to n
    // co-prime: gcd(a,b)=1
    //i.e. total number of numbers which are not factors(except 1) of n from 1 to n
    static int phi(int n) {
        int result=n;
        for(int i=2;i*i<=n;i++) {
            if(n%i==0) {
                while(n%i==0) n/=i;
                result-=result/i;
            }
        }
        if(n>1) result-=result/n;
        return result;
    }

    //euler's totient function for all 1 to n
    //O(NloglogN)
    //https://cp-algorithms.com/algebra/phi-function.html
    int[] phi_1_to_n(int n) {
        int phi[]=new int[n+1];
        for(int i=0;i<=n;i++) phi[i]=i;
        for(int i=2;i<=n;i++) {
            if(phi[i]==i) {
                for(int j=i;j<=n;j+=i)
                    phi[j]-=phi[j]/i;
            }
        }
        return phi;
    }
    //extended euclidean algorithm to calculate
    // soln of a*x + b*y = gcd(a,b)
    //return the gcd and puts the soln(x,y) in the xy[]
    //https://cp-algorithms.com/algebra/extended-euclid-algorithm.html
    static int extendedEuclidean(int a,int b,int[] xy) {
        int x=1,y=0; //soln of Linear Diophantine Equations
        int x1=0,y1=1;
        int tmp,q;
        while(b!=0) {
            q=a/b;
            tmp=x;x=x1;x1=tmp-q*x1;
            tmp=y;y=y1;y1=tmp-q*y1;
            tmp=a;a=b;b=tmp-q*b;
        }
        xy[0]=x; xy[1]=y; //soln
        return a; //gcd of a,b
    }
    
    //modular inverse of n or n^-1
    //is an integer such that (n*modInv(n))%p = 1
    // no.. it is not 1/n cause its not int
    //modInv exists ONLY IF gcd(a,m)=1 
    static int modInv(int a,int m) {
        if(gcd(a,m)!=1) throw new RuntimeException("No modular inverse");
        return(int) powmod(a,phi(m)-1,m); //euler's theorem
//        return powmod(a,m-2,m); //fermat's little theorem (if m is prime)
    }
    
    //modular inverse using extended euclidean
    //https://cp-algorithms.com/algebra/module-inverse.html#finding-the-modular-inverse-using-extended-euclidean-algorithm
    static int modinv(int a,int m) {
        int xy[]=new int[2];
        int g=extendedEuclidean(a,m,xy);
        if(g!=1) throw new RuntimeException("No modular inverse");
        return (xy[0]%m+m)%m;
    }
    
    // Diff % mod
    //Constraints: 0<=a,b<MOD
    //If both a and b are in range [0,MOD-1] and a,b is less than INT_MAX/2,
    // which is usually the case, then it's correct.
    //Constraints: 0<=a,b<MOD
    static int submod(int a,int b,int MOD){
        a-=b;
        if(a<0)
            a+=MOD;
        return a;
    }
    
    static int multiplymod(int a,int b,int m) {
        return (int)((1l*a%m*b%m)%m);
    }
    
    static int divmod(int a,int b,int m) {
        return (a%m)*(modinv(b,m)%m)%m;
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
    static int[] smallestPrimeFactor(int n) {
        int spf[]=new int[n+1];
        for(int i=2;i<=n;i++) {
            spf[i]=i;
            if((i&1)==0) spf[i]=2;
        }
        for(int i=3;1l*i*i<=n;i+=2)
            if(spf[i]==i) 
                for(int j=i*i;j<=n;j+=i)
                    if(spf[j]==j)
                        spf[j]=i;
        return spf;
    }
    static void primeFact_SPF(int n) {
        int spf[]=smallestPrimeFactor(n); // precalculate this once for max n
        while (n!=1) {
            System.out.println(spf[n]);
            n/=spf[n];
        }
    }
    static void primeFactorization(int n) { 
        for(;(n&1)==0;n>>=1) System.out.println(2);
        for(int i=3;1l*i*i<=n;i+=2) {
            while(n%i==0) {
                System.out.println(i);
                n/=i;
            }
        }
        if(n>1) System.out.println(n);
    }
    
    //length of str(n!) i.e. number of digits
    // for n>9, factorial's can be uniquely identified by its length
    // see: https://dmoj.ca/problem/naq16g/editorial
    static int factorial_length(int n) {
        double log10[]=new double[n+1];
        // log10[0]=0; 
        for(int i=1;i<=n;i++)
            log10[i]=log10[i-1]+Math.log10(i);
        return (int)Math.floor(log10[n])+1;
    }
    
    // legendre's formula
    // the largest power of p(a prime) that divides n!
    static int largestPower(int n,int p) {
        int x=0;
        while(n>0) {
            n/=p;
            x+=n;
        }
        return x;
    }
    
    static long nPr(int n,int r) {
        long x=1;
        for(int i=n-r+1;i<=n;i++) x*=i;
        return x;
    }
    static long nCr(int n,int r) { //possibility of overflow
        r=Math.min(r,n-r); //nCr= nC(n-r)
        long x=1;
        for(int i=1;i<=r;i++) x=(x*(n-r+i))/i; //the product of k consecutive integers is divisible by k!
        return x;
    }
    
    static BigInteger npr(int n,int r) {
        BigInteger x=BigInteger.ONE;
        for(int i=0;i<r;i++) x=x.multiply(new BigInteger(n-i+""));
        return x;
    }
    static BigInteger ncr(int n,int r) {
        BigInteger x=BigInteger.ONE;
        for(int i=1,R=Math.min(r,n-r);i<=R;i++) x=x.multiply(new BigInteger(n-R+i+"")).divide(new BigInteger(i+""));
        return x;
    }
    
    static int ncr_mod_m(long n,long r,int m) {
        /*
         * See dsa/ncrModm 
         */
        throw new RuntimeException();
    }
    
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
    
    public static void main(String[] args) {
//        Object a[][]= {{0.0001,2.54,-3.6656},{"hello",4,-65},{2e-90,4,-6556656565565l}};
//        printTable(a);
//        System.out.println(factorial_length(300000-5));
    }
}
