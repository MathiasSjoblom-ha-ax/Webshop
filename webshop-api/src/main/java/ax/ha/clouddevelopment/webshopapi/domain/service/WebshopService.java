
package ax.ha.clouddevelopment.webshopapi.domain.service;

import ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCartCostSummary;
import ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCart;
import ax.ha.clouddevelopment.webshopapi.domain.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Interface of a webshop service managing products and advertisements
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
public interface WebshopService {

    List<Product> getProducts();

    List<ShoppingCart> getShoppingCarts();

    ShoppingCart getShoppingCart(UUID cartId);

    void updateShoppingCart(UUID cartId, ShoppingCart shoppingCart);

    void addShoppingCart(ShoppingCart shoppingCart);

    ShoppingCartCostSummary calculateCostSummary(ShoppingCart shoppingCart);
}