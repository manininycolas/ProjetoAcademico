package projeto.detran.dto;

import java.time.LocalDateTime;

/**
 *  Classe que representa o objeto HistoricoVinculacoesVeiculo no corpo da resposta.
 */
public class HistoricoVinculacoesVeiculoDTO {

    /**
     * Id do Historico
     */
    private Long idHistoricoVinculacao;

    /**
     * Objeto de DTO de Placa
     */
    private PlacaDTO placa;

    /**
     * Data do Emplacamento(Vinculação)
     */
    private LocalDateTime dataEmplacamento;

    public Long getIdHistoricoVinculacao() {
        return idHistoricoVinculacao;
    }

    public void setIdHistoricoVinculacao(Long idHistoricoVinculacao) {
        this.idHistoricoVinculacao = idHistoricoVinculacao;
    }

    public PlacaDTO getPlaca() {
        return placa;
    }

    public void setPlaca(PlacaDTO placa) {
        this.placa = placa;
    }

    public LocalDateTime getDataEmplacamento() {
        return dataEmplacamento;
    }

    public void setDataEmplacamento(LocalDateTime dataEmplacamento) {
        this.dataEmplacamento = dataEmplacamento;
    }
}
