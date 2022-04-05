package de.mydomain;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Scope(value = "session")
@Component(value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {

    private final ProductService productService;
    private HttpServletRequest request;

    private List<Product> products;

    public ProductListController(ProductService productService,
                                 HttpServletRequest request) {
        this.productService = productService;
        this.request = request;
    }


    /* Beachten Sie, dass dieser Controller eine Methode namens loadData hat, die mit @Deferred, @RequestAction und @IgnorePostback annotiert ist. Diese Annotationen werden benötigt, um die Sammlung von Produkten zu laden, bevor die Schnittstelle gerendert wird. Wir könnten diese Sammlung auch in getProducts laden, aber das würde den Prozess des Renderings verlangsamen, da diese Methode im JSF-Lebenszyklus sehr oft aufgerufen wird. */
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        products = productService.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void logout() throws ServletException {
        request.logout();
    }

    public User getUser() {

        KeycloakPrincipal principal = (KeycloakPrincipal) request.getUserPrincipal();

        KeycloakSecurityContext securityContext = principal.getKeycloakSecurityContext();
        AccessToken accessToken = securityContext.getToken();

        String username = accessToken.getPreferredUsername();
        String emailId = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();

        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        Set<String> roles = realmAccess.getRoles();

        return new User(username, emailId, lastname, firstname, realmName, roles);
    }
}