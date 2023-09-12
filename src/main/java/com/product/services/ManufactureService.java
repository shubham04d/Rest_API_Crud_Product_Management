package com.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.product.models.Manufacture;
import com.product.repositories.ManufactureRepository;

@Service
public class ManufactureService {

	@Autowired
	ManufactureRepository manufactureRepository;

	public Iterable<Manufacture> getAllmanufactures() {
		return manufactureRepository.findAll();
	}

	public Manufacture getManufactureByID(int id) {
		Manufacture found_manufacture = manufactureRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no manufacture , with this id ");
		});
		return found_manufacture;
	}

	public Manufacture createManufacture(Manufacture manufacture) {
		Manufacture inserted_manufacture = manufactureRepository.save(manufacture);
		manufacture.setCreatedAt(inserted_manufacture.getCreatedAt());
		return inserted_manufacture;
	}

	public Manufacture updateManufacture(int id, Manufacture manufacture) {
		Manufacture found_manufacture = getManufactureByID(id);
		manufacture.setM_id(found_manufacture.getM_id());
		manufacture.setCreatedAt(found_manufacture.getCreatedAt());
		manufacture.setUpdatedAt(found_manufacture.getUpdatedAt());
		Manufacture update_manufacture = manufactureRepository.save(manufacture);
		return update_manufacture;
	}

	public void deleteManufacture(int id) {
		getManufactureByID(id);
		manufactureRepository.deleteById(id);
	}
}
