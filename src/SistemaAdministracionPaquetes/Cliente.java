/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

/**
 *
 *
 */
public class Cliente {
    private final String tiquete;
    private String nombre;
    private String correo;
    private String telefono;
    private long tiempo;
    private long startTime;
    private int serial;
    
    /**
     * Constructor Method Tiquete
     * Receives no variables
     */
    public Cliente(){
        this.tiquete = null;
        this.nombre = null;
        this.correo = null;
        this.telefono = null;
        this.tiempo = 0;
        this.startTime = 0;
        this.serial = 0;
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
    public Cliente(String tiquete, String nombre, String correo, String telefono, int serial){
        this.tiquete = tiquete;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tiempo = 0;
        this.startTime = 0;
        this.serial = serial;
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
        this.tiempo = (System.currentTimeMillis()-getStartTime());
        return this.tiempo;
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

    /**
     * @return the serial
     */
    public int getSerial() {
        return serial;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }
    
}
