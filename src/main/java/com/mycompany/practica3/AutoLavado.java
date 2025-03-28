package com.mycompany.practica3;

import java.util.Random;

public class AutoLavado implements Runnable{
    private ColaVehiculo acceso;
    private ColaVehiculo maquinaLavado;
    private ColaVehiculo lineaAspirado1;
    private ColaVehiculo lineaAspirado2;
    private ColaVehiculo lineaAspirado3;
    private ColaVehiculo lineaAspirado4; 
    private ColaVehiculo lineaSecadoExpress;
    private volatile float horas;
    
    public AutoLavado(){
        acceso = new ColaVehiculo(10, true);
        maquinaLavado = new ColaVehiculo(3, false);
        lineaAspirado1 = new ColaVehiculo(4, false);
        lineaAspirado2 = new ColaVehiculo(4, false);
        lineaAspirado3 = new ColaVehiculo(4, false);
        lineaAspirado4 = new ColaVehiculo(4, false);
        lineaSecadoExpress = new ColaVehiculo(5, false);
        horas = 0;
    }
    
    @Override
    public void run(){
        Random rand = new Random();
        
            Thread llegadaVehiculosHilo = new Thread(() -> {  
                try{
                    while(horas < 1.75){
                        if(!acceso.colaLlena()){
                            int tiempoNuevoVehiculo = rand.nextInt(8000 - 2000 + 1) + 2000;
                            Thread.sleep(tiempoNuevoVehiculo);
                            if(horas >= 1.75){ 
                                System.out.println("Cerrado");
                                break;
                            }
                            Vehiculo vehiculoNuevo = vehiculoRandom();
                            acceso.agregarVehiculo(vehiculoNuevo);
                            System.out.println("Vehiculo Nuevo:" + vehiculoNuevo.toString());
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });

            Thread lavadoHilo = new Thread(() -> {
                try{
                    while(true){
                        if(!maquinaLavado.colaLlena() && !acceso.colaVacia()){
                            Vehiculo vehiculoAEliminar = acceso.eliminarVehiculo();
                            if(vehiculoAEliminar != null){
                                maquinaLavado.agregarVehiculo(vehiculoAEliminar);
                                System.out.println("Se ha agregado:" + vehiculoAEliminar.toString());
                                Thread.sleep(3000);
                            }
                        } else{
                            Thread.sleep(500);
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });

            Thread secadoHilo = new Thread(() -> {
                try{
                    while(true){
                        if(!mejorColaSecado().colaLlena() && !lineaSecadoExpress.colaLlena()){
                            //System.out.println("Mejor cola actual:" + mejorColaSecado().toString());
                            Thread.sleep(10);
                            if(!maquinaLavado.colaVacia()){
                                Vehiculo vehiculoLavado = maquinaLavado.eliminarVehiculo();

                                if (vehiculoLavado == null) { 
                                    System.out.println("No hay vehiculos en la maquina de lavado");
                                    Thread.sleep(1000); 
                                    continue;
                                }

                                if(vehiculoLavado.getPreferente()){
                                    lineaSecadoExpress.agregarVehiculo(vehiculoLavado);
                                    Thread.sleep(5000);
                                    if(!lineaSecadoExpress.colaVacia()){
                                        Vehiculo vehiculoTerminado1 = lineaSecadoExpress.eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado1.toString());
                                    }
                                } else{
                                    ColaVehiculo mejorColaSecadoCarro = mejorColaSecado();
                                    mejorColaSecadoCarro.agregarVehiculo(vehiculoLavado);
                                    if(vehiculoLavado.getTamano().equals("Pequeno")){
                                        Thread.sleep(5000);
                                        Vehiculo vehiculoTerminado2 = mejorColaSecadoCarro.eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado2.toString());
                                    } else if(vehiculoLavado.getTamano().equals("Mediano")){
                                        Thread.sleep(7000);
                                        Vehiculo vehiculoTerminado3 = mejorColaSecadoCarro.eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado3.toString());
                                    } else{
                                        Thread.sleep(10000);
                                        Vehiculo vehiculoTerminado4 = mejorColaSecadoCarro.eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado4.toString());
                                    }
                                }
                            }
                        } else{
                            Thread.sleep(1000);
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
        
        Thread autoLavadoHilo = new Thread(() -> {
            try{
                while(horas < 2){
                    Thread.sleep(15000);
                    horas += 15.0/60;
                    System.out.println("Horas transcurridas:" + horas);
                }
                
                llegadaVehiculosHilo.interrupt();
                lavadoHilo.interrupt();
                secadoHilo.interrupt();
                
            } catch(InterruptedException e) {
                e.printStackTrace();
            }  
        });
        
        llegadaVehiculosHilo.start();
        lavadoHilo.start();
        secadoHilo.start();
        
        autoLavadoHilo.start();
    }
    
    public Vehiculo vehiculoRandom(){
        Random rand = new Random();
        
        double probabilidadPequeno = 0.50;
        double probabilidadMediano = 0.35;
        
        double probabilidad = rand.nextDouble();
        String tamanoSeleccionado;
        if(probabilidad < probabilidadPequeno){
            tamanoSeleccionado = "Pequeno";
        } else if(probabilidad < probabilidadPequeno + probabilidadMediano){
            tamanoSeleccionado = "Mediano";
        } else{
            tamanoSeleccionado = "Grande";
        }
        
        String[] servicioSolicitado = {"Aspirado", "Aspirado", "Aspirado", "Aspirado", "Aspirado", "Aspirado", "Aspirado" ,"Aspirado", "Secado Express", "Secado Express"};
        String[] marcas = {"Nissan", "Toyota", "Mercedes", "Honda", "Jeep", "Ford", "Kia", "Subaru", "Chevrolet", "Ferrari"}; 
        String[] modelo = {"Altima", "Corolla", "C-Class", "Civic", "Wrangler", "Mustang", "Sportage", "Wrx", "Camaro", "F40"};
        String[] color = {"Rojo", "Negro", "Blanco", "Azul", "Gris"};
        
        int servicioEleccion = rand.nextInt(servicioSolicitado.length);
        int marcaModelo = rand.nextInt(10);
        
        return new Vehiculo(tamanoSeleccionado, servicioSolicitado[servicioEleccion], rand.nextBoolean(), marcas[marcaModelo], modelo[marcaModelo], color[rand.nextInt(5)]);
    }
    
    public ColaVehiculo mejorColaSecado(){
        ColaVehiculo mejorCola = lineaAspirado1;
        
        if(lineaAspirado2.getSize() < mejorCola.getSize()){
            mejorCola = lineaAspirado2;
        } else if(lineaAspirado3.getSize() < mejorCola.getSize()){
            mejorCola = lineaAspirado3;
        } else if(lineaAspirado4.getSize() < mejorCola.getSize()){
            mejorCola = lineaAspirado4;
        }
        
        return mejorCola;
    } 
    
    public ColaVehiculo getAcceso(){
        return acceso;
    }
    
    public ColaVehiculo getMaquinaLavado(){
        return maquinaLavado;
    }
    
    public ColaVehiculo getLineaSecadoExpress(){
        return lineaSecadoExpress;
    } 
    
    public float getHoras(){
        return horas;
    }
}