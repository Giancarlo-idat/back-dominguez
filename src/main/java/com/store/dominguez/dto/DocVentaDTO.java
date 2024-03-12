package com.store.dominguez.dto;


import com.store.dominguez.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocVentaDTO extends BaseDTO {

    private String fechaPedido;
    private ClienteDTO cliente;
    private String tipoDoc;
    private MetodoPagoDTO metodoPago;
    private String subtotal;
    private String IGV;
    private String montoTotal;
    private Boolean estado = true;
}
