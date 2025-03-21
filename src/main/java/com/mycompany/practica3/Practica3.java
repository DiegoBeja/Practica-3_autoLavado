package com.mycompany.practica3;

public class Practica3 {
    public static void main(String[] args) {
        AutoLavado x = new AutoLavado();
        Thread hilo = new Thread(x);
        hilo.start();
    }
}
