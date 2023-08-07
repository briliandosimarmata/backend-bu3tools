package id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class SgTempMenuStructureSettingsId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String menuId;

	private String menuSequence;
	
	private String sessionId;

	public SgTempMenuStructureSettingsId() {
		super();
	}

	public SgTempMenuStructureSettingsId(String menuId, String menuSequence, String sessionId) {
		super();
		this.menuId = menuId;
		this.menuSequence = menuSequence;
		this.sessionId = sessionId;
	}
	
	

}
