/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import reporte.ReporteVentasDTO;
import reporte.VentaLineaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marki
 */
public class PantallaReporteVentas extends JFrame{
    private final ControlPresentacionReporte mediador;
    private final ReporteVentasDTO reporte;

    private static final Color VERDE_PRIMARIO  = new Color(56, 142, 60);
    private static final Color VERDE_CLARO     = new Color(200, 230, 201);
    private static final Color FONDO           = new Color(248, 248, 245);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PantallaReporteVentas(ControlPresentacionReporte mediador, ReporteVentasDTO reporte) {
        this.mediador = mediador;
        this.reporte  = reporte;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Reporte de Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        JPanel contenedor = new JPanel(new BorderLayout(0, 0));
        contenedor.setBackground(FONDO);

       
        contenedor.add(crearCabecera(), BorderLayout.NORTH);

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setBackground(FONDO);
        cuerpo.setBorder(new EmptyBorder(16, 20, 16, 20));

        cuerpo.add(crearPanelKPIs());
        cuerpo.add(Box.createVerticalStrut(16));
        cuerpo.add(crearPanelGraficas());
        cuerpo.add(Box.createVerticalStrut(16));
        cuerpo.add(crearPanelTabla());
        cuerpo.add(Box.createVerticalStrut(16));
        cuerpo.add(crearBotonResultados());

        JScrollPane scroll = new JScrollPane(cuerpo);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        contenedor.add(scroll, BorderLayout.CENTER);

        setContentPane(contenedor);
        setPreferredSize(new Dimension(860, 680));
        pack();
    }


    private JPanel crearCabecera() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(14, 20, 14, 20));

        JButton btnVolver = new JButton("← Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(VERDE_PRIMARIO);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> mediador.regresarAConfig(this));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Reporte de Ventas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitulo.setForeground(Color.DARK_GRAY);

        String periodo = reporte.getFechaInicio().format(FMT) + " – " + reporte.getFechaFin().format(FMT);
        JLabel lblPeriodo = new JLabel("Periodo: " + periodo);
        lblPeriodo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPeriodo.setForeground(Color.GRAY);

        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblPeriodo);

        panel.add(btnVolver, BorderLayout.WEST);
        panel.add(panelTitulo, BorderLayout.CENTER);
        return panel;
    }


    private JPanel crearPanelKPIs() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 0));
        panel.setBackground(FONDO);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        panel.add(crearTarjetaKPI("$", "TOTAL INGRESOS",
                String.format("$ %.2f", reporte.getTotalIngresos()), VERDE_PRIMARIO));
        panel.add(crearTarjetaKPI("▲", "KILOS VENDIDOS",
                String.format("%.3f KG", reporte.getTotalKilosVendidos()), new Color(21, 101, 192)));
        return panel;
    }

    private JPanel crearTarjetaKPI(String icono, String etiqueta, String valor, Color color) {
        JPanel t = new JPanel(new BorderLayout(8, 0));
        t.setBackground(Color.WHITE);
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(12, 16, 12, 16)));

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblIcono.setForeground(color);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBackground(Color.WHITE);

        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblEtiqueta.setForeground(Color.GRAY);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblValor.setForeground(Color.DARK_GRAY);

        panelTexto.add(lblEtiqueta);
        panelTexto.add(lblValor);

        t.add(lblIcono, BorderLayout.WEST);
        t.add(panelTexto, BorderLayout.CENTER);
        return t;
    }


    private JPanel crearPanelGraficas() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 0));
        panel.setBackground(FONDO);
        panel.setAlignmentX(LEFT_ALIGNMENT);

        // Datos para gráfica de barras
        Map<String, Double> datosBarras = new LinkedHashMap<>();
        if (reporte.getIngresosTortilla() > 0) datosBarras.put("Tortilla", reporte.getIngresosTortilla());
        if (reporte.getIngresosMasa()     > 0) datosBarras.put("Masa",     reporte.getIngresosMasa());
        if (datosBarras.isEmpty())             datosBarras.put("Sin ventas", 0.0);

        // Datos para gráfica de pie
        Map<String, Double> datosPie = new LinkedHashMap<>();
        if (reporte.getTotalEfectivo() > 0) datosPie.put("Efectivo",  reporte.getTotalEfectivo());
        if (reporte.getTotalDebito()   > 0) datosPie.put("Débito",    reporte.getTotalDebito());
        if (reporte.getTotalCredito()  > 0) datosPie.put("Crédito",   reporte.getTotalCredito());
        if (datosPie.isEmpty())             datosPie.put("Sin ventas", 1.0);

        panel.add(envolverGrafica(new GraficaBarrasPanel("Ganancia monetaria por producto", datosBarras)));
        panel.add(envolverGrafica(new GraficaPiePanel("Ventas por método de pago", datosPie)));
        return panel;
    }

    private JPanel envolverGrafica(JPanel grafica) {
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(12, 12, 12, 12)));
        contenedor.setPreferredSize(new Dimension(0, 220));
        contenedor.add(grafica, BorderLayout.CENTER);
        return contenedor;
    }


    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(12, 16, 12, 16)));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel("Detalle de Ventas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(Color.DARK_GRAY);
        panel.add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Fecha", "Producto", "Cantidad", "Total", "Método de Pago"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (VentaLineaDTO linea : reporte.getLineas()) {
            modelo.addRow(new Object[]{
                linea.getFecha() != null ? sdf.format(linea.getFecha()) : "—",
                linea.getProducto(),
                String.format("%.3f KG", linea.getKilos()),
                String.format("$ %.2f", linea.getTotal()),
                linea.getMetodoPago()
            });
        }

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(VERDE_CLARO);
        tabla.setSelectionBackground(VERDE_CLARO);
        tabla.setGridColor(new Color(235, 235, 235));
        tabla.setShowVerticalLines(false);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 200));
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }


    private JPanel crearBotonResultados() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(FONDO);
        panel.setAlignmentX(LEFT_ALIGNMENT);

        JButton btn = new JButton("Ver Resultados del Periodo");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(VERDE_PRIMARIO);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(240, 38));
        btn.addActionListener(e -> mediador.verResultadosCompletos(this));
        panel.add(btn);
        return panel;
    }


    static class GraficaBarrasPanel extends JPanel {
        private final String titulo;
        private final Map<String, Double> datos;
        private static final Color[] COLORES = {
            new Color(56, 142, 60), new Color(139, 195, 74), new Color(27, 94, 32)
        };

        GraficaBarrasPanel(String titulo, Map<String, Double> datos) {
            this.titulo = titulo;
            this.datos  = datos;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int marginTop = 30, marginBottom = 30, marginSide = 20;
            int chartH = h - marginTop - marginBottom;
            int chartW = w - 2 * marginSide;

            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(titulo, marginSide, 18);

            if (datos == null || datos.isEmpty()) return;

            double maxVal = datos.values().stream().mapToDouble(Double::doubleValue).max().orElse(1);
            if (maxVal == 0) maxVal = 1;

            int n = datos.size();
            int barW = Math.min(60, (chartW / n) - 20);
            int spacing = (chartW - n * barW) / (n + 1);

            int i = 0;
            for (Map.Entry<String, Double> e : datos.entrySet()) {
                int barH   = (int) ((e.getValue() / maxVal) * chartH);
                int barX   = marginSide + spacing + i * (barW + spacing);
                int barY   = marginTop + (chartH - barH);
                int baseY  = marginTop + chartH;


                g2.setColor(COLORES[i % COLORES.length]);
                g2.fillRoundRect(barX, barY, barW, barH, 6, 6);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                g2.setColor(Color.DARK_GRAY);
                String val = String.format("$%.0f", e.getValue());
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(val, barX + (barW - fm.stringWidth(val)) / 2, barY - 4);

                g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                g2.setColor(Color.GRAY);
                String lbl = e.getKey();
                g2.drawString(lbl, barX + (barW - fm.stringWidth(lbl)) / 2, baseY + 16);
                i++;
            }
        }
    }


    static class GraficaPiePanel extends JPanel {
        private final String titulo;
        private final Map<String, Double> datos;
        private static final Color[] COLORES = {
            new Color(56, 142, 60), new Color(205, 170, 70), new Color(21, 101, 192)
        };

        GraficaPiePanel(String titulo, Map<String, Double> datos) {
            this.titulo = titulo;
            this.datos  = datos;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(titulo, 12, 18);

            if (datos == null || datos.isEmpty()) return;

            double total = datos.values().stream().mapToDouble(Double::doubleValue).sum();
            if (total == 0) return;

            int size    = Math.min(w / 2 - 20, h - 50);
            int pieX    = 12;
            int pieY    = (h - size) / 2 + 10;

            double startAngle = 0;
            int i = 0;
            for (Map.Entry<String, Double> e : datos.entrySet()) {
                double angulo = (e.getValue() / total) * 360.0;
                g2.setColor(COLORES[i % COLORES.length]);
                g2.fill(new Arc2D.Double(pieX, pieY, size, size, startAngle, angulo, Arc2D.PIE));
                startAngle += angulo;
                i++;
            }

            int leyX = pieX + size + 16;
            int leyY = h / 2 - (datos.size() * 20) / 2;
            i = 0;
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            for (Map.Entry<String, Double> e : datos.entrySet()) {
                g2.setColor(COLORES[i % COLORES.length]);
                g2.fillRect(leyX, leyY + i * 22, 12, 12);
                g2.setColor(Color.DARK_GRAY);
                double pct = (e.getValue() / total) * 100;
                g2.drawString(e.getKey() + String.format(" %.0f%%", pct), leyX + 16, leyY + i * 22 + 11);
                i++;
            }
        }
    }
}
