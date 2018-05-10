/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

import DataStructures.InterfazColas;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author allanvz
 */
public class Security extends Thread{
    
    private InterfazColas colaSeguridad;
    private DefaultTableModel securityTableModel;
    private DefaultTableModel totalSecurityTableModel;
    private JLabel tiempoSeguridad;
    
    public Security() {
        this.colaSeguridad = null;
        this.securityTableModel = null;
        this.totalSecurityTableModel = null;
        this.tiempoSeguridad = null;
    }

    public Security(InterfazColas colaSeguridad, DefaultTableModel securityTableModel, DefaultTableModel totalSecurityTableModel, JLabel tiempoSeguridad, int numWindow) {
        this.colaSeguridad = colaSeguridad;
        this.securityTableModel = securityTableModel;
        this.totalSecurityTableModel = totalSecurityTableModel;
        this.tiempoSeguridad = tiempoSeguridad;
    }

    /**
     * @return the colaSeguridad
     */
    public InterfazColas getColaSeguridad() {
        return colaSeguridad;
    }

    /**
     * @param colaSeguridad the colaSeguridad to set
     */
    public void setColaSeguridad(InterfazColas colaSeguridad) {
        this.colaSeguridad = colaSeguridad;
    }

    /**
     * @return the securityTableModel
     */
    public DefaultTableModel getSecurityTableModel() {
        return securityTableModel;
    }

    /**
     * @param securityTableModel the securityTableModel to set
     */
    public void setSecurityTableModel(DefaultTableModel securityTableModel) {
        this.securityTableModel = securityTableModel;
    }

    /**
     * @return the totalSecurityTableModel
     */
    public DefaultTableModel getTotalSecurityTableModel() {
        return totalSecurityTableModel;
    }

    /**
     * @param totalSecurityTableModel the totalSecurityTableModel to set
     */
    public void setTotalSecurityTableModel(DefaultTableModel totalSecurityTableModel) {
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
    
    @Override
    public void run() {
        while(true) {
            while(0 < this.colaSeguridad.getSize()) {
                Cliente next = this.colaSeguridad.getNextPasajero();
                int x = 0;
                for(;x < this.securityTableModel.getRowCount(); x++) {
                    if(!this.securityTableModel.getValueAt(x, 1).equals("Libre")) {
                        break;
                    }
                }
                this.securityTableModel.setValueAt(next.getTiquete(), x, 1);
                
            }
        }
    }
    
}
