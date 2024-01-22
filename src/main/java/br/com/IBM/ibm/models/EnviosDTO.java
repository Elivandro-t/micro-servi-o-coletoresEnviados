package br.com.IBM.ibm.models;

import br.com.IBM.ibm.Dto.Status;
import br.com.IBM.ibm.models.ColetoresEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnviosDTO {
      private  Long id;
      private String setor;
      private String modelo;
      private String protocolo;
      private String obs;
      private String solicitante;
}
