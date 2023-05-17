package com.fuad.crud.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody  ProductRequest product, BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        productService.save(product);
        return new ResponseEntity<>("Product save successfully", HttpStatus.CREATED);
    }
    @Operation(summary = "All book")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Fetch All books from Db",
                    content = {@Content(mediaType = "application/json")}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Available",
                    content = @Content
            )
    })
    @GetMapping("/all")
    public ResponseEntity<?> productList(){
        return new ResponseEntity<>(productService.allProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProduct(id), HttpStatus.FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductEntity productReq){
        productService.updateProduct(id, productReq);
        return new ResponseEntity<>("Update success", HttpStatus.ACCEPTED);
    }
}
