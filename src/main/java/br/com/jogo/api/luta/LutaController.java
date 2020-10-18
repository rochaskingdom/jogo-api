package br.com.jogo.api.luta;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/lutas")
@Api(tags = "API - Lutas")
public class LutaController {

    @Autowired
    private LutaService lutaService;

    @GetMapping
    public List<Luta> buscaTodasLutas() {
        return lutaService.buscaTodasLutas();
    }

    @GetMapping("/aleatorio")
    public ResponseEntity<Lutas> buscaLutaAleatorio() {
        return lutaService.buscaLutaAleatoria();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Luta> buscaLuta(@PathVariable Long codigo) {
        return lutaService.buscaLuta(codigo);
    }

    @PostMapping
    public Luta resultadoLuta(@RequestBody @Valid Lutas lutas) {
        return lutaService.resultadoLuta(lutas);
    }
}
