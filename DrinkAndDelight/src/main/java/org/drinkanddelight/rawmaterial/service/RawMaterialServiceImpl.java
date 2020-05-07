package org.drinkanddelight.rawmaterial.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.drinkanddelight.rawmaterial.dao.RawMaterialDao;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RawMaterialServiceImpl implements IRawMaterialService {
	
	@Autowired
	private RawMaterialDao dao;

	@Override
	public RawMaterialStock addStock(RawMaterialStock stock) {
		stock = dao.save(stock);
		return stock;
	}

	@Override
	public String trackRawMaterialOrder(String orderId) {
		Optional<RawMaterialStock>optional = dao.findById(orderId);
		RawMaterialStock stock = null;
		if(optional.isPresent()) {
			stock = optional.get();			
		}
		return stock.getName();
	}

	@Override
	public boolean validateManufacturingDate(Date manuDate) {
		Calendar currentDateAfter3Months = Calendar.getInstance();
		currentDateAfter3Months.add(Calendar.MONTH, 3);	//date after 3 months from current date;
		boolean flag = false;
		if(manuDate.equals(Calendar.getInstance().getTime()) && manuDate.before(currentDateAfter3Months.getTime())) {
			flag = true;
		}
		else
			flag = false;
		
		return flag;
	}

	@Override
	public boolean validateExpiryDate(Date expiryDate) {
		Calendar currentDateAfter3Months = Calendar.getInstance();
		currentDateAfter3Months.add(Calendar.MONTH, 3);	//date after 3 months from current date;
		boolean flag = false;
		if(expiryDate.equals(Calendar.getInstance().getTime()) && expiryDate.before(currentDateAfter3Months.getTime())) {
			flag = true;
		}
		else
			flag = false;
		
		return flag;
	}

	@Override
	public String updateRawMaterialStock(RawMaterialStock stock, Date date) {
		String msg = " ";
		//RawMaterialStock updatedStock = new RawMaterialStock();
		
		if(dao.existsById(stock.getOrderId()))
		{	
		//Date date = stock.getProcessDate();
		Calendar currentDateAfter3Months = Calendar.getInstance();
		currentDateAfter3Months.add(Calendar.MONTH, 3);	//date after 3 months from current date;
		boolean flag = false;
		if(date.equals(Calendar.getInstance().getTime()) && date.before(currentDateAfter3Months.getTime())) {
			flag = true;
		}
		else {
			flag = false;
		}
		if(flag == true) {
		//updatedStock.setProcessDate(date);
		stock.setProcessDate(date);
		addStock(stock);
		msg = "Data Inserted";
		}
		else
			msg = "Error in data insertion";
		
		}	return msg;
	
	}

	@Override
	public List<RawMaterialStock> fetchAllStock() {
		List<RawMaterialStock>stockList = dao.findAll();
		return stockList;
	}

}
