package projeto.detran.dto;

import java.time.LocalDate;

/**
 *  Classe que representa o objeto CriarPessoa no corpo da requisição.
 */
public class CriarPessoaDTO {

    /**
     * Nome da Pessoa
     */
    private String nomePessoa;

    /**
     * Data de nascimento da Pessoa
     */
    private LocalDate dataNascimento;

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
