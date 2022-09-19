import java.util.HashMap;

public class ncrMODm {
    
    int[] spf;
    long a[];
    
    public ncrMODm(int maxM) {
        spf=smallestPrimeFactor(maxM);
        a=new long[maxM+1];
    }
    
    // nCr%any m  -> O(m log(n)log(m))
    // n,r <= 1e18 ; m<=1e6 
    public long comb(long n,long r,int m) {
        HashMap<Integer,Integer> primes=primeFact_spf(m); //precalc once if 'm' is constant
        int num[]=new int[primes.size()],rem[]=new int[primes.size()];
        int i=0;
        for(int p:primes.keySet()) {
            int e=primes.get(p);
            num[i]=(int)Math.pow(p,e);
            rem[i]=(int)ncrMOD_pE(n,r,p,e);
            ++i;
        }
        return CRT(num,rem);
    }
    void precalc(long p,long e,int m) {
        a[0]=a[1]=1;
        for(int i=2;i<=m;i++)
            a[i]=(a[i-1]*g(i,p))%m;
    }
    long g(long i,long p) {
        return i%p==0?1:i;
    }
    // the largest power of p(a prime) that divides "n!" (legendre's formula)
    long E(long n,long p) {
        long x=0;
        while(n>0) x+=(n/=p);
        return x;
    }
    // product of numbers <=n co-prime to p.
    long f(long n,long p, int m) {
        return (powmod(a[m],n/m,m)*a[(int)(n%m)])%m;
    }
    // n!/p^e mod m, where p^e divides n! -> O(mlogn)
    long F(long n,long p,int m) {
        long i=1;
        long ans=1;
        while(i<=n) {
            ans=(ans*f(n/i,p,m))%m;
            i*=p;
        }
        return ans;
    }
    // ncr mod p^e -> O(p^e logn)
    private long ncrMOD_pE(long n,long r,int p,long e) {
        if(n<r||r<0) return 0;
        int m=(int)Math.pow(p,e);
        precalc(p,e,m);
        long E=E(n,p)-E(r,p)-E(n-r,p);
        if(E>=e) return 0;
//        long numerator=F(n,p,m)*powmod(p,E,m)%m;
//        long denominator=(modInv(F(n-r,p,m),m)%m)*(modInv(F(r,p,m),m)%m) %m;
//        return numerator*denominator%m;
        long numerator=F(n,p,m);
        long denominator=(F(n-r,p,m)*F(r,p,m))%m;
        return ((powmod(p,E,m)*numerator)%m)*(modInv(denominator,m)%m)%m;
    }
    // smallest prime factors for each i from 1 to maxn -> O(sqrt(maxn))
    int[] smallestPrimeFactor(int maxn) {
        int spf[]=new int[maxn+1];
        for(int i=2;i<=maxn;i++) {
            spf[i]=i;
            if((i&1)==0) spf[i]=2;
        }
        for(int i=3;i*i<=maxn;i+=2)
            if(spf[i]==i) 
                for(int j=i*i;j<=maxn;j+=i)
                    if(spf[j]==j)
                        spf[j]=i;
        return spf;
    }
    //prime factorization with their count -> O(logn)
    HashMap<Integer,Integer> primeFact_spf(int n) {
        HashMap<Integer,Integer> m=new HashMap<>();
        while (n!=1) {
            m.put(spf[n],m.getOrDefault(spf[n],0)+1);
            n/=spf[n];
        }
        return m;
    }
    // Chinese remainder theorem to find 'x' such that x%num[i]=rem[i] for all i -> O(n)
    long CRT(int num[],int rem[]) {
        long prod=1,res=0,n=num.length;
        for(int i=0;i<n;i++) prod*=num[i];
        for(int i=0;i<n;i++) {
            long pp=prod/num[i];
            res+=rem[i]*modInv(pp,num[i])*pp;
        }
        return res%prod;
    }
    //modular inverse using extended euclidean
    // modular inverse of n or n^-1 is an integer such that (n*modInv(n))%p = 1
    long modInv(long a,int m) {
        long xy[]=new long[2];
        long g=extendedEuclidean(a,m,xy);
        if(g!=1) throw new RuntimeException("No modular inverse");
        return (xy[0]%m+m)%m;
    }
    //soln of a*x+b*y=gcd(a,b) -> O(log(min(a,b)))
    long extendedEuclidean(long a,long b,long[] xy) {
        long x=1,y=0; //soln of Linear Diophantine Equations
        long x1=0,y1=1;
        long tmp,q;
        while(b!=0) {
            q=a/b;
            tmp=x;x=x1;x1=tmp-q*x1;
            tmp=y;y=y1;y1=tmp-q*y1;
            tmp=a;a=b;b=tmp-q*b;
        }
        xy[0]=x; xy[1]=y; //soln
        return a; //gcd of a,b
    }
    //a^b % m -> O(logb)
    long powmod(long a,long b,int m) {
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
}
