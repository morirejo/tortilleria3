/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;

import com.mongodb.client.MongoCollection;
import com.mycompany.tortilleriadatos.ConexionDB;
import com.mycompany.tortilleriadtos.PedidoDTO;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author USUARIO
 */
public class PedidoDAO {
    
    private MongoCollection<Document> collection;

    public PedidoDAO() {
        this.collection = ConexionDB.getInstance().getDatabase().getCollection("pedidos");
    }
    
    private ArrayList<PedidoDTO> listaPedidos =
            new ArrayList<>();

    public void guardarPedido(PedidoDTO pedido) {

        listaPedidos.add(pedido);
    }

    public ArrayList<PedidoDTO> obtenerPedidos() {

        return listaPedidos;
    }
}