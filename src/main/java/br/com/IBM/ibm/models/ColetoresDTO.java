package br.com.IBM.ibm.models;

import br.com.IBM.ibm.Dto.Status;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ColetoresDTO {
 Long id;
 String patrimonio;
 String mac;
 @JsonFormat(pattern = "dd/MM/yyyy")
 LocalDate data;
 @JsonProperty("status")
 Status status;
 boolean ativo;
 EnviosDTO itens;




}
