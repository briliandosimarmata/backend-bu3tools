package id.co.sofcograha.domain.modules.packages.admModul.dtos;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
@Setter
@Getter
class AdmModulDto {
	private String id;
	private String modulName;
	private String modulType;
	private String serviceId;
	private String modulAccessLevel;
	private String defaultMenuFlag;
	private String menuTitleFlag;
	private Long version;

}
