package projeto.detran.service;

import projeto.detran.dto.*;
import projeto.detran.enums.TiposPlacasEnum;
import projeto.detran.enums.TiposPlacasEnumConverter;
import projeto.detran.models.Placa;
import projeto.detran.models.SolicitacaoCriacaoPlaca;
import projeto.detran.repository.PlacaRepository;
import projeto.detran.repository.SolicitacaoCriacaoPlacaRepository;

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
 * e implementação da manipulação da entidade SolicitacaoCriacaoPlaca
 */
@ApplicationScoped
public class SolicitacaoCriacaoPlacaService {

    /**
     * Definindo constante do valor minimo para numeração das placas
     */
    private static final int MINIMO_CODIGO_PLACA = 1000000;

    /**
     * Definindo constante do valor maximo para numeração das placas
     */
    private static final int MAXIMO_CODIGO_PLACA = 9999999;

    /**
     * Injetando repositorio da entidade SolicitacaoCriacaoPlaca
     */
    @Inject
    SolicitacaoCriacaoPlacaRepository solicitacaoCriacaoPlacaRepository;

    /**
     * Injeção do Conversor de TiposPlacaEnum
     */
    @Inject
    TiposPlacasEnumConverter tiposPlacasEnumConverter;

    /**
     * Injetando repositorio da entidade Placa
     */
    @Inject
    PlacaRepository placaRepository;

    /**
     * Metodo responsável por criar uma SolicitacoaCriacaoPlaca no banco de dados
     * @param dto Informações da SolicitacaoCriacaoPlaca
     * @return DTO da nova solicitação de Criação
     */
    @Transactional
    public SolicitacaoCriacaoPlacaDTO criarSolicitacaoCriacaoPlaca(NovaSolicitacaoCriacaoPlacaDTO dto) {
        SolicitacaoCriacaoPlaca orm = new SolicitacaoCriacaoPlaca();
        orm.setDataCriacaoSolicitacao(LocalDateTime.now());
        orm.setTipoPlaca(this.tiposPlacasEnumConverter.convertToEntityAttribute(dto.getTipoPlaca()));
        orm.setCodigoEstadoSolicitante(dto.getCodigoEstadoSolicitante().toUpperCase());

        this.solicitacaoCriacaoPlacaRepository.persist(orm);

        return this.converteOrmToDto(orm);
    }

    /**
     * Metodo responsável por realizar uma busca de SolicitacaoCriacaoPlaca pelo ID e deferir
     * a Solicitacao e assim realizando a criação de uma entidade Placa no banco
     * de dados, após a gravação é deletada a SolicitacaoCriacaoPlaca da base
     * @param id Id da SolicitacaoCriacaoPlaca para filtro
     */
    @Transactional
    public void deferirSolicitacaoCriacaoPlaca(Long id) {
        SolicitacaoCriacaoPlaca orm = this.solicitacaoCriacaoPlacaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("ID da Entidade da Solicitacao de Criação de Placa inexistente"));

        Placa placa = new Placa();
        placa.setTipoPlaca(orm.getTipoPlaca());
        placa.setDataFabricacao(LocalDate.now());
        placa.setEstadoFabricacao(orm.getCodigoEstadoSolicitante());
        placa.setCodigoPlaca(this.geraNovoNumeroPlacaUnico());

        this.placaRepository.persist(placa);
        this.solicitacaoCriacaoPlacaRepository.delete(orm);
    }

    /**
     * Metodo responsavel por realizar a busca da lista com todas as SolicitacaoCriacaoPlaca,
     * utilizando opcionalmente 3 filtros
     * @param dataCriacao Data de criação da Solicitação para filtro
     * @param tipoPlaca Tipo da placa da Solicitação para filto
     * @param estadoSolicitante Estado solicitante da Solicitação para filtro
     * @return lista com as informações utilizadas por filtros, caso não utilize filtros irá listar
     * todas as solicitações em DTO
     */
    public List<SolicitacaoCriacaoPlacaDTO> listarPorFiltros(String dataCriacao,
                                                             String tipoPlaca,
                                                             String estadoSolicitante){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = dataCriacao != null ? LocalDate.parse(dataCriacao, formatter) : null;
        TiposPlacasEnum tipoPlacaEnum = TiposPlacasEnum.convertStringToPlacaEnum(tipoPlaca);

        List<SolicitacaoCriacaoPlaca> ormList = this.solicitacaoCriacaoPlacaRepository
                .listarComFiltros(data, tipoPlacaEnum, estadoSolicitante);

        return ormList.stream()
                .map(orm -> this.converteOrmToDto(orm))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que realiza a conversão do SolicitacaoCriacaoPlaca ORM/Entidade para DTO da
     * SolicitacaoCricaoPlaca
     * @param orm Entidade da SolicitacaoCriacaoPlaca/ORM
     * @return Conversão em DTO do SolicitacaoCriacaoPlaca
     */
    private SolicitacaoCriacaoPlacaDTO converteOrmToDto(SolicitacaoCriacaoPlaca orm) {
        SolicitacaoCriacaoPlacaDTO dto = new SolicitacaoCriacaoPlacaDTO();
        dto.setIdSolicitacao(orm.getIdSolicitacao());
        dto.setDataCriacaoSolicitacao(orm.getDataCriacaoSolicitacao());
        dto.setTipoPlaca(this.tiposPlacasEnumConverter.convertToDatabaseColumn(orm.getTipoPlaca()));
        dto.setCodigoEstadoSolicitante(orm.getCodigoEstadoSolicitante());

        return dto;
    }

    /**
     * Metodo responsável pela geração de um numero aleatorio
     * @return numero aleatorio gerado
     */
    private int geraNumeroAleatorio() {
        return (int) (MINIMO_CODIGO_PLACA + Math.random() *
                (MAXIMO_CODIGO_PLACA - MINIMO_CODIGO_PLACA));
    }

    /**
     * Metodo responsável por validar o se o número gerado para
     * placa já consta no banco de dados, gerar uma nova placa
     * @return numero da placa formato para String
     */
    private String geraNovoNumeroPlacaUnico() {
        int numAleatorio = this.geraNumeroAleatorio();

        while (this.placaRepository.validaSePlacaExistePeloNumero(numAleatorio)) {
            numAleatorio = this.geraNumeroAleatorio();
        }
        return String.format("%07d", numAleatorio);
    }
}
