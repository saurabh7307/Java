public class getMultipleOfXandYTillN {
    public static int getSomeOfMultiple(int var0, int var1, int var2) {
        int var3 = 0;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var4 % var0 == 0 || var4 % var1 == 0) {
                var3 += var4;
            }
        }

        return var3;
    }

    public static void main(String[] var0) {
        int var1 = getSomeOfMultiple(3, 5, 1000);
        System.out.println(var1);
    }
}
