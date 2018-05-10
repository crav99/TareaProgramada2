/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

import DataStructures.InterfazColas;
import java.util.Random;
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
    private Cliente[] windows;
    private int range;
    
    public Security() {
        this.colaSeguridad = null;
        this.securityTableModel = null;
        this.totalSecurityTableModel = null;
        this.tiempoSeguridad = null;
        this.windows = new Cliente[this.securityTableModel.getRowCount()];
        this.range = 0;
    }

    public Security(InterfazColas colaSeguridad, DefaultTableModel securityTableModel, DefaultTableModel totalSecurityTableModel, JLabel tiempoSeguridad, int range) {
        this.colaSeguridad = colaSeguridad;
        this.securityTableModel = securityTableModel;
        this.totalSecurityTableModel = totalSecurityTableModel;
        this.tiempoSeguridad = tiempoSeguridad;
        this.windows = new Cliente[this.securityTableModel.getRowCount()];
        this.range = range;
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
                int x = 0;
                for(;x < this.securityTableModel.getRowCount(); x++) {
                    if(this.windows[x] == null) {
                        Cliente next = this.colaSeguridad.getNextPasajero();
                        this.securityTableModel.setValueAt(next.getTiquete(), x, 1);
                        Random random = new Random();
                        long number = random.nextInt(this.range) + 1;
                        this.securityTableModel.setValueAt(number, x, 2);
                        this.windows[x] = next;
                        int amount = (int)this.totalSecurityTableModel.getValueAt(x, 1);
                        amount ++;
                        this.totalSecurityTableModel.setValueAt(amount, x, 1);
                    }else {
                        long time = System.currentTimeMillis() - this.windows[x].getStartTime();
                        if(time == (long)this.securityTableModel.getValueAt(x, 2)) {
                            this.windows[x] = null;
                            this.securityTableModel.setValueAt("Libre", x, 1);
                            int amount = Integer.parseInt(this.tiempoSeguridad.getText()) + 1;
                            this.tiempoSeguridad.setText(String.valueOf(amount));
                        }
                    }
                }
            }
        }
    }
    
}