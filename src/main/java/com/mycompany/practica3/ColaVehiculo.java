package com.mycompany.practica3;
import java.util.*;

public class ColaVehiculo {
    private Vehiculo[] vehiculos;
    private Queue<Vehiculo> prioridadVehiculos;
    private int inicio;
    private int fin;
    private int max;
    private boolean prioridad;
    
    public ColaVehiculo(int max, boolean prioridad){
        this.prioridad = prioridad;
        this.max = max;
        if(!prioridad){
            vehiculos = new Vehiculo[max];
            inicio = -1;
            fin = -1;
        } else{
            prioridadVehiculos = new PriorityQueue<>();
        }
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        if(colaLlena()){
            System.out.println("Desbordamiento lol");
            return;
        }
        if(prioridad){
            prioridadVehiculos.add(vehiculo);
        } else{
            if(fin < max - 1){
                fin++;
                vehiculos[fin] = vehiculo;
                if(inicio == -1){
                    inicio = 0;
                }
            } else{
                System.out.println("Desbordamiento");
            }
        }
    }
    
    public Vehiculo eliminarVehiculo(){
        Vehiculo vehiculo = null;
        if(prioridad){
            return prioridadVehiculos.poll();
        } else{
            if(!colaVacia()){
                vehiculo = vehiculos[inicio];
                if(inicio == fin){
                    inicio = -1;
                    fin = -1;
                } else{
                    inicio++;
                }
            } else{
                System.out.println("Subdesbordamiento");
            }
            return vehiculo;
        }
    }
    
    public boolean colaLlena(){
        return prioridad ? prioridadVehiculos.size() == max : fin + 1 == max;
    }
    
    public boolean colaVacia(){
        return prioridad ? prioridadVehiculos.isEmpty() : inicio == -1;
    }
    
    public int getSize(){
        if (prioridad){
            return prioridadVehiculos.size();
        } else{
            return colaVacia() ? 0 : (fin - inicio + 1);
        }
    }
    
    public void mostrarCola(){
//        if(inicio == -1){
//            System.out.println("Error al mostrar la cola");
//        } else{
//            for(int i=inicio; i<=fin; i++){
//                System.out.print(vehiculos[i].toString());
//            }
//            System.out.println();
//        }
        
        if(prioridad){
            while(!prioridadVehiculos.isEmpty()){
                System.out.println(prioridadVehiculos.poll());
            }
        }
    }
    
    
    public Vehiculo peek(){
        if(prioridad){
            return prioridadVehiculos.peek();
        } else{
            if(colaVacia()){
                return null;
            }  
            return vehiculos[inicio];
        }
    }
    
    public Vehiculo getVehiculo(int index){
    if(prioridad){
        List<Vehiculo> lista = new ArrayList<>(prioridadVehiculos);
        return index < lista.size() ? lista.get(index) : null;
    } else {
        return index < (fin - inicio + 1) ? vehiculos[inicio + index] : null;
    }
}
}
