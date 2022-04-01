package de.mydomain;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {

    private final ProductRepository productRepository;

    private List<Product> products;

    public ProductListController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /* Beachten Sie, dass dieser Controller eine Methode namens loadData hat, die mit @Deferred, @RequestAction und @IgnorePostback annotiert ist. Diese Annotationen werden benötigt, um die Sammlung von Produkten zu laden, bevor die Schnittstelle gerendert wird. Wir könnten diese Sammlung auch in getProducts laden, aber das würde den Prozess des Renderings verlangsamen, da diese Methode im JSF-Lebenszyklus sehr oft aufgerufen wird. */
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        products = productRepository.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }
}