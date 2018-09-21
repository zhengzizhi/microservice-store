package com.contoso.warehouse;

import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouse,Long> {
}
