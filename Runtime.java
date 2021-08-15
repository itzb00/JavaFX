import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;

public class Runtime {
    public static void main(String[] args) {
        int[] nums1 = getRandomNumberArray(20000);
        int[] nums2 = getRandomNumberArray(20000);
        long start = System.currentTimeMillis();
        ArrayList<Integer> commons = getCommonElements(nums1, nums2);
        long end = System.currentTimeMillis();
        System.out.println("time taken was " + (end - start) + " ms");
        System.out.println(nums1.length);
        System.out.println(nums2.length);
        System.out.println("number of common elements is: " + commons.size());
    }

    private static int[] getRandomNumberArray(int size) {
        int[] num = new int[size];
        for (int i = 0; i < size; i++) {
            num[i] = RandomUtils.nextInt(size);
        }
        return num;
    }


    public static ArrayList<Integer> getCommonElements(int[] nums1, int[] nums2) {
        ArrayList<Integer> commons = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j] && !commons.contains(nums1[i])) {
                    commons.add(nums1[i]);
                }
            }
        }
        return commons;
    }
}


