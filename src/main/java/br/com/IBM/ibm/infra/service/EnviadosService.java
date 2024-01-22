package br.com.IBM.ibm.infra.service;

import br.com.IBM.ibm.Dto.*;
import br.com.IBM.ibm.http.DadosRecebidos;
import br.com.IBM.ibm.infra.security.ErroMsg;
import br.com.IBM.ibm.models.ColetoresDTO;
import br.com.IBM.ibm.infra.ServiceMapper.StatusDTO;
import br.com.IBM.ibm.infra.service.validator.ValidadandoId;
import br.com.IBM.ibm.infra.service.validator.ValidadandoRegras;
import br.com.IBM.ibm.models.ColetoresEnvio;
import br.com.IBM.ibm.models.StatusBanco;
import br.com.IBM.ibm.repository.ColetoresRepository;
import br.com.IBM.ibm.repository.StatusBancoRepository;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnviadosService {
    @Autowired
 private ModelMapper modelMapper;
    @Autowired
    private ValidadandoRegras validadandoRegras;
    @Autowired
    private ValidadandoId validadandoId;
    @Autowired
    private ColetoresRepository coletoresRepository;
    @Autowired
    private DadosRecebidos dadosRecebidos;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private StatusBancoRepository statusBancoRepository;



// dados dado registar coletores enviados para assitencia
    public ColetoresDTO  registrar(ColetoresDTO coletoresDTO){
        if(coletoresDTO==null){
            throw new ErroMsg("dados nao podem ser nulos");
        }
        validadandoRegras.validation(coletoresDTO);
       var response = modelMapper.map(coletoresDTO, ColetoresEnvio.class);
        response.setStatus(Status.AGUARDANDO_ENVIO);
        response.setData(LocalDate.now());
        response.getItens().setProtocolo(response.getItens().gerarNumero());
        response.setAtivo(true);
        response.getItens().setEnviados(response);
        coletoresRepository.save(response);
        return modelMapper.map(response,ColetoresDTO.class);
    }
    //
    public Page<ColetoresDTO> Lista(Pageable page){
       return coletoresRepository.pegarItensAtivo(page).map(e->modelMapper.map(e,ColetoresDTO.class));
    }
    public Page<ColetoresDadosRelatorios> ListasRelatorio(Pageable page){
        return coletoresRepository.findByAtivoTrue(page).map(e->modelMapper.map(e,ColetoresDadosRelatorios.class));
    }
    public Page<ColetoresDTO> enviados(Pageable page){
        return coletoresRepository.pegarItensAtivoFalse(page).map(e->modelMapper.map(e,ColetoresDTO.class));
    }
    public ColetoresDTO MudarStatus(Long id,StatusDTO status){
     ColetoresEnvio result = coletoresRepository.pegarPorId(id);
     if(result==null){
         throw new RuntimeException("nada Encontrado!");
     }
        result.setStatus(Status.mudaStatus(String.valueOf(status.getStatus())));
        coletoresRepository.updateStatus(result.getStatus(),result);
      return  modelMapper.map(result,ColetoresDTO.class);
    }
    public  ColetoresEnvio atualizar(Long id,ColetoresDTO coletoresDTO){
        var result = modelMapper.map(coletoresDTO,ColetoresEnvio.class);
        if(coletoresDTO==null){
            throw new RuntimeException();
        }
        var coletores = coletoresRepository.update(id,result);
        return coletores;
    }

    public ColetoresDTO StatusParaEntregue(Long id){
            var result = validadandoId.coletoresEnvio(id);
            var coletor = modelMapper.map(result, ColetoresEnvioDTO.class);
            Optional pt = coletoresRepository.findByProtocolo(result.getItens().getProtocolo());
//             dadosRecebidos.dadosrecebidos(new RecebidosDTO(coletor));
        result.setStatus(Status.ENTREGUE);
        result.setAtivo(false);
            if(pt.isPresent()){
                throw new  RuntimeException("coletor ja foi entregue");
            }
            var sm = new RecebidosDTO(coletor);
        rabbitTemplate.convertAndSend("recebidos.ex","",sm);
            coletoresRepository.updateStatusEntregue(id,result);
            return modelMapper.map(result,ColetoresDTO.class);

    }
//     public ColetoresDTO alterarStatus(Long id,Exception ew){
//
//             var result = validadandoId.coletoresEnvio(id);
//             var coletor = modelMapper.map(result,ColetoresDTO.class);
//             result.setStatus(Status.AGUARDANDO_REENTREGACAO);
//             System.out.println("meu dados "+coletor.getStatus());
//         coletoresRepository.updateStatusEntregue(id,result);
//             return coletor;
//
//     }

    public ColetoresDTO ColetorId(Long id){
       var result = validadandoId.coletoresEnvio(id);
        try {
            return modelMapper.map(result,ColetoresDTO.class);
        }catch (MappingException e){
            throw new RuntimeException("nada encontado");
        }
    }
    public void remove(Long id){
        coletoresRepository.deleteById(id);
    }

    public ColetoresDTO atualizarDadosColetor(AtualizarDadosDTO dadosDTO) {
        var model = coletoresRepository.getReferenceById(dadosDTO.getId());
            model.setData(LocalDate.now());
            model.atualizar(dadosDTO);
        return modelMapper.map(model,ColetoresDTO.class);
    }

    public StatusBancoDTo statusCreate(StatusBancoDTo statusBancoDTo) {
        System.out.println("status "+statusBancoDTo.name());
        StatusBanco statusBanco = new StatusBanco(statusBancoDTo);
        Optional dados = statusBancoRepository.findOneByName(statusBancoDTo.name());
        if(dados.isPresent()){
            throw new RuntimeException("status ja existe");
        }
        System.out.println("segundo "+statusBanco.getName());
        statusBancoRepository.save(statusBanco);
        return new StatusBancoDTo(statusBanco);

    }
    public List<StatusBancoDTo> StatusLista(){
        var lista = statusBancoRepository.findAll();
        var dadosLista = lista.stream().map(StatusBancoDTo::new).collect(Collectors.toList());
        return  dadosLista;
    }
}
