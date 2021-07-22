/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

import java.util.HashSet;

class _longest_substring_without_repeat_characters {
    public static void main(String[] args) {

    }

    private static int lengthOfLongestSubstring(String s){
        int n = s.length();
        int i = 0, j = 0;
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        while (i < n && j < n) {
            char c = s.charAt(i);
            if(set.contains(c)) {
                set.remove(s.charAt(j));
                j++;
            } else {
                set.add(c);
                i++;
                max = Math.max(max,set.size());
            }
        }
        return max;
    }
}
