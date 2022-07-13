package projeto.detran.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe refletora da tabela "historico_vinculacoes_veiculo" do banco de dados no Java.
 * Entidade HistoricoVinculacoesVeiculo
 */
@Entity
@Table(name = "historico_vinculacoes_veiculo", schema = "public")
public class HistoricoVinculacoesVeiculo {

    /**
     * Propriedade que reflete a coluna "id_historico_vinculacao"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_vinculacao")
    private Long idHistoricoVinculacao;

    /**
     * Propriedade que reflete a coluna "id_veiculo"
     */
    @ManyToOne
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    /**
     * Propriedade que reflete a coluna "id_placa"
     */
    @ManyToOne
    @JoinColumn(name = "id_placa", referencedColumnName = "id_placa", unique = true, nullable = false)
    private Placa placa;

    /**
     * Propriedade que reflete a coluna "dt_emplacamento"
     */
    @Column(name = "dt_emplacamento", nullable = false)
    private LocalDateTime dataEmplacamento;

    public Long getIdHistoricoVinculacao() {
        return idHistoricoVinculacao;
    }

    public void setIdHistoricoVinculacao(Long idHistoricoVinculacao) {
        this.idHistoricoVinculacao = idHistoricoVinculacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public LocalDateTime getDataEmplacamento() {
        return dataEmplacamento;
    }

    public void setDataEmplacamento(LocalDateTime dataEmplacamento) {
        this.dataEmplacamento = dataEmplacamento;
    }
}
