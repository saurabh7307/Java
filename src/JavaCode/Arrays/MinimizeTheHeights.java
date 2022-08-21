package src.JavaCode.Arrays;

import java.util.Arrays;

public class MinimizeTheHeights {
    public static int getMinimizeDiff(int[] array, int k) {
        int arrayLength = array.length;

        if (arrayLength == 1) return 0; // nothing to do if length =1
        Arrays.sort(array);  // sorting the array here.
        int difference = array[arrayLength-1] - array[0];
        int  minimum, maximum;
        for (int i = 1; i<arrayLength; i++) {
            if (array[i]-k <0) continue; // height should not be non-negative
            maximum = Math.max(array[i-1] + k, array[arrayLength-1] - k);
            minimum = Math.min(array[0] + k, array[i]-k);
            difference = Math.min(difference, maximum-minimum);
        }
        return difference;
    }


    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 12, 13, 15, 18, 21};
        int k  = 3; // you can plus or minus in any tower's height
        System.out.println(getMinimizeDiff(array, k));
    }



}
