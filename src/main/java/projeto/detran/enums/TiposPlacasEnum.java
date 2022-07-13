package projeto.detran.enums;

/**
 * Enum que representa os tipos de placas
 */
public enum TiposPlacasEnum {

    /**
     * Declaração dos tipos de placas
     */
    PARTICULAR(1),
    COMERCIAL(2),
    ESPECIAL(3),
    OFICIAL(4),
    DIPLOMATICO(5),
    COLECIONADOR(6);

    /**
     * Codigo dos tipos de placas
     */
    private final Integer codigo;

    TiposPlacasEnum(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Metodo estatico responsável por converter uma String que representa o nome de cada instancia
     * do Enum para TipoPlacaEnum
     * @param nomeEnum Nome da Instancia
     * @return Valor que equivale o nome da instancia
     */
    public static TiposPlacasEnum convertStringToPlacaEnum(String nomeEnum){
        if (nomeEnum == null){
            return null;
        }
        for (TiposPlacasEnum enumValue : values()) {
            if (enumValue.name().equalsIgnoreCase(nomeEnum)){
                return enumValue;
            }
        }
        throw new RuntimeException("Tipo_Placa deve ser uma string valida");
    }
}
