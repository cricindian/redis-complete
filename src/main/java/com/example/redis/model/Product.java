package com.example.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    @NotNull(message = "Id filed Mandatory")
    Integer id;
    @NotNull(message = "Name filed mandatory")
    String name;
    @NotNull(message = "Description filed mandatory")
    String description;
    @Min(1)
    Double price;
    @Valid
    List<Item> items;
}
