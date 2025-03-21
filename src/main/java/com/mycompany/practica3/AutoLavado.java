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
    private int horas;
    
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
        
//        if(horas >= 2){
//            System.out.println("Se acabo el tiempo");
//            return;
//        }
        
        while(horas < 2){
        
            int tiempoNuevoVehiculo = rand.nextInt(8000 - 2000 + 1) + 2000;
            //Thread autoLavadoHilo = new Thread(() -> {

                Thread llegadaVehiculosHilo = new Thread(() -> {  
                    try{
                        while(!acceso.colaLlena()){
                            Thread.sleep(tiempoNuevoVehiculo);
                            Vehiculo vehiculoNuevo = vehiculoRandom();
                            acceso.agregarVehiculo(vehiculoNuevo);
                            System.out.println("Vehiculo Nuevo:" + vehiculoNuevo.toString());
                        }
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                });

                Thread lavadoHilo = new Thread(() -> {
                    try{
                        //if(!acceso.colaVacia() ){
                            while(!maquinaLavado.colaLlena() && !acceso.colaVacia()){
                                Vehiculo vehiculoAEliminar = acceso.eliminarVehiculo();
                                if(vehiculoAEliminar != null){
                                    maquinaLavado.agregarVehiculo(vehiculoAEliminar);
                                    System.out.println("Se ha agregado:" + maquinaLavado.peek());
                                    Thread.sleep(3000);
                                }
                            }
                        //}
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                });

                Thread secadoHilo = new Thread(() -> {
                    try{
                        while(!mejorColaSecado().colaLlena() && !lineaSecadoExpress.colaLlena()){

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
                                        Vehiculo vehiculoTerminado = lineaSecadoExpress.eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado.toString());
                                    }
                                } else{
                                    mejorColaSecado().agregarVehiculo(vehiculoLavado);
                                    if(vehiculoLavado.getTamano().equals("Pequeno")){
                                        Thread.sleep(5000);
                                        Vehiculo vehiculoTerminado = mejorColaSecado().eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado.toString());
                                    } else if(vehiculoLavado.getTamano().equals("Mediano")){
                                        Thread.sleep(7000);
                                        Vehiculo vehiculoTerminado = mejorColaSecado().eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado.toString());
                                    } else{
                                        Thread.sleep(10000);
                                        Vehiculo vehiculoTerminado = mejorColaSecado().eliminarVehiculo();
                                        System.out.println("Listo: " + vehiculoTerminado.toString());
                                    }
                                }
                            }
                        }
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                });

                llegadaVehiculosHilo.start();
                lavadoHilo.start();
                secadoHilo.start();

                try{
                    Thread.sleep(60000);
                    horas++;
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        //});  
        //autoLavadoHilo.start();
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
}