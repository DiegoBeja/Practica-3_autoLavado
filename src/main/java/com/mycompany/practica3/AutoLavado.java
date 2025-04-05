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
    private float milis;
    private StringBuilder registroVehiculos;
    private float minutos;
    private int horasR;
    
    public AutoLavado(){
        acceso = new ColaVehiculo(10, true);
        maquinaLavado = new ColaVehiculo(3, false);
        lineaAspirado1 = new ColaVehiculo(4, false);
        lineaAspirado2 = new ColaVehiculo(4, false);
        lineaAspirado3 = new ColaVehiculo(4, false);
        lineaAspirado4 = new ColaVehiculo(4, false);
        lineaSecadoExpress = new ColaVehiculo(5, false);
        horas = 8;
        horasR = 0;
        minutos = 0;
        milis = 1;
        registroVehiculos = new StringBuilder();
    }
    
    @Override
    public void run(){
        Random rand = new Random();
        
            Thread llegadaVehiculosHilo = new Thread(() -> {  
                try{
                    while(true){
                        if(Thread.currentThread().isInterrupted()){
                            System.out.println("Hilo interrumpido");
                            break;
                        }
                        
                        if(!acceso.colaLlena()){
                            int tiempoNuevoVehiculo = rand.nextInt(8000 - 2000 + 1) + 2000;
                            Thread.sleep((long) (tiempoNuevoVehiculo * milis));
                            if(horas >= 9.75){ 
                                System.out.println("Cerrado");
                                break;
                            }
                            Vehiculo vehiculoNuevo = vehiculoRandom();
                            acceso.agregarVehiculo(vehiculoNuevo);
                            System.out.println("Vehiculo nuevo:" + vehiculoNuevo.toString());
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });

            Thread lavadoHilo = new Thread(() -> {
                try{
                    while(true){
                        if(Thread.currentThread().isInterrupted()){
                            System.out.println("Hilo interrumpido");
                            break;
                        }
                        
                        if(!maquinaLavado.colaLlena() && !acceso.colaVacia()){
                            Vehiculo vehiculoAEliminar = acceso.eliminarVehiculo();
                            if(vehiculoAEliminar != null){
                                maquinaLavado.agregarVehiculo(vehiculoAEliminar);
                                System.out.println("Lavando:" + vehiculoAEliminar.toString());
                                Thread.sleep((long) (3000 * milis));
                                Vehiculo vehiculoLavado = maquinaLavado.eliminarVehiculo();
                                if(vehiculoLavado.getPreferente()){
                                    lineaSecadoExpress.agregarVehiculo(vehiculoLavado);
                                    System.out.println("Secado Express");
                                } else{
                                    ColaVehiculo mejorColaSecadoCarro = mejorColaSecado();
                                    mejorColaSecadoCarro.agregarVehiculo(vehiculoLavado);
                                    System.out.println("Vehiculo lavado");
                                }
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
                        if(Thread.currentThread().isInterrupted()){
                            System.out.println("Hilo interrumpido");
                            break;
                        }
                        
                        if(lineaSecadoExpress.getSize() > 0){
                            Thread.sleep((long) (3000 * milis));
                            Vehiculo vehiculoTerminado = lineaSecadoExpress.eliminarVehiculo();
                            System.out.println("Listo: " + vehiculoTerminado);
                            registroVehiculos.append("Vehiculo secado Express: ").append(vehiculoTerminado.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                        }
                        
                        if(lineaAspirado1.getSize() > 0){
                            if(lineaAspirado1.peek().getTamano().equals("Pequeno")){
                                Thread.sleep((long) (5000 * milis));
                                Vehiculo vehiculoTerminado2 = lineaAspirado1.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado2.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 1: ").append(vehiculoTerminado2.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else if(lineaAspirado1.peek().getTamano().equals("Mediano")){
                                Thread.sleep((long) (7000 * milis));
                                Vehiculo vehiculoTerminado3 = lineaAspirado1.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado3.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 1: ").append(vehiculoTerminado3.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else{
                                Thread.sleep((long) (10000 * milis * 2));
                                Vehiculo vehiculoTerminado4 = lineaAspirado1.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado4.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 1: ").append(vehiculoTerminado4.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            }
                        }
                        
                        if(lineaAspirado2.getSize() > 0){
                            if(lineaAspirado2.peek().getTamano().equals("Pequeno")){
                                Thread.sleep((long) (5000 * milis));
                                Vehiculo vehiculoTerminado2 = lineaAspirado2.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado2.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 2: ").append(vehiculoTerminado2.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else if(lineaAspirado2.peek().getTamano().equals("Mediano")){
                                Thread.sleep((long) (7000 * milis));
                                Vehiculo vehiculoTerminado3 = lineaAspirado2.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado3.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 2: ").append(vehiculoTerminado3.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else{
                                Thread.sleep((long) (10000 * milis));
                                Vehiculo vehiculoTerminado4 = lineaAspirado2.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado4.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 2: ").append(vehiculoTerminado4.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            }
                        }
                        
                        if(lineaAspirado3.getSize() > 0){
                            if(lineaAspirado3.peek().getTamano().equals("Pequeno")){
                                Thread.sleep((long) (5000 * milis));
                                Vehiculo vehiculoTerminado2 = lineaAspirado3.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado2.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 3: ").append(vehiculoTerminado2.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else if(lineaAspirado3.peek().getTamano().equals("Mediano")){
                                Thread.sleep((long) (7000 * milis));
                                Vehiculo vehiculoTerminado3 = lineaAspirado3.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado3.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 3: ").append(vehiculoTerminado3.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else{
                                Thread.sleep((long) (10000 * milis));
                                Vehiculo vehiculoTerminado4 = lineaAspirado3.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado4.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 3: ").append(vehiculoTerminado4.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            }
                        }
                        
                        if(lineaAspirado4.getSize() > 0){
                            if(lineaAspirado4.peek().getTamano().equals("Pequeno")){
                                Thread.sleep((long) (5000 * milis));
                                Vehiculo vehiculoTerminado2 = lineaAspirado4.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado2.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 4: ").append(vehiculoTerminado2.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else if(lineaAspirado4.peek().getTamano().equals("Mediano")){
                                Thread.sleep((long) (7000 * milis));
                                Vehiculo vehiculoTerminado3 = lineaAspirado4.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado3.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 4: ").append(vehiculoTerminado3.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            } else{
                                Thread.sleep((long) (10000 * milis));
                                Vehiculo vehiculoTerminado4 = lineaAspirado4.eliminarVehiculo();
                                System.out.println("Listo: " + vehiculoTerminado4.toString());
                                registroVehiculos.append("Vehiculo aspirado linea 4: ").append(vehiculoTerminado4.toString()).append(" Hora: ").append(formatearHora()).append("\n");
                            }
                        }
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
        
        Thread autoLavadoHilo = new Thread(() -> {
            try{
                while(horas < 10){
                    Thread.sleep((long) (15000 * milis));
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
    
    public String formatearHora(){
        int horasEnteras = (int) horas;  
        int minutosEnteros = (int) ((horas - horasEnteras) * 60);  
        return String.format("%02d:%02d", horasEnteras, minutosEnteros); 
    }
    
    public String getRegistro(){
        return registroVehiculos.toString();
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
    
    public void setMilis(float milis){
        this.milis = milis;
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
    
    public ColaVehiculo getLineaAspirado1(){
        return lineaAspirado1;
    }
    
    public ColaVehiculo getLineaAspirado2(){
        return lineaAspirado2;
    }
    
    public ColaVehiculo getLineaAspirado3(){
        return lineaAspirado3;
    }
    
    public ColaVehiculo getLineaAspirado4(){
        return lineaAspirado4;
    }
}