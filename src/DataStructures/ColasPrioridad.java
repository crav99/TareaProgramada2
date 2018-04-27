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
public class ColasPrioridad implements clasePrioridad {
    
    /**
     * Class Node
     * Specific class for ColasPrioridad12
     */
    public class Node {
        
        private Tiquete pasajero;
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
         * Constructor Methos Node
         * @param pasajero Sets passenger of class
         */
        public Node(Tiquete pasajero){
            this.pasajero = pasajero;
            this.next = null;
            this.prioridad = pasajero.getTiquete().substring(0,4);
        }
        
        /**
         * Method Tiquete
         * @return pasajero
         */
        public Tiquete getPasajero(){
            return this.pasajero;
        }
        
        /**
         * Method setPasajero
         * @param pasajero Sets pasajero
         */
        public void setPasajero(Tiquete pasajero){
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
     * Constructor Method ColasPrioridad12
     */
    public ColasPrioridad(){
        Node nodo = new Node();
        this.head = nodo;
        this.current = this.head;
        this.tail = this.head;
        this.size = 0;
        this.position = -1;
        this.prioridad = new String[8];
        this.prioridad[0] = "EJ-D";
        this.prioridad[1] = "EJ-M";
        this.prioridad[2] = "EJ-E";
        this.prioridad[3] = "EJ-R";
        this.prioridad[4] = "EC-D";
        this.prioridad[5] = "EC-M";
        this.prioridad[6] = "EC-E";
        this.prioridad[7] = "EC-R";
    }
    
    /**
     * Method agregarPasajero
     * @param pasajero Sets next passenger
     */
    public void agregarPasajero(Tiquete pasajero) {
        if (pasajero != null){
            Node nodo = new Node(pasajero);
            if (this.head.getPasajero() == null) {
                this.head = nodo;
                this.current = this.head;
                this.tail = this.head;
            }else{
                this.tail.setNext(nodo);
                this.tail = nodo;
            }
            this.position ++;
            this.size ++;
            this.current = this.head;
        }
    }

    /**
     * Method getTamañoCola
     * @return size
     */
    public Integer getTamañoCola(){
        return this.size;
    }    
    
    /**
     * 
     * @return First Passenger name
     */
    public String getFirstPasajero(){
        for(int i = 0; i < 8; i++){
            this.current = this.head;
            String prioridadActual = this.prioridad[i];
            for(int j=0; j < this.size; j++){
                if(prioridadActual.equals(this.current.getPrioridad()) == true){
                    return this.current.getPasajero().getTiquete();
                } else {
                    this.current = this.current.getNext();
                }    
            }
        }
        return null;
    }
        
    /**
     * Method goToFirst
     * Sets position to first
     */
    public void goToFirst(){
        this.current = this.head;
    }
    
    /**
     * Method getTiquetePasajero
     * @return Tiquete of passenger in current position
     */
    public String getTiquetePasajero(){
        return this.current.getPasajero().getTiquete();
    }
    
    /**
     * Method next
     * Sets position to next
     */
    public void next(){
        this.current = this.current.getNext();
    }
    
    /**
     * Method getNextPasajero
     * @return Next Passenger
     */
    public Tiquete getNextPasajero() {
        this.current = this.head;
        if(this.size == 1){
            Node temp = this.current;
            this.head = new Node();
            this.current = this.head;
            this.tail = this.head;
            this.size--;
            return temp.getPasajero();
        } else{            
            for(int i = 0; i < 8; i++){
                this.current = this.head;
                String prioridadActual = this.prioridad[i];
                if (prioridadActual.equals(this.current.getPrioridad()) == true){
                    Node temp = this.current;
                    this.head = this.current.getNext();
                    this.head.setNext(this.current.getNext().getNext());
                    this.current = this.head;
                    this.size--;
                    return temp.getPasajero();
                }
                for(int j=0; j < this.size-1; j++){
                    if(prioridadActual.equals(this.current.getNext().getPrioridad()) == true){
                        if (this.current.getNext() == this.tail){
                            Node temp = this.current.getNext();
                            this.tail = this.current;
                            this.current = this.head;
                            this.size--;
                            return temp.getPasajero();
                        } else{
                            Node temp = this.current.getNext();
                            this.current.setNext(this.current.getNext().getNext());
                            this.current = this.head;
                            this.size--;
                            return temp.getPasajero();
                        } 
                    } else {
                        this.current = this.current.getNext();
                    }    
                }
            }   
        }
        this.current = this.head;
        return null;
    }
    
    /**
     * Method getTipo
     * @return Class type
     */
    public String getTipo(){
        return "Cola Prioridad";
    }
}
