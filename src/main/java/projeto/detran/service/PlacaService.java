package projeto.detran.service;

import projeto.detran.dto.PlacaDTO;
import projeto.detran.enums.TiposPlacasEnumConverter;
import projeto.detran.models.Placa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Responsavel por conter as regras de negocio
 * e implementação da manipulação da entidade Placa
 */
@ApplicationScoped
public class PlacaService {

    /**
     * Injeção do Conversor de TiposPlacaEnum
     */
    @Inject
    TiposPlacasEnumConverter tiposPlacasEnumConverter;

    /**
     * Metodo que realiza a conversão da Placa ORM/Entidade para DTO da Placa
     * @param orm Entidade da Placa/ORM
     * @return Conversão em DTO da Placa
     */
    public PlacaDTO convertePlacaOrmToDto (Placa orm){
        PlacaDTO placaDto = new PlacaDTO();
        placaDto.setIdPlaca(orm.getIdPlaca());
        placaDto.setCodigoPlaca(orm.getCodigoPlaca());
        placaDto.setTipoPlaca(this.tiposPlacasEnumConverter.convertToDatabaseColumn(orm.getTipoPlaca()));
        placaDto.setDataFabricacao(orm.getDataFabricacao());
        placaDto.setEstadoFabricacao(orm.getEstadoFabricacao());

        return placaDto;
    }
}
