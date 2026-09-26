package com.accenture.franquicias.service;

import com.accenture.franquicias.dto.MaxStockResponse;
import com.accenture.franquicias.model.Franquicia;
import com.accenture.franquicias.model.Producto;
import com.accenture.franquicias.model.Sucursal;
import com.accenture.franquicias.repository.FranquiciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class FranquiciaService {

    private final FranquiciaRepository repository;

    // 1. Crear franquicia
    public Mono<Franquicia> crearFranquicia(Franquicia franquicia) {
        return repository.save(franquicia);
    }

    // 2. Agregar sucursal a franquicia
    public Mono<Franquicia> agregarSucursal(String franquiciaId, Sucursal sucursal) {
        return repository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Franquicia no encontrada")))
                .flatMap(franquicia -> {
                    franquicia.getSucursales().add(sucursal);
                    return repository.save(franquicia);
                });
    }

    // 3. Agregar producto a sucursal
    public Mono<Franquicia> agregarProducto(String franquiciaId, String sucursalId, Producto producto) {
        return repository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Franquicia no encontrada")))
                .flatMap(franquicia -> {
                    Sucursal sucursal = franquicia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));
                    
                    sucursal.getProductos().add(producto);
                    return repository.save(franquicia);
                });
    }

    // 4. Eliminar producto de sucursal
    public Mono<Franquicia> eliminarProducto(String franquiciaId, String sucursalId, String productoId) {
        return repository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Franquicia no encontrada")))
                .flatMap(franquicia -> {
                    Sucursal sucursal = franquicia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));

                    sucursal.getProductos().removeIf(p -> p.getId().equals(productoId));
                    return repository.save(franquicia);
                });
    }

    // 5. Modificar stock de producto
    public Mono<Franquicia> modificarStock(String franquiciaId, String sucursalId, String productoId, Integer nuevoStock) {
        return repository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Franquicia no encontrada")))
                .flatMap(franquicia -> {
                    Sucursal sucursal = franquicia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));

                    Producto producto = sucursal.getProductos().stream()
                            .filter(p -> p.getId().equals(productoId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

                    producto.setStock(nuevoStock);
                    return repository.save(franquicia);
                });
    }

    // 6. Producto con más stock por sucursal para una franquicia
    public Flux<MaxStockResponse> obtenerMaxStockPorSucursal(String franquiciaId) {
        return repository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Franquicia no encontrada")))
                .flatMapMany(franquicia -> Flux.fromIterable(franquicia.getSucursales()))
                .flatMap(sucursal -> {
                    return Mono.justOrEmpty(
                            sucursal.getProductos().stream()
                                    .max(Comparator.comparingInt(Producto::getStock))
                    ).map(p -> new MaxStockResponse(
                            sucursal.getId(),
                            sucursal.getNombre(),
                            p.getId(),
                            p.getNombre(),
                            p.getStock()
                    ));
                });
    }

    // PLUS: Actualizar nombre de Franquicia
    public Mono<Franquicia> actualizarNombreFranquicia(String franquiciaId, String nuevoNombre) {
        return repository.findById(franquiciaId)
                .flatMap(f -> {
                    f.setNombre(nuevoNombre);
                    return repository.save(f);
                });
    }

    // PLUS: Actualizar nombre de Sucursal
    public Mono<Franquicia> actualizarNombreSucursal(String franquiciaId, String sucursalId, String nuevoNombre) {
        return repository.findById(franquiciaId)
                .flatMap(f -> {
                    f.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .ifPresent(s -> s.setNombre(nuevoNombre));
                    return repository.save(f);
                });
    }

    // PLUS: Actualizar nombre de Producto
    public Mono<Franquicia> actualizarNombreProducto(String franquiciaId, String sucursalId, String productoId, String nuevoNombre) {
        return repository.findById(franquiciaId)
                .flatMap(f -> {
                    f.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .ifPresent(s -> s.getProductos().stream()
                                    .filter(p -> p.getId().equals(productoId))
                                    .findFirst()
                                    .ifPresent(p -> p.setNombre(nuevoNombre)));
                    return repository.save(f);
                });
    }
}