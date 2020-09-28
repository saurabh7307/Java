import java.util.Scanner;

public class getMultipleOfXAndYTillN {

    public static int getSomeOfMultiple(int var0, int var1, int var2) {
        int var3 = 0;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var4 % var0 == 0 || var4 % var1 == 0) {
                var3 += var4;
            }
        }

        return var3;
    }

    // Reverse the Numer
    public static int reverseNumber(int number){
        int reversed = 0;
        while (number != 0) {
            int remainder = number % 10;
            reversed = reversed * 10 + remainder;
            number /= 10;
        }return reversed;
    }

    public static void main(String[] var0) {
        Scanner scanner = new Scanner(System.in); // System.in is a standard input stream
        System.out.println("Enter three value of x, y and n. To get multiple of x and y and till n : like 3 5 1000");
        int firstValue = scanner.nextInt();
        int secondValue = scanner.nextInt();
        int thirdValue = scanner.nextInt();
        int var1 = getSomeOfMultiple(firstValue, secondValue, thirdValue);
        System.out.println(var1);
        int reverseDigit = reverseNumber(var1);
        System.out.println("Reverse Digit : " + reverseDigit);
    }
}
