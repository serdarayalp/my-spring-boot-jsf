package de.mydomain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/* @Data-Annotation stammt aus der Lombok-Bibliothek, die wir noch in unsere Anwendung importieren müssen. Das Projekt Lombok zielt darauf ab, den sich in vielen Teilen einer Java-Anwendung wiederholenden Boilerplate-Code, wie Getter und Setter, zu reduzieren. */
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    protected Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
