package ECC;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class EC_Elgamal_Exchange_Key {
    public static void main(String[] args) {
        // chon duong cong ELiptic
        BigInteger p = new BigInteger("269068799397132727202882133496946646815733655727593238307263899291678185520300020344358946667029659366165859460901389056148984122856261675884176207652755403345305759578455378275368738922600884742392847667124496532780326861246126107720205589429864059384318699363647832473542296122760695370255014084297");
        FinitePrimeField finitePrimeField = new FinitePrimeField(p);
        PrimeCurve primeCurve = new PrimeCurve(finitePrimeField, BigInteger.ONE, BigInteger.ONE, 1);
        primeCurve.print();
        // Chon diem P
        Random random = new Random();
        int r = random.nextInt(1,1000);
        Point P = primeCurve.getPoint(r);
        while (!P.isValid()) {
            r = random.nextInt(1,1000);
            P = primeCurve.getPoint(r);
        }
        System.out.println(P.toString());
        Scanner scanner = new Scanner(System.in);
        // A chooses dA and caculates bA = P * dA and sends bA to B
        System.out.println("A choose a secret number dA = ");
        BigInteger dA = new BigInteger(scanner.next());
        Point bA = primeCurve.mul(P, dA);
        // B chooses dB and calculates bB = P * dB and sends bB to A
        System.out.println("B choose a secret number dB = ");
        BigInteger dB = new BigInteger(scanner.next());
        Point bB = primeCurve.mul(P,dB);
        // KAB

        Point KAB = primeCurve.mul(bB,dA);
        Point t = primeCurve.mul(bA,dB);
        System.out.println(KAB.toString());
        System.out.println(t.toString());


    }
}
