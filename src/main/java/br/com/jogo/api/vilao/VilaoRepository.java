package br.com.jogo.api.vilao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilaoRepository extends JpaRepository<Vilao, Long> {
    Vilao findFirstByCodigo(Long codigo);
}
