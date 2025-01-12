package emsi.moncef.product.services;

import emsi.moncef.product.dtos.ProductDTO;
import emsi.moncef.product.mappers.ProductMapper;
import emsi.moncef.product.models.Product;
import emsi.moncef.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    
    public ProductDTO getProductBySkuCode(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode);
        return productMapper.toDto(product);
    }

    
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        productMapper.toEntity(productDTO, product);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    
    public ProductDTO updateProduct(String skuCode, ProductDTO updatedProductDTO) {
        Product existingProduct = productRepository.findBySkuCode(skuCode);
        productMapper.toEntity(updatedProductDTO, existingProduct);
        Product savedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(savedProduct);
    }

    
    public ProductDTO patchProduct(String skuCode, ProductDTO partialUpdateDTO) {
        Product existingProduct = productRepository.findBySkuCode(skuCode);

        if (partialUpdateDTO.name() != null) existingProduct.setName(partialUpdateDTO.name());
        if (partialUpdateDTO.description() != null) existingProduct.setDescription(partialUpdateDTO.description());
        if (partialUpdateDTO.price() != null) existingProduct.setPrice(partialUpdateDTO.price());
        if (partialUpdateDTO.stockQuantity() != 0) existingProduct.setStockQuantity(partialUpdateDTO.stockQuantity());
        if (partialUpdateDTO.category() != null) existingProduct.setCategory(partialUpdateDTO.category());
        if (partialUpdateDTO.manufacturer() != null) existingProduct.setManufacturer(partialUpdateDTO.manufacturer());
        existingProduct.setAvailable(partialUpdateDTO.isAvailable());

        Product savedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(savedProduct);
    }

    
    public void deleteProduct(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode);
        productRepository.delete(product);
    }


    public List<String> getAllSkuCodes() {
        return productRepository.findAll()
                .stream()
                .map(Product::getSkuCode)
                .collect(Collectors.toList());
    }
}
