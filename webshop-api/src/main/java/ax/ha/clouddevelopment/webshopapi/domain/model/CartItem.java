package ax.ha.clouddevelopment.webshopapi.domain.model;

import java.util.UUID;

public class CartItem {
    private UUID productId;
    private int amount;

    public CartItem(UUID productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public CartItem() {
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
