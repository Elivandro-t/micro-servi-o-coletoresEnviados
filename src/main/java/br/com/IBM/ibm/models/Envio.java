package br.com.IBM.ibm.models;

import br.com.IBM.ibm.Dto.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enviados")
public class Envio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String protocolo;
    @NotBlank
    private String setor;
    @NotBlank
    private String modelo;
    @NotBlank
    private String obs;
    @NotBlank
    private String solicitante;
    @OneToOne(optional = false)
    private ColetoresEnvio enviados;

    public String gerarNumero(){
        Random random = new Random();
        int numero = random.nextInt(1000);
        String formt = String.format("%04d",numero);
        return formt;
    }
}
