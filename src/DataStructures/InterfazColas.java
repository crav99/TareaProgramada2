/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import SistemaAdministracionPaquetes.Cliente;

/**
 *
 * Creates interface between ClasePrioridad classes and Heap class
 */
public interface InterfazColas {

    /**
     *
     * @param tiquete
     */
    public void agregarPasajero(Cliente tiquete);

    /**
     *
     * @return
     */
    public Cliente getNextPasajero();

    /**
     *
     * @return
     */
    public String getFirstPasajero();

    /**
     *
     * @return
     */
    public Integer getTamañoCola();

    /**
     *
     * @return
     */
    public String getTiquetePasajero();

    /**
     *
     */
    public void goToFirst();

    /**
     *
     */
    public void next();

    /**
     *
     * @return
     */
    public String getTipo();

    /**
     *
     * @return
     */
    public Integer getSize();

    /**
     *
     * @return
     */
    public Integer getPos();

    /**
     *
     * @return
     */
    public Cliente[] getClienteOrder();
}
