package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.EnviosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ColetoresDadosRelatorios {
     String patrimonio;
    private String mac;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    EnviosDTO itens;
    private Status status;
}
