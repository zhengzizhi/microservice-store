package com.contoso.catalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CatalogRepository extends CrudRepository<Catalog,Long> {
    Catalog findCatalogByCatalogNumber(@Param("catalogNumber") Long catalogNumber);
}
