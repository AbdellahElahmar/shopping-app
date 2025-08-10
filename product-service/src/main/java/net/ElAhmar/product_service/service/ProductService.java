package net.ElAhmar.product_service.service;
import lombok.RequiredArgsConstructor;
import net.ElAhmar.product_service.dto.ProductRequest;
import net.ElAhmar.product_service.dto.ProductResponse;
import net.ElAhmar.product_service.model.Product;
import net.ElAhmar.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(String id) {
        return productRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        return mapToResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(String id, ProductRequest request) {
        Product updated = productRepository.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setDescription(request.getDescription());
                    existing.setPrice(request.getPrice());
                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        return mapToResponse(updated);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }



}