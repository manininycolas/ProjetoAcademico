package projeto.detran.dto;

import java.time.LocalDateTime;

/**
 *  Classe que representa o objeto SolicitacaoCriacaoPlaca no corpo da requisição e resposta.
 */
public class SolicitacaoCriacaoPlacaDTO {

    /**
     * Id da Solicitacao
     */
    private Long idSolicitacao;

    /**
     * Data de criacao da Solicitacao
     */
    private LocalDateTime dataCriacaoSolicitacao;

    /**
     * Codigo(UF) do estado solicitante da Solicitacao
     */
    private String codigoEstadoSolicitante;

    /**
     * Tipo da placa da Solicitacao
     */
    private Integer tipoPlaca;

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

    public Integer getTipoPlaca() {
        return tipoPlaca;
    }

    public void setTipoPlaca(Integer tipoPlaca) {
        this.tipoPlaca = tipoPlaca;
    }
}
