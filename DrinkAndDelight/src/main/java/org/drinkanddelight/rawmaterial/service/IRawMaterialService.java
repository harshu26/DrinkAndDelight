package org.drinkanddelight.rawmaterial.service;

import java.util.Date;
import java.util.List;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStock;

public interface IRawMaterialService {
	RawMaterialStock addStock(RawMaterialStock stock);
	String trackRawMaterialOrder(String orderId);
	boolean validateManufacturingDate(Date manuDate);
	boolean validateExpiryDate(Date expiryDate);
	//String updateRawMaterialStock(RawMaterialStock stock);
	List<RawMaterialStock> fetchAllStock();
	String updateRawMaterialStock(RawMaterialStock stock, Date newDate);
	
}
