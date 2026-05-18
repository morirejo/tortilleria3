/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio.entidades;

/**
 *
 * @author USUARIO
 */
public class Pedido {
    
    private Producto producto;
    private int cantidad;
    private String direccion;
    private double total;
    private Repartidor repartidor;

    public Pedido(Producto producto, int cantidad, String direccion) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.direccion = direccion;
        calcularTotal();
    }

    public void calcularTotal() {
        total = producto.getPrecio() * cantidad;
    }

    public void asignarRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
        repartidor.setDisponible(false);
    }

    public void mostrarPedido() {
        System.out.println("\n===== PEDIDO A DOMICILIO =====");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Total: $" + total);

        if (repartidor != null) {
            System.out.println("Repartidor asignado: " + repartidor.getNombre());
        }
    }
}