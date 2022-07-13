package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.models.SolicitacaoVinculacaoPlaca;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de SolicitacaoVinculacaoPlaca.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class SolicitacaoVinculacaoPlacaRepository implements PanacheRepository<SolicitacaoVinculacaoPlaca> {

    /**
     * Metodo responsável por listar as SolicitacaoVinculacaoPlaca no banco de dados filtrando
     * pela data de criação, tipo de placa, estado de fabricação da placa, codigo da placa,
     * nome do veiculo, marca do veiculo, ano de fabricação do veiculo, dono do veiculo
     * validando se o filtro utilizado não está null
     * @param dataCriacao Recebimento da data de criação da Solicitacao
     * @param tipoPlaca Recebimento do tipo de placa da Solicitacao
     * @param estadoFabricacaoPlaca Recebimento do estado de Fabricação da placa da Solicitacao
     * @param codigoPlaca Recebimento do codigo da placa da Solicitacao
     * @param nomeVeiculo Recebimento do nome do veiculo da Solicitacao
     * @param marcaVeiculo Recebimento da marca do veiculo da Solicitacao
     * @param anoFabricacaoVeiculo Recebimento do ano de fabricação do veiculo da Solicitacao
     * @param donoVeiculo Recebimento do nome do dono do veiculo da Solicitacao
     * @return Lista com os dados da SolicitacaoVinculacaoPlaca com base nos filtros utilizados
     */
    public List<SolicitacaoVinculacaoPlaca> listarComFiltros(LocalDate dataCriacao,
                                                             TiposPlacasEnum tipoPlaca,
                                                             String estadoFabricacaoPlaca,
                                                             String codigoPlaca,
                                                             String nomeVeiculo,
                                                             String marcaVeiculo,
                                                             Integer anoFabricacaoVeiculo,
                                                             String donoVeiculo) {

        StringJoiner query = new StringJoiner(" and ");
        Map<String, Object> params = new HashMap<>();

        if (dataCriacao != null) {
            query.add("DATE(dtCriacaoSolicitacao) = :dataCriacao");
            params.put("dataCriacao", dataCriacao);
        }
        if (tipoPlaca != null) {
            query.add("placa.tipoPlaca = :tipoPlaca");
            params.put("tipoPlaca", tipoPlaca);
        }
        if (estadoFabricacaoPlaca != null) {
            query.add("placa.estadoFabricacao = :estadoFabricacaoPlaca");
            params.put("estadoFabricacaoPlaca", estadoFabricacaoPlaca);
        }
        if (codigoPlaca != null) {
            query.add("placa.codigoPlaca = :codigoPlaca");
            params.put("codigoPlaca", tipoPlaca);
        }
        if (nomeVeiculo != null) {
            query.add("veiculo.nomeVeiculo = :nomeVeiculo");
            params.put("nomeVeiculo", nomeVeiculo.toUpperCase());
        }
        if (marcaVeiculo != null) {
            query.add("veiculo.marcaVeiculo = :marcaVeiculo");
            params.put("marcaVeiculo", marcaVeiculo.toUpperCase());
        }
        if (anoFabricacaoVeiculo != null) {
            query.add("veiculo.numAnoFabricacao = :anoFabricaoVeiculo");
            params.put("anoFabricacaoVeiculo", anoFabricacaoVeiculo);
        }
        if (donoVeiculo != null) {
            query.add("veiculo.pessoa.nomePessoa = :donoVeiculo");
            params.put("donoVeiculo", donoVeiculo.toUpperCase());
        }
        return this.list(query.toString(), params);
    }
}


