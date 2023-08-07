package id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_menu_structure")
@Getter
@Setter
public class MstMenuStructure implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MstMenuStructureId menuStructureId;
	
	@Column(name = "menu_desc")
	private String menuDesc;

	@Column(name = "modul_id")
	private String modulId;

	@Column(name = "menu_sort_number")
	private Integer menuSortNumber;
	
	@Column(name = "flag_b2b_principal")
	private String flagB2bPrincipal;

	@Column(name = "keyin_date")
	private String keyinDate;

	@Column(name = "keyin_time")
	private String keyinTime;

	@Column(name = "keyin_user")
	private String keyinUser;
	
	@Column(name = "version")
	private Long version;
	
	public MstMenuStructure() {
		super();
	}

	public MstMenuStructure(MstMenuStructureId menuStructureId, String menuDesc, String modulId, Integer menuSortNumber,
			String flagB2bPrincipal, String keyinDate, String keyinTime, String keyinUser, Long version) {
		super();
		this.menuStructureId = menuStructureId;
		this.menuDesc = menuDesc;
		this.modulId = modulId;
		this.menuSortNumber = menuSortNumber;
		this.flagB2bPrincipal = flagB2bPrincipal;
		this.keyinDate = keyinDate;
		this.keyinTime = keyinTime;
		this.keyinUser = keyinUser;
		this.version = version;
	}

}
