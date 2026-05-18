/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriapresentacion;

import com.mycompany.tortilleriadtos.VentaDTO;
import java.awt.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class PantallaCancelacion  extends javax.swing.JFrame{
    private ControlPresentacionVenta mediador;
    private DefaultTableModel modeloTabla;
    private List<VentaDTO> ventas;

    public PantallaCancelacion(ControlPresentacionVenta mediador) {
        this.mediador = mediador;
        initComponents();
        cargarVentas();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cancelar Venta");
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Selecciona una venta para cancelar");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(
            new String[]{"Folio", "Fecha", "Kilos", "Total", "Método Pago"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(550, 300));
        panel.add(scroll, BorderLayout.CENTER);

        // Botón ver ticket
        JButton btnVerTicket = new JButton("Ver Ticket y Cancelar");
        btnVerTicket.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVerTicket.setBackground(new Color(200, 50, 50));
        btnVerTicket.setForeground(Color.WHITE);
        btnVerTicket.setFocusPainted(false);
        btnVerTicket.setBorderPainted(false);
        btnVerTicket.setOpaque(true);
        btnVerTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVerTicket.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una venta de la tabla.");
                return;
            }
            VentaDTO ventaSeleccionada = ventas.get(fila);
            new PantallaTicketCancelacion(mediador, ventaSeleccionada, this).setVisible(true);
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);
        panelBoton.add(btnVerTicket);
        panel.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public void cargarVentas() {
        modeloTabla.setRowCount(0);
        ventas = mediador.obtenerTodasLasVentas();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (VentaDTO v : ventas) {
            double kilos = v.getCarrito() != null
                ? v.getCarrito().stream().mapToDouble(d -> d.getCantidadKilos()).sum()
                : 0;
            String metodoPago = "Desconocido";
            if (v instanceof com.mycompany.tortilleriadtos.VentaLocalDTO) {
                com.mycompany.tortilleriadtos.VentaLocalDTO vLocal = (com.mycompany.tortilleriadtos.VentaLocalDTO) v;
                metodoPago = vLocal.getMetodoPago();
            }
            modeloTabla.addRow(new Object[]{
                v.getIdVenta(),
                v.getFechaHora() != null ? sdf.format(v.getFechaHora()) : "—",
                String.format("%.3f KG", kilos),
                String.format("$ %.2f", v.getMontoTotal()),
                metodoPago
            });
        }
    }
}
