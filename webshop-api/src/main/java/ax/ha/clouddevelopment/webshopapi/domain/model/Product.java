
package ax.ha.clouddevelopment.webshopapi.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

/**
 * Product domain model
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Data // These two annotations actually generates the constructors, equal & builder methods aswell as setter and getters,
@Builder // But depending on used IDE you might not have support for annotation processing.
public class Product {
    private UUID id;
    private String name;
    private int price;
    private String description;
    private URI image;

    public Product() {
    }

    public Product(UUID id, String name, int price, String description, URI image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private UUID id;
        private String name;
        private int price;
        private String description;
        private URI image;

        ProductBuilder() {
        }

        public ProductBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(final int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder image(final URI image) {
            this.image = image;
            return this;
        }

        public Product build() {
            return new Product(this.id, this.name, this.price, this.description, this.image);
        }
    }

}