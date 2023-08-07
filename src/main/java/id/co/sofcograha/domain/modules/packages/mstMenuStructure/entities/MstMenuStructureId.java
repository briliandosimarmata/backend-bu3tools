package id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class MstMenuStructureId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String menuId;
	
	private String menuSequence;
	
	public MstMenuStructureId(String menuId, String menuSequence) {
		super();
		this.menuId = menuId;
		this.menuSequence = menuSequence;
	}

	public MstMenuStructureId() {
		super();
	}

}
