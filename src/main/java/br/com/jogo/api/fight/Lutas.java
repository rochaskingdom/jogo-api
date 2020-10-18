package br.com.jogo.api.fight;

import br.com.jogo.api.heroi.Heroi;
import br.com.jogo.api.vilao.Vilao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lutas {

    @NotNull
    private Heroi heroi;

    @NotNull
    private Vilao vilao;
}
