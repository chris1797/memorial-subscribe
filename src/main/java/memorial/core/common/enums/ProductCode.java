package memorial.core.common.enums;

public enum ProductCode {

    IN_MEMORIAL("IN_MEMORIAL", "실내 추모관"),
    OUT_MEMORIAL("OUT_MEMORIAL", "야외 추모관"),
    ;

    private final String code;
    private final String name;

    ProductCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
