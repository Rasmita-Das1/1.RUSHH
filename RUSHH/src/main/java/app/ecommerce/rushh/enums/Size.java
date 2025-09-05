package app.ecommerce.rushh.enums;

public enum Size {
	
    SIZE_26(26),
    SIZE_28(28),
    SIZE_30(30),
    S(null),
    M(null),
    L(null),
    XL(null);

    private final Integer numericValue;

    Size(Integer numericValue) {
        this.numericValue = numericValue;
    }

    public Integer getNumericValue() {
        return numericValue;
    }
    
}

