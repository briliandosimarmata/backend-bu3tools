package id.co.sofcograha.domain.modules.packages.sgTempMenuStructureSettings.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sg_temp_menu_structure_settings")
@Getter
@Setter
public class SgTempMenuStructureSettings {
	
	@EmbeddedId
	private SgTempMenuStructureSettingsId sgTempMenuStructureSettingsId;
	
	@Column(name = "icon_class")
	private String iconClass;
	
	@Column(name = "routing_path")
	private String routingPath;
	
	@Column(name = "variable")
	private String variable;

	public SgTempMenuStructureSettings() {
		super();
	}

	public SgTempMenuStructureSettings(SgTempMenuStructureSettingsId sgTempMenuStructureSettingsId, String menuDesc,
			String modulId, String iconClass, String routingPath, String variable) {
		super();
		this.sgTempMenuStructureSettingsId = sgTempMenuStructureSettingsId;
		this.iconClass = iconClass;
		this.routingPath = routingPath;
		this.variable = variable;
	}
	
}
