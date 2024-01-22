package br.com.IBM.ibm.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.stream.Stream;

@Getter
@Setter
public class ColetoresRelatoriodto{
        private String protocolo;
        private String setor;
        private String patrimonio; String mac;
        private LocalDate data;
        private String solicitante;
        private String descricao;
        private Status status;

        public ColetoresRelatoriodto(ColetoresDadosRelatorios e) {
                this.protocolo = e.itens.getProtocolo();
                this.setor = e.itens.getSetor();
                this.patrimonio = e.getPatrimonio();
                this.mac = e.getMac();
                this.data = e.getData();
                this.solicitante = e.itens.getSolicitante();
                this.descricao = e.itens.getObs();
                this.status = e.getStatus();
        }
}