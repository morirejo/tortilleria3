/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriapresentacion;

import com.mycompany.tortilleriadtos.VentaDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jorge
 */
public class PantallaTicketCancelacion extends javax.swing.JFrame{
    private ControlPresentacionVenta mediador;
    private VentaDTO venta;
    private PantallaCancelacion pantallaCancelacion;

    public PantallaTicketCancelacion(ControlPresentacionVenta mediador, VentaDTO venta, PantallaCancelacion pantallaCancelacion) {
        this.mediador = mediador;
        this.venta = venta;
        this.pantallaCancelacion = pantallaCancelacion;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ticket de Venta");
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.WHITE);

        // Encabezado
        JLabel lblTitulo = new JLabel("TORTILLERÍA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Ticket de Venta");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        // Folio
        JLabel lblFolio = new JLabel("Folio: " + venta.getIdVenta());
        lblFolio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFolio.setForeground(Color.DARK_GRAY);
        lblFolio.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        String fecha = venta.getFechaHora() != null ? sdf.format(venta.getFechaHora()) : "—";
        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setForeground(Color.DARK_GRAY);
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        // Detalle
        double kilos = venta.getCarrito() != null
            ? venta.getCarrito().stream().mapToDouble(d -> d.getCantidadKilos()).sum()
            : 0;
        double precioKg = (kilos > 0) ? venta.getMontoTotal() / kilos : 0;
        String textoMetodo = "N/A";
        if (venta instanceof com.mycompany.tortilleriadtos.VentaLocalDTO) {
            textoMetodo = ((com.mycompany.tortilleriadtos.VentaLocalDTO) venta).getMetodoPago();
        }

        JPanel panelDetalle = new JPanel(new GridLayout(4, 2, 10, 8));
        panelDetalle.setBackground(Color.WHITE);
        panelDetalle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDetalle.setMaximumSize(new Dimension(300, 120));

        panelDetalle.add(new JLabel("Cantidad:"));
        JLabel lblKilos = new JLabel(String.format("%.3f KG", kilos));
        lblKilos.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblKilos.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblKilos);

        panelDetalle.add(new JLabel("Precio KG:"));
        JLabel lblPrecio = new JLabel(String.format("$ %.2f", precioKg));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblPrecio);

        JLabel lblTotalTxt = new JLabel("TOTAL:");
        lblTotalTxt.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panelDetalle.add(lblTotalTxt);
        JLabel lblTotal = new JLabel(String.format("$ %.2f", venta.getMontoTotal()));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTotal.setForeground(new Color(34, 139, 34));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblTotal);

        panelDetalle.add(new JLabel("Método Pago:"));
        JLabel lblMetodo = new JLabel(textoMetodo);
        lblMetodo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMetodo.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblMetodo);

        JSeparator sep3 = new JSeparator();
        sep3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        // Botón cancelar
        JButton btnCancelar = new JButton("Cancelar Venta");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setOpaque(true);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setMaximumSize(new Dimension(180, 35));

        btnCancelar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de cancelar esta venta?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = mediador.cancelarVenta(venta.getIdVenta());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Venta cancelada correctamente.");
                    this.dispose();
                    pantallaCancelacion.cargarVentas();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al cancelar la venta.");
                }
            }
        });

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblSubtitulo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(sep1);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblFolio);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblFecha);
        panel.add(Box.createVerticalStrut(8));
        panel.add(sep2);
        panel.add(Box.createVerticalStrut(16));
        panel.add(panelDetalle);
        panel.add(Box.createVerticalStrut(16));
        panel.add(sep3);
        panel.add(Box.createVerticalStrut(16));
        panel.add(btnCancelar);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }
}
