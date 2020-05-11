package org.drinkanddelight.rawmaterial.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.drinkanddelight.rawmaterial.dao.SupplierDao;
import org.drinkanddelight.rawmaterial.entities.Supplier;
import org.drinkanddelight.rawmaterial.exceptions.SupplierNotFoundException;
import org.drinkanddelight.rawmaterial.service.ISupplierService;
import org.drinkanddelight.rawmaterial.service.SupplierServiceImpl;
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
@Import(SupplierServiceImpl.class)
public class SupplierServiceImplTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ISupplierService service;

	// when supplier is not present in the database.
	@Test
	public void test_addSupplier() {
		int id = 10;
		String name = "Harsh";
		String address = "bhopal";
		int phoneNo = 13456;
		Supplier supplier = new Supplier();
		supplier.setSupplierId(id);
		supplier.setSupplierName(name);
		supplier.setSupplierAddress(address);
		supplier.setSupplierPhoneNo(phoneNo);
		Supplier supplier2 = service.addSupplier(supplier);
		List<Supplier> list = entityManager.createQuery("from Supplier").getResultList();
		Assertions.assertEquals(1, list.size());
		Supplier expected = list.get(0);
		Assertions.assertEquals(expected, supplier2);
		Assertions.assertEquals(name, expected.getSupplierName());
		System.out.println("Supplier Added Successfully!");
	}

	// when supplier is not present.
	@Test
	public void test_addSupplier2() {
		int id = 10;
		String name = "Harsh";
		String address = "bhopal";
		int phoneNo = 13456;
		Supplier supplier = new Supplier();
		supplier.setSupplierId(id);
		supplier.setSupplierName(name);
		supplier.setSupplierAddress(address);
		supplier.setSupplierPhoneNo(phoneNo);
		supplier = entityManager.merge(supplier);

		int id2 = 20;
		String name2 = "Ravi";
		String address2 = "indore";
		int phoneNo2 = 4455;
		supplier.setSupplierId(id2);
		supplier.setSupplierName(name2);
		supplier.setSupplierAddress(address2);
		supplier.setSupplierPhoneNo(phoneNo2);
		Supplier newSupplier = service.addSupplier(supplier);
		List<Supplier> list = entityManager.createQuery("from Supplier").getResultList();
		Assertions.assertEquals(1, list.size());
		Supplier supplier2 = list.get(0);
		Assertions.assertEquals(supplier2, newSupplier);
		Assertions.assertEquals(name2, supplier2.getSupplierName());
	}

	// when id is wrong or supplier doesn't exist.
	@Test
	public void test_fetchSupplierById() {
		Executable executable = () -> service.fetchSupplierById(111);
		Assertions.assertThrows(SupplierNotFoundException.class, executable);
	}

	// when id is correct and supplier is fetched.
	@Test
	public void test_fetchSupplierById2() {
		int id = 10;
		String name = "Harsh";
		String address = "bhopal";
		int phoneNo = 13456;
		Supplier supplier = new Supplier();
		supplier.setSupplierId(id);
		supplier.setSupplierName(name);
		supplier.setSupplierAddress(address);
		supplier.setSupplierPhoneNo(phoneNo);
		supplier = entityManager.merge(supplier);

		int id2 = supplier.getSupplierId();
		Supplier supplier2 = service.fetchSupplierById(id2);
		Assertions.assertEquals(supplier, supplier2);
		Assertions.assertEquals(name, supplier.getSupplierName());
	}
}
