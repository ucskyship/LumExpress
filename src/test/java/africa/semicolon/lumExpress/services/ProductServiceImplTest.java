package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllElementRequest;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class ProductServiceImplTest {
    @Autowired
    private iProductService productService;
    AddProductRequest request;
    AddProductRequest request1;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucskyship/uc OTHERS/Documents/uc DOCUMENTS/SEMICOLON/lumExpress/lumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));
        request = AddProductRequest
                .builder()
                .productName("Milk")
                .productCategory("BEVERAGES")
                .price(BigDecimal.valueOf(39.89))
                .quantity(10)
                .imageUrl(file)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createProductTest() throws IOException {
        var createProductResponse = productService.addProduct(request);
        assertThat(createProductResponse).isNotNull();
        assertThat(createProductResponse.getProductId()).isGreaterThan(0L);
        assertThat(createProductResponse.getMessage()).isNotNull();
        assertThat(createProductResponse.getCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
    }

    @Test
    void getProductByIdTes() throws IOException {
        var savedProduct = productService.addProduct(request);
        var foundProduct = productService.getProductById(savedProduct.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(savedProduct.getProductId());
    }

    @Test
    void getAllProductsTest() throws IOException {
        productService.addProduct(request);
        var getItemRequest = buildGetAllElementRequest();
        var productPage = productService.getAllProducts(getItemRequest);
        log.info("page contents::{}", productPage);
    }

    private GetAllElementRequest buildGetAllElementRequest() {
        return GetAllElementRequest
                .builder()
                .numberOfProductPerPage(8)
                .pageNumber(1)
                .build();
    }
}