package org.drinkanddelight.rawmaterial.controller;

import java.util.ArrayList;
import java.util.List;
import org.drinkanddelight.rawmaterial.dto.RawMaterialStockDto;
import org.drinkanddelight.rawmaterial.dto.StockAndSupplierDto;
import org.drinkanddelight.rawmaterial.dto.SupplierDto;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStock;
import org.drinkanddelight.rawmaterial.service.IRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class Controller {
	
	@Autowired
	private IRawMaterialService service;
	
	 @PostMapping("/add")	//will add RawMaterialStock imp details.
	 public ResponseEntity<RawMaterialStock> addStock(@RequestBody RawMaterialStockDto dto){
		 RawMaterialStock stock = convert(dto);
		 stock = service.addStock(stock);
		 ResponseEntity<RawMaterialStock> response = new ResponseEntity<>(stock, HttpStatus.OK);
	      return response; 
	 }

	public RawMaterialStock convert(RawMaterialStockDto dto) {
		RawMaterialStock stock = new RawMaterialStock();
		stock.setOrderId(dto.getOrderId());
		stock.setName(dto.getName());
		stock.setPrice_per_unit(dto.getPrice_per_unit());
		stock.setQuantityUnit(dto.getQuantityUnit());
		stock.setWarehouseId(dto.getWarehouseId());
		stock.setSupplierId(dto.getSupplierId());
		return stock;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<String> fetchStock(@PathVariable("id") String id){
		String stockName = service.trackRawMaterialOrder(id);
		ResponseEntity<String>response = new ResponseEntity<String>(stockName, HttpStatus.OK);
		return response;
	}
	
	@GetMapping
    public ResponseEntity<List<StockAndSupplierDto>> fetchAll() {
        List<RawMaterialStock> stocks = service.fetchAllStock();
        List<StockAndSupplierDto> list = convertStockDetails(stocks);
        ResponseEntity<List<StockAndSupplierDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
        return response;
    }
	
	//will be used for supplier details.
	public SupplierDto fetchSupplier(int id) {	
		SupplierDto supplier = new SupplierDto();
		supplier.setSupplierName("Drink");
		supplier.setSupplierAddress("Bhopal");
		supplier.setSupplierPhoneNo(123456);
		return supplier;
	}
	
	//for displayig details of supplier and RawMaterialStock.
	StockAndSupplierDto convertStockDetails(RawMaterialStock stock) {
		StockAndSupplierDto dto = new StockAndSupplierDto();
		dto.setOrderId(stock.getOrderId());
		dto.setName(stock.getName());
		int id = stock.getSupplierId();
		SupplierDto supplier = fetchSupplier(id);
		dto.setSupplier(supplier);
		return dto;
	}
	
	 public List<StockAndSupplierDto> convertStockDetails(List<RawMaterialStock> stocks) {
	        List<StockAndSupplierDto> list = new ArrayList<>();
	        for (RawMaterialStock stock : stocks) {
	            StockAndSupplierDto detailsDto = convertStockDetails(stock);
	            list.add(detailsDto);
	        }
	        return list;
	    }
	
	
	
}
