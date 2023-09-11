package id.co.sofcograha.domain.modules.packages.admMenuStructure.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.admMenuStructure.services.AdmMenuStructureService;
import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestController
@RequestMapping(value = "adm-menu-structure")
public class AdmMenuStructureController {

	@Autowired
	AdmMenuStructureService service;

	@PostMapping(value = "/file")
	public HttpCustomResponse createMenuInfoTSFile(@RequestBody List<MenuStructureDto> menuStructureDtos,
			HttpServletRequest httpServletRequest) throws Exception {

		byte[] file = service.createMenuInfoTSFile(menuStructureDtos, httpServletRequest.getHeader("sessionId"));

		return new HttpCustomResponse(file);
	}

	@PostMapping
	public HttpCustomResponse findAllParentAndChildMenuWithSettings(
			@RequestBody List<MenuStructureDto> menuStructureDtos, HttpServletRequest httpServletRequest) {

		Map<String, List<MenuStructureDto>> result = service.findAllParentAndChildMenuWithSettings(menuStructureDtos,
				httpServletRequest.getHeader("sessionId"));

		return new HttpCustomResponse(result);
	}

}
