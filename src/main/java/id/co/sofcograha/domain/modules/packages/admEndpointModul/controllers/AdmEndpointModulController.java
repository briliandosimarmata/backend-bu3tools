package id.co.sofcograha.domain.modules.packages.admEndpointModul.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.sofcograha.domain.modules.packages.admEndpointModul.services.AdmEndpointModulService;
import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestController
@RequestMapping("adm-endpoint-modul")
public class AdmEndpointModulController {
	@Autowired AdmEndpointModulService service;
	
	@GetMapping
	public HttpCustomResponse getAllAdmEndpointModul() {
		return new HttpCustomResponse(service.getAllData());
	}
}
