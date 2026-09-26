package com.accenture.franquicias.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private Integer stock;
}