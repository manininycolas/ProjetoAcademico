package projeto.detran.dto;

import java.time.LocalDate;

/**
 * Classe que representa o objeto Pessoa no corpo da requisição e resposta.
 *
 * Quando for usada para definir o body de uma requisicao, o JSON será convertido
 * para uma instancia desta classe.
 *
 * Quando for usada para definir o body de uma resposta, a instacia desta classe
 * será convertida para JSON
 */
public class PessoaDTO {

    /**
     * Id da Pessoa
     */
    private Long idPessoa;

    /**
     * Nome da Pessoa
     */
    private String nomePessoa;

    /**
     * Data de Nascimento da Pessoa
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
