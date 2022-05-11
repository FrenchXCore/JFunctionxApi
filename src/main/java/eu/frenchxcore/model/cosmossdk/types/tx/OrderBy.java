package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderBy {

    ORDER_BY_UNSPECIFIED(0, "0-ORDER_BY_UNSPECIFIED specifies an unknown sorting order. OrderBy defaults to ASC in this case."),
    ORDER_BY_ASC(1, "1-ORDER_BY_ASC defines ascending order."),
    ORDER_BY_DESC(2, "2-ORDER_BY_DESC defines descending order.");

    public final int iVal;
    public final String sVal;

    @JsonValue
    public final String name;

    OrderBy(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }

}
