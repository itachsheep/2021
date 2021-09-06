/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import java.util.HashSet;
import java.util.Set;

public class _4_longest_no_repeat_substring {
    public static void main(String[] args) {
        String s1 = "abcbdebb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";

        System.out.println(lengthOfLongestSubstring2(s1));
        System.out.println(lengthOfLongestSubstring2(s2));
        System.out.println(lengthOfLongestSubstring2(s3));
    }



    public static String lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        String res = "";
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            if(rk - i + 1 > ans) {
                res = s.substring(i,rk+1);
            }
            ans = Math.max(ans, rk - i + 1);
        }
        System.out.println("res: " + res + ", ans: " + ans);
        return res;
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
