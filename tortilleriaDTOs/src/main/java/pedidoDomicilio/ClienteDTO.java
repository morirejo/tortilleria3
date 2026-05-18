/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;

/**
 *
 * @author USUARIO
 */
public class ClienteDTO {

    private String nombre;
    private String telefono;

    public ClienteDTO(
            String nombre,
            String telefono) {

        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}