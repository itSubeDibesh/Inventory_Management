package com.itsubedibesh.walmart.controllers.api.Mart.Sales;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.UsersRepo;
import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProducts;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProductsRepo;
import com.itsubedibesh.walmart.controllers.api.Members.Customers.Customers;
import com.itsubedibesh.walmart.controllers.api.Members.Customers.CustomersRepo;
import com.itsubedibesh.walmart.controllers.configuration.RestResponse.SuccessResponse;
import com.itsubedibesh.walmart.controllers.web.Mart.Sales.SalesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SalesApi {

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    WarehouseAndMartProductsRepo productsRepo;

    @Autowired
    CustomersRepo customersRepo;

    @Autowired
    UsersRepo usersRepo;

    SuccessResponse response = new SuccessResponse();

    @GetMapping("/sales/{invoiceNumber}")
    public ResponseEntity<List<Sales>> getSalesByInvoice(@PathVariable() String invoiceNumber) {
        try {
            List<Sales> sales = new ArrayList<Sales>();
            salesRepo.findAllByInvoiceNumber(invoiceNumber).forEach(sales::add);
            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/OrderList")
    public ResponseEntity<List<Sales>> getSalesGroupByInvoice() {
        try {
            List<Sales> sales = new ArrayList<Sales>();
            salesRepo.findAllGroupByInvoiceNumber().forEach(sales::add);
            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sales/bills")
    public ResponseEntity<SuccessResponse> AddBill(@RequestBody List<SalesDto> sales) {
        try {
            int successCount = 0;
            for (SalesDto salesDto : sales) {
                System.out.println(salesDto.getInvoiceNumber());
                // Find out User Details
                Optional<Users> user = usersRepo.findById(salesDto.getUserId());
                // Find out Customer Details
                Optional<Customers> customer = customersRepo.findById(salesDto.getCustomerId());
                // Find out Products Details
                Optional<WarehouseAndMartProducts> product = productsRepo.findById(salesDto.getProductId());
                // Subtract Products Details
                if (product.isPresent() && user.isPresent() && customer.isPresent()) {
                    WarehouseAndMartProducts _products = product.get();
                    _products.setAvailableQuantity(_products.getAvailableQuantity() - salesDto.getQuantity());
                    productsRepo.save(_products);
                    // Saving the result
                    salesRepo.save(new Sales(salesDto.getInvoiceNumber(), salesDto.getInvoiceDate(), product.get(), customer.get(), user.get(), salesDto.getQuantity(), salesDto.getTaxAmount(), salesDto.getReturnedAmount(), salesDto.getReceivedAmount()));
                    successCount++;
                } else successCount--;
            }
            if (sales.size() == successCount)
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
