
import java.math.*;
import java.util.Scanner;
public class RSA{
    static BigInteger phi,N,e,d,x,y,M,C,p,q,s;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter two relative prime numbers");
        System.out.println("enter p:");
        p = sc.nextBigInteger();
        System.out.println("enter q:");
        q = sc.nextBigInteger();
        N = p.multiply(q);
        System.out.println("N = p+q..N = "+N);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("phi = (p-1)(q-1)...phi = "+phi);
        System.out.println("\n\nenter e:");
        e = sc.nextBigInteger();
        d = e.modInverse(phi);
        System.out.println("d is "+d);
        System.out.println("\n\tpublic key\t:"+e+" "+N);
        System.out.println("\n\tprivate key\t:"+d+" "+N);
        System.out.println("\n\nenter the plain text \t");
        M = sc.nextBigInteger();
        x=encrypt(M);
        System.out.println("encrypted messege is "+x);
        System.out.println("\n\nenter the cipher text\t");
        C = sc.nextBigInteger();
        y = decrypt(C);
        System.out.println("decrypted messege is "+y);
        sc.close();
    }
    public static BigInteger encrypt(BigInteger M){
        return M.modPow(e,N);
    }
    public static BigInteger decrypt(BigInteger C){
        return C.modPow(d,N);
    }
}