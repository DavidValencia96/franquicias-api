package com.accenture.franquicias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxStockResponse {
    private String sucursalId;
    private String sucursalNombre;
    private String productoId;
    private String productoNombre;
    private Integer stock;
}