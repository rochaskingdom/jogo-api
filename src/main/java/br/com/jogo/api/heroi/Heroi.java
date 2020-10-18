package br.com.jogo.api.heroi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "HEROI")
public class Heroi {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long codigo;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "OUTRO_NOME")
    private String outroNome;

    @NotNull
    @Min(1)
    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "IMAGEM")
    private String imagem;

    @Column(name = "PODERES")
    private String poderes;
}
