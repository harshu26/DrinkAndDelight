package org.drinkanddelight.rawmaterial.exceptions;

public class StockNotFoundException extends RuntimeException{
	public StockNotFoundException(String message) {
		super(message);
	}
}
