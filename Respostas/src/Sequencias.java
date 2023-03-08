public class Sequencias {

    public static void main(String[] args) {
        
        // Sequência a)
        System.out.print("Sequência a: ");
        for(int i = 1; i < 10; i+=2) {
            System.out.print(i + ", ");
        }
        System.out.println();
        
        // Sequência b)
        System.out.print("Sequência b: ");
        int num = 2;
        for(int i = 0; i < 7; i++) {
            System.out.print(num + ", ");
            num *= 2;
        }
        System.out.println();
        
        // Sequência c)
        System.out.print("Sequência c: ");
        for(int i = 0; i < 8; i++) {
            System.out.print((i*i) + ", ");
        }
        System.out.println();
        
        // Sequência d)
        System.out.print("Sequência d: ");
        for(int i = 2; i <= 10; i+=2) {
            System.out.print((i*i) + ", ");
        }
        System.out.println();
        
        // Sequência e)
        System.out.print("Sequência e: ");
        int a = 1;
        int b = 1;
        System.out.print(a + ", " + b + ", ");
        for(int i = 0; i < 5; i++) {
            int c = a + b;
            System.out.print(c + ", ");
            a = b;
            b = c;
        }
  
    }
}


