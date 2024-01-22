package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.EnviosDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarDadosDTO {
    Long id;
    private String patrimonio;
    private String mac;
    private String status;
    private String data;
    private EnvioUpdateDTO itens;


}
