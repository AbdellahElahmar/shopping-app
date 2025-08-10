package net.ElAhmar.product_service.unit;


import net.ElAhmar.product_service.dto.ProductResponse;
import net.ElAhmar.product_service.model.Product;
import net.ElAhmar.product_service.repository.ProductRepository;
import net.ElAhmar.product_service.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void shouldCreateProduct() {
        Product product = Product.builder()
                .id("1")
                .name("Laptop")
                .description("Test")
                .price(1000.00)
                .build();
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponse> products = productService.getAllProducts();

        Assertions.assertEquals(1, products.size());

        Assertions.assertEquals("Laptop", products.get(0).getName());

        Assertions.assertEquals(1000.00, products.get(0).getPrice());
    }
}
