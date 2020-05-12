package org.drinkanddelight.rawmaterial.dao;

import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialDao extends JpaRepository<RawMaterialStockEntity,String> {

	RawMaterialStockEntity findByOrderId(String orderId);
}
