package org.drinkanddelight.rawmaterial.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.drinkanddelight.rawmaterial.dao.RawMaterialDao;
import org.drinkanddelight.rawmaterial.entities.RawMaterialStockEntity;
import org.drinkanddelight.rawmaterial.exceptions.StockNotFoundException;
import org.drinkanddelight.rawmaterial.service.IRawMaterialService;
import org.drinkanddelight.rawmaterial.service.RawMaterialServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(RawMaterialServiceImpl.class)
public class RawMaterialServiceImplTest {

	@Autowired
	private IRawMaterialService service;

	@Autowired
	private EntityManager entityManager;

	// when RawMaterialStock is not present in the database.
	// only few fields of the entity class are used for checking test case.
	@Test
	public void test_addStock() {
		String orderId = "A101";
		String name = "Order1";
		double price_per_unit = 10;
		double quantityUnit = 22;
		String warehouseId = "W1";
		RawMaterialStockEntity stock = new RawMaterialStockEntity();
		stock.setOrderId(orderId);
		stock.setName(name);
		stock.setPrice_per_unit(price_per_unit);
		stock.setQuantityUnit(quantityUnit);
		stock.setWarehouseId(warehouseId);
		RawMaterialStockEntity stock2 = service.addStock(stock);
		// List<RawMaterialStockEntity>list = dao.findAll();
		List<RawMaterialStockEntity> list = entityManager.createQuery("from	RawMaterialStockEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		RawMaterialStockEntity expected = list.get(0);
		Assertions.assertEquals(expected, stock2);
		Assertions.assertEquals(orderId, expected.getOrderId());
		System.out.print("Stock Successfully added!");
	}

	// when RawMaterialStock is present in the database.
	@Test
	public void test_addStock2() {
		String orderId = "A101";
		String name = "Order1";
		double price_per_unit = 10;
		double quantityUnit = 22;
		String warehouseId = "W1";
		Date processDate = new Date(2020 - 05 - 11);
		RawMaterialStockEntity stock = new RawMaterialStockEntity();
		stock.setOrderId(orderId);
		stock.setName(name);
		stock.setPrice_per_unit(price_per_unit);
		stock.setQuantityUnit(quantityUnit);
		stock.setWarehouseId(warehouseId);
		stock.setProcessDate(processDate);
		// stock = dao.save(stock);
		stock = entityManager.merge(stock);
		// if stock already exist, modification will be done on process date.
		Date newProcessDate = new Date(2020 - 05 - 12);
		stock.setProcessDate(newProcessDate);
		RawMaterialStockEntity newStock = service.addStock(stock);
		List<RawMaterialStockEntity> list = entityManager.createQuery("from RawMaterialStockEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		RawMaterialStockEntity stock2 = list.get(0);
		Assertions.assertEquals(stock2, newStock);
		Assertions.assertEquals(newProcessDate, stock2.getProcessDate());
	}

	// when Stock doesn't exist or invalid argument is provided.
	@Test
	public void test_trackRawMaterialOrder() {
		Executable executable = () -> service.trackRawMaterialOrder("aaav");
		Assertions.assertThrows(StockNotFoundException.class, executable);
	}

	// when Stock is present in the database and is successfully fetched.
	@Test
	public void test_trackRawMaterialOrder2() {
		String orderId = "A101";
		String name = "Order1";
		double price_per_unit = 10;
		double quantityUnit = 22;
		String warehouseId = "W1";
		RawMaterialStockEntity stock = new RawMaterialStockEntity();
		stock.setOrderId(orderId);
		stock.setName(name);
		stock.setPrice_per_unit(price_per_unit);
		stock.setQuantityUnit(quantityUnit);
		stock.setWarehouseId(warehouseId);
		stock = entityManager.merge(stock);

		String orderId2 = stock.getOrderId();
		RawMaterialStockEntity resultStock = service.trackRawMaterialOrder(orderId2);
		Assertions.assertEquals(stock, resultStock);
		Assertions.assertEquals(name, stock.getName());
	}

}
