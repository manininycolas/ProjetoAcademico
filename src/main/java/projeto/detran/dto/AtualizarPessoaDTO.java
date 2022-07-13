package projeto.detran.dto;

import java.time.LocalDate;

/**
 * Classe que representa o objeto AtualizarPessoa no corpo da requisição
 */
public class AtualizarPessoaDTO {

    /**
     * Id da Pessoa
     */
    private Long idPessoa;

    /**
     * Nome da Pessoa
     */
    private String nomePessoa;

    /**
     * Data de nascimento da Pessoa
     */
    private LocalDate dataNascimento;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
