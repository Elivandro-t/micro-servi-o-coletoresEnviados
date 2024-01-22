package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.ColetoresDTO;
import br.com.IBM.ibm.models.EnviosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
@JsonIgnoreProperties
public record RecebidosDTO(
        String protocolo,
        String setor,
        String modelo,
        String patrimonio,
        String mac
        ) {
    public RecebidosDTO(ColetoresEnvioDTO coletores) {
        this(coletores.itens.getProtocolo(),coletores.getItens().getSetor(),coletores.getItens().getModelo()
                ,coletores.patrimonio,coletores.mac
        );
    }
}

