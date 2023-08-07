package id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettings;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.repositories.SgTempMenuStructureSettingsRepository;

@Service
public class SgTempMenuStructureSettingsService {

	@Autowired
	SgTempMenuStructureSettingsRepository repository;
	
	@Transactional
	public SgTempMenuStructureSettings save(SgTempMenuStructureSettings entity) {
		return repository.saveAndFlush(entity);
	}
	
	@Transactional
	public void deleteAllBySessionId(String sessionId) {
		repository.deleteAllBySessionId(sessionId);
	}
}
