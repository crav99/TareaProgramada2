/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import SistemaAdministracionPaquetes.Tiquete;

/**
 *
 */
public class implementHeap implements clasePrioridad {
    private Heap heap;
    
    public implementHeap(){
        heap = new Heap();
    }
    
    /**
     * Method agregarPasajero
     * @param tiquete Adds passenger to enqueue
     */
    public void agregarPasajero(Tiquete tiquete){
        heap.insert(tiquete);
    }
    
    /**
     * Method getNextPasajero
     * @return next
     */
    public Tiquete getNextPasajero(){
        return heap.removeRoot();
    }
    
    /**
     * Method getTamañoCola
     * @return size
     */
    public Integer getTamañoCola(){
        return heap.getTamañoCola();
    }
    
    /**
     * Method getFirstPasajero
     * @return  First passenger
     */
    public String getFirstPasajero(){
        return heap.getFirstPasajero();
    }
    
    /**
     * Method next
     * Sets current position to next
     */
    public void next(){
        this.heap.next();
    }
    
    /**
     * Method goToFirst
     * Sets current position to first
     */
    public void goToFirst(){
        this.heap.goToFirst();
    }
    
    /**
     * Method getTiquetePasajero
     * @return Tiquete
     */
    public String getTiquetePasajero(){
        return this.heap.getTiquetePasajero();
    }
    
    /**
     * Method getTipo
     * @return type of class
     */
    public String getTipo(){
        return this.heap.getTipo();
    }
}
