/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;

import com.mycompany.tortilleriadtos.VentaDTO;

/**
 *
 * @author USUARIO
 */
public class VentaDomicilioDTO
        extends VentaDTO {

    private ClienteDTO cliente;
    private String direccion;
    private double total;

    public VentaDomicilioDTO(
            ClienteDTO cliente,
            String direccion,
            double total) {

        this.cliente = cliente;
        this.direccion = direccion;
        this.total = total;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public String getDireccion() {
        return direccion;
    }
}