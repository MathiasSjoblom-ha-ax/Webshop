
package ax.ha.clouddevelopment.webshopapi.interfaces.controller;

import ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCartCostSummary;
import ax.ha.clouddevelopment.webshopapi.domain.service.NoteService;
import ax.ha.clouddevelopment.webshopapi.domain.service.WebshopService;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.ProductsApi;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.ShoppingCartsApi;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller for the webshop API
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@RestController
public class WebshopApiController implements ShoppingCartsApi, ProductsApi {

    private final NoteService noteService;

    private final WebshopService webshopService;

    @Autowired
    public WebshopApiController(final NoteService noteService,
                                final WebshopService webshopService) {
        this.noteService = noteService;
        this.webshopService = webshopService;
    }

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(webshopService.getProducts().stream()
                .map(product -> new Product()
                        .id(product.getId())
                        .description(product.getDescription())
                        .image(product.getImage())
                        .name(product.getName())
                        .price(product.getPrice()))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ShoppingCartSummary> createCart(@Valid ShoppingCart shoppingCart) {
        final var cart = toShoppingCart(shoppingCart);
        webshopService.addShoppingCart(cart);
        return ResponseEntity.ok(toCartSummary(cart));
    }

    @Override
    public ResponseEntity<ShoppingCartSummary> getCart(UUID cartId) {
        return ResponseEntity.ok(toCartSummary(webshopService.getShoppingCart(cartId)));
    }

    @Override
    public ResponseEntity<List<ShoppingCartSummary>> getCarts() {
        return ResponseEntity.ok(webshopService.getShoppingCarts().stream()
                .map(this::toCartSummary)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ShoppingCartSummary> updateCart(UUID cartId, @Valid ShoppingCart shoppingCart) {
        var cart = toShoppingCart(shoppingCart);
        webshopService.updateShoppingCart(cartId, cart);
        return ResponseEntity.ok(toCartSummary(cart));
    }

    private ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCart toShoppingCart(ShoppingCart shoppingCart) {
        final var cart = new ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCart();
        cart.setId(UUID.randomUUID());
        cart.setProductItems(shoppingCart.getProductItems().stream()
                .map(item -> new ax.ha.clouddevelopment.webshopapi.domain.model.CartItem(item.getProductId(), item.getAmount()))
                .collect(Collectors.toList()));
        return cart;
    }

    private ShoppingCartSummary toCartSummary(final ax.ha.clouddevelopment.webshopapi.domain.model.ShoppingCart shoppingCart) {
        final ShoppingCartCostSummary costSummary = webshopService.calculateCostSummary(shoppingCart);
        return new ShoppingCartSummary()
                .id(shoppingCart.getId())
                .productItems(shoppingCart.getProductItems().stream()
                        .map(item -> new CartItem()
                                .amount(item.getAmount())
                                .productId(item.getProductId()))
                        .collect(Collectors.toList()))
                .cost(new CostSummary()
                        .currency(costSummary.getCurrency())
                        .total(costSummary.getTotal())
                        .tax(costSummary.getTax()));
    }
}