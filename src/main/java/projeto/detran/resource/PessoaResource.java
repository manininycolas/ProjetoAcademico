package projeto.detran.resource;

import projeto.detran.dto.AtualizarPessoaDTO;
import projeto.detran.dto.CriarPessoaDTO;
import projeto.detran.service.PessoaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe que contem as declaracoes dos endpoints para manipulação da entidade Pessoa
 */
@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    /**
     * Injeção do Service da Pessoa
     */
    @Inject
    PessoaService pessoaService;

    /**
     * Metodo com a declaração do endpoint que busca uma lista de todas as Pessoas
     * @return Response com status (200) Ok e o body sendo a lista de DTOs da Pessoa
     */
    @GET
    public Response listarTodos(){
        return Response.ok(this.pessoaService.listarTodos()).build();
    }

    /**
     * Metodo com a declaração do endpoint que busca apenas uma Pessoa pelo id
     * @param buscaId Parametro recebido na URL com o ID da Pessoa
     * @return Response com status (200) OK e o body sendo um DTO da Pessoa pelo id
     */
    @Path("/{id}")
    @GET
    public Response buscarPeloID(@PathParam("id") Long buscaId){
        return Response.ok(this.pessoaService.buscarPeloId(buscaId)).build();
    }

    /**
     * Metodo com a declaração do endpoint que realiza a criação de uma nova Pessoa
     * @param dto Body da Request "Corpo da requisição"
     * @return Response com status (200) OK e o body sendo DTO da nova Pessoa
     */
    @POST
    public Response salvarNovo(CriarPessoaDTO dto) {
        return Response.ok(this.pessoaService.salvarNovo(dto)).build();
    }

    /**
     * Metodo com a declaração do endpoint que realiza a alteração de dados da Pessoa
     * @param dto Body da Request "Corpo da requisição"
     * @return Response com status (200) OK e o body sendo DTO da pessoa com a alteração feita
     */
    @PUT
    public Response atualizar(AtualizarPessoaDTO dto) {
        return Response.ok(this.pessoaService.atualizar(dto)).build();
    }
}
