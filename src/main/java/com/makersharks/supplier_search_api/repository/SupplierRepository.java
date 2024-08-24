package com.makersharks.supplier_search_api.repository;

import com.makersharks.supplier_search_api.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcesses(
            String location,
            String natureOfBusiness,
            String manufacturingProcesses,
            Pageable pageable);
}
