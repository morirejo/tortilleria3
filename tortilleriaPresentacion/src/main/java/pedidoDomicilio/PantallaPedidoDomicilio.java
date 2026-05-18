/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidoDomicilio;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import com.mycompany.tortilleriadtos.PedidoDTO;


/**
 *
 * @author USUARIO
 */
public class PantallaPedidoDomicilio extends javax.swing.JFrame {

    private FachadaPedido fachada =
            new FachadaPedido();

    public PantallaPedidoDomicilio() {

        initComponents();
        personalizarComponentes();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();

        lblTitulo = new javax.swing.JLabel();

        lblProducto = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();

        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();

        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();

        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Pedido a Domicilio");

        setResizable(false);

        panelPrincipal.setBackground(
                new Color(255, 248, 220));

        lblTitulo.setText(
                "TORTILLERÍA - PEDIDO A DOMICILIO");

        lblProducto.setText("Producto:");

        lblCantidad.setText("Cantidad:");

        lblDireccion.setText("Dirección:");

        btnConfirmar.setText(
                "Confirmar Pedido");

        btnConfirmar.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent evt) {

                confirmarPedido();
            }
        });

        javax.swing.GroupLayout panelLayout =
                new javax.swing.GroupLayout(panelPrincipal);

        panelPrincipal.setLayout(panelLayout);

        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(panelLayout.createSequentialGroup()

                .addGap(35, 35, 35)

                .addGroup(panelLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(lblTitulo)

                    .addComponent(lblProducto)

                    .addComponent(txtProducto,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        250,
                        javax.swing.GroupLayout.PREFERRED_SIZE)

                    .addComponent(lblCantidad)

                    .addComponent(txtCantidad,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        250,
                        javax.swing.GroupLayout.PREFERRED_SIZE)

                    .addComponent(lblDireccion)

                    .addComponent(txtDireccion,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        250,
                        javax.swing.GroupLayout.PREFERRED_SIZE)

                    .addComponent(btnConfirmar,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        250,
                        javax.swing.GroupLayout.PREFERRED_SIZE))

                .addContainerGap(35, Short.MAX_VALUE))
        );

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(panelLayout.createSequentialGroup()

                .addGap(30, 30, 30)

                .addComponent(lblTitulo)

                .addGap(25, 25, 25)

                .addComponent(lblProducto)

                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addComponent(txtProducto,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    35,
                    javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGap(20, 20, 20)

                .addComponent(lblCantidad)

                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addComponent(txtCantidad,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    35,
                    javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGap(20, 20, 20)

                .addComponent(lblDireccion)

                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addComponent(txtDireccion,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    35,
                    javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGap(30, 30, 30)

                .addComponent(btnConfirmar,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)

                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(
                        getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(panelPrincipal,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(panelPrincipal,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
        );

        pack();

        setLocationRelativeTo(null);
    }

    private void personalizarComponentes() {

        lblTitulo.setFont(
                new Font("Arial", Font.BOLD, 20));

        lblTitulo.setForeground(
                new Color(153, 76, 0));

        btnConfirmar.setBackground(
                new Color(255, 153, 0));

        btnConfirmar.setForeground(
                Color.WHITE);

        btnConfirmar.setFont(
                new Font("Arial", Font.BOLD, 14));

        txtProducto.setBorder(
                BorderFactory.createLineBorder(Color.GRAY));

        txtCantidad.setBorder(
                BorderFactory.createLineBorder(Color.GRAY));

        txtDireccion.setBorder(
                BorderFactory.createLineBorder(Color.GRAY));
    }

    private void confirmarPedido() {

        String producto =
                txtProducto.getText();

        int cantidad =
                Integer.parseInt(
                        txtCantidad.getText());

        String direccion =
                txtDireccion.getText();

        double total =
                cantidad * 20;

        PedidoDTO pedido =
                new PedidoDTO(
                        producto,
                        cantidad,
                        direccion,
                        total);

        fachada.procesarPedido(pedido);

        JOptionPane.showMessageDialog(this,

                "Pedido registrado correctamente\n"
                + "Producto: " + producto
                + "\nCantidad: " + cantidad
                + "\nDirección: " + direccion
                + "\nTotal: $" + total
                + "\nVenta a domicilio registrada");
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(
                new Runnable() {

            public void run() {

                new PantallaPedidoDomicilio()
                        .setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnConfirmar;

    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTitulo;

    private javax.swing.JPanel panelPrincipal;

    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtProducto;
}