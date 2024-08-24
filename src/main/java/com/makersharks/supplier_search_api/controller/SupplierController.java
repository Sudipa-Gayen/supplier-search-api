package com.makersharks.supplier_search_api.controller;

import com.makersharks.supplier_search_api.model.Supplier;
import com.makersharks.supplier_search_api.service.SupplierService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<Page<Supplier>> querySuppliers(@RequestBody SupplierSearchRequest request){
        Page<Supplier> suppliers = supplierService.searchSuppliers(
                request.getLocation(),
                request.getNatureOfBusiness(),
                request.getManufacturingProcesses(),
                request.getPage(),
                request.getSize());
        return ResponseEntity.ok(suppliers);
    }
}

// DTO for search request
class SupplierSearchRequest {
    @NotEmpty(message = "Location is required")
    private String location;

    @Pattern(regexp = "small_scale|medium_scale|large_scale", message = "Invalid nature of business")
    private String natureOfBusiness;

    @Pattern(regexp = "moulding|3d_printing|casting|coating", message = "Invalid manufacturing process")
    private String manufacturingProcesses;

    private int page = 0;  // Default to page 0
    private int size = 10; // Default to 10 results per page

    // Getters and Setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public String getManufacturingProcesses() {
        return manufacturingProcesses;
    }

    public void setManufacturingProcesses(String manufacturingProcesses) {
        this.manufacturingProcesses = manufacturingProcesses;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
