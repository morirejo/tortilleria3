/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriapresentacion;

import java.awt.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jorge
 */
public class PantallaTicket extends javax.swing.JFrame {
    private ControlPresentacionVenta mediador;

    public PantallaTicket(ControlPresentacionVenta mediador, double kilos, double precioKg, double total) {
        this.mediador = mediador;
        initComponents(kilos, precioKg, total);
    }

    private void initComponents(double kilos, double precioKg, double total) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ticket de Venta");
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));
        panelPrincipal.setBackground(Color.WHITE);

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

        // Fecha y hora
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        JLabel lblFecha = new JLabel(ahora.format(fmt));
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setForeground(Color.DARK_GRAY);
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        // Detalle de la venta
        JPanel panelDetalle = new JPanel(new GridLayout(3, 2, 10, 8));
        panelDetalle.setBackground(Color.WHITE);
        panelDetalle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDetalle.setMaximumSize(new Dimension(300, 100));

        panelDetalle.add(new JLabel("Cantidad:"));
        JLabel lblCantidad = new JLabel(String.format("%.3f KG", kilos));
        lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblCantidad);

        panelDetalle.add(new JLabel("Precio KG:"));
        JLabel lblPrecio = new JLabel(String.format("$ %.2f", precioKg));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblPrecio);

        JLabel lblTotalTxt = new JLabel("TOTAL:");
        lblTotalTxt.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panelDetalle.add(lblTotalTxt);

        JLabel lblTotal = new JLabel(String.format("$ %.2f", total));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTotal.setForeground(new Color(34, 139, 34));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDetalle.add(lblTotal);

        JSeparator sep3 = new JSeparator();
        sep3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JLabel lblGracias = new JLabel("¡Gracias por su compra!");
        lblGracias.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblGracias.setForeground(Color.GRAY);
        lblGracias.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setBorderPainted(false);
        btnContinuar.setOpaque(true);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuar.setMaximumSize(new Dimension(150, 35));
        btnContinuar.addActionListener(evt -> mediador.mostrarPantallaVenta(this));

        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(4));
        panelPrincipal.add(lblSubtitulo);
        panelPrincipal.add(Box.createVerticalStrut(12));
        panelPrincipal.add(sep1);
        panelPrincipal.add(Box.createVerticalStrut(8));
        panelPrincipal.add(lblFecha);
        panelPrincipal.add(Box.createVerticalStrut(8));
        panelPrincipal.add(sep2);
        panelPrincipal.add(Box.createVerticalStrut(16));
        panelPrincipal.add(panelDetalle);
        panelPrincipal.add(Box.createVerticalStrut(16));
        panelPrincipal.add(sep3);
        panelPrincipal.add(Box.createVerticalStrut(12));
        panelPrincipal.add(lblGracias);
        panelPrincipal.add(Box.createVerticalStrut(16));
        panelPrincipal.add(btnContinuar);

        setContentPane(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }
}
