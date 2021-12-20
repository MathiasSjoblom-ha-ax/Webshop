
package ax.ha.clouddevelopment.webshopapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Original page of swagger presents a petshop which requires user to use long and
 * ugly request parameters.
 *
 * This class changes that, and presents the correct documentation straight away
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Configuration
public class SwaggerFixConfiguration implements WebMvcConfigurer {

    private static final String SWAGGER_PETSHOP_URL = "https://petstore.swagger.io/v2/swagger.json";

    private final String openapiUri;

    public SwaggerFixConfiguration(@Value("${springdoc.swagger-ui.url}") final String openapiUri) {
        this.openapiUri = openapiUri;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!StringUtils.isEmpty(openapiUri)) {
            registry.addResourceHandler("/swagger-ui/index.html")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/")
                    .resourceChain(false)
                    .addResolver(new WebJarsResourceResolver())
                    .addResolver(new PathResourceResolver())
                    .addTransformer(this::transformSwaggerIndexPage);
        }
    }

    /**
     * Reads resource and replaces the default petshop URL in index page of swagger
     *
     * @param request request
     * @param resource resource
     * @param transformerChain transformerChain
     * @return Resource transformedResource
     * @throws IOException e
     */
    public Resource transformSwaggerIndexPage(final HttpServletRequest request,
                                              final Resource resource,
                                              final ResourceTransformerChain transformerChain)
            throws IOException {
        try (final BufferedReader buffer = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {
            final String content = buffer.lines().collect(Collectors.joining("\n"));

            final String changedContent = content.replace(SWAGGER_PETSHOP_URL, openapiUri);
            return new TransformedResource(resource, changedContent.getBytes());
        }
    }
}