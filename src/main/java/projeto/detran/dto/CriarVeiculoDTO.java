package projeto.detran.dto;

/**
 *  Classe que representa o objeto CriarVeiculo no corpo da requisição.
 */
public class CriarVeiculoDTO {

    /**
     * Objeto de DTO de Pessoa
     */
    private PessoaDTO pessoa;

    /**
     * Nome do Veiculo
     */
    private String nomeVeiculo;

    /**
     * Marca do Veiculo
     */
    private String marcaVeiculo;

    /**
     * Ano de fabricação do Veiculo
     */
    private int numAnoFabricacao;

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
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
}
