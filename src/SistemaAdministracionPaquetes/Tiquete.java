/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

/**
 *
 */
public class Tiquete {
    private final int serial;
    private final String tiquete;
    private String nombre;
    private String correo;
    private String telefono;
    private long tiempo;
    private long startTime;
    
    /**
     * Constructor Method Tiquete
     * Receives no variables
     */
    public Tiquete(){
        this.serial = 0;
        this.tiquete = null;
        this.nombre = null;
        this.correo = null;
        this.telefono = null;
        this.tiempo = 0;
        this.startTime = 0;
    }
    
    /**
     * Constructor Method Tiquete
     * Receives variables
     * @param serial Sets position in a integer
     * @param tiquete Sets position of passenger in system
     * @param nombre Sets passenger's name
     * @param correo Sets passenger's email address
     * @param telefono Sets passenger's phone number
     */
    public Tiquete(int serial, String tiquete, String nombre, String correo, String telefono){
        this.serial = serial;
        this.tiquete = tiquete;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tiempo = 0;
        this.startTime = 0;
    }
    
    /**
     * Method startTiempo
     * Starts time of passenger in queue of service window
     */
    public void startTiempo(){
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Method finishTime
     * Gets the time taken in queue
     * @return tiempo
     */
    public long finishTime(){
        this.tiempo = (System.currentTimeMillis()-startTime);
        return this.tiempo;
    }
    
    /**
     * Method getSerial
     * @return serial
     */
    public int getSerial(){
        return this.serial;
    }
    
    /**
     * Method getTiquete
     * @return tiquete
     */
    public String getTiquete(){
        return this.tiquete;
    }
    
    /**
     * Method getNombre
     * @return nombre
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Method getCorreo
     * @return correo
     */
    public String getCorreo(){
        return this.correo;
    }
    
    /**
     * Method getTelefono
     * @return telefono
     */
    public String getTelefono(){
        return this.telefono;
    }
}
