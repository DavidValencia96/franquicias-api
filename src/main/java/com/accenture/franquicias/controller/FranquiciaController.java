package com.accenture.franquicias.controller;

import com.accenture.franquicias.dto.MaxStockResponse;
import com.accenture.franquicias.dto.NombreUpdateDto;
import com.accenture.franquicias.model.Franquicia;
import com.accenture.franquicias.model.Producto;
import com.accenture.franquicias.model.Sucursal;
import com.accenture.franquicias.service.FranquiciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService service;

    // Criterio 2: Agregar nueva franquicia
    @PostMapping
    public Mono<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        return service.crearFranquicia(franquicia);
    }

    // Criterio 3: Agregar nueva sucursal
    @PostMapping("/{franquiciaId}/sucursales")
    public Mono<Franquicia> agregarSucursal(@PathVariable String franquiciaId, @RequestBody Sucursal sucursal) {
        return service.agregarSucursal(franquiciaId, sucursal);
    }

    // Criterio 4: Agregar nuevo producto
    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public Mono<Franquicia> agregarProducto(@PathVariable String franquiciaId,
                                            @PathVariable String sucursalId,
                                            @RequestBody Producto producto) {
        return service.agregarProducto(franquiciaId, sucursalId, producto);
    }

    // Criterio 5: Eliminar producto
    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public Mono<Franquicia> eliminarProducto(@PathVariable String franquiciaId,
                                              @PathVariable String sucursalId,
                                              @PathVariable String productoId) {
        return service.eliminarProducto(franquiciaId, sucursalId, productoId);
    }

    // Criterio 6: Modificar stock de producto
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public Mono<Franquicia> modificarStock(@PathVariable String franquiciaId,
                                           @PathVariable String sucursalId,
                                           @PathVariable String productoId,
                                           @RequestParam Integer nuevoStock) {
        return service.modificarStock(franquiciaId, sucursalId, productoId, nuevoStock);
    }

    // Criterio 7: Producto con más stock por sucursal
    @GetMapping("/{franquiciaId}/max-stock")
    public Flux<MaxStockResponse> obtenerMaxStock(@PathVariable String franquiciaId) {
        return service.obtenerMaxStockPorSucursal(franquiciaId);
    }

    // PLUS: Actualizar nombre franquicia
    @PatchMapping("/{franquiciaId}/nombre")
    public Mono<Franquicia> actualizarNombreFranquicia(@PathVariable String franquiciaId, @RequestBody NombreUpdateDto dto) {
        return service.actualizarNombreFranquicia(franquiciaId, dto.getNombre());
    }

    // PLUS: Actualizar nombre sucursal
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/nombre")
    public Mono<Franquicia> actualizarNombreSucursal(@PathVariable String franquiciaId,
                                                      @PathVariable String sucursalId,
                                                      @RequestBody NombreUpdateDto dto) {
        return service.actualizarNombreSucursal(franquiciaId, sucursalId, dto.getNombre());
    }

    // PLUS: Actualizar nombre producto
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/nombre")
    public Mono<Franquicia> actualizarNombreProducto(@PathVariable String franquiciaId,
                                                      @PathVariable String sucursalId,
                                                      @PathVariable String productoId,
                                                      @RequestBody NombreUpdateDto dto) {
        return service.actualizarNombreProducto(franquiciaId, sucursalId, productoId, dto.getNombre());
    }
}