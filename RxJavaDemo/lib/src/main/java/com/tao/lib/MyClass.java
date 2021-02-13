package com.tao.lib;

public class MyClass {
    public static void main(String[] args) {
        ClassLoader loader = MyClass.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);//1
            loader = loader.getParent();
        }
    }
}