package projeto.detran.enums;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe Converter que converte o Enum de tipos de placas para Integer e Vice Versa
 */
@ApplicationScoped
@Converter
public class TiposPlacasEnumConverter implements AttributeConverter<TiposPlacasEnum, Integer> {

    /**
     * Metodo responsável por converter o Enum do TipoPlacaEnum para um integer
     * @param tiposPlacasEnum Recebimento do Enum TipoPlacaEnum
     * @return Id do TipoPlacaEnum
     */
    @Override
    public Integer convertToDatabaseColumn(TiposPlacasEnum tiposPlacasEnum) {
        if (tiposPlacasEnum == null) {
            return null;
        }
        return tiposPlacasEnum.getCodigo();
    }

    /**
     * Metodo respónsavel por converter o Integer do Tipo placa para um Enum TipoPlacaEnum
     * @param integer Id do Tipo Placa
     * @return Enum TipoPlacaEnum
     */
    @Override
    public TiposPlacasEnum convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        for (TiposPlacasEnum valorEnum : TiposPlacasEnum.values()) {
            if (valorEnum.getCodigo().equals(integer)) {
                return valorEnum;
            }
        }
        return null;
    }
}
