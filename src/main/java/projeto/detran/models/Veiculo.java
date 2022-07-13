package projeto.detran.models;

import javax.persistence.*;

/**
 * Classe que reflete a tabela "veiculo" do banco de dados
 * Entidade Veiculo
 */
@Entity
@Table(name = "veiculo", schema = "public")
public class Veiculo {

    /**
     * Propriedade que reflete a coluna "id_veiculo" do banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    /**
     * Propriedade que reflete a coluna "id_pessoa" do banco de dados
     */
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    /**
     * Propriedade que reflete a coluna "nm_veiculo" do banco de dados
     */
    @Column(name = "nm_veiculo", length = 150, nullable = false)
    private String nomeVeiculo;

    /**
     * Propriedade que reflete a coluna "nm_marca" do banco de dados
     */
    @Column(name = "nm_marca", length = 150, nullable = false)
    private String marcaVeiculo;

    /**
     * Propriedade que reflete a coluna "nr_ano_fabricacao" do banco de dados
     */
    @Column(name = "nr_ano_fabricacao", nullable = false)
    private int numAnoFabricacao;

    /**
     * Propriedade que reflete a coluna "id_placa" do banco de dados
     */
    @ManyToOne
    @JoinColumn(name = "id_placa", referencedColumnName = "id_placa")
    private Placa placa;

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public int getNumAnoFabricacao() {
        return numAnoFabricacao;
    }

    public void setNumAnoFabricacao(int numAnoFabricacao) {
        this.numAnoFabricacao = numAnoFabricacao;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }
}
