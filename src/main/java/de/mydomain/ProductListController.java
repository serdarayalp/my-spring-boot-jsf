package de.mydomain;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {

    private final ProductService productService;

    private List<Product> products;

    public ProductListController(ProductService productService) {
        this.productService = productService;
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
}