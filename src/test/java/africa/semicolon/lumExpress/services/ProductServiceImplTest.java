package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.CreateProductRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private iProductService productService;
    CreateProductRequest request;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucskyship/Documents/SEMICOLON/lumExpress/lumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));
        request = CreateProductRequest
                .builder()
                .productName("Milk")
                .category("Beverages")
                .price(BigDecimal.valueOf(30.00))
                .quantity(10)
                .imageUrl(file)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createProduct() {
        var createProductResponse = productService.createProduct(request);
        assertThat(createProductResponse).isNotNull();
        assertThat(createProductResponse.getProductId()).isGreaterThan(0L);
        assertThat(createProductResponse.getMessage()).isNotNull();
        assertThat(createProductResponse.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetails() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProducts() {
    }
}