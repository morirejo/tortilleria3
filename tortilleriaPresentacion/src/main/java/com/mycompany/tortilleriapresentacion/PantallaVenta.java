/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriapresentacion;

import com.mycompany.tortilleriadtos.DetalleVentaDTO;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MoriTejo
 */
public class PantallaVenta extends JFrame {
    
    private ControlPresentacionVenta mediador;
    
    private DefaultTableModel modeloTabla;
    private JTable tablaCarrito;
    private JLabel lblTotal;
    private JTextField txtKilosTortilla;
    private JTextField txtKilosMasa;

    public PantallaVenta(ControlPresentacionVenta mediador) {
        this.mediador = mediador;
        
        setTitle("Punto de Venta - Tortillería");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(15, 15)); 

        crearInterfaz();
        actualizarTabla(); 
    }

    private void crearInterfaz() {
        // =======================================================
        // 1. ZONA SUPERIOR: Barra de Herramientas (NUEVO)
        // =======================================================
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelNorte.setBackground(new Color(240, 240, 240));
        panelNorte.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JButton btnCancelarVentas = new JButton("Cancelar Venta");
        JButton btnReportes = new JButton("Reportes");
        JButton btnCierreCaja = new JButton("Cierre de Caja");

        // Estilo de los botones administrativos
        btnCancelarVentas.setBackground(new Color(200, 50, 50));
        btnCancelarVentas.setForeground(Color.WHITE);
        btnCancelarVentas.setFocusPainted(false);

        btnReportes.setBackground(new Color(70, 130, 180));
        btnReportes.setForeground(Color.WHITE);
        btnReportes.setFocusPainted(false);

        btnCierreCaja.setBackground(new Color(210, 105, 30));
        btnCierreCaja.setForeground(Color.WHITE);
        btnCierreCaja.setFocusPainted(false);

        panelNorte.add(btnCancelarVentas);
        panelNorte.add(btnReportes);
        panelNorte.add(btnCierreCaja);
        add(panelNorte, BorderLayout.NORTH);

        // =======================================================
        // 2. ZONA CENTRAL: La Tabla del Carrito
        // =======================================================
        String[] columnas = {"Producto", "Tipo", "Kilos", "Subtotal"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCarrito = new JTable(modeloTabla);
        tablaCarrito.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaCarrito.setRowHeight(25);
        
        JScrollPane scrollTabla = new JScrollPane(tablaCarrito);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Carrito de Compras"));
        add(scrollTabla, BorderLayout.CENTER);

        // =======================================================
        // 3. ZONA IZQUIERDA: Formularios para agregar productos
        // =======================================================
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1, 10, 10));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Catálogo"));
        panelIzquierdo.setPreferredSize(new Dimension(250, 0));

        JPanel panelTortilla = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTortilla.add(new JLabel("Tortilla (Kg):"));
        txtKilosTortilla = new JTextField("1", 5);
        JButton btnAgregarTortilla = new JButton("+ Agregar");
        btnAgregarTortilla.setBackground(new Color(70, 130, 180)); 
        btnAgregarTortilla.setForeground(Color.WHITE);
        panelTortilla.add(txtKilosTortilla);
        panelTortilla.add(btnAgregarTortilla);

        JPanel panelMasa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMasa.add(new JLabel("Masa (Kg):   "));
        txtKilosMasa = new JTextField("1", 5);
        JButton btnAgregarMasa = new JButton("+ Agregar");
        btnAgregarMasa.setBackground(new Color(70, 130, 180));
        btnAgregarMasa.setForeground(Color.WHITE);
        panelMasa.add(txtKilosMasa);
        panelMasa.add(btnAgregarMasa);

        panelIzquierdo.add(panelTortilla);
        panelIzquierdo.add(panelMasa);
        add(panelIzquierdo, BorderLayout.WEST);

        // =======================================================
        // 4. ZONA INFERIOR: Total y Botón de Cobro
        // =======================================================
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 22));
        lblTotal.setForeground(new Color(0, 100, 0)); 

        JButton btnCobrar = new JButton("Procesar Pago");
        btnCobrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCobrar.setBackground(new Color(34, 139, 34)); 
        btnCobrar.setForeground(Color.WHITE);
        btnCobrar.setPreferredSize(new Dimension(180, 40));

        panelInferior.add(lblTotal);
        panelInferior.add(btnCobrar);
        add(panelInferior, BorderLayout.SOUTH);

        // =======================================================
        // 5. PROGRAMACIÓN DE LOS EVENTOS
        // =======================================================
        
        // --- Botones Superiores (Navegación Administrativa) ---
        btnCancelarVentas.addActionListener(e -> mediador.mostrarPantallaCancelacion(this));
        btnReportes.addActionListener(e -> mediador.mostrarPantallaReportes(this));
        btnCierreCaja.addActionListener(e -> mediador.mostrarPantallaCierreCaja(this));

        // --- Botones de Venta ---
        btnAgregarTortilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double kilos = Double.parseDouble(txtKilosTortilla.getText());
                    mediador.agregarProducto("Tortilla de Maíz", "Tortilla", kilos);
                    actualizarTabla();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PantallaVenta.this, "Ingrese una cantidad válida para Tortilla.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAgregarMasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double kilos = Double.parseDouble(txtKilosMasa.getText());
                    mediador.agregarProducto("Masa Blanca", "Masa", kilos);
                    actualizarTabla();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PantallaVenta.this, "Ingrese una cantidad válida para Masa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCobrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mediador.getCarritoActual().isEmpty()) {
                    JOptionPane.showMessageDialog(PantallaVenta.this, "Agregue productos al carrito antes de cobrar.", "Carrito Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                mediador.mostrarMetodoPago();
            }
        });
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (DetalleVentaDTO detalle : mediador.getCarritoActual()) {
            Object[] fila = {
                detalle.getNombreProducto(),
                detalle.getTipoProducto(),
                detalle.getCantidadKilos(),
                "$" + String.format("%.2f", detalle.getSubtotal())
            };
            modeloTabla.addRow(fila);
        }
        lblTotal.setText("Total: $" + String.format("%.2f", mediador.getTotalActual()));
    }
}