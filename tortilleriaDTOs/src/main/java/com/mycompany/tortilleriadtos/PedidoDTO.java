/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriadtos;

/**
 *
 * @author USUARIO
 */
public class PedidoDTO {

    private String producto;
    private int cantidad;
    private String direccion;
    private double total;

    public PedidoDTO() {
    }

    public PedidoDTO(String producto, int cantidad, String direccion, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.total = total;
    }

    

}