
package ax.ha.clouddevelopment.webshopapi.interfaces.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redirects to swagger UI automatically
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String indexPage() {
        return "redirect:/swagger-ui/index.html";
    }
}