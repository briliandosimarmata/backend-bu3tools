package id.co.sofcograha.domain.modules.packages.admEndpoint.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
@Setter
@Getter
public class AdmEnpointDto {
	protected static final int DEFAULT_DEPTH_LEVEL = 2;
	
	private String id;
	private String uri;
	private String httpMethod;
	private String permission;
	private String description;
	private Long version;
	
	public AdmEndpoint toEntity() {
		AdmEndpoint entity = new AdmEndpoint();
		
		entity.setUri(uri);
		entity.setHttpMethod(httpMethod);
		entity.setPermission(permission);
		entity.setDescription(description);
		entity.setVersion(version!= null ? version: 0);
		entity.setUserCreate("Sofco");
		entity.setUserModify("Sofco");
		return entity;
	}
	
	public static AdmEnpointDto fromEntity(AdmEndpoint entity) {
		return fromEntity(entity, DEFAULT_DEPTH_LEVEL);
	}
	
	public static AdmEnpointDto fromEntity(AdmEndpoint entity, int depthLevel) {
		if (entity == null) {
			return null;
		}

		if (depthLevel < 0) {
			depthLevel = DEFAULT_DEPTH_LEVEL;
		}

		AdmEnpointDto dto = new AdmEnpointDto();

		if (depthLevel > 0) {
			depthLevel--;
			dto.id = entity.getId();
			dto.uri = entity.getUri();
			dto.httpMethod = entity.getHttpMethod();
			dto.permission = entity.getPermission();
			dto.description = entity.getDescription();
			dto.version = entity.getVersion();
		}

		return dto;
	}
	
	public List<AdmEnpointDto> fromEntities(List<AdmEndpoint> entities) {
		return fromEntities(entities, DEFAULT_DEPTH_LEVEL);
	}
	
	public static List<AdmEnpointDto> fromEntities(List<AdmEndpoint> entities, int depthLevel){
		if (entities == null) {
			return null;
		}

		if (depthLevel < 0) {
			depthLevel = DEFAULT_DEPTH_LEVEL;
		}

		List<AdmEnpointDto> dtos = new ArrayList<AdmEnpointDto>();
		for (AdmEndpoint entity : entities) {
			dtos.add(AdmEnpointDto.fromEntity(entity, depthLevel));
		}

		return dtos;
	}
}
