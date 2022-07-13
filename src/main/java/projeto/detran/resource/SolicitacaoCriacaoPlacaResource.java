package projeto.detran.resource;

import projeto.detran.dto.NovaSolicitacaoCriacaoPlacaDTO;
import projeto.detran.service.SolicitacaoCriacaoPlacaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe que contem as declaracoes dos endpoints para manipulação
 * da entidade SolicitacaoCriacaoPlaca
 */
@Path("/solicitacoes-criacao-placa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacaoCriacaoPlacaResource {

    /**
     * Injeção do Service da entidade SolicitacaoCriacaoPlaca
     */
    @Inject
    SolicitacaoCriacaoPlacaService solicitacaoCriacaoPlacaService;

    /**
     * Metodo com a declaração do endpoint que realiza a criação de uma nova SolicitacaoCriacaoPlaca
     * @param dto Body da Request
     * @return Response com status (200) OK e o body sendo DTO da nova SolicitacaoCriacaoPlaca
     */
    @POST
    public Response criarSolicitacaoCriacaoPlaca(NovaSolicitacaoCriacaoPlacaDTO dto) {
        return Response.ok(this.solicitacaoCriacaoPlacaService
                .criarSolicitacaoCriacaoPlaca(dto)).build();
    }

    /**
     * Metodo com a declarão do endpoint que realiza o deferimento de uma SolicitacaoCriacaoPlaca
     * @param id Parametro recebido na URL com o ID da SolicitacaoCriacaoPlaca
     * @return Response com status (200) OK e o body sendo um DTO da SolicitacaoCriacaoPlaca pelo id
     */
    @Path("/deferir/{id}")
    @PUT
    public Response deferirSolicitacaoCriacaoPlaca(@PathParam("id")Long id) {
        this.solicitacaoCriacaoPlacaService.deferirSolicitacaoCriacaoPlaca(id);
        return Response.noContent().build();
    }

    /**
     * Metodo com a declaração do endpoint que busca uma lista de SolicitacaoCriacaoPlaca utilizando
     * filtros
     * @param dataCriacao Data da criação da Solicitacao para filtro
     * @param tipoPlaca Tipo da placa da Solicitacao para filtro
     * @param estadoSolicitante Estado solicitante da Solicitacao para filtro
     * @return Response com status (200) OK e o body sendo uma lista de DTO da SolicitacaoCriacaoPlaca
     * pelos filtros utilizados, caso nao utilize filtros, irá listar todos
     */
    @GET
    public Response listarSolicitacoesDeCriacao(@QueryParam("dataCriacao") String dataCriacao,
                                                @QueryParam("tipoPlaca") String tipoPlaca,
                                                @QueryParam("estadoSolicitante") String estadoSolicitante) {

        return Response.ok(this.solicitacaoCriacaoPlacaService
                .listarPorFiltros(dataCriacao, tipoPlaca, estadoSolicitante)).build();
    }

}
