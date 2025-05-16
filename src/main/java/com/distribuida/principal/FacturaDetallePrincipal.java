package com.distribuida.principal;

import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.Libro;

public class FacturaDetallePrincipal {

    public static void main(String[] args) {
        FacturaDetalle facturaDetalle = new FacturaDetalle();


        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(12);
        facturaDetalle.setSubtotal(35.5);


    }

}
