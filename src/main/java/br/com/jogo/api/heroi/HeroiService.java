package br.com.jogo.api.heroi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    private final int levelMultiplicador = 3;

    public List<Heroi> buscaTodosHerois() {
        return heroiRepository.findAll();
    }

    public ResponseEntity<Heroi> buscaHeroi(Long codigo) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        return buscaHeroi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public Heroi buscaHeroiAleatorio() {
        long countHerois = heroiRepository.count();
        Random random = new Random();
        int randomHeroi = random.nextInt((int) countHerois) + 1;
        return heroiRepository.findFirstByCodigo((long) randomHeroi);
    }

    public Heroi criaHeroi(@Valid Heroi heroi) {
        heroi.setLevel(heroi.getLevel() * levelMultiplicador);
        return heroiRepository.save(heroi);
    }

    public ResponseEntity<Heroi> atualizaHeroi(Long codigo, @Valid Heroi heroi) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        if (buscaHeroi.isPresent()) {
            BeanUtils.copyProperties(heroi, buscaHeroi.get(), "codigo");
            buscaHeroi.get().setLevel(heroi.getLevel() * levelMultiplicador);
            Heroi heroiSalva = heroiRepository.save(buscaHeroi.get());
            return ResponseEntity.ok(heroiSalva);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Heroi> deletaHeroi(Long codigo) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        if (buscaHeroi.isPresent()) {
            heroiRepository.deleteById(codigo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
