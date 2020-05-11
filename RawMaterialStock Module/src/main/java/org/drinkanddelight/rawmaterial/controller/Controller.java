package org.drinkanddelight.rawmaterial.controller;

import java.util.*;
import org.drinkanddelight.rawmaterial.dto.StockAndSupplierDto;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;
import org.drinkanddelight.rawmaterial.entities.Supplier;
import org.drinkanddelight.rawmaterial.exceptions.StockNotFoundException;
import org.drinkanddelight.rawmaterial.exceptions.SupplierNotFoundException;
import org.drinkanddelight.rawmaterial.service.IRawMaterialService;
import org.drinkanddelight.rawmaterial.service.ISupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class Controller {
	private static final Logger Log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private IRawMaterialService service;

	@Autowired
	private ISupplierService service2;

	@PostMapping("/add") // will add RawMaterialStock imp details.
	public ResponseEntity<RawMaterialStockEntity> addStock(@RequestBody RawMaterialStockEntity stock) {
		// RawMaterialStockEntity stock1 = convert(stock);
		stock = service.addStock(stock);
		ResponseEntity<RawMaterialStockEntity> response = new ResponseEntity<>(stock, HttpStatus.OK);
		return response;
	}

	@PostMapping("/addSupplier") // will add Supplier details.
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
		supplier = service2.addSupplier(supplier);
		ResponseEntity<Supplier> response = new ResponseEntity<>(supplier, HttpStatus.OK);
		return response;
	}

	/*
	 * // SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * public RawMaterialStockEntity convert(RawMaterialStockDto dto) {
	 * RawMaterialStockEntity stock = new RawMaterialStockEntity();
	 * stock.setOrderId(dto.getOrderId()); stock.setName(dto.getName());
	 * stock.setPrice_per_unit(dto.getPrice_per_unit());
	 * stock.setQuantityUnit(dto.getQuantityUnit());
	 * stock.setWarehouseId(dto.getWarehouseId());
	 * //stock.setSupplierId(dto.getSupplierId());
	 * 
	 * stock.setDeliveryDate(dto.getDeliveryDate()); return stock; }
	 * 
	 */
	@GetMapping("/get/{id}") // will fetch RawMaterialStovck details through id.
	public ResponseEntity<RawMaterialStockEntity> fetchStock(@PathVariable("id") String id) {
		RawMaterialStockEntity stock = service.trackRawMaterialOrder(id);
		ResponseEntity<RawMaterialStockEntity> response = new ResponseEntity<RawMaterialStockEntity>(stock,
				HttpStatus.OK);
		return response;
	}

	@GetMapping("/getS/{supplierId}")
	public ResponseEntity<Supplier> fetchSupplier(@PathVariable("supplierId") int supplierId) {
		Supplier supplier = service2.fetchSupplierById(supplierId);
		ResponseEntity<Supplier> response = new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		return response;
	}

	// will fetch details of all RawMaterialStocks.
	@GetMapping("/getStocks")
	public ResponseEntity<List<RawMaterialStockEntity>> fetchAllRawMaterialStock() {
		List<RawMaterialStockEntity> stocks = service.fetchAllStock();
		ResponseEntity<List<RawMaterialStockEntity>> response = new ResponseEntity<>(stocks, HttpStatus.OK);
		return response;
	}

	// will fetch details of all Suppliers.
	@GetMapping("/getSuppliers")
	public ResponseEntity<List<Supplier>> fetchAllSuppliers() {
		List<Supplier> suppliers = service2.fetchAllSuppliers();
		ResponseEntity<List<Supplier>> response = new ResponseEntity<>(suppliers, HttpStatus.OK);
		return response;
	}

	// will fetch details of supplier and RawMaterialStock
	@GetMapping
	public ResponseEntity<List<StockAndSupplierDto>> fetchAll() {
		List<RawMaterialStockEntity> stocks = service.fetchAllStock();
		List<Supplier> suppliers = service2.fetchAllSuppliers();
		List<StockAndSupplierDto> list = convertStockDetails(stocks, suppliers);
		ResponseEntity<List<StockAndSupplierDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}

	/*
	 * //will be used for supplier details. public SupplierDto fetchSupplier(int id)
	 * { SupplierDto supplier = new SupplierDto();
	 * supplier.setSupplierName("Drink"); supplier.setSupplierAddress("Bhopal");
	 * supplier.setSupplierPhoneNo(123456); return supplier; }
	 */

	// for displayig details of supplier and RawMaterialStock.
	StockAndSupplierDto convertStockDetails(RawMaterialStockEntity stock, Supplier supplier) {
		StockAndSupplierDto dto = new StockAndSupplierDto();
		dto.setOrderId(stock.getOrderId());
		dto.setName(stock.getName());
		dto.setDeliveryDate(stock.getDeliveryDate());
		int id = supplier.getSupplierId();
		// SupplierDto supplier = fetchSupplier(id);
		dto.setSupplier(service2.fetchSupplierById(id));
		return dto;
	}

	public List<StockAndSupplierDto> convertStockDetails(List<RawMaterialStockEntity> stocks,
			List<Supplier> suppliers) {
		List<StockAndSupplierDto> list = new ArrayList<>();
		for (RawMaterialStockEntity stock : stocks) {
			for (Supplier supplier : suppliers) {
				StockAndSupplierDto detailsDto = convertStockDetails(stock, supplier);

				list.add(detailsDto);
			}
		}
		return list;
	}

	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<String> handleStockNotFound(StockNotFoundException ex) {
		Log.error("Stock not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(SupplierNotFoundException.class)
	public ResponseEntity<String> handleSupplierNotFound(SupplierNotFoundException ex) {
		Log.error("Supplier not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) {
		Log.error("exception caught", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
