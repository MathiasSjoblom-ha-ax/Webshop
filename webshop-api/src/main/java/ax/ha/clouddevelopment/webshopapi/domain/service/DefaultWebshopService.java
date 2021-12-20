
package ax.ha.clouddevelopment.webshopapi.domain.service;

import ax.ha.clouddevelopment.webshopapi.domain.model.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Default implementation of {@link WebshopService}
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Service
public class DefaultWebshopService implements WebshopService {

    private final List<Product> products;

    private final List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public DefaultWebshopService() {
        this.products = new ArrayList<>(Arrays.asList(Product.builder()
                        .id(UUID.randomUUID())
                        .name("AWS Costs")
                        .image(URI.create("https://lumecloud.com/wp-content/uploads/2017/10/Lume_BraceYourselves-AWS-meme.jpg"))
                        .description("Its actually not that bad. Unless we screw up ¯\\_(ツ)_/¯")
                        .price(50)
                        .build(),
                Product.builder()
                        .id(UUID.randomUUID())
                        .name("In the cloud")
                        .image(URI.create("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe7S--qdUwTA0j2JJB9fiaLEPv4Ei5XbNA3w&usqp=CAU"))
                        .description("That means it is awesome, right?")
                        .price(150)
                        .build(),
                Product.builder()
                        .id(UUID.randomUUID())
                        .name("AWS Data Center")
                        .image(URI.create("https://www.memecreator.org/static/images/memes/5250630.jpg"))
                        .price(25)
                        .build()));
    }

    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public List<ShoppingCart> getShoppingCarts() {
        return this.shoppingCarts;
    }

    @Override
    public ShoppingCart getShoppingCart(UUID cartId) {
        return this.shoppingCarts.stream()
                .filter(cart -> cart.getId().equals(cartId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such cart exists. " +
                        "In a real situation, this should be handled with a HTTP 400 response"));
    }

    @Override
    public void updateShoppingCart(UUID cartId, ShoppingCart shoppingCart) {
        shoppingCart.setId(cartId); // In case this was forgotten in interface
        this.shoppingCarts.removeIf(cart -> cart.getId().equals(cartId));
        this.shoppingCarts.add(shoppingCart);
    }

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCarts.add(shoppingCart);
    }

    @Override
    public ShoppingCartCostSummary calculateCostSummary(ShoppingCart shoppingCart) {
        int itemsCost = 0;
        for (CartItem cartItem : shoppingCart.getProductItems()) {
            final Product currentProduct = products.stream()
                    .filter(product -> product.getId().equals(cartItem.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No product exists with id " + cartItem.getProductId()));
            itemsCost += currentProduct.getPrice() * cartItem.getAmount();
        }
        int taxCost = (int)(itemsCost * 0.24); // In this webshop we dont deal in cents.
        int totalCost = itemsCost + taxCost;
        return new ShoppingCartCostSummary(totalCost, taxCost, "EUR");
    }

}