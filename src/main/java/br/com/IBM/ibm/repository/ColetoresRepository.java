package br.com.IBM.ibm.repository;

import br.com.IBM.ibm.Dto.Status;
import br.com.IBM.ibm.models.ColetoresEnvio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ColetoresRepository extends JpaRepository<ColetoresEnvio,Long> {
    @Query("select a from ColetoresEnvio a where a.ativo = true")
    Page<ColetoresEnvio> pegarItensAtivo(Pageable page);
//    @Query("select a from ColetoresEnvio a where a.ativo = true")
//    List<ColetoresEnvio> pegarItensAtivos();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ColetoresEnvio p set p.status = :status where p = :coletoresEnvio")
    void updateStatus(Status status, ColetoresEnvio coletoresEnvio);

    @Query("select p from ColetoresEnvio p left join fetch p.itens where p.id = :id")
    ColetoresEnvio pegarPorId(Long id);
    @Query("select p from ColetoresEnvio p where p.mac = :mac and p.ativo=true")
    Optional<ColetoresEnvio> pegarPorMa(String mac);
    @Query("select p from ColetoresEnvio p where p.patrimonio = :patrimonio and p.ativo = true ")
    Optional<ColetoresEnvio> pegarPorPat(String patrimonio);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ColetoresEnvio p set p.id = :id where p = :result")
    void updateStatusEntregue(Long id, ColetoresEnvio result);
    void deleteById(Long id);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ColetoresEnvio p set p.id = :id where p = :result")
    ColetoresEnvio update(Long id, ColetoresEnvio result);
    @Query("select a from ColetoresEnvio a where a.ativo = false")
    Page<ColetoresEnvio> pegarItensAtivoFalse(Pageable page);
    @Query("select p from ColetoresEnvio p  left join fetch  p.itens a where a.protocolo = :protocolo and p.ativo = false")
    Optional findByProtocolo(String protocolo);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ColetoresEnvio p set p.id = :id where p = :result")
    void updateStatusEntregueAguard(Long id,ColetoresEnvio result);
    @Query("select p from ColetoresEnvio p where p.status = AGUARDANDO_REENTREGACAO")
    List<ColetoresEnvio> findAllByStatus();

    Page<ColetoresEnvio> findByAtivoTrue(Pageable page);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ColetoresEnvio p set p.id = :id where p = :model")
    void updateColetor(Long id, ColetoresEnvio model);
}
