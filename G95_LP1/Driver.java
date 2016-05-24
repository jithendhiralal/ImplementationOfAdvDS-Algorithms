import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter a :");
        long n = in.nextLong();
        System.out.println("Enter b :");
        long m = in.nextLong(); 
        
        BigInt a = new BigInt(n);
        BigInt b = new BigInt(m);
        
        System.out.println("Addition of BigInt :");
        BigInt add = a.add(a, b);
        add.printList();
        System.out.println("Subtraction of BigInt:");
        BigInt sub = a.subtract(a, b);
        sub.printList();
        System.out.println("Multiplication of BigInt :");
        BigInt prod = a.product(a, b);
        prod.printList();
        System.out.println("Power of BigInt to the ^ long:");
        BigInt powerLong = a.power(a, m);
        powerLong.printList();
        System.out.println("Power of BigInt to the ^ BignInt :");
        BigInt power = a.power(a, b);
        power.printList();
        System.out.println("Division of  BigInt:");
        BigInt div = a.divide(a, b);
        div.printList();
        System.out.println("Mod of BigInt :");
        BigInt mod = a.mod(a, b);
        mod.printList();
        System.out.println("Squareroot of BigInt a :");
        BigInt square = a.squareRoot(a);
        square.printList();
        System.out.println("Squareroot of BigInt b :");
        BigInt squareRoot = a.squareRoot(b);
        squareRoot.printList();
        
        
    }

}
