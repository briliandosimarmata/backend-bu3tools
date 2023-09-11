package id.co.sofcograha.domain.modules.packages.admEndpoint.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;

@Repository
public class AdmEndpointRepository  extends SimpleJpaRepository<AdmEndpoint, String> {

	@Autowired
	private EntityManager em;

	public AdmEndpointRepository(EntityManager em) {
		super(AdmEndpoint.class, em);
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmEndpoint> findListAdmEndpoint(){
		try {
			Query query = em.createNativeQuery("select * from adm_endpoint", AdmEndpoint.class);
			
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}
	
	public AdmEndpoint findAdmEndpointById(String id) {
		try {
			Query query = em.createNativeQuery("select * from adm_endpoint where id = :id", AdmEndpoint.class);
			query.setParameter("id", id);
			
			return (AdmEndpoint) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<AdmEndpoint> findAdmEndpointByUri(String uri) {
		try {
			uri = "%"+uri+"%";
			Query query = em.createNativeQuery("select * from adm_endpoint where uri like :uri limit 10", AdmEndpoint.class);
			query.setParameter("uri", uri);
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
