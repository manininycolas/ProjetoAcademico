package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.models.Veiculo;

import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de Veiculo.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class VeiculoRepository implements PanacheRepository<Veiculo> {

}
