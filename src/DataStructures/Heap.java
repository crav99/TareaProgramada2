/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import SistemaAdministracionPaquetes.Cliente;



/**
 *
 */
public class Heap {

    public Cliente[] heap;
    public Integer size;
    public int posicion;

    /**
     * Constructor Method Heap
 Assigns attributes to the heapSort and starts class
     */
    public Heap() {
        this.posicion = -1;
        this.size = 0;
        heap = new Cliente[this.size];
    }
    
    /**
     * Method isLeaf
 Returns boolean of whether the position entered corresponds to a heapSort leaf
     * @param pos Sets the position to search
     * @return boolean
     */
    public boolean isLeaf(int pos){
        return 2*pos+1>this.heap.length-1 && 2*pos+2>this.heap.length-1;
    }
    
    /**
     * Returns left child of selected position
     * @param pos Sets the position to search
     * @return Cliente
     */
    public Cliente getLeftChild(int pos){
        int left = 2*pos+1;
        return this.heap[left];
    }
    
    /**
     * Returns left child of selected position
     * @param pos Sets position to search
     * @return Cliente
    */
    public Cliente getRightChild(int pos){
        int right = 2*pos+2;
        return this.heap[right];
    }
    
    /**
     * Returns parent of selected position
     * @param pos Sets position to search
     * @return Cliente
     */
    public Cliente parent(int pos){
        int parent = (pos-1)/2;
        return this.heap[parent];
    }
    
    /**
     * Inserts the element entered into the hepa
     * @param element Element to insert in heapSort
     */
    public void insert(Cliente element){
        this.size ++;
        Cliente[] newHeap = new Cliente[this.size];
        int position = 0;
        while(this.heap.length > position){
            newHeap[position] = this.heap[position];
            position ++;
        }
        newHeap[position] = element;
        this.heap = new Cliente[this.size];
        
        position = 0;
        while(this.heap.length > position){
            this.heap[position] = newHeap[position];
            position ++;
        }
        siftup(this.size-1);
        
    }
    
    /**
     * Sets selected Node of array to correct position in heapSort starting from the bottom
     * @param pos Sets starting position
     */
    public void siftup(int pos){
        int posPadre = (pos-1)/2;
        while(posPadre >= 0){
            Cliente padre = this.heap[posPadre];
            Cliente actual = this.heap[pos];
            if(padre.getSerial() < actual.getSerial()){
                this.heap[posPadre] = actual;
                this.heap[pos] = padre;
            }else{
                break;
            }
            pos = posPadre;
            posPadre = (pos-1)/2;
        }
    }
    
    /**
     * Sets position selected to correct position in heapSort starting from the top
     * @param heapTemp
     * @param pos Sets position to start
     */
    public Cliente[] siftdown(Cliente[] heapTemp, int pos){
        int cambios = 1;
        while(pos*2+1 < heapTemp.length-1 && cambios > 0){
            cambios = 0;
            int prioridadCambio;
            Cliente actual = heapTemp[pos];
            Cliente hijoIzquierdo = heapTemp[pos*2+1];
            Cliente hijoDerecho = null;
            Cliente cambio;
            if (pos*2+2 < heapTemp.length-1){
                hijoDerecho = heapTemp[pos*2+2];
            }
            if (hijoDerecho != null){
                if(hijoIzquierdo.getSerial() < hijoDerecho.getSerial()){
                    cambio = hijoIzquierdo;
                    prioridadCambio = pos*2+1;
                }else{
                    cambio = hijoDerecho;
                    prioridadCambio = pos*2+2;
                }
            }else{
                cambio = hijoIzquierdo;
                prioridadCambio = pos*2+1;
            }
            
            if(cambio.getSerial() < actual.getSerial()){
                heapTemp[pos] = cambio;
                heapTemp[prioridadCambio] = actual;
                cambios ++;
                pos = prioridadCambio;
            }
        }
        return heapTemp;
    }
    
    /**
     * Creates a messy normal array into a heapSort
     */
    public void buildheap(){
        int position = 1;
        while(this.heap.length > position){
            int posParent = (position-1)/2;
            Cliente parent = this.heap[posParent];
            Cliente change = this.heap[position];
            if (this.heap[position].getSerial() > this.heap[posParent].getSerial()){
                this.heap[position] = parent;
                this.heap[posParent] = change;
            }
            position ++;
        }
    }
    
    /**
     * Removes selected heapSort node and puts it in order
     * @param pos Sets position to remove
     * @return Cliente
     */
    public Cliente remove(int pos){
        this.size --;
        Cliente pasajero = this.heap[pos];
        Cliente[] newHeap = new Cliente[this.size];
        int position = 0;
        this.heap[pos] = this.heap[this.size];
        while(this.heap.length > position){
            newHeap[position] = this.heap[position];
            position ++;
        }
        this.heap = new Cliente[this.size];
        position = 0;
        while(this.heap.length > position){
            this.heap[position] = newHeap[position];
            position ++;
        }
        siftdown(this.heap, pos);
        return pasajero;
    }
    
    /**
     * Returns root, then changes it with bottom element of heapSort and eliminates it.
     * @return Cliente
     */
    public Cliente removeRoot(){
        if (this.size > 0){
            this.size --;
            Cliente pasajero = this.heap[0];
            Cliente[] newHeap = new Cliente[this.size];
            int position = 0;
            this.heap[0] = this.heap[this.size];
            while(this.heap.length-1 > position){
                newHeap[position] = this.heap[position];
                position ++;
            }

            this.heap = new Cliente[this.size];

            position = 0;
            while(this.heap.length > position){
                this.heap[position] = newHeap[position];
                position ++;
            }
            this.heap = siftdown(this.heap, 0);
            return pasajero;
        }else{
            return null;
        }
    }
    
    /**
     * Returns size of heapSort
     *
     * @return Integer
    */
    public Integer getTamañoCola(){
        return this.size;
    }
    
    /**
     * Returns the ticket of the first passenger
     * @return  String
     */
    public String getFirstPasajero(){
        return this.heap[0].getTiquete();
    }
    
    /**
     * Returns Ticket of passenger in current psoition
     * @return String
     */
    public String getTiquetePasajero(){
        return this.heap[posicion].getTiquete();
    }
    
    /**
     * Sets current position to the first position of the heapSort
     */
    public void goToFirst(){
        this.posicion = 0;
    }
    
    /**
     * Sets current position to the next position in the array
     */
    public void next(){
        this.posicion ++;
    }
    
    /**
     * Returns text "Heap" to determine the type of class
     * @return String
     */
    public String getTipo(){
        return "Heap";
    }
    
    public Integer getSize() {
        return this.size;
    }
    
    public Integer getPos() {
        return this.posicion;
    }
    
    /**
     * Sets position selected to correct position in heapSort starting from the top
     * @param heapTemp
     * @param pos Sets position to start
     */
    public void siftdown(Cliente[] heapTemp, int pos, int end){
        int cambios = 1;
        while(pos*2+1 < end && cambios > 0){
            cambios = 0;
            int prioridadCambio;
            Cliente actual = heapTemp[pos];
            Cliente hijoIzquierdo = heapTemp[pos*2+1];
            Cliente hijoDerecho = null;
            Cliente cambio;
            if (pos*2+2 < end){
                hijoDerecho = heapTemp[pos*2+2];
            }
            if (hijoDerecho != null){
                if(hijoIzquierdo.getSerial() < hijoDerecho.getSerial()){
                    cambio = hijoIzquierdo;
                    prioridadCambio = pos*2+1;
                }else{
                    cambio = hijoDerecho;
                    prioridadCambio = pos*2+2;
                }
            }else{
                cambio = hijoIzquierdo;
                prioridadCambio = pos*2+1;
            }
            
            if(cambio.getSerial() < actual.getSerial()){
                heapTemp[pos] = cambio;
                heapTemp[prioridadCambio] = actual;
                cambios ++;
                pos = prioridadCambio;
            }
        }
    }
    
    public Cliente[] BubbleSort(Cliente[] list) {
        boolean swapped = true;
        while(swapped) {
            swapped = false;
            for(int x = 0; x < list.length - 1; x++) {
                Cliente element = list[x];
                if(element.getSerial() < list[x + 1].getSerial()) {
                    list[x] = list[x+1];
                    list[x+1] = element;
                    swapped = true;
                }
            }
        }
        return list;
    }
}

