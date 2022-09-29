package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.models.Category;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.data.repositories.ProductRepository;
import africa.semicolon.lumExpress.exceptions.ProductNotFoundException;
import africa.semicolon.lumExpress.services.cloud.iCloudService;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements iProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final iCloudService cloudService;

    @Override
    public AddProductResponse addProduct(AddProductRequest productRequest) throws IOException {
        var product = modelMapper.map(productRequest, Product.class);
        product.getCategories().add(Category.valueOf(productRequest.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.uploadImage(productRequest.getImageUrl().getBytes(), ObjectUtils.emptyMap());
        log.info("cloudinary image url::{}", imageUrl);
        product.setImageUrl(imageUrl);
        var savedProduct = productRepository.save(product);

        return buildAddProductResponse(savedProduct);
    }

    private AddProductResponse buildAddProductResponse(Product savedProduct) {
        return AddProductResponse.builder()
                .productId(savedProduct.getId())
                .message("product add success")
                .code(201)
                .build();
    }

    @Override
    public String updateProductDetails(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("product %d not found", id)));
    }

    @Override
    public Page<Product> getAllProducts() {
        Pageable pageSpecs = PageRequest.of(0, 5);
        productRepository.findAll();
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }
}
