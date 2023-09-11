package id.co.sofcograha.domain.modules.packages.admEndpoint.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.sofcograha.domain.modules.packages.admEndpoint.dtos.AdmEnpointDto;
import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;
import id.co.sofcograha.domain.modules.packages.admEndpoint.services.AdmEndpointService;
import id.co.sofcograha.domain.responses.HttpCustomResponse;

@RestController
@RequestMapping(value = "adm-endpoint")
public class AdmEndpointController {
	
	@Autowired AdmEndpointService service;
	
	@GetMapping
	public HttpCustomResponse getData() throws Exception {
		AdmEnpointDto dto = new AdmEnpointDto();
		return new HttpCustomResponse(dto.fromEntities(service.getListAdmEndpoint()));
	}
	
	@PostMapping
	public HttpCustomResponse addAdmEndpoint(@RequestBody AdmEnpointDto request) throws Exception {
		AdmEndpoint result = service.saveAdmEndpoint(request.toEntity());
		AdmEnpointDto dto = new AdmEnpointDto();
		
		return new HttpCustomResponse(dto.fromEntity(result));
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("{id}")
	public HttpCustomResponse getDetailAdmEndpoint(@PathVariable String id) throws Exception {
		AdmEnpointDto dto = new AdmEnpointDto();
		return new HttpCustomResponse(dto.fromEntity(service.getAdmEndpointById(id)));
	}
	
	@SuppressWarnings("static-access")
	@PutMapping("{id}")
	public HttpCustomResponse editAdmEndpoint(@PathVariable String id, @RequestBody AdmEnpointDto body) throws Exception {
		AdmEndpoint result = service.editAdmEndpoint(id, body.toEntity());
		AdmEnpointDto dto = new AdmEnpointDto();
		
		return new HttpCustomResponse(dto.fromEntity(result));
	}
	
	@PostMapping("delete")
	public void deleteBatchAdmEndpoint(@RequestBody Map<String, List<String>> body) {
		service.deleteBacthEndpoint(body.get("ids"));
	}
	
	@GetMapping("search-uri")
	public HttpCustomResponse searchUri(@RequestParam Map<String, String> params) {
		AdmEnpointDto dto = new AdmEnpointDto();
		String uri = params.get("uri");
		return new HttpCustomResponse(dto.fromEntities(service.getAdmEndpointByUri(uri)));
	}
	
}
