package projeto.detran.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe que reflete a tabela "solicitacao_vinculacao_placa" do banco de dados
 * Entidade SolicitacaoVinculacaoPlaca
 */
@Entity
@Table(name = "solicitacao_vinculacao_placa", schema = "public")
public class SolicitacaoVinculacaoPlaca {

    /**
     * Propriedade que reflete a coluna "id_solicitacao_vinculacao" do banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao_vinculacao")
    private Long idSolicitacaoVinculacao;

    /**
     * Propriedade que reflete a coluna "dt_criacao_solicitacao" do banco de dados
     */
    @Column(name = "dt_criacao_solicitacao", nullable = false)
    private LocalDateTime dtCriacaoSolicitacao;

    /**
     * Propriedade que reflete a coluna "id_veiculo" do banco de dados
     */
    @ManyToOne
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    /**
     * Propriedade que reflete a coluna "id_placa" do banco de dados
     */
    @ManyToOne
    @JoinColumn(name = "id_placa", referencedColumnName = "id_placa", nullable = false)
    private Placa placa;

    public Long getIdSolicitacaoVinculacao() {
        return idSolicitacaoVinculacao;
    }

    public void setIdSolicitacaoVinculacao(Long idSolicitacaoVinculacao) {
        this.idSolicitacaoVinculacao = idSolicitacaoVinculacao;
    }

    public LocalDateTime getDtCriacaoSolicitacao() {
        return dtCriacaoSolicitacao;
    }

    public void setDtCriacaoSolicitacao(LocalDateTime dtCriacaoSolicitacao) {
        this.dtCriacaoSolicitacao = dtCriacaoSolicitacao;
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

}