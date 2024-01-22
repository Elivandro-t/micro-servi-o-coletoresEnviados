package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.EnviosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class ColetoresEnvioDTO {
    Long id;
    String patrimonio;
    String mac;
    Status status;
    boolean ativo;
    EnviosDTO itens;
}
