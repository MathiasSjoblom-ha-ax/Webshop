package ax.ha.clouddevelopment.webshopapi.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a entity is not found.
 *
 * The correct response is 400 Bad Request, since the API was found but the data was invalid
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
