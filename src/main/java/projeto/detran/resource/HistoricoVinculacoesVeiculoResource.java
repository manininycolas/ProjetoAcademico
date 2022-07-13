package projeto.detran.resource;

import projeto.detran.service.HistoricoVinculacoesVeiculoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe que contem as declaracoes dos endpoints para manipulação
 * da entidade HistoricoVinculacoesVeiculo
 */
@Path("/historico-vinculacoes-veiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoVinculacoesVeiculoResource {

    /**
     * Injeção do Service da Entidade HistoricoVinculacoesVeiculo
     */
    @Inject
    HistoricoVinculacoesVeiculoService historicoVinculacoesVeiculoService;

    /**
     * Metodo com a declaração do endpoint que busca uma lista de HistoricoVinculacoesVeiculo pelo id
     * do Veiculo
     * @param idVeiculo Parametro que é recebido na URL com o id do Veiculo
     * @return Response com status (200) OK e o body sendo uma Lista do
     * HistoricoVinculacoesVeiculoculo pelo id do Veiculo
     */
    @Path("{idVeiculo}")
    @GET
    public Response buscarPorIdVeiculo(@PathParam("idVeiculo") Long idVeiculo){
        return Response.ok(this.historicoVinculacoesVeiculoService
                .listarHistoricoDoVeiculo(idVeiculo))
                .build();
    }
}
