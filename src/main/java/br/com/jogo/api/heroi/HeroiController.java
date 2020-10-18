package br.com.jogo.api.heroi;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/herois")
@Api(tags = "API - Herois")
public class HeroiController {

    @Autowired
    HeroiService heroiService;

    @GetMapping
    public List<Heroi> buscaTodosHerois() {
        return heroiService.buscaTodosHerois();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Heroi> buscaHeroi(@PathVariable Long codigo) {
        return heroiService.buscaHeroi(codigo);
    }

    @GetMapping("/aleatorio")
    public Heroi buscaHeroiAleatorio() {
        return heroiService.buscaHeroiAleatorio();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Heroi criaHeroi(@RequestBody @Valid Heroi heroi) {
        return heroiService.criaHeroi(heroi);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Heroi> atualizaHeroi(@PathVariable Long codigo, @RequestBody @Valid Heroi heroi) {
        return heroiService.atualizaHeroi(codigo, heroi);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Heroi> deletaHeroi(@PathVariable Long codigo) {
        return heroiService.deletaHeroi(codigo);
    }
}
