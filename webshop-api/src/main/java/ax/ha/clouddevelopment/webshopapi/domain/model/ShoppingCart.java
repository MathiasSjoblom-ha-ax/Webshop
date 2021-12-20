
package ax.ha.clouddevelopment.webshopapi.domain.model;

import java.util.List;
import java.util.UUID;

/**
 * Shopping cart domain model
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
public class ShoppingCart {
    private UUID id;
    private List<CartItem> productItems;

    public ShoppingCart() {
    }

    public ShoppingCart(UUID id, List<CartItem> productItems) {
        this.id = id;
        this.productItems = productItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CartItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<CartItem> productItems) {
        this.productItems = productItems;
    }
}