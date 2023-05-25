package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.*;
import static java.time.LocalDateTime.now;

@Getter
@Setter
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 4000)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
    @Max(value = 9999, message = "Quantity must be less than or equal to 9999")
    private Integer quantity;

    private Boolean promo;
    private BigDecimal promoPrice;
    private Boolean active;
    private Boolean archived;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImageEntity> productImages;

    @PrePersist
    public void prePersist() {
        this.createdAt = now();
    }
}
