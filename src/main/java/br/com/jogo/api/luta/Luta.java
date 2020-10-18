package br.com.jogo.api.luta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LUTA")
public class Luta {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long codigo;

    @NotNull
    @CreationTimestamp
    @Column(name = "LUTA_DATA")
    public LocalDateTime lutaData;

    @NotNull
    @Column(name = "NOME_VENCEDOR")
    public String nomeVencedor;

    @NotNull
    @Column(name = "LEVEL_VENCEDOR")
    public int levelVencedor;

    @NotNull
    @Column(name = "IMAGEM_VENCEDOR")
    public String imagemVencedor;

    @NotNull
    @Column(name = "NOME_PERDEDOR")
    public String nomePerpededor;

    @NotNull
    @Column(name = "LEVEL_PERDEDOR")
    public int levelPerpededor;

    @NotNull
    @Column(name = "IMAGEM_PERDEDOR")
    public String imagemPerdedor;

    @NotNull
    @Column(name = "TIME_VENCEDOR")
    public String timeVencedor;

    @NotNull
    @Column(name = "TIME_PERDEDOR")
    public String timePerdedor;
}
