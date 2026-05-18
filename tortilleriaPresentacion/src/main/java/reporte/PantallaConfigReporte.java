/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author marki
 */
public class PantallaConfigReporte extends JFrame{
    private final ControlPresentacionReporte mediador;

    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JRadioButton rbVentasTotales;
    private JRadioButton rbPorMetodoPago;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Color VERDE_PRIMARIO = new Color(56, 142, 60);
    private static final Color VERDE_CLARO = new Color(200, 230, 201);
    private static final Color FONDO = new Color(245, 245, 240);
    /**
     * Creates new form PantallaConfigReporte
     */
    public PantallaConfigReporte(ControlPresentacionReporte mediador) {
        this.mediador = mediador;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Sistema de Reportes – Tortillería");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(FONDO);
        panelPrincipal.setBorder(new EmptyBorder(30, 40, 30, 40));

        // ── Encabezado ──────────────────────────────────────────────────────
        JLabel lblTitulo = crearLabel("Sistema de Reportes", 20, Font.BOLD, Color.DARK_GRAY);
        JLabel lblSub    = crearLabel("Tortillería", 13, Font.PLAIN, Color.GRAY);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        lblSub.setAlignmentX(CENTER_ALIGNMENT);

        // ── Tarjeta de configuración ─────────────────────────────────────────
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(20, 24, 20, 24)));
        tarjeta.setAlignmentX(CENTER_ALIGNMENT);

        // Título de la tarjeta
        JLabel lblConfig = crearLabel("Configuración del Reporte", 14, Font.BOLD, Color.DARK_GRAY);
        lblConfig.setAlignmentX(LEFT_ALIGNMENT);

        // ── Rango de fechas ──────────────────────────────────────────────────
        JLabel lblRango = crearLabel("RANGO DE FECHAS:", 11, Font.BOLD, Color.GRAY);
        lblRango.setAlignmentX(LEFT_ALIGNMENT);

        // Botones rápidos
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setAlignmentX(LEFT_ALIGNMENT);

        JButton btnDia    = crearBotonFecha("Día");
        JButton btnSemana = crearBotonFecha("Semana");
        JButton btnMes    = crearBotonFecha("Mes");

        panelBotones.add(btnDia);
        panelBotones.add(btnSemana);
        panelBotones.add(btnMes);

        // Campos manuales
        JPanel panelFechas = new JPanel(new GridLayout(2, 2, 8, 6));
        panelFechas.setBackground(Color.WHITE);
        panelFechas.setAlignmentX(LEFT_ALIGNMENT);

        txtFechaInicio = new JTextField(LocalDate.now().format(FMT));
        txtFechaFin    = new JTextField(LocalDate.now().format(FMT));
        estilizarCampo(txtFechaInicio);
        estilizarCampo(txtFechaFin);

        panelFechas.add(crearLabel("Inicio (dd/MM/yyyy):", 12, Font.PLAIN, Color.DARK_GRAY));
        panelFechas.add(txtFechaInicio);
        panelFechas.add(crearLabel("Fin (dd/MM/yyyy):", 12, Font.PLAIN, Color.DARK_GRAY));
        panelFechas.add(txtFechaFin);

        // Acciones de botones rápidos
        btnDia.addActionListener(e -> {
            String hoy = LocalDate.now().format(FMT);
            txtFechaInicio.setText(hoy);
            txtFechaFin.setText(hoy);
            resaltarBoton(btnDia, btnSemana, btnMes);
        });
        btnSemana.addActionListener(e -> {
            txtFechaInicio.setText(LocalDate.now().minusDays(6).format(FMT));
            txtFechaFin.setText(LocalDate.now().format(FMT));
            resaltarBoton(btnSemana, btnDia, btnMes);
        });
        btnMes.addActionListener(e -> {
            txtFechaInicio.setText(LocalDate.now().withDayOfMonth(1).format(FMT));
            txtFechaFin.setText(LocalDate.now().format(FMT));
            resaltarBoton(btnMes, btnDia, btnSemana);
        });

        // ── Tipo de reporte ──────────────────────────────────────────────────
        JLabel lblTipo = crearLabel("TIPO DE REPORTE:", 11, Font.BOLD, Color.GRAY);
        lblTipo.setAlignmentX(LEFT_ALIGNMENT);

        rbVentasTotales  = new JRadioButton("Ventas Totales", true);
        rbPorMetodoPago  = new JRadioButton("Ventas por Método de Pago");
        estilizarRadio(rbVentasTotales);
        estilizarRadio(rbPorMetodoPago);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbVentasTotales);
        grupo.add(rbPorMetodoPago);

        JPanel panelRadios = new JPanel();
        panelRadios.setLayout(new BoxLayout(panelRadios, BoxLayout.Y_AXIS));
        panelRadios.setBackground(Color.WHITE);
        panelRadios.setAlignmentX(LEFT_ALIGNMENT);
        panelRadios.add(rbVentasTotales);
        panelRadios.add(rbPorMetodoPago);

        // ── Botón Generar ────────────────────────────────────────────────────
        JButton btnGenerar = new JButton("GENERAR REPORTE");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGenerar.setBackground(VERDE_PRIMARIO);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setBorderPainted(false);
        btnGenerar.setOpaque(true);
        btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGenerar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnGenerar.setAlignmentX(LEFT_ALIGNMENT);
        btnGenerar.addActionListener(e -> onGenerarReporte());

        JLabel lblHint = crearLabel("Selecciona el periodo y tipo de reporte para comenzar", 11, Font.ITALIC, Color.GRAY);
        lblHint.setAlignmentX(LEFT_ALIGNMENT);

        // Ensamblar tarjeta
        tarjeta.add(lblConfig);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(lblRango);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(panelBotones);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(panelFechas);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(lblTipo);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(panelRadios);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(btnGenerar);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(lblHint);

        // Ensamblar panel principal
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(4));
        panelPrincipal.add(lblSub);
        panelPrincipal.add(Box.createVerticalStrut(24));
        panelPrincipal.add(tarjeta);

        setContentPane(panelPrincipal);
        pack();
        setMinimumSize(new Dimension(340, 0));
    }
     private void onGenerarReporte() {
        try {
            LocalDate inicio = LocalDate.parse(txtFechaInicio.getText().trim(), FMT);
            LocalDate fin    = LocalDate.parse(txtFechaFin.getText().trim(), FMT);
            if (inicio.isAfter(fin)) {
                JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }
            mediador.procesarReporte(inicio, fin, this);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Usa dd/MM/yyyy.");
        }
    }

    // ── Helpers de estilo ────────────────────────────────────────────────────

    private JLabel crearLabel(String texto, int size, int style, Color color) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        return l;
    }

    private JButton crearBotonFecha(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        b.setBackground(VERDE_CLARO);
        b.setForeground(new Color(33, 88, 36));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void resaltarBoton(JButton activo, JButton... otros) {
        activo.setBackground(VERDE_PRIMARIO);
        activo.setForeground(Color.WHITE);
        for (JButton b : otros) {
            b.setBackground(VERDE_CLARO);
            b.setForeground(new Color(33, 88, 36));
        }
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(4, 6, 4, 6)));
    }

    private void estilizarRadio(JRadioButton rb) {
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setBackground(Color.WHITE);
        rb.setForeground(Color.DARK_GRAY);
    }
}
