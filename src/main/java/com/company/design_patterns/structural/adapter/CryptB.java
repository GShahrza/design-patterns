package com.company.design_patterns.structural.adapter;

public class CryptB implements Crypt{

    public void encrypt(String text) {

        System.out.println("#CryptB#encrypt()");
    }

    public void decrypt(String text) {

        System.out.println("#CryptB#decrypt()");
    }
}
