package br.com.IBM.ibm.controlers;

import br.com.IBM.ibm.Dto.*;
import br.com.IBM.ibm.models.ColetoresDTO;
import br.com.IBM.ibm.infra.ServiceMapper.StatusDTO;
import br.com.IBM.ibm.infra.service.EnviadosService;
import br.com.IBM.ibm.models.StatusBanco;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/service/api")
public class Controler {
    @Autowired
    private EnviadosService enviadosService;
    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DadosColetoresDTO> registrar(@RequestBody @Valid ColetoresDTO coletoresDTO, UriComponentsBuilder uriBuilder){
        var coletores = enviadosService.registrar(coletoresDTO);
        var uri = uriBuilder.path("/{id}").buildAndExpand(coletoresDTO.getId()).toUri();
           return ResponseEntity.created(uri).body(new DadosColetoresDTO(coletores));

    }
    @GetMapping("/lista")
    public  ResponseEntity<Page<ColetoresDTO>> Lista(@PageableDefault Pageable paginacao){
        var lista = enviadosService.Lista(paginacao);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/lista/relatorio")
    public  ResponseEntity<Page<ColetoresRelatoriodto>> relatorio(@PageableDefault Pageable paginacao){
        var lista = enviadosService.ListasRelatorio(paginacao);
        var name = lista.map(ColetoresRelatoriodto::new);
        return ResponseEntity.ok(name);
    }
    @GetMapping("/entregues")
    public  ResponseEntity<Page<ColetoresDTO>> entregues(@PageableDefault Pageable paginacao){
        var lista = enviadosService.enviados(paginacao);
        return ResponseEntity.ok(lista);
    }
    // editar status
    @PutMapping("/{id}/status")
    @Transactional
    public  ResponseEntity<ColetoresDTO> List(@PathVariable Long id,@RequestBody StatusDTO statusDTO){
        var lista = enviadosService.MudarStatus(id,statusDTO);
        if(lista==null){
            throw new IllegalArgumentException();
        }
        return ResponseEntity.ok(lista);
    }
    // mudar status
    @GetMapping("/{id}/entregar")
//    @CircuitBreaker(name = "coletoresEntregar",fallbackMethod = "envioSemInegrar")
    public ResponseEntity<ColetoresDTO> MudarStatus(@PathVariable Long id){
            var result = enviadosService.StatusParaEntregue(id);
            return ResponseEntity.ok(result);

    }
//    public   ResponseEntity<ColetoresDTO> envioSemInegrar(Long id, Exception e){
//
//        var result = enviadosService.alterarStatus(id,e);
//        System.out.println("minha exeption"+e);
//        return ResponseEntity.ok(result);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<ColetoresDTO> getID(@PathVariable Long id){
        var result = enviadosService.ColetorId(id);
        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Valid Long id){
        enviadosService.remove(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/atualizar")
    @Transactional
    public  ResponseEntity<ColetoresDTO> atualizar(@RequestBody  AtualizarDadosDTO updateDTO){
        System.out.println("id "+updateDTO.getId() + "minha lista de dto"+ updateDTO.getItens().getSetor() + updateDTO.getMac());
        var result = enviadosService.atualizarDadosColetor(updateDTO);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/create/status")
    @Transactional
    public  ResponseEntity<StatusBancoDTo> status(@RequestBody @Valid StatusBancoDTo statusBancoDTo){
       var result = enviadosService.statusCreate(statusBancoDTo);
       return ResponseEntity.ok().body(result);

     }
     @GetMapping("status/lista")
    public List<StatusBancoDTo> statusLista(){
        var result = enviadosService.StatusLista();
        return result;
     }
}
