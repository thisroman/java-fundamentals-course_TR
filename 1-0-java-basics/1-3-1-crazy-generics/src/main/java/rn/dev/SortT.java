package rn.dev;

import javax.print.attribute.IntegerSyntax;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortT {

    public static void main(String[] args) {

        var arr = generateArray(10);
        System.out.println(Arrays.toString(arr));
        var res = sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] arr) {
        var n = arr.length;

        var element = arr[0];
        for (int curr = 0; curr < n; curr++) {
           compare(curr, arr);
        }
        return arr;
    }

    private static void compare(int curr, int[] arr) {
        var cr = arr[curr];
        var n = curr;
        while (n > 0){
            if (cr < arr[n - 1]){
                arr[n] = arr[n - 1];
            } else {
                break;
            }
            n--;
        }
        arr[n] = cr;
    }

    private static int[] generateArray(int n) {
        var res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = ThreadLocalRandom.current().nextInt(0, 100);//Integer.MAX_VALUE);
        }

        return res;
    }



}
