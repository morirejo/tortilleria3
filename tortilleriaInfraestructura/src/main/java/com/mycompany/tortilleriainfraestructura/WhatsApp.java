/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortilleriainfraestructura;

/**
 *
 * @author MoriTejo
 */
public class WhatsApp implements IServicioNotificacion {
    @Override
    public boolean enviarTicket(String numero, String mensaje) {
        System.out.println("Enviar whatspas");
        return false;
    }
}
