package org.drinkanddelight.rawmaterial.service;

import org.drinkanddelight.rawmaterial.entities.Supplier;
import java.util.*;

public interface ISupplierService {
	Supplier addSupplier(Supplier supplier);
	Supplier fetchSupplierById(int id);
	List<Supplier> fetchAllSuppliers();
}
