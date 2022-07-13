package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.models.SolicitacaoCriacaoPlaca;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de SolicitacaoCriacaoPlaca.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class SolicitacaoCriacaoPlacaRepository implements PanacheRepository<SolicitacaoCriacaoPlaca> {

    /**
     * Metodo responsável por listar as SolicitacaoCriacaoPlaca no banco de dados filtrando
     * pela data de criação, tipo de placa, estado solicitante
     * @param dataCriacao Recebimento da data de criação da Solicitacao
     * @param tipoPlaca Recebimento do tipo da placa da Solicitacao
     * @param estadoSolicitante Recebimento do estado Solicitante da Solicitacao
     * @return Lista com os dados da SolicitacaoCriacaoPlaca com base nos filtros utilizados
     */
    public List<SolicitacaoCriacaoPlaca> listarComFiltros(LocalDate dataCriacao,
                                                          TiposPlacasEnum tipoPlaca,
                                                          String estadoSolicitante){

        StringJoiner query = new StringJoiner(" and ");
        Map<String, Object> params = new HashMap<>();

        if (dataCriacao != null) {
            query.add("DATE(dataCriacaoSolicitacao) = :dataCriacao");
            params.put("dataCriacao", dataCriacao);
        }

        if (tipoPlaca != null) {
            query.add("tipoPlaca = :tipoPlaca");
            params.put("tipoPlaca", tipoPlaca);
        }

        if (estadoSolicitante != null) {
            query.add("codigoEstadoSolicitante = :estadoSolicitante");
            params.put("estadoSolicitante", estadoSolicitante.toUpperCase());
        }

        return this.list(query.toString(), params);
    }
}
