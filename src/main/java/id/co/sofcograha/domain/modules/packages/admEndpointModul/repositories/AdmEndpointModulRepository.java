package id.co.sofcograha.domain.modules.packages.admEndpointModul.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;
import id.co.sofcograha.domain.modules.packages.admEndpointModul.entities.AdmEndpointModul;

@Repository
public class AdmEndpointModulRepository extends SimpleJpaRepository<AdmEndpointModul, String>  {

	@Autowired
	private EntityManager em;

	public AdmEndpointModulRepository(EntityManager em) {
		super(AdmEndpointModul.class, em);
	}
	
}
