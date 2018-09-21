package com.contoso.inventory;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    @Query("MATCH (product:Product),\n" +
            "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory)\n" +
            "WHERE product.productId = {productId} AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
            "RETURN inventory")
    List<Inventory> getAvailableInventoryForProduct(@Param("productId") String productId);

    @Query("MATCH (product:Product),\n" +
            "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory),\n" +
            "    (inventory)-[:STOCKED_IN]->(:Warehouse { name: \"{warehouseName}\" })\n" +
            "WHERE product.productId = {productId} AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
            "RETURN inventory")
    List<Inventory> getAvailableInventoryForProductAndWarehouse(@Param("productId") String productId,
                                                                @Param("warehouseName") String warehouseName);

    @Query("MATCH (product:Product),\n" +
            "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory)\n" +
            "WHERE product.productId in {productIds} AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
            "RETURN inventory")
    List<Inventory> getAvailableInventoryForProductList(@Param("productIds") String[] productIds);
}
