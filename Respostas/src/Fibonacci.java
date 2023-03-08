import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int num = sc.nextInt();

        int fib1 = 0;
        int fib2 = 1;
        int fib = fib1 + fib2;

        while (fib < num) {
            fib1 = fib2;
            fib2 = fib;
            fib = fib1 + fib2;
        }

        if (fib == num) {
            System.out.println(num + " pertence à sequência de Fibonacci");
        } else {
            System.out.println(num + " não pertence à sequência de Fibonacci");
        }

        sc.close();
    }
}
