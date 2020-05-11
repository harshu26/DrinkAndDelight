package org.drinkanddelight.rawmaterial.service;

import java.util.Date;
import java.util.List;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;

public interface IRawMaterialService {
	RawMaterialStockEntity addStock(RawMaterialStockEntity stock);
	RawMaterialStockEntity trackRawMaterialOrder(String orderId);
	boolean validateManufacturingDate(Date manuDate);
	boolean validateExpiryDate(Date expiryDate);
	//String updateRawMaterialStock(RawMaterialStock stock);
	List<RawMaterialStockEntity> fetchAllStock();
	String updateRawMaterialStock(RawMaterialStockEntity stock, Date newDate);
	
}
