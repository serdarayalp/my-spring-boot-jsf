package de.mydomain;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productController")
@ELBeanName(value = "productController")
@Join(path = "/product", to = "/product-form.jsf")
public class ProductController {

    private final ProductService productService;

    private Product product = new Product();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public String save() {
        productService.save(product);
        product = new Product();
        return "/product-list.xhtml?faces-redirect=true";
    }

    public Product getProduct() {
        return product;
    }
}