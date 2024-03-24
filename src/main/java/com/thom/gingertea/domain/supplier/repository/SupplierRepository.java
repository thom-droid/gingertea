package com.thom.gingertea.domain.supplier.repository;

import com.thom.gingertea.domain.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
