package backend.services;

import java.util.List;

import backend.models.Pharmacy;

public interface IPharmacyService extends IService<Pharmacy> {
	List<Pharmacy> findAllByName(String name);
}
