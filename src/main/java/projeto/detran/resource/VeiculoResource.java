package projeto.detran.resource;

import projeto.detran.dto.AtualizarVeiculoDTO;
import projeto.detran.dto.CriarVeiculoDTO;
import projeto.detran.service.VeiculosService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe que contem as declaracoes dos endpoints para manipulação da entidade Veiculo
 */
@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    /**
     * Injeção da classe Service da entidade Veiculo
     */
    @Inject
    VeiculosService veiculosService;

    /**
     * Metodo com a declaração do endpoint que busca uma lista de todos os Veiculos
     * @return Response com status (200) Ok e o body sendo a lista de DTOs do Veiculo
     */
    @GET
    public Response listarTodos() {
        return Response.ok(this.veiculosService.listarTodos()).build();
    }

    /**
     * Metodo com a declaração do endpoint que busca apenas um Veiculo pelo id
     * @param id Parametro que é recebido na URL com o id do Veiculo
     * @return Response com status (200) OK e o body sendo um DTO do Veiculo pelo id
     */
    @Path("/{id}")
    @GET
    public Response buscarPeloId(@PathParam("id") Long id){
        return Response.ok(this.veiculosService.buscarPeloId(id)).build();
    }

    /**
     * Metodo com declaração do endpoint que realiza a criação de um novo Veiculo
     * @param dto Body da Request
     * @return Response com status (200) OK e o body sendo DTO de um novo Veiculo
     */
    @POST
    public Response salvarNovo(CriarVeiculoDTO dto){
        return Response.ok(this.veiculosService.salvarNovo(dto)).build();
    }

    /**
     * Metodo com declaração do endpoint que realiza alteração dos Dados do Veiculo
     * @param dto Body da Request
     * @return Response com status (200) OK e o body sendo DTO do Veiculo com a alteração feita
     */
    @PUT
    public Response atualizar(AtualizarVeiculoDTO dto) {
        return Response.ok(this.veiculosService.atualizar(dto)).build();
    }
}
