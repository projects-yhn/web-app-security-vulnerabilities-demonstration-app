package com.yhn.webappcyberattacksdemoapp.xxe;

import com.yhn.webappcyberattacksdemoapp.xxe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
