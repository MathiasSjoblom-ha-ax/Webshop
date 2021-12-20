package ax.ha.clouddevelopment.webshopapi.domain.model;

public class ShoppingCartCostSummary {
    private int total;
    private int tax;
    private String currency;

    public ShoppingCartCostSummary(int total, int tax, String currency) {
        this.total = total;
        this.tax = tax;
        this.currency = currency;
    }

    public int getTotal() {
        return total;
    }

    public int getTax() {
        return tax;
    }

    public String getCurrency() {
        return currency;
    }
}
