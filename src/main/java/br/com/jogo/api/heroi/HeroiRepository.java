package br.com.jogo.api.heroi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {
    Heroi findFirstByCodigo(Long codigo);
}
