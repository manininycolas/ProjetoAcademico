package projeto.detran.service;

import projeto.detran.dto.SolicitacaoVinculacaoPlacaDTO;
import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.models.Placa;
import projeto.detran.models.SolicitacaoVinculacaoPlaca;
import projeto.detran.models.Veiculo;
import projeto.detran.repository.PlacaRepository;
import projeto.detran.repository.SolicitacaoVinculacaoPlacaRepository;
import projeto.detran.repository.VeiculoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsavel por conter as regras de negocio
 * e implementação da manipulação da entidade SolicitacaoVinculacaoPlaca
 */
@ApplicationScoped
public class SolicitacaoVinculacaoPlacaService {

    /**
     * Injeção do repositorio da entidade SolicitacaoVinculacaoPlaca
     */
    @Inject
    SolicitacaoVinculacaoPlacaRepository solicitacaoVinculacaoPlacaRepository;

    /**
     * Injeção do Service da entidade Placa
     */
    @Inject
    PlacaService placaService;

    /**
     * Injeção do repositorio da entidade Placa
     */
    @Inject
    PlacaRepository placaRepository;

    /**
     * Injeção do repositorio da entidade Veiculo
     */
    @Inject
    VeiculoRepository veiculoRepository;

    /**
     * Injeção do Service da entidade Veiculo
     */
    @Inject
    VeiculosService veiculosService;

    /**
     * Injeção do Service da entidade HistoricoVinculacoesVeiculo
     */
    @Inject
    HistoricoVinculacoesVeiculoService historicoVinculacoesVeiculoService;

    /**
     * Metodo responsável por criar uma nova SolicitacaoVinculaoPlaca no banco de dados
     * @param idVeiculo Id do Veiculo que será vinculado a placa
     * @param idPlaca Id da placa que será vinulada no Veiculo
     * @return DTO da nova SolicitacaoVinculacaoPlaca
     */
    @Transactional
    public SolicitacaoVinculacaoPlacaDTO criarSolicitacaoVinculacaoPlaca(Long idVeiculo, Long idPlaca) {
        Veiculo ormVeiculo = this.veiculoRepository.findByIdOptional(idVeiculo)
                .orElseThrow(() -> new NotFoundException("Id da Entidade Veiculo inexistente"));

        Placa ormPlaca = this.placaRepository.findByIdOptional(idPlaca)
                .orElseThrow(() -> new NotFoundException("Id da Entidade Placa inexistente"));

        SolicitacaoVinculacaoPlaca solicitacaoVinculacaoPlaca = new SolicitacaoVinculacaoPlaca();
        solicitacaoVinculacaoPlaca.setDtCriacaoSolicitacao(LocalDateTime.now());
        solicitacaoVinculacaoPlaca.setPlaca(ormPlaca);
        solicitacaoVinculacaoPlaca.setVeiculo(ormVeiculo);

        this.solicitacaoVinculacaoPlacaRepository.persist(solicitacaoVinculacaoPlaca);

        return this.converteOrmToDto(solicitacaoVinculacaoPlaca);
    }

    /**
     * Metodo responsável por realizar uma busca de SolicitacaoVinculacaoPlaca pelo ID e
     * deferir a Solicitacao assim realizando a vinculacao de dados da entidade Placa com a
     * entidade Veiculo no banco de dados, após a gravação, é deletada a SolicitacaoVinculacaoPlaca
     * da base e criada uma nova entidade HistoricoVinculacaoVeiculo com os dados da vinculação
     * @param idVinculacaoPlaca Id da SolicitacaoVinculacaoPlaca para filtro
     */
    @Transactional
    public void deferirSolicitacaoVinculacaoPlaca(Long idVinculacaoPlaca){
        SolicitacaoVinculacaoPlaca orm = this.solicitacaoVinculacaoPlacaRepository.findByIdOptional(idVinculacaoPlaca)
                .orElseThrow(() -> new NotFoundException("Id da Entidade de Vinculacao inexistente"));

        Placa placa = orm.getPlaca();
        Veiculo veiculo = orm.getVeiculo();

        veiculo.setPlaca(placa);

        this.veiculoRepository.persist(veiculo);
        this.solicitacaoVinculacaoPlacaRepository.delete(orm);
        this.historicoVinculacoesVeiculoService.salvarHistoricoNovo(veiculo, placa);
    }

    /**
     * Metodo responsavel por realizar a busca da lista com todas as SolicitacaoVinculacaoPlaca,
     * utilizando opcionalmente 8 filtros
     * @param dataCriacao Data de criação da Solicitacao para filtro
     * @param tipoPlaca Tipo da placa da Solicitacao para filtro
     * @param estadoFabricacaoPlaca Estado de Fabricacao da Placa para filtro
     * @param codigoPlaca Codigo da placa para filtro
     * @param nomeVeiculo Nome do veiculo para filtro
     * @param marcaVeiculo Marca do veiculo para filtro
     * @param anoFabricacaoVeiculo Ano de fabricação do veiculo para filtro
     * @param donoVeiculo Nome do dono do veiculo para filtro
     * @return lista com as Solicitações em DTO com as informações utilizadas por filtros, caso não utilize filtros irá listar
     * todas as solicitações em DTO
     */
    public List<SolicitacaoVinculacaoPlacaDTO> listarPorFiltros(String dataCriacao,
                                                                 String tipoPlaca,
                                                                 String estadoFabricacaoPlaca,
                                                                 String codigoPlaca,
                                                                 String nomeVeiculo,
                                                                 String marcaVeiculo,
                                                                 Integer anoFabricacaoVeiculo,
                                                                 String donoVeiculo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = dataCriacao != null ? LocalDate.parse(dataCriacao, formatter) : null;
        TiposPlacasEnum tiposPlacasEnum = TiposPlacasEnum.convertStringToPlacaEnum(tipoPlaca);

        List<SolicitacaoVinculacaoPlaca> ormList = this.solicitacaoVinculacaoPlacaRepository
                .listarComFiltros(
                        data,
                        tiposPlacasEnum,
                        estadoFabricacaoPlaca,
                        codigoPlaca,
                        nomeVeiculo,
                        marcaVeiculo,
                        anoFabricacaoVeiculo,
                        donoVeiculo);

        return ormList.stream()
                .map(orm -> this.converteOrmToDto(orm))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que realiza a conversão do SolicitacaoVinculacaoPlaca ORM/Entidade para DTO da
     * SolicitacaoVinculacaoPlaca
     * @param orm Entidade da SolicitacaoVinculacaoPlaca/OR
     * @return Conversão em DTO da SolicitacaoVinculacaoPlaca
     */
    private SolicitacaoVinculacaoPlacaDTO converteOrmToDto(SolicitacaoVinculacaoPlaca orm) {

        SolicitacaoVinculacaoPlacaDTO dto = new SolicitacaoVinculacaoPlacaDTO();
        dto.setIdSolicitacaoVinculacao(orm.getIdSolicitacaoVinculacao());
        dto.setPlaca(this.placaService.convertePlacaOrmToDto(orm.getPlaca()));
        dto.setVeiculo(this.veiculosService.converteOrmToDTO(orm.getVeiculo()));
        dto.setDtCriacaoSolicitacao(orm.getDtCriacaoSolicitacao());

        return dto;
    }

}
