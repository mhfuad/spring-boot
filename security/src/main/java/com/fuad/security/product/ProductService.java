package com.fuad.security.product;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(ProductRequest product) {
        ProductEntity p = new ProductEntity();
        p.setName(product.getName());
        p.setOwner(product.getOwner());
        p.setSize(product.getSize());
        productRepository.save(p);
    }

    public List<ProductEntity> allProduct() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findProduct(Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not found with the id " + id)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, ProductEntity productReq) {
        ProductEntity product = productRepository.findById(id).get();
        product.setName(productReq.getName());
        product.setOwner(productReq.getOwner());
        product.setSize(productReq.getSize());
        productRepository.save(product);
    }
}
