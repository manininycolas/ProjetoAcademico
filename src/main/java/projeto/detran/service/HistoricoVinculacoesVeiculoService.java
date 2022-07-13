package projeto.detran.service;

import projeto.detran.dto.HistoricoVinculacoesVeiculoDTO;
import projeto.detran.models.HistoricoVinculacoesVeiculo;
import projeto.detran.models.Placa;
import projeto.detran.models.Veiculo;
import projeto.detran.repository.HistoricoVinculacoesVeiculoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsavel por conter as regras de negocio
 * e implementação da manipulação da entidade HistoricoVinculacoesVeiculo
 */
@ApplicationScoped
public class HistoricoVinculacoesVeiculoService {

    /**
     * Injeção do repositorio da Entidade HistoricoVinculacoesVeiculo
     */
    @Inject
    HistoricoVinculacoesVeiculoRepository historicoVinculacoesVeiculoRepository;

    /**
     * Injeção do Service da Entidade Placa
     */
    @Inject
    PlacaService placaService;

    /**
     * Metodo responsável por criar um novo HistoricoVinculacoesVeiculo no banco de dados
     * @param veiculo Veiculo que será vinculado no Historico
     * @param placa Placa que será vinculada no Historico
     */
    public void salvarHistoricoNovo(Veiculo veiculo, Placa placa) {
        HistoricoVinculacoesVeiculo historico = new HistoricoVinculacoesVeiculo();
        historico.setVeiculo(veiculo);
        historico.setPlaca(placa);
        historico.setDataEmplacamento(LocalDateTime.now());

        this.historicoVinculacoesVeiculoRepository.persist(historico);
    }

    /**
     * Metodo responsável por realizar a busca da lista com todos os Historicos pelo id do Veiculo
     * @param idVeiculo Id do Veiculo para filtro
     * @return Lista de Historicos em DTO com o Id do Veiculo, caso não utilize filtros irá listar todos os historicos em DTO
     */
    public List<HistoricoVinculacoesVeiculoDTO> listarHistoricoDoVeiculo(Long idVeiculo) {
        List<HistoricoVinculacoesVeiculo> ormList = this.historicoVinculacoesVeiculoRepository
                .listarPorVeiculo(idVeiculo);

        return ormList.stream()
                .map(orm -> this.converteOrmToDTO(orm))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que realiza a conversão do HistoricoVinculacoesVeiculo ORM/Entidade para DTO do
     * HistoricoVinculacoesVeiculo
     * @param orm Entidade do HistoricoVinculacoesVeiculo
     * @return Conversão em DTO do HistoricoVinculacoesVeiculo
     */
    private HistoricoVinculacoesVeiculoDTO converteOrmToDTO(HistoricoVinculacoesVeiculo orm) {
        HistoricoVinculacoesVeiculoDTO dto = new HistoricoVinculacoesVeiculoDTO();
        dto.setIdHistoricoVinculacao(orm.getIdHistoricoVinculacao());
        dto.setDataEmplacamento(orm.getDataEmplacamento());
        dto.setPlaca(this.placaService.convertePlacaOrmToDto(orm.getPlaca()));

        return dto;
    }

}
