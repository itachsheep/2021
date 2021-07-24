/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

class _1143_longest_common_subsequence {
    public static void main(String[] args) {

        String text1 = "abcde", text2 = "abce";
        System.out.println("res = " + longestCommonSubsequence(text1,text2));
        System.out.println("res = " + longestCommonSubString(text1,text2));
    }

    /**
     * https://leetcode-cn.com/problems/longest-common-subsequence/solution/zui-chang-gong-gong-zi-xu-lie-by-leetcod-y7u0/
     *
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < m ; i++) {
            char c1 = text1.charAt(i-1);
            for (int j = 1; j < n ; j++) {
                char c2 = text2.charAt(j-1);
                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    sb.append(c1);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        //System.out.println("sb = " + sb.toString());
        return dp[m-1][n-1];
    }

    public static String longestCommonSubString(String text1,String text2) {
        int m = text1.length(), n = text2.length();
        int max = -1;
        int[][] dp = new int[m + 1][n + 1];
        String res = "";
        for (int i = 1; i < m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j < n; j++) {
                char c2 = text2.charAt(j - 1);
                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > max) {
                        max = dp[i][j];
                        res = text1.substring(i-max,i);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }

            }
        }
        return res;
    }
}
