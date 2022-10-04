package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllElementRequest;
import africa.semicolon.lumExpress.data.dtos.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.dtos.response.UpdateProductResponse;
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
                .message("product add success")
                .productId(savedProduct.getId())
                .productName(savedProduct.getName())
                .statusCode(201)
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public UpdateProductResponse updateProductDetails(UpdateProductRequest updateProductRequest) {
        var foundProduct = productRepository.findById(updateProductRequest.getId()).orElseThrow(
                ()-> new ProductNotFoundException(String.format("product with id %d not found", updateProductRequest.getId())));
        foundProduct.setName(updateProductRequest.getProductName());
        foundProduct.setPrice(updateProductRequest.getPrice());
        foundProduct.setQuantity(updateProductRequest.getQuantity());
        foundProduct.setDescription(updateProductRequest.getDescription());
        var updatedProductDetails = productRepository.save(foundProduct);

        return UpdateProductResponse
                .builder()
                .productName(updatedProductDetails.getName())
                .message("update success")
                .description(updatedProductDetails.getDescription())
                .statusCode(201)
                .build();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("product %d not found", id)));
    }

    @Override
    public Page<Product> getAllProducts(GetAllElementRequest getAllElementRequest) {
        Pageable pageSpecs = PageRequest.of(
                getAllElementRequest.getPageNumber()-1, getAllElementRequest.getNumberOfProductPerPage());
        return productRepository.findAll(pageSpecs);
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "product deleted successfully";
    }

    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public void deleteALl() {
        productRepository.deleteAll();
    }
}
