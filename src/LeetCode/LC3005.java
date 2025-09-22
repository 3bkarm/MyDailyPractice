package LeetCode;

import java.util.Arrays;

public class LC3005 {

    // https://leetcode.com/problems/count-elements-with-maximum-frequency?envType=daily-question&envId=2025-09-22

    public int maxFrequencyElements(int[] nums) {
        Arrays.sort(nums);
        int maxFrequency = 0, count = 0;
        for (int i = 0, frequency = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                ++frequency;
            } else {
                frequency = 1;
            }
            if (maxFrequency < frequency) {
                maxFrequency = frequency;
                count = 1;
            } else if (maxFrequency == frequency) {
                ++count;
            }
        }
        return maxFrequency * count;
    }

}
