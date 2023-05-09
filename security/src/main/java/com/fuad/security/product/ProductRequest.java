package com.fuad.security.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {
    @NotNull(message = "Product Name is required")
    @Size(min = 2, max = 30)
    private String name;
    @NotNull(message = "Product Owner is required")
    @Size(min = 2, max = 30)
    private String owner;
    @NotNull(message = "Product Size is required")
    private Integer size;
}
