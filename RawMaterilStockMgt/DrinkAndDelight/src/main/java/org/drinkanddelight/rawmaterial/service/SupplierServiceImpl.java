package org.drinkanddelight.rawmaterial.service;

import java.util.List;
import java.util.Optional;

import org.drinkanddelight.rawmaterial.dao.SupplierDao;
import org.drinkanddelight.rawmaterial.entities.Supplier;
import org.drinkanddelight.rawmaterial.exceptions.InvalidArgumentException;
import org.drinkanddelight.rawmaterial.exceptions.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private SupplierDao dao;

	@Override
	public Supplier addSupplier(Supplier supplier) {
		supplier = dao.save(supplier);
		return supplier;
	}

	@Override
	public Supplier fetchSupplierById(int id) {
		if (id == 0) {
			throw new InvalidArgumentException("Invlaid Supplier Id");
		}
		Optional<Supplier> optional = dao.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new SupplierNotFoundException("Supplier not found");
	}

	@Override
	public List<Supplier> fetchAllSuppliers() {
		return dao.findAll();

	}

}
