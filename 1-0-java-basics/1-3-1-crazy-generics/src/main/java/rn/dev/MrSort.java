package rn.dev;

import java.util.Arrays;

public class MrSort {

    public static void main(String[] args) {
        int[] input = new int[]{1111, 11, 34, 6, 77, 32, 8, 0, 76, 5, 3, 3, 43, 45, 66, -6};
        System.out.println(Arrays.toString(mergeSort(input)));
    }

    private static int[] mergeSort(int[] input) {
        int len = input.length;
        if (len == 1) {
            return input;
        }
        int[] aPart = mergeSort(Arrays.copyOf(input, len / 2));
        int[] bPart = mergeSort(Arrays.copyOfRange(input, len / 2, len));
        return merge(aPart, bPart);
    }

    private static int[] merge(int[] aPart, int[] bPart) {
        int[] result = new int[aPart.length + bPart.length];
        int posA = 0;
        int posB = 0;
        int lastResult;
        if (aPart[0] < bPart[0]) {
            lastResult = aPart[0];
            posA++;
        } else {
            lastResult = bPart[0];
            posB++;
        }
        int i = 1;
        result[0] = lastResult;
        while (posA < aPart.length && posB < bPart.length) {
            int aEl = aPart[posA];
            int bEl = bPart[posB];
            if (aEl < bEl) {
                result[i] = aEl;
                posA++;
            } else {
                result[i] = bEl;
                posB++;
            }
            i++;
        }
        while (posA < aPart.length) {
            result[i] = aPart[posA];
            i++;
            posA++;
        }
        while (posB < bPart.length) {
            result[i] = bPart[posB];
            i++;
            posB++;
        }

        return result;
    }
}
