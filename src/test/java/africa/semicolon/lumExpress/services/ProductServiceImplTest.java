package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllElementRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
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
    AddProductResponse savedProduct;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucskyship/uc OTHERS/Documents/uc DOCUMENTS/SEMICOLON/lumExpress/lumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));

        request = buildAddProductRequest(file);
        savedProduct = productService.addProduct(request);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProductTest() throws IOException {
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductId()).isGreaterThan(0L);
        assertThat(savedProduct.getMessage()).isNotNull();
        assertThat(savedProduct.getStatusCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
        var updateRequest = buildUpdateProductRequest();
        var response = productService.updateProductDetails(updateRequest);
        assertThat(response).isNotNull();
        assertThat(response.getDescription()).isEqualTo("it's just milo");
    }

    @Test
    void getProductByIdTest() throws IOException {
        var foundProduct = productService.getProductById(savedProduct.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(savedProduct.getProductId());
    }

    @Test
    void getAllProductsTest() throws IOException {
        var getItemRequest = buildGetAllElementRequest();
        var productPage = productService.getAllProducts(getItemRequest);
        log.info("page contents::{}", productPage);
    }

    @Test
    void deleteProductTest() {
    }

    private AddProductRequest buildAddProductRequest(MultipartFile file) {
        return AddProductRequest
                .builder()
                .productName("Milk")
                .productCategory("BEVERAGES")
                .price(BigDecimal.valueOf(39.89))
                .quantity(10)
                .imageUrl(file)
                .build();
    }

    private UpdateProductRequest buildUpdateProductRequest() {
        return UpdateProductRequest
                .builder()
                .id(1L)
                .price(BigDecimal.valueOf(40.99))
                .quantity(1)
                .description("it's just milo")
                .build();
    }

    private GetAllElementRequest buildGetAllElementRequest() {
        return GetAllElementRequest
                .builder()
                .numberOfProductPerPage(8)
                .pageNumber(1)
                .build();
    }
}