package br.com.IBM.ibm.Dto;

import br.com.IBM.ibm.models.StatusBanco;

import java.util.List;

public record StatusBancoDTo(Long id, String name) {
    public StatusBancoDTo(StatusBanco statusBanco) {
        this(statusBanco.getId(),statusBanco.getName());
    }

}
