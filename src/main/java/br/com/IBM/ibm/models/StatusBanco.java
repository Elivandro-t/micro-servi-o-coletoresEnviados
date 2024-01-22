package br.com.IBM.ibm.models;

import br.com.IBM.ibm.Dto.StatusBancoDTo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusBanco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;

    public StatusBanco(StatusBancoDTo statusBancoDTo) {
        this.name = statusBancoDTo.name();
    }
}
