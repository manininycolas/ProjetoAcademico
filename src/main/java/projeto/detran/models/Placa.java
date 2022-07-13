package projeto.detran.models;

import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.enums.TiposPlacasEnumConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe que reflete a tabela "placa" do banco de dados
 * Entidade Placa
 */
@Entity
@Table(name = "placa", schema = "public")
public class Placa {

    /**
     * Propriedade que reflete a coluna "id_placa" do banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_placa")
    private Long idPlaca;

    /**
     * Propriedade que reflete a coluna "ds_placa" do banco de dados
     */
    @Column(name = "ds_placa", length = 7, nullable = false, unique = true)
    private String codigoPlaca;

    /**
     * Propriedade que reflete a coluna "dt_fabricacao" do banco de dados
     */
    @Column(name = "dt_fabricacao", nullable = false)
    private LocalDate dataFabricacao;

    /**
     * Propriedade que reflete a coluna "ds_estado_fabricacao" do banco de dados
     */
    @Column(name = "ds_estado_fabricacao", length = 2, nullable = false)
    private String estadoFabricacao;

    /**
     * Propriedade que reflete a coluna "tp_placa" do banco de dados
     */
    @Column(name = "tp_placa", nullable = false)
    @Convert(converter = TiposPlacasEnumConverter.class)
    private TiposPlacasEnum tipoPlaca;

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

    public TiposPlacasEnum getTipoPlaca() {
        return tipoPlaca;
    }

    public void setTipoPlaca(TiposPlacasEnum tipoPlaca) {
        this.tipoPlaca = tipoPlaca;
    }
}
