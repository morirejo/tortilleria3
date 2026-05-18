/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio.entidades;

/**
 *
 * @author USUARIO
 */
public class Repartidor {
    
    private String nombre;
    private boolean disponible;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.disponible = true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}