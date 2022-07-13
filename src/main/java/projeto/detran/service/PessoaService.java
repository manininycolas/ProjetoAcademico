package projeto.detran.service;

import projeto.detran.dto.AtualizarPessoaDTO;
import projeto.detran.dto.CriarPessoaDTO;
import projeto.detran.dto.PessoaDTO;
import projeto.detran.models.Pessoa;
import projeto.detran.repository.PessoaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsavel por conter as regras de negocio
 * e implementação da manipulação da Entidade Pessoa
 */
@ApplicationScoped
public class PessoaService {

    /**
     * Injeção do repositorio da entidade Pessoa
     */
    @Inject
    PessoaRepository pessoaRepository;

    /**
     * Metodo responsável por realizar a busca da lista com todas as Pessoas
     * e retorna a lista em DTO
     * @return lista com todas as Pessoas em DTO
     */
    public List<PessoaDTO> listarTodos() {
        List<Pessoa> ormList = this.pessoaRepository.listAll();
        List<PessoaDTO> dtoList = new ArrayList<>();
        for (Pessoa orm : ormList) {
            dtoList.add(this.converteOrmToDTO(orm));
        }
        return dtoList;
//        return ormList.stream() // -> Mesma Funcão de cima;
//                .map(orm -> {
//                    return this.converteOrmToDTO(orm);
//                })
//                .collect(Collectors.toList());
    }

    /**
     * Metodo responsável por realizar uma busca de Pessoa pelo ID
     * @param id ID da Pessoa para filtro
     * @return Pessoa filtrada pelo ID em DTO
     */
    public PessoaDTO buscarPeloId(Long id) {
        Pessoa orm = this.pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("ID da entidade Pessoa não existe"));

        return this.converteOrmToDTO(orm);
    }

        /**
         * Metodo responsável por realizar a gravação de uma nova Pessoa no banco de dados
         * @param dto Informações da nova Pessoa
         * @return DTO da nova Pessoa
         */
    @Transactional
    public PessoaDTO salvarNovo(CriarPessoaDTO dto) {
        Pessoa orm = new Pessoa();
        orm.setNomePessoa(dto.getNomePessoa().toUpperCase());
        orm.setDataNascimento(dto.getDataNascimento());
        this.pessoaRepository.persist(orm);

        return this.converteOrmToDTO(orm);
    }

    /**
     * Metodo responsavel por realizar a alteração de dados da Pessoa no banco de dados
     * @param dto Alterações da Pessoa
     * @return DTO da Pessoa com as alterações
     */
    @Transactional
    public PessoaDTO atualizar(AtualizarPessoaDTO dto) {
        Pessoa orm = this.pessoaRepository.findByIdOptional(dto.getIdPessoa())
                .orElseThrow(() -> new RuntimeException("ID da entidade Pessoa não existe"));

        orm.setNomePessoa(dto.getNomePessoa().toUpperCase());
        orm.setDataNascimento(dto.getDataNascimento());
        this.pessoaRepository.persist(orm);

        return this.converteOrmToDTO(orm);
    }

    /**
     * Metodo que realiza a conversão da Pessoa ORM/Entidade para DTO da Pessoa
     * @param orm Entidade da Pessoa /ORM
     * @return Conversão em DTO da Pessoa
     */
    public PessoaDTO converteOrmToDTO(Pessoa orm) {
        PessoaDTO dto = new PessoaDTO();
        dto.setIdPessoa(orm.getIdPessoa());
        dto.setNomePessoa(orm.getNomePessoa());
        dto.setDataNascimento(orm.getDataNascimento());

        return dto;
    }
}
