package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.models.HistoricoVinculacoesVeiculo;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de HistoricoVinculacoesVeiculo.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class HistoricoVinculacoesVeiculoRepository implements PanacheRepository<HistoricoVinculacoesVeiculo> {

    /**
     * Metodo responsável por listar os HistoricoVinculacoesVeiculo no banco de dados filtrando
     * pelo id do Veiculo
     * @param idVeiculo Recebimendo do id do Veiculo
     * @return Lista com os dados do HistoricoVinculacoesVeiculo com base nos filtros utilizados
     */
    public List<HistoricoVinculacoesVeiculo> listarPorVeiculo(Long idVeiculo) {
        return this.list("veiculo.idVeiculo", idVeiculo);
    }

}
