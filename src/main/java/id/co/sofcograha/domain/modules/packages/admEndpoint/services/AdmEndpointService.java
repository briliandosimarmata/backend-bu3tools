package id.co.sofcograha.domain.modules.packages.admEndpoint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.sofcograha.domain.modules.enums.HttpMethod;
import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;
import id.co.sofcograha.domain.modules.packages.admEndpoint.repositories.AdmEndpointRepository;

@Service
public class AdmEndpointService {
	
	@Autowired AdmEndpointRepository repository;
	
	public List<AdmEndpoint> getListAdmEndpoint() {
		return repository.findListAdmEndpoint();
	}
	
	public AdmEndpoint getAdmEndpointById(String id) {
		return repository.findAdmEndpointById(id);
	}
	
	public AdmEndpoint saveAdmEndpoint(AdmEndpoint entity) {
		
		validateHttpMethodMustValid(entity.getHttpMethod());
		
		return repository.saveAndFlush(entity);
	}
	
	public AdmEndpoint editAdmEndpoint(String id, AdmEndpoint entity) {
		AdmEndpoint admEndpoint = repository.findById(id).get();
		admEndpoint.setUri(entity.getUri());
		admEndpoint.setHttpMethod(entity.getHttpMethod());
		admEndpoint.setPermission(entity.getPermission());
		admEndpoint.setDescription(entity.getDescription());
		
		return repository.save(admEndpoint);
		
	}
	
	public void deleteBacthEndpoint(List<String> ids) {
		repository.deleteAllByIdInBatch(ids);
	}
	
	public List<AdmEndpoint> getAdmEndpointByUri(String uri) {
		return repository.findAdmEndpointByUri(uri);
	}
	
	private void validateHttpMethodMustValid(String httpMethod) {
		Boolean isMatch = false;
		
		for(HttpMethod value : HttpMethod.values()) {
			if(value.toString().equals(httpMethod)) {
				isMatch = true;
			}
		}
		
		if(!isMatch) {
			new Throwable("Tidak Valid");
		}
	}

}
