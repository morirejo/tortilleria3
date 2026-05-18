/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import reporte.ReporteVentasDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author marki
 */
public class PantallaResultadosPeriodo extends JFrame{
    private static final Color VERDE_PRIMARIO = new Color(56, 142, 60);
    private static final Color VERDE_FONDO    = new Color(232, 245, 233);
    private static final Color FONDO          = new Color(248, 248, 245);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PantallaResultadosPeriodo(ControlPresentacionReporte mediador, ReporteVentasDTO reporte) {
        initComponents(mediador, reporte);
        setLocationRelativeTo(null);
    }

    private void initComponents(ControlPresentacionReporte mediador, ReporteVentasDTO reporte) {
        setTitle("Resultados del Periodo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(FONDO);
        panel.setBorder(new EmptyBorder(24, 32, 24, 32));
        
        JLabel lblTitulo = label("Resultados del Periodo", 18, Font.BOLD, Color.DARK_GRAY);
        String periodo = reporte.getFechaInicio().format(FMT) + " – " + reporte.getFechaFin().format(FMT);
        JLabel lblPeriodo = label(periodo, 12, Font.PLAIN, Color.GRAY);
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        lblPeriodo.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblPeriodo);
        panel.add(Box.createVerticalStrut(20));

        panel.add(crearSeccion("Ingresos"));
        panel.add(Box.createVerticalStrut(8));

        if (reporte.getIngresosTortilla() > 0) {
            panel.add(crearFila("Ventas de Tortilla",
                    String.format("$ %.2f", reporte.getIngresosTortilla()), false));
        }
        if (reporte.getIngresosMasa() > 0) {
            panel.add(crearFila("Ventas de Masa",
                    String.format("$ %.2f", reporte.getIngresosMasa()), false));
        }
        panel.add(separador());
        panel.add(crearFila("Total Ingresos",
                String.format("$ %.2f", reporte.getTotalIngresos()), true));

        panel.add(Box.createVerticalStrut(20));

        panel.add(crearSeccion("Desglose por Método de Pago"));
        panel.add(Box.createVerticalStrut(8));

        if (reporte.getTotalEfectivo() > 0) {
            panel.add(crearFila("Efectivo",
                    String.format("$ %.2f", reporte.getTotalEfectivo()), false));
        }
        if (reporte.getTotalDebito() > 0) {
            panel.add(crearFila("Tarjeta Débito",
                    String.format("$ %.2f", reporte.getTotalDebito()), false));
        }
        if (reporte.getTotalCredito() > 0) {
            panel.add(crearFila("Tarjeta Crédito",
                    String.format("$ %.2f", reporte.getTotalCredito()), false));
        }

        panel.add(Box.createVerticalStrut(20));


        panel.add(crearTarjetaKilos(reporte));

        panel.add(Box.createVerticalStrut(24));


        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrar.setBackground(VERDE_PRIMARIO);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnCerrar.setAlignmentX(LEFT_ALIGNMENT);
        btnCerrar.addActionListener(e -> dispose());

        panel.add(btnCerrar);

        setContentPane(panel);
        pack();
        setMinimumSize(new Dimension(420, 0));
    }

    private JPanel crearSeccion(String titulo) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p.setBackground(FONDO);
        p.setAlignmentX(LEFT_ALIGNMENT);
        JLabel lbl = label(titulo, 12, Font.BOLD, Color.GRAY);
        p.add(lbl);
        return p;
    }

    private JPanel crearFila(String nombre, String valor, boolean negrita) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(FONDO);
        fila.setAlignmentX(LEFT_ALIGNMENT);
        fila.setBorder(new EmptyBorder(4, 0, 4, 0));

        int estilo = negrita ? Font.BOLD : Font.PLAIN;
        JLabel lblNombre = label(nombre, 13, estilo, Color.DARK_GRAY);
        JLabel lblValor  = label(valor,  13, estilo,
                negrita ? VERDE_PRIMARIO : Color.DARK_GRAY);

        fila.add(lblNombre, BorderLayout.WEST);
        fila.add(lblValor,  BorderLayout.EAST);
        return fila;
    }

    private JSeparator separador() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(200, 200, 200));
        sep.setAlignmentX(LEFT_ALIGNMENT);
        return sep;
    }

    private JPanel crearTarjetaKilos(ReporteVentasDTO reporte) {
        JPanel t = new JPanel(new BorderLayout(0, 4));
        t.setBackground(VERDE_FONDO);
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(165, 214, 167), 1, true),
                new EmptyBorder(12, 16, 12, 16)));
        t.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lblKilos = label(
                String.format("%.3f KG totales vendidos", reporte.getTotalKilosVendidos()),
                13, Font.BOLD, VERDE_PRIMARIO);
        JLabel lblDetalle = label(
                String.format("Tortilla: %.3f KG  |  Masa: %.3f KG",
                        reporte.getKilosTortilla(), reporte.getKilosMasa()),
                11, Font.PLAIN, new Color(33, 88, 36));

        t.add(lblKilos,   BorderLayout.NORTH);
        t.add(lblDetalle, BorderLayout.CENTER);
        return t;
    }

    private JLabel label(String texto, int size, int style, Color color) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        return l;
    }
}
