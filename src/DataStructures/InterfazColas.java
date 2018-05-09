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
public interface InterfazColas<T> {
    public void agregarPasajero(Cliente tiquete);
    public Cliente getNextPasajero();
    public String getFirstPasajero();
    public Integer getTamañoCola();
    public String getTiquetePasajero();
    public void goToFirst();
    public void next();
    public String getTipo();
    public Integer getSize();
    public Integer getPos();
    public Cliente[] getClienteOrder();
}
