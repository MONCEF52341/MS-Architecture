package emsi.moncef.product.services;

import emsi.moncef.product.dtos.ProductDTO;
import emsi.moncef.product.mappers.ProductMapper;
import emsi.moncef.product.models.Product;
import emsi.moncef.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<Product> orders = productRepository.findAll();
        return productMapper.toDtoList(orders);
    }
    public Optional<Product> fetchProductByskuCode(String skuCode) {
        return Optional.ofNullable(productRepository.findBySkuCode(skuCode));
    }
    public Product addProduct(Product product) {

        productRepository.save(product);
        return product;

    }

    public Optional<Product> updateProduct(String skuCode, Product updatedProduct) {
        Optional<Product> existingProduct = fetchProductByskuCode(skuCode);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setSkuCode(updatedProduct.getSkuCode());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setCategory(updatedProduct.getCategory());
            product.setManufacturer(updatedProduct.getManufacturer());
            product.setAvailable(updatedProduct.isAvailable());

            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    public Optional<Product> patchProduct(String skuCode, Product partialUpdate) {
        Optional<Product> existingProduct = fetchProductByskuCode(skuCode);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if (partialUpdate.getSkuCode() != null) product.setSkuCode(partialUpdate.getSkuCode());
            if (partialUpdate.getName() != null) product.setName(partialUpdate.getName());
            if (partialUpdate.getDescription() != null) product.setDescription(partialUpdate.getDescription());
            if (partialUpdate.getPrice() != null) product.setPrice(partialUpdate.getPrice());
            if (partialUpdate.getStockQuantity() != 0) product.setStockQuantity(partialUpdate.getStockQuantity());
            if (partialUpdate.getCategory() != null) product.setCategory(partialUpdate.getCategory());
            if (partialUpdate.getManufacturer() != null) product.setManufacturer(partialUpdate.getManufacturer());
            product.setAvailable(partialUpdate.isAvailable());
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    public boolean deleteProduct(String skuCode) {
        Optional<Product> existingProduct = fetchProductByskuCode(skuCode);
        if (existingProduct.isPresent()) {
            productRepository.delete(existingProduct.get());
            return true;
        }
        return false;
    }
}
