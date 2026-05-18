/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;

import com.mycompany.tortilleriadtos.PedidoDTO;

/**
 *
 * @author MoriTejo
 */
public class GestorPedido {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public boolean registrarPedido(PedidoDTO pedido) {
        if (pedido == null) {
            return false;
        }
        try {
            pedidoDAO.guardarPedido(pedido);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar el pedido a domicilio: " + e.getMessage());
            return false;
        }
    }
}
