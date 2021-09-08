/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 
 * 也不能直接将输入的字符串转换为整数形式。
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 */
class _7_integer_string_add {
    public static void main(String[] args) {
        System.out.println(addStrings("11","123"));
        System.out.println(addStrings("456","77"));
        System.out.println(addStrings("89899","9945489"));
        System.out.println(addStrings("0","0"));
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuffer res = new StringBuffer();
        int add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = a + b + add;
            res.append(temp % 10);
            add = temp / 10;
            i--;
            j--;
        }
        res.reverse();
        return res.toString();
    }
}
