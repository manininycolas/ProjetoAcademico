package projeto.detran.dto;

import java.time.LocalDateTime;

/**
 *  Classe que representa o objeto SolicitacaoVinculacaoPlaca no corpo da requisição e resposta.
 */
public class SolicitacaoVinculacaoPlacaDTO {

    /**
     * Id da Solicitacao
     */
    private Long idSolicitacaoVinculacao;

    /**
     * Data de criação da Solicitacao
     */
    private LocalDateTime dtCriacaoSolicitacao;

    /**
     * Objeto de DTO de Veiculo
     */
    private VeiculoDTO veiculo;

    /**
     * Objeto de DTO de Placa
     */
    private PlacaDTO placa;

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

    public VeiculoDTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoDTO veiculo) {
        this.veiculo = veiculo;
    }

    public PlacaDTO getPlaca() {
        return placa;
    }

    public void setPlaca(PlacaDTO placa) {
        this.placa = placa;
    }
}
