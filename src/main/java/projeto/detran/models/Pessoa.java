package projeto.detran.models;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe refletora da tabela "pessoa" do banco de dados no Java.
 * Entidade Pessoa
 */
@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa {

    /**
     * Propriedade que reflete a coluna "id_pessoa"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL
    @Column(name = "id_pessoa")
    private Long idPessoa;

    /**
     * Propriedade que reflete a coluna "nm_pessoa"
     */
    @Column(name = "nm_pessoa", length = 150, nullable = false)
    private String nomePessoa;

    /**
     * Propriedade que reflete a coluna "dt_nascimento"
     */
    @Column(name = "dt_nascimento", nullable = false)
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
