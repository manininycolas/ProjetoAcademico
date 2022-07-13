package projeto.detran.dto;

/**
 *  Classe que representa o objeto NovaSolicitacaoCriacaoPlaca no corpo da requisição.
 */
public class NovaSolicitacaoCriacaoPlacaDTO {

    /**
     * Codigo do estado solicitante
     */
    private String codigoEstadoSolicitante;

    /**
     * Tipo da placa
     */
    private Integer tipoPlaca;

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
