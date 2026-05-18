/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortillerianegocio;

import BOs.MapeadorVenta;
import BOs.VentaBO;
import BOs.VentaLocalBO;
import com.mycompany.tortilleriadatos.IVentaDAO;
import com.mycompany.tortilleriadatos.VentaDAO;
import com.mycompany.tortilleriadtos.VentaDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author MoriTejo
 */
public class GestorVentas implements IGestorVentas{
    private IVentaDAO ventaDAO = new VentaDAO();
    
    @Override
    public double calcularTotal(double kilos) {
        return kilos * 27.00;
    }

    @Override
    public boolean procesarVenta(VentaBO venta, double efectivoRecibido) {
        if (venta instanceof VentaLocalBO) {
            VentaLocalBO ventaLocal = (VentaLocalBO) venta;
            if (ventaLocal.getMetodoPago().equalsIgnoreCase("Efectivo") && efectivoRecibido < ventaLocal.getMontoTotal()) {
                return false; 
            }
        }
        VentaDTO ventaParaGuardar = MapeadorVenta.convertirBOaDTO(venta);
        return ventaDAO.guardarVenta(ventaParaGuardar);
    }
    
    @Override
    public List<VentaBO> obtenerVentasPorFecha(LocalDate inicio, LocalDate fin) {
        List<VentaDTO> listaDTO = ventaDAO.ventasPorFecha(inicio, fin);
        List<VentaBO> listaBO = new ArrayList<>();
        for (VentaDTO dto : listaDTO) {
            listaBO.add(MapeadorVenta.convertirDTOaBO(dto));
        }
        return listaBO;
    }

    @Override
    public List<VentaBO> obtenerTodasLasVentas() {
        List<VentaDTO> listaDTO = ventaDAO.obtenerTodasLasVentas();
        List<VentaBO> listaBO = new ArrayList<>();
        for (VentaDTO dto : listaDTO) {
            listaBO.add(MapeadorVenta.convertirDTOaBO(dto));
        }
        return listaBO;
    }

    @Override
    public double calcularTotaldeVentas(List<VentaBO> ventas) {
        return ventas.stream().mapToDouble(VentaBO::getMontoTotal).sum();
    }

    @Override
    public double calcularKilosTotales(List<VentaBO> ventas) {
        double totalKilos = 0.0;
        for (VentaBO venta : ventas) {
            if (venta.getCarrito() != null) {
                for (var producto : venta.getCarrito()) {
                    totalKilos += producto.getCantidadKilos();
                }
            }
        }
        return totalKilos;
    }

    @Override
    public boolean cancelarVenta(int idVenta) {
        return ventaDAO.cancelarVenta(idVenta);
    }
}
