package com.product.controllers;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.product.helper.ResponseWrapper;
import com.product.models.Manufacture;
import com.product.services.ManufactureService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manufactures")
public class ManufactureController {

	@Autowired
	ManufactureService manufactureService;

	@GetMapping("")
	public ResponseEntity<?> getAllManufactures() {
		Iterable<Manufacture> manufacture = manufactureService.getAllmanufactures();
		Iterator<Manufacture> all_manufacture = manufacture.iterator();
		if (all_manufacture.hasNext()) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setMessage("Following Manufacture found");
			rw.setData(all_manufacture);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no manufacture, please add manufactures ");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getManufactureByID(@PathVariable int id) {
		Manufacture found_manufacture = manufactureService.getManufactureByID(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Manufacture found by Id");
		rw.setData(found_manufacture);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createManufacture(@RequestBody @Valid Manufacture manufacture) {
		Manufacture created_manufacture = manufactureService.createManufacture(manufacture);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("New Manufacture Added successfully.");
		rw.setData(created_manufacture);
		return new ResponseEntity<>(rw, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateManufactureByID(@PathVariable int id, @RequestBody Manufacture manufacture) {
		Manufacture updated_manufacture = manufactureService.updateManufacture(id, manufacture);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Manufacture updated by Id successfully.");
		rw.setData(updated_manufacture);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delteManufactureByID(@PathVariable int id) {
		manufactureService.deleteManufacture(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Manufacture deleted by Id successfully.");
		rw.setData(null);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}
}
