package projeto.detran.service;

import projeto.detran.dto.AtualizarVeiculoDTO;
import projeto.detran.dto.CriarVeiculoDTO;
import projeto.detran.dto.VeiculoDTO;
import projeto.detran.models.Pessoa;
import projeto.detran.models.Veiculo;
import projeto.detran.repository.PessoaRepository;
import projeto.detran.repository.VeiculoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsavel por conter as regras de negocio
 * e implementação da manipulação da entidade Veiculo
 */
@ApplicationScoped
public class VeiculosService {

    /**
     * Injeção do repositorio da entidade Veiculo
     */
    @Inject
    VeiculoRepository veiculoRepository;

    /**
     * Injeção do repositorio da entidade Pessoa
     */
    @Inject
    PessoaRepository pessoaRepository;

    /**
     * Injeção da classe Service da entidade Pessoa
     */
    @Inject
    PessoaService pessoaService;

    /**
     * Metodo responsavel por realizar a busca da lista com todos os Veiculos
     * e retorna a lista em DTO
     * @return lista com todos os Veiculos em DTO
     */
    public List<VeiculoDTO> listarTodos() {
        List<Veiculo> ormList = this.veiculoRepository.listAll();
        return ormList.stream()
                .map(orm -> this.converteOrmToDTO(orm))
                .collect(Collectors.toList());
    }

    /**
     * Metodo responsável por realizar uma busca de Veiculo pelo ID
     * @param id ID do Veiculo para filtro
     * @return Veiculo filtrado pelo ID em DTO
     */
    public VeiculoDTO buscarPeloId(Long id) {
        Veiculo orm = this.veiculoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("ID da entidade Veiculos inexistente"));

        return this.converteOrmToDTO(orm);
    }

    /**
     * Metodo responsável por realizar a gravação de um novo Veiculo no banco de dados
     * @param dto Informações do Veiculo
     * @return DTO do novo Veiculo
     */
    @Transactional
    public VeiculoDTO salvarNovo(CriarVeiculoDTO dto){
        Veiculo orm = new Veiculo();
        orm.setNomeVeiculo(dto.getNomeVeiculo().toUpperCase());
        orm.setMarcaVeiculo(dto.getMarcaVeiculo().toUpperCase());
        orm.setNumAnoFabricacao(dto.getNumAnoFabricacao());

        this.validaESettaPessoaNoOrm(orm, dto.getPessoa().getIdPessoa());

        this.veiculoRepository.persist(orm);

        return this.converteOrmToDTO(orm);
    }

    /**
     * Metodo responsável por realizar a alteração de dados do Veiculo no banco de dados
     * @param dto Alterações de Veiculo
     * @return DTO do Veiculo com as alterações
     */
    @Transactional
    public VeiculoDTO atualizar(AtualizarVeiculoDTO dto) {
        Veiculo orm = this.veiculoRepository.findByIdOptional(dto.getIdVeiculo())
                .orElseThrow(() -> new NotFoundException("ID da entidade Veiculos inexistente"));

        orm.setNomeVeiculo(dto.getNomeVeiculo().toUpperCase());
        orm.setMarcaVeiculo(dto.getMarcaVeiculo().toUpperCase());
        orm.setNumAnoFabricacao(dto.getNumAnoFabricacao());

        this.validaESettaPessoaNoOrm(orm, dto.getPessoa().getIdPessoa());

        return this.converteOrmToDTO(orm);
    }

    /**
     * Metodo que realiza a conversão do Veiculo ORM/Entidade para DTO do Veiculo
     * @param orm Entidade do Veiculo/ORM
     * @return Conversão em DTO do Veiculo
     */
    public VeiculoDTO converteOrmToDTO(Veiculo orm) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setIdVeiculo(orm.getIdVeiculo());
        dto.setMarcaVeiculo(orm.getMarcaVeiculo());
        dto.setNomeVeiculo(orm.getNomeVeiculo());
        dto.setNumAnoFabricacao(orm.getNumAnoFabricacao());
        dto.setPessoa(this.pessoaService.converteOrmToDTO(orm.getPessoa()));

        return dto;
    }

    /**
     * Metodo que realiza a validação de verificar existencia da Pessoa no banco de dados
     * filtrando pelo id e settar as informações de Pessoa no ORM do Veiculo
     * @param orm Entidade do Veiculo/ORM
     * @param idPessoa Recebimento do id da Pessoa
     */
    private void validaESettaPessoaNoOrm(Veiculo orm, Long idPessoa) {
        Pessoa pessoaOrm = this.pessoaRepository.findByIdOptional(idPessoa)
                .orElseThrow(() -> new NotFoundException("ID da entidade Pessoa inexistente"));
        orm.setPessoa(pessoaOrm);
    }
}
