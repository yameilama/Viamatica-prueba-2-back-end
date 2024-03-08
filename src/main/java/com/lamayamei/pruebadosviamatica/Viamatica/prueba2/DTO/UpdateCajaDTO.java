package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCajaDTO {

    private Long cajaId;
   private List<Long> assignedUserIds;
   private List<Long> workingUserIds;

}
