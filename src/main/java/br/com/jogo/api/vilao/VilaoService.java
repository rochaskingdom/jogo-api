package br.com.jogo.api.vilao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VilaoService {

    @Autowired
    private VilaoRepository vilaoRepository;

    private final int levelMultiplicador = 2;

    public List<Vilao> buscaTodosViloes() {
        return vilaoRepository.findAll();
    }

    public ResponseEntity<Vilao> buscaVilao(Long codigo) {
        Optional<Vilao> buscaVilao = vilaoRepository.findById(codigo);
        return buscaVilao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Vilao buscaVilaoAleatorio() {
        long countViloes = vilaoRepository.count();
        Random random = new Random();
        int randomVilao = random.nextInt((int) countViloes) + 1;
        return vilaoRepository.findFirstByCodigo((long) randomVilao);
    }

    public Vilao criaVilao(@Valid Vilao vilao) {
        vilao.setLevel(vilao.getLevel() * levelMultiplicador);
        return vilaoRepository.save(vilao);
    }

    public ResponseEntity<Vilao> atualizaVilao(Long codigo, @Valid Vilao vilao) {
        Optional<Vilao> buscaVilao = vilaoRepository.findById(codigo);
        if (buscaVilao.isPresent()) {
            BeanUtils.copyProperties(vilao, buscaVilao.get(), "codigo");
            buscaVilao.get().setLevel(vilao.getLevel() * levelMultiplicador);
            Vilao vilaoSalva = vilaoRepository.save(buscaVilao.get());
            return ResponseEntity.ok(vilaoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Vilao> deletaVilao(Long codigo) {
        Optional<Vilao> buscaVilao = vilaoRepository.findById(codigo);
        if (buscaVilao.isPresent()) {
            vilaoRepository.deleteById(codigo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
