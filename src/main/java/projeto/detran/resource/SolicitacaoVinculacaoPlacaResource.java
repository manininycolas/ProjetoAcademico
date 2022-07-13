package projeto.detran.resource;


import projeto.detran.service.SolicitacaoVinculacaoPlacaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe que contem as declaracoes dos endpoints para manipulação
 * da entidade SolicitacaoVinculacaoPlaca
 */
@Path("/solicitacoes-vinculacao-placa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacaoVinculacaoPlacaResource {
    /**
     * Injeção do Servie da Entidade SolicitacooVinculacaoPlaca
     */
    @Inject
    SolicitacaoVinculacaoPlacaService solicitacaoVinculacaoPlacaPlacaService;

    /**
     * Metodo com a declaração do endpoint que realiza a criação de uma nova SolicitacooVinculacaoPlaca
     * @param idVeiculo Parametro recebido na URL com o Id do Veiculo
     * @param idPlaca Parametro recebido na URL com o Id da Placa
     * @return Response com status (200) OK e o body sendo DTO da nova SolicitacooVinculacaoPlaca
     */
    @Path("/novo/veiculo/{idVeiculo}/placa/{idPlaca}")
    @POST
    public Response criarSolicitacaoVinculacaoPlaca(@PathParam("idVeiculo") Long idVeiculo,
                                                    @PathParam("idPlaca") Long idPlaca) {
        return Response.ok(this.solicitacaoVinculacaoPlacaPlacaService
                .criarSolicitacaoVinculacaoPlaca(idVeiculo, idPlaca)).build();
    }

    /**
     * Metodo com a declarão do endpoint que realiza o deferimento de uma SolicitacooVinculacaoPlaca
     * @param idSolicitacaoVinculacao Parametro recebido na URL com o Id da SolicitacooVinculacaoPlaca
     * @return Response com status (200) OK e o body sendo um DTO da SolicitacooVinculacaoPlaca pelo id
     */
    @Path("deferir/{id}")
    @PUT
    public Response deferirVinculacaoPlaca(@PathParam("id")Long idSolicitacaoVinculacao) {
        this.solicitacaoVinculacaoPlacaPlacaService.deferirSolicitacaoVinculacaoPlaca(idSolicitacaoVinculacao);
        return Response.noContent().build();
    }

    /**
     * Metodo com a declaração do endpoint que busca uma lista de SolicitacooVinculacaoPlaca utilizando
     * filtros
     * @param dataCriacao Data de criação da Solicitacoo
     * @param tipoPlaca Tipo da placa da Solicitacoo
     * @param estadoFabricacaoPlaca Estado de fabricação da placa da Solicitacoo
     * @param codigoPlaca Codigo da placa da Solicitacoo
     * @param nomeVeiculo Nome do veiculo da Solicitacoo
     * @param marcaVeiculo Marca do veiculo da Solicitacoo
     * @param anoFabricacaoVeiculo Ano de fabricação do veiculo da Solicitacoo
     * @param donoVeiculo Nome do dono do veiculo da Solicitacoo
     * @return Response com status (200) OK e o body sendo uma lista de DTO da SolicitacooVinculacaoPlaca
     * pelos filtros utilizados, caso nao utilize filtros, irá listar todos
     */
    @GET
    public Response listarSolicitacoes(@QueryParam("dataCriacao") String dataCriacao,
                                       @QueryParam("tipoPlaca") String tipoPlaca,
                                       @QueryParam("estadoFabricacaoPlaca") String estadoFabricacaoPlaca,
                                       @QueryParam("codigoPlaca") String codigoPlaca,
                                       @QueryParam("nomeVeiculo") String nomeVeiculo,
                                       @QueryParam("marcaVeiculo") String marcaVeiculo,
                                       @QueryParam("anoFabricacaoVeiculo") Integer anoFabricacaoVeiculo,
                                       @QueryParam("nomeDonoVeiculo") String donoVeiculo) {

        return Response.ok(this.solicitacaoVinculacaoPlacaPlacaService.listarPorFiltros(
                dataCriacao,
                tipoPlaca,
                estadoFabricacaoPlaca,
                codigoPlaca,
                nomeVeiculo,
                marcaVeiculo,
                anoFabricacaoVeiculo,
                donoVeiculo)).build();
    }

}

