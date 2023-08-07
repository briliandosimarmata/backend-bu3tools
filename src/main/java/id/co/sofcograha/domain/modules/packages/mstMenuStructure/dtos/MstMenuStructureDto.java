package id.co.sofcograha.domain.modules.packages.mstMenuStructure.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities.MstMenuStructure;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities.MstMenuStructureId;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@Getter
@Setter
public class MstMenuStructureDto extends MenuStructureDto{

	private Integer menuSortNumber;

	private String flagB2bPrincipal;

	private String keyinDate;

	private String keyinTime;

	private String keyinUser;

	private Long version;

	public MstMenuStructure toEntity() {
		MstMenuStructureId entityId = new MstMenuStructureId(menuId, menuSequence);
		MstMenuStructure entity = new MstMenuStructure(entityId, menuDesc, modulId, menuSortNumber, flagB2bPrincipal,
				keyinDate, keyinTime, keyinUser, version);
		return entity;
	}

	public static MstMenuStructureDto fromEntity(MstMenuStructure entity) {
		MstMenuStructureDto dto = new MstMenuStructureDto();
		dto.menuId = entity.getMenuStructureId().getMenuId();
		dto.menuSequence = entity.getMenuStructureId().getMenuSequence();
		dto.menuDesc = entity.getMenuDesc();
		dto.modulId = entity.getModulId();
		dto.menuSortNumber = entity.getMenuSortNumber();
		dto.flagB2bPrincipal = entity.getFlagB2bPrincipal();
		dto.keyinDate = entity.getKeyinDate();
		dto.keyinTime = entity.getKeyinTime();
		dto.keyinUser = entity.getKeyinUser();
		dto.version = entity.getVersion();
		return dto;
	}
	
	public static List<MstMenuStructureDto> fromEntities(List<MstMenuStructure> entities) {
		List<MstMenuStructureDto> dtos = new ArrayList<>();
		for (MstMenuStructure entity : entities) {
			dtos.add(fromEntity(entity));
		}
		
		return dtos;
	}
}
