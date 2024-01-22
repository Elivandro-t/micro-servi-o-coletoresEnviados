package br.com.IBM.ibm.models;

import br.com.IBM.ibm.Dto.AtualizarDadosDTO;
import br.com.IBM.ibm.Dto.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "coletores_enviados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColetoresEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "dado nao podem ser nulo")
    private String patrimonio;
    @NotBlank(message = "dado nao podem ser nulo")
    private String mac;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean ativo;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "enviados")
    private Envio itens ;

   public void atualizar(AtualizarDadosDTO dados){
       this.getItens().setSetor(dados.getItens().getSetor());
       this.getItens().setModelo(dados.getItens().getModelo());
       this.setMac(dados.getMac());
       this.setPatrimonio(dados.getPatrimonio());
       this.getItens().setObs(dados.getItens().getObs());

   }

}
