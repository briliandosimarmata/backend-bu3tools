package id.co.sofcograha.domain.modules.packages.admEndpointModul.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.sofcograha.domain.modules.packages.admEndpoint.dtos.AdmEnpointDto;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
@Setter
@Getter
public class AdmEndpointModulDto {

	private String id;
	private AdmEnpointDto enpoint;
	private AdmEndpointModulDto modul;
	private Long version;
}
