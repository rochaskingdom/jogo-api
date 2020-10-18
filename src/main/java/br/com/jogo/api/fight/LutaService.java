package br.com.jogo.api.fight;

import br.com.jogo.api.heroi.Heroi;
import br.com.jogo.api.heroi.HeroiService;
import br.com.jogo.api.vilao.Vilao;
import br.com.jogo.api.vilao.VilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LutaService {

    @Autowired
    LutaRepository lutaRepository;

    @Autowired
    HeroiService heroiService;

    @Autowired
    VilaoService vilaoService;

    private final Random random = new Random();

    public List<Luta> buscaTodasLutas() {
        return lutaRepository.findAll();
    }

    public ResponseEntity<Luta> buscaLuta(Long codigo) {
        Optional<Luta> buscaLuta = lutaRepository.findById(codigo);
        return buscaLuta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public Luta resultadoLuta(Lutas lutas) {
        Luta luta;

        int ajusteHeroi = random.nextInt(20);
        int ajusteVilao = random.nextInt(20);

        if ((lutas.getHeroi().getLevel() + ajusteHeroi) > (lutas.getVilao().getLevel() + ajusteVilao)) {
            luta = heroiVencedor(lutas);
        } else if (lutas.getHeroi().getLevel() < lutas.getVilao().getLevel()) {
            luta = vilaoVencedor(lutas);
        } else {
            luta = random.nextBoolean() ? heroiVencedor(lutas) : vilaoVencedor(lutas);
        }

        luta.lutaData = LocalDateTime.now();
        return lutaRepository.save(luta);
    }

    public ResponseEntity<Lutas> buscaLutaAleatoria() {
        Heroi heroi = buscaHeroiAleatorio();
        Vilao vilao = buscaVilaoAleatorio();
        Lutas lutas = new Lutas();
        lutas.setHeroi(heroi);
        lutas.setVilao(vilao);
        return ResponseEntity.ok(lutas);
    }

    Heroi buscaHeroiAleatorio() {
        return heroiService.buscaHeroiAleatorio();
    }

    Vilao buscaVilaoAleatorio() {
        return vilaoService.buscaVilaoAleatorio();
    }

    private Luta heroiVencedor(Lutas lutas) {
        Luta luta = new Luta();
        luta.setNomeVencedor(lutas.getHeroi().getNome());
        luta.setImagemVencedor(lutas.getHeroi().getImagem());
        luta.setLevelVencedor(lutas.getHeroi().getLevel());
        luta.setNomePerpededor(lutas.getVilao().getNome());
        luta.setImagemPerpededor(lutas.getVilao().getImagem());
        luta.setLevelPerpededor(lutas.getVilao().getLevel());
        luta.setTimeVencedor("herois");
        luta.setTimePerdedor("viloes");
        return luta;
    }

    private Luta vilaoVencedor(Lutas lutas) {
        Luta luta = new Luta();
        luta.setNomeVencedor(lutas.getVilao().getNome());
        luta.setImagemVencedor(lutas.getVilao().getImagem());
        luta.setLevelVencedor(lutas.getVilao().getLevel());
        luta.setNomePerpededor(lutas.getHeroi().getNome());
        luta.setImagemPerpededor(lutas.getHeroi().getImagem());
        luta.setLevelPerpededor(lutas.getHeroi().getLevel());
        luta.setTimeVencedor("viloes");
        luta.setTimePerdedor("herois");
        return luta;
    }
}
