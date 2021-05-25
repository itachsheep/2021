/**
 * @ClassName: Test
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mjnindk;

public class Test {
    private volatile static Test sTest;

    private Test() {

    }

    public static Test getInstance() {
        if(sTest == null) {
            synchronized (Test.class) {
                if(sTest == null) {
                    sTest = new Test();
                }
            }
        }
        return sTest;
    }

    public String bigIntegerMulti(String num1, String num2) {

        int n1 = num1.length();
        int n2 = num2.length();
        int[] res = new int[n1 + n2];
        int carry = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                int a1 = num1.indexOf(n1 - i) - '0';
                int a2 = num2.indexOf(n2 -j) - '0';
                int sum = a1 * a2 + carry;

                res[i + j] += sum % 10;
                if(res[i + j] > 10) {
                    res[i+j] %= 10;
                    res[i + j + 1] += 1;
                }

                if(sum > 10) {
                    res[i + j + 1] += (sum / 10);
                }

                if(res[i + j + 1] > 10) {
                    res[i + j + 1] %= 10;
                    carry = res[i + j + 1] / 10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = res.length -1 ; i >= 0; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

}
