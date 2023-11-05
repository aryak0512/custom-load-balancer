package socket;

import java.util.HashMap;
import java.util.Map;

import utils.LoggerUtils;

public class ProductService {

	static Map<Long, Product> productMap = new HashMap<>();

	public ProductService() {
		productMap.put(1l, new Product(1L, "iphone 15", 68000.00));
		productMap.put(2l, new Product(2L, "iphone 14", 63000.00));
		productMap.put(3l, new Product(3L, "iphone 13", 58000.00));
		LoggerUtils.log("Product map initialised.");

	}

	public static Object getProduct(Long id) {

		Product p = productMap.get(id);
		LoggerUtils.log(p == null ? "null" : p.toString());
		return p == null ? "Invalid product" : p;
	}

}
