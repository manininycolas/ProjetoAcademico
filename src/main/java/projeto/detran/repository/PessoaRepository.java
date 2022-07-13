package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.models.Pessoa;

import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de Pessoa.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
}
