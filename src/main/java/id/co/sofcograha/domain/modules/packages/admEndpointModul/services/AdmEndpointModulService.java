package id.co.sofcograha.domain.modules.packages.admEndpointModul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.sofcograha.domain.modules.packages.admEndpoint.repositories.AdmEndpointRepository;
import id.co.sofcograha.domain.modules.packages.admEndpointModul.entities.AdmEndpointModul;
import id.co.sofcograha.domain.modules.packages.admEndpointModul.repositories.AdmEndpointModulRepository;

@Service
public class AdmEndpointModulService {
	@Autowired AdmEndpointModulRepository repository;
	
	public List<AdmEndpointModul> getAllData() {
		List<AdmEndpointModul> entity = repository.findAll();
		
		return entity;
	}

}
