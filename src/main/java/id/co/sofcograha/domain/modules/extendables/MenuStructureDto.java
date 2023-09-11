package id.co.sofcograha.domain.modules.extendables;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
@Getter
@Setter
@Entity
public class MenuStructureDto {

	@Id
	protected String id;

	protected String menuId;

	protected String menuSequence;

	protected String menuDesc;

	protected String modulId;

	protected String iconClass;

	protected String routingPath;
}
