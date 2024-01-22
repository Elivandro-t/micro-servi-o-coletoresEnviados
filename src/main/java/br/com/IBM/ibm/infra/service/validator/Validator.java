package br.com.IBM.ibm.infra.service.validator;

import br.com.IBM.ibm.models.ColetoresDTO;
import br.com.IBM.ibm.models.ColetoresEnvio;
import br.com.IBM.ibm.repository.ColetoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validator implements ValidadandoRegras{
    @Autowired
    private ColetoresRepository coletoresRepository;
    public void validation(ColetoresDTO coletoresDTO){
        var mac = coletoresRepository.pegarPorMa(coletoresDTO.getMac());
        var pat = coletoresRepository.pegarPorPat(coletoresDTO.getPatrimonio());
        if(mac.isPresent()||pat.isPresent()){
            throw new RuntimeException("ja existe um coletor em assistencia ");
        }
    }
    public void valida(ColetoresEnvio coletoresDTO){
                var dataConsulta = coletoresDTO.getData();
                var  agora = LocalDateTime.now();
                var diferencaemMinutos = Duration.between(agora,dataConsulta).toMinutes();
                if(diferencaemMinutos<30){
                    throw new RuntimeException(" deve ser  com antecedencia minima de 30 minutos");
                }


    }
}
