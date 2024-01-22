package br.com.IBM.ibm.infra.service.validator;

import br.com.IBM.ibm.models.ColetoresEnvio;
import br.com.IBM.ibm.repository.ColetoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ValidadandoId {
    @Autowired
    private ColetoresRepository coletoresRepository;
    public ColetoresEnvio coletoresEnvio(Long id){
        var result = coletoresRepository.getReferenceById(id);
        if(result==null){
            throw new RuntimeException("dados nao encontrado");
        }
        return result;
    }
}
