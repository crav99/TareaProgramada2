/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

import DataStructures.InterfazColas;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 *
 */
public class securityQueue extends Thread{
    
    private InterfazColas securityQueue;
    private JTable securityTableModel;
    private JTable totalSecurityTableModel;
    private JLabel tiempoSeguridad;
    private Cliente[] windows;
    private int range;
    private int time;
    private DefaultTableModel colasTableModel;
    private InterfazColas perecederoQueue;
    private InterfazColas noPerecederoQueue;
    
    /**
     *
     */
    public securityQueue() {
        this.securityQueue = null;
        this.securityTableModel = null;
        this.totalSecurityTableModel = null;
        this.tiempoSeguridad = null;
        this.colasTableModel = null;
        this.perecederoQueue = null;
        this.noPerecederoQueue = null;
        this.windows = new Cliente[this.securityTableModel.getRowCount()];
        this.range = 0;
        this.time = 0;
    }

    /**
     *
     * @param colaSeguridad
     * @param perecederoQueue
     * @param noPerecederoQueue
     * @param colasTableModel
     * @param securityTableModel
     * @param totalSecurityTableModel
     * @param tiempoSeguridad
     * @param range
     */
    public securityQueue(InterfazColas colaSeguridad, InterfazColas perecederoQueue, InterfazColas noPerecederoQueue, DefaultTableModel colasTableModel, JTable securityTableModel, JTable totalSecurityTableModel, JLabel tiempoSeguridad, int range) {
        this.securityQueue = colaSeguridad;
        this.securityTableModel = securityTableModel;
        this.totalSecurityTableModel = totalSecurityTableModel;
        this.tiempoSeguridad = tiempoSeguridad;
        this.perecederoQueue = perecederoQueue;
        this.noPerecederoQueue = noPerecederoQueue;
        this.colasTableModel = colasTableModel;
        this.windows = new Cliente[this.securityTableModel.getRowCount()];
        this.range = range;
        this.time = 0;
    }

    /**
     * @return the securityQueue
     */
    public InterfazColas getColaSeguridad() {
        return securityQueue;
    }

    /**
     * @param colaSeguridad the securityQueue to set
     */
    public void setColaSeguridad(InterfazColas colaSeguridad) {
        this.securityQueue = colaSeguridad;
    }

    /**
     * @return the securityTableModel
     */
    public JTable getSecurityTableModel() {
        return securityTableModel;
    }

    /**
     * @param securityTableModel the securityTableModel to set
     */
    public void setSecurityTableModel(JTable securityTableModel) {
        this.securityTableModel = securityTableModel;
    }

    /**
     * @return the totalSecurityTableModel
     */
    public JTable getTotalSecurityTableModel() {
        return totalSecurityTableModel;
    }

    /**
     * @param totalSecurityTableModel the totalSecurityTableModel to set
     */
    public void setTotalSecurityTableModel(JTable totalSecurityTableModel) {
        this.totalSecurityTableModel = totalSecurityTableModel;
    }

    /**
     * @return the tiempoSeguridad
     */
    public JLabel getTiempoSeguridad() {
        return tiempoSeguridad;
    }

    /**
     * @param tiempoSeguridad the tiempoSeguridad to set
     */
    public void setTiempoSeguridad(JLabel tiempoSeguridad) {
        this.tiempoSeguridad = tiempoSeguridad;
    }
    
    /**
     *
     */
    public void updateQueueStatus() {
        try {
            this.colasTableModel.setValueAt(this.perecederoQueue.getSize(), 0, 2);
            this.colasTableModel.setValueAt(this.perecederoQueue.getFirstPasajero(), 0, 3);
        }catch(NullPointerException ex) {
            this.colasTableModel.setValueAt(this.perecederoQueue.getSize(), 0, 2);
            this.colasTableModel.setValueAt("", 0, 3);
        }
        try {
            this.colasTableModel.setValueAt(this.noPerecederoQueue.getSize(), 1, 2);
            this.colasTableModel.setValueAt(this.noPerecederoQueue.getFirstPasajero(), 1, 3);
        }catch(NullPointerException ex) {
            this.colasTableModel.setValueAt(this.noPerecederoQueue.getSize(), 1, 2);
            this.colasTableModel.setValueAt("", 1, 3);
        }
        try {
            this.colasTableModel.setValueAt(this.securityQueue.getSize(), 2, 2);
            this.colasTableModel.setValueAt(this.securityQueue.getFirstPasajero(), 2, 3);
        }catch(NullPointerException ex) {
            this.colasTableModel.setValueAt(this.securityQueue.getSize(), 2, 2);
            this.colasTableModel.setValueAt("", 2, 3);
        }
    }
    
    @Override
    public void run() {
        while(true) {
            int x = 0;
            for(;x < this.securityTableModel.getRowCount(); x++) {
                if(this.securityQueue.getSize() > 0) {
                    if(this.windows[x] == null) {
                        Cliente next = this.securityQueue.getNextPasajero();
                        this.securityTableModel.setValueAt(next.getTiquete(), x, 1);
                        Random random = new Random();
                        long number = random.nextInt(this.range) + 1;
                        this.securityTableModel.setValueAt(number, x, 2);
                        this.time += next.finishTime()/1000;
                        this.tiempoSeguridad.setText(String.valueOf(this.time));
                        next.startTiempo();
                        this.windows[x] = next;
                        int amount = (int)this.totalSecurityTableModel.getValueAt(x, 1);
                        amount ++;
                        this.totalSecurityTableModel.setValueAt(amount, x, 1);
                    }
                }
                if(this.windows[x] != null) {
                    long time = (System.currentTimeMillis() - this.windows[x].getStartTime())/1000;
                    if(time >= (long)this.securityTableModel.getValueAt(x, 2)) {
                        this.windows[x] = null;
                        this.securityTableModel.setValueAt("Libre", x, 1);
                        this.securityTableModel.setValueAt(null, x, 2);
                    }
                }
            }
            updateQueueStatus();
        }
    }
    
}
