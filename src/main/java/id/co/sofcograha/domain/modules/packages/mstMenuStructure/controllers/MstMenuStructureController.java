package id.co.sofcograha.domain.modules.packages.mstMenuStructure.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.services.MstMenuStructureService;
import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestController
@RequestMapping(value = "mst-menu-structure")
public class MstMenuStructureController {
	
	@Autowired MstMenuStructureService service;
	
	@PostMapping
	public HttpCustomResponse findAllParentAndChildMenuWithSettings(
			@RequestBody List<MenuStructureDto> menuStructureDtos, 
			HttpServletRequest httpServletRequest) {
		
		Map<String, List<MenuStructureDto>> result = 
				service.findAllParentAndChildMenuWithSettings(
						menuStructureDtos,
						httpServletRequest.getHeader("sessionId"));
		
		return new HttpCustomResponse(result);
	}
	
	@PostMapping(value = "/file")
	public HttpCustomResponse createMenuInfoTSFile(
			@RequestBody List<MenuStructureDto> menuStructureDtos,
			HttpServletRequest httpServletRequest) throws Exception {
		
		byte[] file = service.createMenuInfoTSFile(
				menuStructureDtos,
				httpServletRequest.getHeader("sessionId"));
		
		return new HttpCustomResponse(file);
	}
}
