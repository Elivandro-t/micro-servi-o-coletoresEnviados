package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.ColetoresDTO;
import br.com.IBM.ibm.models.EnviosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosColetoresDTO(
        String patrimonio,
        String setor,
        String mac,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        Status status,
        boolean ativo,
        EnviosDTO itens) {
    public DadosColetoresDTO(ColetoresDTO coletores) {
        this(coletores.getPatrimonio(),coletores.getItens().getSetor(),coletores.getMac()
                ,coletores.getData(),coletores.getStatus(),coletores.isAtivo(),coletores.getItens());
    }
}
