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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {
    @Autowired
    private iProductService productService;
    AddProductRequest request;
    AddProductResponse response;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucskyship/uc OTHERS/Documents/uc DOCUMENTS/SEMICOLON/lumExpress/lumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));

        request = buildAddProductRequest(file);
        response = productService.addProduct(request);
    }

    @AfterEach
    void tearDown() {
        productService.deleteALl();
    }

    @Test
    void addProductTest() {
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isGreaterThan(0L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    void updateProductDetailsTest() {
        var updateRequest = buildUpdateProductRequest();
        var response = productService.updateProductDetails(updateRequest);
        assertThat(response).isNotNull();
        assertThat(response.getDescription()).isEqualTo("it's just milo");
        assertThat(productService.count()).isEqualTo(1L);
    }

    @Test
    void getProductByIdTest() {
        var foundProduct = productService.getProductById(response.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(response.getProductId());
    }

    @Test
    void getAllProductsTest() {
        var getItemRequest = buildGetAllElementRequest();
        var productPage = productService.getAllProducts(getItemRequest);
        log.info("page contents::{}", productPage);
        assertEquals(1L, productService.count());
    }

    @Test
    void deleteProductTest() {
        var foundProduct = productService.getProductById(response.getProductId());
        productService.deleteProduct(foundProduct.getId());
        assertThat(productService.count()).isEqualTo(0L);
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