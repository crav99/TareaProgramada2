/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import SistemaAdministracionPaquetes.Cliente;

/**
 *
 *
 */
public class PriorityQueue implements InterfazColas {
    
    /**
     * Class Node
     * Specific class for ColasPrioridad11
     */
    public class Node {
        
        private Cliente pasajero;
        private Node next;
        private String prioridad;
        
        /**
         * Constructor Method Node
         */
        public Node(){
            this.pasajero = null;
            this.next = null;
            this.prioridad = null;
        }
        
        /**
         * Constructor Method Node
         * @param pasajero Sets passenger of class
         */
        public Node(Cliente pasajero){
            this.pasajero = pasajero;
            this.next = null;
            this.prioridad = pasajero.getTiquete().substring(0, 4);
        }
        
        /**
         * Method Cliente
         * @return pasajero
         */
        public Cliente getPasajero(){
            return this.pasajero;
        }
        
        /**
         * Method setPasajero
         * @param pasajero Sets pasajero
         */
        public void setPasajero(Cliente pasajero){
            this.pasajero = pasajero;
        }
        
        /**
         * Method getNext
         * @return next
         */
        public Node getNext(){
            return this.next;
        }
        
        /**
         * Method setNext
         * @param next Sets next
         */
        public void setNext(Node next){
            this.next = next;
        }
        
        /**
         * Method getPrioridad
         * @return prioridad
         */
        public String getPrioridad() {
            return this.prioridad;
        }
        
        /**
         * Methos setPrioridad
         * @param prioridad Sets Prioridad
         */
        public void setPrioridad(String prioridad) {
            this.prioridad = prioridad;
        }
        
    }
    
    private Node head;
    private Node current;
    private Node tail;
    private Integer size;
    private int position;
    private String[] prioridad;
    
    /**
     * Constructor Method ColasPrioridad11
     */
    public PriorityQueue(){
        this.head = new Node();
        this.current = this.head;
        this.tail = this.head;
        this.size = 0;
        this.position = -1;
        this.prioridad = new String[8];
        this.prioridad[0] = "P-D";
        this.prioridad[1] = "P-M";
        this.prioridad[2] = "P-E";
        this.prioridad[3] = "P-R";
        this.prioridad[4] = "NP-D";
        this.prioridad[5] = "NP-M";
        this.prioridad[6] = "NP-E";
        this.prioridad[7] = "NP-R";
    }
    
    /**
     * Method agregarPasajero
     * @param pasajero Sets next passenger
     */
    public void agregarPasajero(Cliente pasajero) {
        Node nodo = new Node(pasajero);
        if (this.head.getPasajero() == null) {
            this.head = nodo;
            this.current = this.tail = this.head;
        }else if(getSize() > 1) {
            goToFirst();
            while(this.current.getNext() != null) {
                if(this.current.getPasajero().getSerial() >= pasajero.getSerial() & this.current.getNext().getPasajero().getSerial() < pasajero.getSerial()) {
                    nodo.setNext(this.current.getNext());
                    this.current.setNext(nodo);
                    this.size ++;
                    return;
                }
                next();
            }
            if(this.current.getNext() == null) {
                nodo.setNext(this.current.getNext());
                this.current.setNext(nodo);
                this.tail = nodo;
            }
        }else {
            if(this.head.getPasajero().getSerial() < pasajero.getSerial()) {
                nodo.setNext(this.head);
                this.head = nodo;
            }else{
                nodo.setNext(this.current.getNext());
                this.current.setNext(nodo);
                this.tail = nodo;
            }
        }
        this.size ++;
    }
    
    /**
     * Method getNextPasajero
     * @return Next Passenger
     */
    public Cliente getNextPasajero() {
        if (getSize() == 0){
            return null;
        }else if(getSize() == 1) {
            Cliente next = this.head.getPasajero();
            this.head = new Node();
            this.current = this.tail = this.head;
            this.size --;
            return next;
        }
        Cliente next = this.head.getPasajero();
        this.head = this.head.getNext();
        this.size --;
        return next;
    }
    
    /**
     * 
     * @return First Passenger name
     */
    public String getFirstPasajero(){
        return this.head.getPasajero().getTiquete();
    }
    
    /**
     * Method getTamañoCola
     * @return size
     */
    public Integer getTamañoCola(){
        return this.getSize();
    }
    
    /**
     * Method getTiquetePasajero
     * @return Cliente of passenger in current position
     */
    public String getTiquetePasajero(){
        return this.current.getPasajero().getTiquete();
    }
    
    /**
     * Method goToFirst
     * Sets position to first
     */
    public void goToFirst(){
        this.current = this.head;
        this.position = 0;
    }
    
    /**
     * Method next
     * Sets position to next
     */
    public void next(){
        this.current = this.current.getNext();
        this.position ++;
    }
    
    /**
     * Method getTipo
     * @return Class type
     */
    public String getTipo(){
        return "Cola Prioridad";
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return this.size;
    }
    
    /**
     *
     * @return
     */
    public Integer getPos() {
        return this.position;
    }
    
    /**
     *
     * @return
     */
    public Cliente getElement() {
        return this.current.getPasajero();
    }
    
    /**
     *
     * @return
     */
    public Cliente[] getClienteOrder() {
        Cliente[] arrayTemp = new Cliente[this.size];
        goToFirst();
        for(int x = 0; x < this.size; x++) {
            arrayTemp[x] = getElement();
            next();
        }
        return arrayTemp;
    }
    
}
