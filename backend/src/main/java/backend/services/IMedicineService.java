package backend.services;

import java.util.List;

import backend.models.Medicine;

public interface IMedicineService extends IService<Medicine> {
	List<Medicine> findAllByName(String name);
}
