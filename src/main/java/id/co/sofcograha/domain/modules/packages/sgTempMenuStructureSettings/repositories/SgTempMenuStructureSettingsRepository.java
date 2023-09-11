package id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettings;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettingsId;

@Repository
public class SgTempMenuStructureSettingsRepository
		extends SimpleJpaRepository<SgTempMenuStructureSettings, SgTempMenuStructureSettingsId> {

	@Autowired
	private EntityManager em;

	public SgTempMenuStructureSettingsRepository(EntityManager em) {
		super(SgTempMenuStructureSettings.class, em);
	}

	public void deleteAllBySessionId(String sessionId) {
		em.createNativeQuery("delete from sg_temp_menu_structure_settings \n" + "		where session_id = :sessionId ")
				.setParameter("sessionId", sessionId).executeUpdate();
	}

}
