package com.mycompany.practica3;

public class Vehiculo implements Comparable<Vehiculo>{
    private String tamano;
    private String servicioSolicitado;
    private boolean preferente;
    private String marca;
    private String modelo;
    private String color;
    
    public Vehiculo(String tamano, String servicioSolicitado, boolean preferente, String marca, String modelo, String color){
        this.tamano = tamano;
        this.servicioSolicitado = servicioSolicitado;
        this.preferente = preferente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
    
    public Vehiculo(){
        
    }

    public String getTamano() {
        return tamano;
    }

    public String getServicioSolicitado() {
        return servicioSolicitado;
    }

    public boolean getPreferente() {
        return preferente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }
    
    @Override
    public String toString(){
        return "Tamano:" + tamano + " Servicio:" + servicioSolicitado + " Preferente:" + preferente + " Marca:" + marca + " Modelo:" + modelo + " Color:" + color;
    }
    
    @Override
    public int compareTo(Vehiculo otro){
        if (this.preferente && !otro.preferente) {
            return -1; 
        } else if (!this.preferente && otro.preferente) {
            return 1; 
        } else {
            return 0; 
        }
    }
}
