package com.tao.mjnindk;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);

        System.out.println(bigIntegerMulti("1111","222"));

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