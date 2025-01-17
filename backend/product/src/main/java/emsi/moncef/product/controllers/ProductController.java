package emsi.moncef.product.controllers;

import emsi.moncef.product.dtos.ProductDTO;
import emsi.moncef.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{skuCode}")
    public ResponseEntity<ProductDTO> getProductBySkuCode(@PathVariable String skuCode) {
        ProductDTO product = productService.getProductBySkuCode(skuCode);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }


    @PutMapping("/{skuCode}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String skuCode, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(skuCode, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }


    @PatchMapping("/{skuCode}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable String skuCode, @RequestBody ProductDTO partialProductDTO) {
        ProductDTO patchedProduct = productService.patchProduct(skuCode, partialProductDTO);
        return ResponseEntity.ok(patchedProduct);
    }

    @DeleteMapping("/{skuCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String skuCode) {
        productService.deleteProduct(skuCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skucodes")
    public ResponseEntity<List<String>> getAllSkuCodes() {
        List<String> skuCodes = productService.getAllSkuCodes();
        return ResponseEntity.ok(skuCodes);
    }
}
