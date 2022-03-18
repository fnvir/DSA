public class Main {
    public static void main(String[] args) {
        iostream io=new iostream();
        
        io.close();
    }
    static class iostream {
        private byte inBuff[];
        private int inSize,nums, outSize,outChars;
        private java.io.InputStream instream;
        private java.io.Writer outStream;
        private char[] outbuff;
        public iostream() {
            instream=System.in;
            outStream=new java.io.OutputStreamWriter(System.out);
            initialize();
        }
//        public iostream(String in,String out) throws Exception {
//            instream=new java.io.FileInputStream(in);
//            outStream=new java.io.OutputStreamWriter(new java.io.FileOutputStream(out));
//            initialize();
//        }
        private void initialize() {
            inBuff=new byte[4096];
            outbuff=new char[outChars=8192];
        }
        private boolean isInvalid(int c) {
            return c<=' ';// c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;
        }
        private int read() {
//            if(nums==-1) throw new RuntimeException("Input mismatch");
            if (inSize>=nums) {
                inSize=0;
                try {nums=instream.read(inBuff);}
                catch (Throwable e) {e.printStackTrace();}
                if (nums<=0) return -1;
            }
            return inBuff[inSize++];
        }
        void print(char c)  {
            if (outSize>=outChars)
                try {
                    flushBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            outbuff[outSize++]=c;
        }
        void print(String str) {
            try {
                write(str,0,str.length());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        void println(char c[]) {
            try {
                write(c,0,c.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            print('\n'); //Better to use System.lineSeparator()
        }
        void println(Object o) {
            print(String.valueOf(o));
            print('\n');
        }
        private void write(char cbuf[], int start, int len) throws Exception {
            if(len>=outChars) {
                flushBuffer();
                outStream.write(cbuf, start, len);
                return;
            }
            int b=start,t=start+len;
            while (b<t) {
                int d=outChars-outSize;
                if(d>t-b)d=t-b;
                System.arraycopy(cbuf,b,outbuff,outSize,d);
                b+=d;
                outSize+=d;
                if (outSize>=outChars) flushBuffer();
            }
        }
        int nextInt() {
            int x=0,p=1,c=nextChar();if(c=='-'){p=-1;c=read();}
            for(;c>='0'&&c<='9';c=read())x=x*10+c-'0'; return x*p;
        }
        char nextChar() {
            int c=read(); while(c<=32)c=read();
            return (char)c;
        }
        long nextLong() {
            long x=0,p=1,c=nextChar();if(c=='-'){p=-1;c=read();}
            for(;c>='0'&&c<='9';c=read())x=(x<<3)+(x<<1)+c-'0';return x*p;
        }
        private String nextStr(boolean spaces) {
            StringBuilder s=new StringBuilder();
            for(int c=nextChar();c>32-(spaces?1:0);c=read())s.append((char)c);
            return s.toString();
        }
        String next() {return nextStr(false);}
        String nextLine() {return nextStr(true);}
        double nextDouble() {return Double.parseDouble(next());}
        double nextDouble1() {
            int p=1,c=nextChar();if(c=='-'){p=-1;c=read();}
            double x=0.0;
            while(c>32&&c!='.') {
//                if(c<'0'||c>'9') throw new RuntimeException("Input mismatch");
                x=x*10+c-'0';
                c=read();
            }
            if (c=='.') {
                double i=1;
                for(c=read();!isInvalid(c);c=read()) {
//                    if (c<'0'||c>'9') throw new RuntimeException("Input mismatch");
                    x+=(c-'0')*(i/=10);
                }
            }
            return x*p;
        }
//        BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
        private void write(String s, int off, int len) throws Exception {
            int b = off, t = off + len;
            while (b < t) {
                int d=outChars-outSize;
                if(d>t-b)d=t-b;
                s.getChars(b,b+d,outbuff,outSize);
                b+=d;
                outSize+=d;
                if (outSize>=outChars) flushBuffer();
            }
        }
        private void flushBuffer() throws Exception {
            if(outSize == 0) return;
            outStream.write(outbuff,0,outSize);
            outSize=0;
        }
        void flush() {
            try {
                flushBuffer();
                outStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void close() {
            if (outStream==null) return;
            try (java.io.Writer w=outStream) {
                flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                outStream=null;
                outbuff=null;
            }
        }
        int[] nextIntArr(int n) {
            int a[]=new int[n];
            for(int i=0;i<n;i++) a[i]=nextInt();
            return a;
        }
    }
}
