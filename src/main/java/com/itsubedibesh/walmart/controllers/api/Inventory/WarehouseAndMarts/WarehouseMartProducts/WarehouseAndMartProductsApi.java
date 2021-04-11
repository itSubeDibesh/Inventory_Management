package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.ProductsRepo;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WarehouseAndMartRepo;
import com.itsubedibesh.walmart.controllers.configuration.RestResponse.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseAndMartProductsApi {
    @Autowired
    WarehouseAndMartProductsRepo wampRepo;

    @Autowired
    WarehouseAndMartRepo wamRepo;

    @Autowired
    ProductsRepo pRepo;

    SuccessResponse response = new SuccessResponse();


    @GetMapping("/WarehouseAndMartProducts")
    public ResponseEntity<List<WarehouseAndMartProducts>> getAllWarehouseAndMartProducts() {
        try {
            List<WarehouseAndMartProducts> products = new ArrayList<WarehouseAndMartProducts>();
            wampRepo.findAll().forEach(products::add);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("WarehouseAndMartProducts/transfer")
    public ResponseEntity<SuccessResponse> transferProducts(@RequestBody TransferDto transferDto) {
        try {
            // Get Receiving Mart
            WarehouseAndMartProducts receiverProducts = wampRepo.findWarehouseAndMartProductsByProductIdAndWMId(transferDto.getSendingProduct().getId(), transferDto.getReceivingMart().getId());
            // Get Sending Mart
            WarehouseAndMartProducts senderProducts = wampRepo.findWarehouseAndMartProductsByProductIdAndWMId(transferDto.getSendingProduct().getId(), transferDto.getSendingMart().getId());
            // Checking if Receiving Mart has Product
            if (receiverProducts != null) {
                // If Product Exists in Receiving Mart Subtract from sending and add on receiving
                receiverProducts.setAvailableQuantity(receiverProducts.getAvailableQuantity() + transferDto.getQuantity());
                wampRepo.save(receiverProducts);
            } else {
                // Else Product not Exists Add Product and Subtract from sending mart
                // Add New Product
                wampRepo.save(new WarehouseAndMartProducts(transferDto.getReceivingMart(), senderProducts.getProductId(), transferDto.getQuantity(), senderProducts.getSellingPrice()));
            }
            // Subtract Existing Product
            senderProducts.setAvailableQuantity(senderProducts.getAvailableQuantity() - transferDto.getQuantity());
            wampRepo.save(senderProducts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/paginate")
    public List<WarehouseAndMartProducts> getPaginatedProducts(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<WarehouseAndMartProducts> pagedResult = wampRepo.findAll(page);
        return pagedResult.getContent();
    }
}
