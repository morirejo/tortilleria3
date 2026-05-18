/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortillerianegocio;

import BOs.MapeadorVenta;
import BOs.VentaBO;
import com.mycompany.tortilleriadtos.VentaDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public class FachadaVenta implements IFachadaVentas {
    private IGestorVentas gestorVentas = new GestorVentas();

    @Override
    public double calcularTotal(double kilos) {
        return gestorVentas.calcularTotal(kilos);
    }

    @Override
    public boolean confirmarVentaLocal(VentaDTO ventaDTO, double efectivo) {
        VentaBO ventaBO = MapeadorVenta.convertirDTOaBO(ventaDTO);
        return gestorVentas.procesarVenta(ventaBO, efectivo);
    }

    @Override
    public List<VentaDTO> obtenerVentas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<VentaBO> listaBO = gestorVentas.obtenerVentasPorFecha(fechaInicio, fechaFin);
        return convertirListaBOaDTO(listaBO);
    }

    @Override
    public List<VentaDTO> obtenerTodasLasVentas() {
        List<VentaBO> listaBO = gestorVentas.obtenerTodasLasVentas();
        return convertirListaBOaDTO(listaBO);
    }

    @Override
    public double calcularTotalVentas(List<VentaDTO> ventasDTO) {
        return gestorVentas.calcularTotaldeVentas(convertirListaDTOaBO(ventasDTO));
    }

    @Override
    public double calcularKilosTotales(List<VentaDTO> ventasDTO) {
        return gestorVentas.calcularKilosTotales(convertirListaDTOaBO(ventasDTO));
    }

    @Override
    public boolean cancelarVenta(int idVenta) {
        return gestorVentas.cancelarVenta(idVenta);
    }
    

    private List<VentaDTO> convertirListaBOaDTO(List<VentaBO> listaBO) {
        List<VentaDTO> listaDTO = new ArrayList<>();
        for (VentaBO bo : listaBO) {
            listaDTO.add(MapeadorVenta.convertirBOaDTO(bo));
        }
        return listaDTO;
    }

    private List<VentaBO> convertirListaDTOaBO(List<VentaDTO> listaDTO) {
        List<VentaBO> listaBO = new ArrayList<>();
        for (VentaDTO dto : listaDTO) {
            listaBO.add(MapeadorVenta.convertirDTOaBO(dto));
        }
        return listaBO;
    }
}
