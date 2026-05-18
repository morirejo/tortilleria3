/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import com.mycompany.tortilleriadtos.DetalleVentaDTO;
import com.mycompany.tortilleriadtos.VentaDTO;
import com.mycompany.tortilleriadtos.VentaLocalDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MoriTejo
 */
public class MapeadorVenta {
    
    public static VentaBO convertirDTOaBO(VentaDTO dto) {
        List<DetalleVentaBO> carritoBO = new ArrayList<>();
        
        for (DetalleVentaDTO d : dto.getCarrito()) {
            carritoBO.add(new DetalleVentaBO(d.getNombreProducto(), d.getTipoProducto(), d.getCantidadKilos(), d.getSubtotal()));
        }

        if (dto instanceof VentaLocalDTO) {
            VentaLocalDTO local = (VentaLocalDTO) dto;
            return new VentaLocalBO(local.getIdVenta(), local.getMontoTotal(), local.getFechaHora(), local.getMetodoPago(), carritoBO);
        }
        
        return null; 
    }
    
    public static VentaDTO convertirBOaDTO(VentaBO bo) {
        List<DetalleVentaDTO> carritoDTO = new ArrayList<>();
        for (DetalleVentaBO d : bo.getCarrito()) {
            carritoDTO.add(new DetalleVentaDTO(d.getNombreProducto(), d.getTipoProducto(), d.getCantidadKilos(), d.getSubtotal()));
        }
        if (bo instanceof VentaLocalBO) {
            VentaLocalBO local = (VentaLocalBO) bo;
            return new VentaLocalDTO(local.getIdVenta(), local.getMontoTotal(), local.getFechaHora(), local.getMetodoPago(), carritoDTO);
        }
        return null;
    }
}
