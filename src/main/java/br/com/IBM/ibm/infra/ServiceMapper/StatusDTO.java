package br.com.IBM.ibm.infra.ServiceMapper;

import br.com.IBM.ibm.Dto.Status;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {
    @JsonAlias("status")
    String status;
}
