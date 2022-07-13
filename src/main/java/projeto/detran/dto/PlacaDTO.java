package projeto.detran.dto;

import java.time.LocalDate;

/**
 *  Classe que representa o objeto Pessoa no corpo da requisição e resposta.
 */
public class PlacaDTO {
    /**
     * Id da Placa
     */
    private Long idPlaca;

    /**
     * Codigo da Placa
     */
    private String codigoPlaca;

    /**
     * Data de fabricação da Placa
     */
    private LocalDate dataFabricacao;

    /**
     * Estado onde a Placa foi fabricada
     */
    private String estadoFabricacao;

    /**
     * Codigo do tipo da Placa
     */
    private Integer tipoPlaca;

    public Long getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(Long idPlaca) {
        this.idPlaca = idPlaca;
    }

    public String getCodigoPlaca() {
        return codigoPlaca;
    }

    public void setCodigoPlaca(String codigoPlaca) {
        this.codigoPlaca = codigoPlaca;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getEstadoFabricacao() {
        return estadoFabricacao;
    }

    public void setEstadoFabricacao(String estadoFabricacao) {
        this.estadoFabricacao = estadoFabricacao;
    }

    public Integer getTipoPlaca() {
        return tipoPlaca;
    }

    public void setTipoPlaca(Integer tipoPlaca) {
        this.tipoPlaca = tipoPlaca;
    }
}
