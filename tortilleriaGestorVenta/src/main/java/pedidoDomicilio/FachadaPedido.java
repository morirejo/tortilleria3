/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;
import com.mycompany.tortilleriadtos.PedidoDTO;


/**
 *
 * @author USUARIO
 */
public class FachadaPedido {

    private GestorPedido gestor = new GestorPedido();

    public boolean procesarPedido(PedidoDTO pedido) {
        return gestor.registrarPedido(pedido);
    }
}