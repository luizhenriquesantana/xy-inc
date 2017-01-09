package br.com.zup.xy_inc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.xy_inc.dao.ProductDAO;
import br.com.zup.xy_inc.model.Product;

@RestController
public class ProductRestController {

	@Autowired
	private ProductDAO productDAO;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productDAO.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		Product product = productDAO.getObj(id);
		if (product == null) {
			return new ResponseEntity("No Product found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(product, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		try {
			productDAO.create(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(product, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id) {

		try {
			if (!productDAO.delete(id)) {
				return new ResponseEntity("No Product found for ID " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {

		try {
			if (!productDAO.update(product)) {
				return new ResponseEntity("No Product found for ID " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(product, HttpStatus.OK);
	}


}
