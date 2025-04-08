package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor

public class SupplierDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
}
