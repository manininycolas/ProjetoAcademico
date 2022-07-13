package projeto.detran.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import projeto.detran.models.Placa;

import javax.enterprise.context.ApplicationScoped;

/**
 * Classe responsável por fazer a manipulação via SQL da tabela de Placa.
 * (LISTAGEM - INSERT - UPDATE - DELETE)
 */
@ApplicationScoped
public class PlacaRepository implements PanacheRepository<Placa> {

    /**
     * Metodo responsável por validar se a placa existe no banco de dados filtrando pelo seu número
     * @param numPlaca Recebimento do número da placa
     * @return true: numeração já existente no banco; false: numeração não existe no banco
     */
    public boolean validaSePlacaExistePeloNumero(int numPlaca){
        String numPlacaEmString = numPlaca+"";
        return this.count("codigoPlaca", numPlacaEmString) > 0;
    }
}
