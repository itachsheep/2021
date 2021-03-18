/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import java.util.HashSet;

public class _4_longest_no_repeat_substring {
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";

        System.out.println(longestNoRepeatString(s1));
        System.out.println(longestNoRepeatString(s2));
        System.out.println(longestNoRepeatString(s3));
    }

    public static String longestNoRepeatString(String s) {
        if(s == null) {
            return "";
        }
        int i = 0, j = 0, length = s.length();
        HashSet<Character> set = new HashSet<>();
        String res = "";
        while (i < length && j < length) {
            char c = s.charAt(j);
            if(!set.contains(c)) {
                set.add(c);
                j++;
                if(j - i + 1 > res.length()) {
                    res = s.substring(i,j);
                }
            } else {
                set.remove(c);
                i++;
            }
        }
        return res;
    }
}
