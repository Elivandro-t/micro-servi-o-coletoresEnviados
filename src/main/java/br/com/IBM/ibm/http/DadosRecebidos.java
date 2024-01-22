package br.com.IBM.ibm.http;

import br.com.IBM.ibm.Dto.RecebidosDTO;
import br.com.IBM.ibm.models.ColetoresDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "recebidos-ms")
public interface DadosRecebidos {
    @RequestMapping(method = RequestMethod.POST,value = "/receber")
    ColetoresDTO dadosrecebidos(@RequestBody RecebidosDTO recebidosDTO);


}
