package backend.services;

import java.util.List;

import backend.models.Ratings;

public interface IRatingService extends IService<Ratings> {

	List<Ratings> findAllByPatientId(Long patientId);
	
	List<Ratings> findAllByOId(Long objId);
	
	List<Ratings> findAllByType(int type);
	
	List<Ratings> findByObjIdAndType(Long objId, int type);
	
}
