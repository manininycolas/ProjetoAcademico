package projeto.detran.models;

import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.enums.TiposPlacasEnumConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe que reflete a tabela "solicitacao_criacao_placa" do banco de dados
 * Entidade SolicitacaoCriacaoPlaca
 */
@Entity
@Table(name = "solicitacao_criacao_placa", schema = "public")
public class SolicitacaoCriacaoPlaca {

    /**
     * Propriedade que reflete a coluna "id_solicitacao" do banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    /**
     * Propriedade que reflete a coluna "dt_criacao_solicitacao" do banco de dados
     */
    @Column(name = "dt_criacao_solicitacao", nullable = false)
    private LocalDateTime dataCriacaoSolicitacao;

    /**
     * Propriedade que reflete a coluna "ds_estado_solicitante" do banco de dados
     */
    @Column(name = "ds_estado_solicitante", length = 2, nullable = false)
    private String codigoEstadoSolicitante;

    /**
     * Propriedade que reflete a coluna "tp_placa" do banco de dados
     */
    @Column(name = "tp_placa", nullable = false)
    @Convert(converter = TiposPlacasEnumConverter.class)
    private TiposPlacasEnum tipoPlaca;


    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public LocalDateTime getDataCriacaoSolicitacao() {
        return dataCriacaoSolicitacao;
    }

    public void setDataCriacaoSolicitacao(LocalDateTime dataCriacaoSolicitacao) {
        this.dataCriacaoSolicitacao = dataCriacaoSolicitacao;
    }

    public String getCodigoEstadoSolicitante() {
        return codigoEstadoSolicitante;
    }

    public void setCodigoEstadoSolicitante(String codigoEstadoSolicitante) {
        this.codigoEstadoSolicitante = codigoEstadoSolicitante;
    }

    public TiposPlacasEnum getTipoPlaca() {
        return tipoPlaca;
    }

    public void setTipoPlaca(TiposPlacasEnum tipoPlaca) {
        this.tipoPlaca = tipoPlaca;
    }

}
