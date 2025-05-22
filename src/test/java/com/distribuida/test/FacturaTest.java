package com.distribuida.test;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacturaTest {

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup(){
        factura = new Factura();

        cliente = new Cliente(1,"176123031265","Juan","Av.Americas","Taipe","0991654355","jauntaioe@gmail.com");

        factura.setIdFactura(1);
       factura.setNumFactura("FAC-00001");
       factura.setFecha(new Date());
       factura.setTotalNeto(100.00);
       factura.setIva(15.00);
       factura.setTotal(115.00);
        //inyeccion de dependencias
       factura.setCliente(cliente);
    }

    @Test
    public void testFacturaConstructorAndGetters(){

        assertAll("Validar datos factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-00001", factura.getNumFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertEquals("Juan", factura.getCliente().getNombre())

        );

    }




}