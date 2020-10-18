package br.com.jogo.api.vilao;

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
@RequestMapping("/viloes")
@Api(tags = "API - Vilões")
public class VilaoController {

    @Autowired
    VilaoService vilaoService;

    @GetMapping
    public List<Vilao> buscaTodosViloes() {
        return vilaoService.buscaTodosViloes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Vilao> buscaVilao(@PathVariable Long codigo) {
        return vilaoService.buscaVilao(codigo);
    }

    @GetMapping("/aleatorio")
    public Vilao buscaVilaoAleatorio() {
        return vilaoService.buscaVilaoAleatorio();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vilao criaVilao(@RequestBody @Valid Vilao vilao) {
        return vilaoService.criaVilao(vilao);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Vilao> atualizaVilao(@PathVariable Long codigo, @RequestBody @Valid Vilao vilao) {
        return vilaoService.atualizaVilao(codigo, vilao);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Vilao> deletaVilao(@PathVariable Long codigo) {
        return vilaoService.deletaVilao(codigo);
    }
}
