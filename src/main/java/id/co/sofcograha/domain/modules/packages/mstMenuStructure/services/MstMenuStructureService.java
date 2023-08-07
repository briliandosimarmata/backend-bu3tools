package id.co.sofcograha.domain.modules.packages.mstMenuStructure.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.repositories.MstMenuStructureRepository;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettings;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities.SgTempMenuStructureSettingsId;
import id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.services.SgTempMenuStructureSettingsService;
import id.co.sofcograha.domain.modules.utils.MenuStructureUtils;

@Service
public class MstMenuStructureService {
	@Autowired MstMenuStructureRepository repository;
	@Autowired SgTempMenuStructureSettingsService sgTempMenuStructureSettingsService;
	
	@Transactional
	public Map<String, List<MenuStructureDto>> findAllParentAndChildMenuWithSettings(List<MenuStructureDto> menuStructureDtos, String sessionId) {
		Map<String, List<MenuStructureDto>> menus = new HashMap<>();
		
		sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);
		
		for (MenuStructureDto menuStructureDto : menuStructureDtos) {
			if(menuStructureDto.getMenuId().trim().isBlank() && 
					menuStructureDto.getMenuSequence().trim().isBlank()) {
				continue;
			}
			SgTempMenuStructureSettingsId menuStructureSettingsId = 
					new SgTempMenuStructureSettingsId(menuStructureDto.getMenuId(), 
							menuStructureDto.getMenuSequence(), sessionId);
			
			SgTempMenuStructureSettings menuStructureSettings = new SgTempMenuStructureSettings();
			menuStructureSettings.setSgTempMenuStructureSettingsId(menuStructureSettingsId);
			menuStructureSettings.setIconClass(menuStructureDto.getIconClass());
			menuStructureSettings.setRoutingPath(menuStructureDto.getRoutingPath());
			
			sgTempMenuStructureSettingsService.save(menuStructureSettings);
		}
		
		menus.put("parentMenuStructures", repository.findAllParentMenuWithSettings(sessionId));
		menus.put("childMenuStructures", repository.findAllChildMenuWithSettings(sessionId));
		
		sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);
		
		return menus;
	}
	
	@Transactional
	public byte[] createMenuInfoTSFile(List<MenuStructureDto> menuStructureDtos, String sessionId) throws Exception {
		List<MenuStructureDto> completeMenuStruct = new ArrayList<>();
		byte[] fileBytes = null;
		
		sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);
		
		for (MenuStructureDto menuStructureDto : menuStructureDtos) {
			if(menuStructureDto.getMenuId().trim().isBlank() && 
					menuStructureDto.getMenuSequence().trim().isBlank()) {
				continue;
			}
			SgTempMenuStructureSettingsId menuStructureSettingsId = 
					new SgTempMenuStructureSettingsId(menuStructureDto.getMenuId(), 
							menuStructureDto.getMenuSequence(), sessionId);
			
			SgTempMenuStructureSettings menuStructureSettings = new SgTempMenuStructureSettings();
			menuStructureSettings.setSgTempMenuStructureSettingsId(menuStructureSettingsId);
			menuStructureSettings.setIconClass(menuStructureDto.getIconClass());
			menuStructureSettings.setRoutingPath(menuStructureDto.getRoutingPath());
			
			sgTempMenuStructureSettingsService.save(menuStructureSettings);
		}
		
		completeMenuStruct = repository.findAllMenuWithSettings(sessionId);
		
		fileBytes = MenuStructureUtils.generateMenuInfoTypescript(completeMenuStruct);
		
		sgTempMenuStructureSettingsService.deleteAllBySessionId(sessionId);
		
		return fileBytes;
	}
}
