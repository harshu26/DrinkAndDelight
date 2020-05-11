package org.drinkanddelight.rawmaterial.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.drinkanddelight.rawmaterial.dao.RawMaterialDao;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;
import org.drinkanddelight.rawmaterial.exceptions.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RawMaterialServiceImpl implements IRawMaterialService {
	
	@Autowired
	private RawMaterialDao dao;

	//This method will be used to add RawMaterailStock.
	@Override
	public RawMaterialStockEntity addStock(RawMaterialStockEntity stock) {
		stock = dao.save(stock);
		return stock;
	}

	//This method will track RawMaterialStock details on the basis of orderId.
	@Override
	public RawMaterialStockEntity trackRawMaterialOrder(String orderId) {
		if(orderId.isEmpty() || orderId.equals(null)) {
			throw new StockNotFoundException("Invalid Id");
		}
		Optional<RawMaterialStockEntity>optional = dao.findById(orderId);
		RawMaterialStockEntity stock = null;
		if(optional.isPresent()) {
			stock = optional.get();			
		}
		return stock;
	}

	//This method will validate the manufacturing date according to required conditions.
	//Date should not be less then current date and not greater than date after 3 months of current date.
	@Override
	public boolean validateManufacturingDate(Date manuDate) {
		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = LocalDate.of(manuDate.getYear(),manuDate.getMonth(), manuDate.getDay());
		LocalDate endDate = currentDate.plusMonths(3);
		return(startDate.isAfter(currentDate) && startDate.isBefore(endDate));
	}


	//This method will validate the expiry date according to required conditions.
	//Date should not be less then current date and not greater than date after 3 months of current date.
	@Override
	public boolean validateExpiryDate(Date expiryDate) {
		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = LocalDate.of(expiryDate.getYear(),expiryDate.getMonth(), expiryDate.getDay());
		LocalDate endDate = currentDate.plusMonths(3);
		return(startDate.isAfter(currentDate) && startDate.isBefore(endDate));
	}

	//This method will update RawMaterialStock on basis of its processDate.
	@Override
	public String updateRawMaterialStock(RawMaterialStockEntity stock, Date date) {
		String msg = " ";
		//RawMaterialStock updatedStock = new RawMaterialStock();
		
		if(dao.existsById(stock.getOrderId()))
		{	
			LocalDate currentDate = LocalDate.now();
			LocalDate startDate = LocalDate.of(date.getYear(),date.getMonth(), date.getDay());
			LocalDate endDate = currentDate.plusMonths(3);
			if(startDate.isAfter(currentDate) && startDate.isBefore(endDate)) {
				//updatedStock.setProcessDate(date);
				stock.setProcessDate(date);
				addStock(stock);
				msg = "Data Inserted";
			}
			else
				msg = "Error in data insertion";
			
			}	return msg;	
	
	}

	//This method will be used to fetch all RawMaterialStock present in a list.
	@Override
	public List<RawMaterialStockEntity> fetchAllStock() {
		List<RawMaterialStockEntity>stockList = dao.findAll();
		return stockList;
	}

}
