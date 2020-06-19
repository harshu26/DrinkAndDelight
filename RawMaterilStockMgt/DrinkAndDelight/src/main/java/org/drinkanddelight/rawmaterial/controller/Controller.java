package org.drinkanddelight.rawmaterial.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.drinkanddelight.rawmaterial.dto.RawMaterialStockDto;
import org.drinkanddelight.rawmaterial.dto.SupplierDto;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;
import org.drinkanddelight.rawmaterial.entities.Supplier;

import org.drinkanddelight.rawmaterial.service.IRawMaterialService;
import org.drinkanddelight.rawmaterial.service.ISupplierService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
@RequestMapping("/stocks")
@Validated
public class Controller {
	private static final Logger Log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private IRawMaterialService rawMaterialService;

	@Autowired
	private ISupplierService supplierService;

	@PostMapping("/add") // will add RawMaterialStock imp details.
	public ResponseEntity<RawMaterialStockEntity> addStock(@RequestBody @Valid RawMaterialStockDto dto) {
		RawMaterialStockEntity stock1 = convert(dto);
		stock1 = rawMaterialService.addStock(stock1);
		return new ResponseEntity<>(stock1, HttpStatus.OK);
	}

	public RawMaterialStockEntity convert(RawMaterialStockDto dto) {
		RawMaterialStockEntity stock = new RawMaterialStockEntity();
		stock.setOrderId(dto.getOrderId());
		stock.setName(dto.getName());
		stock.setPrice_per_unit(dto.getPrice_per_unit());
		stock.setPrice(dto.getPrice());
		stock.setQuantityUnit(dto.getQuantityUnit());
		stock.setWarehouseId(dto.getWarehouseId());
		stock.setDeliveryDate(dto.getDeliveryDate());
		stock.setQuantityValue(dto.getQuantityValue());

		Date manufacturingDate = dto.getManuDate();
		if (rawMaterialService.validateManufacturingDate(manufacturingDate))
			stock.setManuDate(dto.getManuDate());
		else
			Log.error("Manufacturing Date not in range");
		Date expirydate = dto.getExpiryDate();
		if (rawMaterialService.validateExpiryDate(expirydate))
			stock.setExpiryDate(expirydate);
		else
			Log.error("Expiry Date not in range");

		stock.setProcessDate(dto.getProcessDate());
		stock.setQualityCheck(dto.getQualityCheck());
		return stock;
	}

	@PostMapping("/addSupplier") // will add Supplier details.
	public ResponseEntity<Supplier> addSupplier(@RequestBody @Valid SupplierDto dto) {
		Supplier supplier = convertSupplier(dto);
		supplier = supplierService.addSupplier(supplier);
		return new ResponseEntity<>(supplier, HttpStatus.OK);
	}

	public static Supplier convertSupplier(SupplierDto dto) {
		Supplier supplier = new Supplier();
		supplier.setSupplierName(dto.getSupplierName());
		supplier.setSupplierAddress(dto.getSupplierAddress());
		supplier.setSupplierPhoneNo(dto.getSupplierPhoneNo());
		return supplier;
	}

	@GetMapping("/get/{id}") // will fetch RawMaterialStovck details through id.
	public ResponseEntity<RawMaterialStockEntity> fetchStock(@PathVariable("id") String id) {
		RawMaterialStockEntity stock = rawMaterialService.trackRawMaterialOrder(id);
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}

	// will fetch supplier details on the basis of id.
	@GetMapping("/getS/{supplierId}")
	public ResponseEntity<Supplier> fetchSupplier(@PathVariable("supplierId") @Min(1) int supplierId) {
		Supplier supplier = supplierService.fetchSupplierById(supplierId);
		return new ResponseEntity<>(supplier, HttpStatus.OK);
	}

	// will fetch details of all RawMaterialStocks.
	@GetMapping("/getStocks")
	public ResponseEntity<List<RawMaterialStockEntity>> fetchAllRawMaterialStock() {
		List<RawMaterialStockEntity> stocks = rawMaterialService.fetchAllStock();
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}

	// will fetch details of all Suppliers.
	@GetMapping("/getSuppliers")
	public ResponseEntity<List<Supplier>> fetchAllSuppliers() {
		List<Supplier> suppliers = supplierService.fetchAllSuppliers();
		return new ResponseEntity<>(suppliers, HttpStatus.OK);

	}

	// will update RawMaterialStock process date.
	@PutMapping("/update/{id}/{date}")
	public ResponseEntity<String> updateStock(@RequestBody Map<String, String> map) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String id = map.get("orderId");
		String date = map.get("processDate");
		Date requiredate = format.parse(date);
		String message = rawMaterialService.updateRawMaterialStock(id, requiredate);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}
