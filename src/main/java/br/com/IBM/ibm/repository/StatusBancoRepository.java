package br.com.IBM.ibm.repository;

import br.com.IBM.ibm.models.StatusBanco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusBancoRepository extends JpaRepository<StatusBanco,Long> {
    Optional findOneByName(String name);
}
