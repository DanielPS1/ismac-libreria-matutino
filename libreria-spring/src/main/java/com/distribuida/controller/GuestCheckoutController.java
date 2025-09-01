package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.model.Factura;
import com.distribuida.service.CarritoService;
import com.distribuida.service.GuestCheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/guest/checkout")
public class GuestCheckoutController {

    private final GuestCheckoutService guestCheckoutService;
    private final CarritoService carritoService;

    public GuestCheckoutController(GuestCheckoutService checkoutService,
                                   CarritoService carritoService) {
        this.guestCheckoutService = checkoutService;
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestParam String token) {
        try {
            System.out.println("=== INICIO CHECKOUT ===");
            System.out.println("Token recibido: " + token);

            // Usar getByToken en lugar de getOrCreateByToken
            Carrito carrito = carritoService.getByToken(token);

            if (carrito == null) {
                System.out.println("ERROR: Carrito no encontrado para token: " + token);
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("message", "Carrito no encontrado. Token inválido o expirado"));
            }

            System.out.println("Carrito encontrado. Items: " +
                    (carrito.getItems() != null ? carrito.getItems().size() : 0));

            if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
                System.out.println("ERROR: Carrito vacío");
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("message", "Carrito vacío, no se puede procesar el checkout"));
            }

            System.out.println("Procesando checkout...");
            Factura factura = guestCheckoutService.checkoutByToken(token);

            System.out.println("=== CHECKOUT EXITOSO ===");
            return ResponseEntity.ok(factura);

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            System.out.println("ERROR INTERNO: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }
}