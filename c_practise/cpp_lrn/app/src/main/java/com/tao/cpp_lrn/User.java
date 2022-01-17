/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.cpp_lrn;

public class User {
    private String name;
    private String age;

    public User(String name,String age) {
        this.name = name;
        this.age = age;
    }

    public String getResult() {
        return String.format("%1$s-=-%2$s", name, age);
    }
}
