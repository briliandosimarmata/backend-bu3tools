package id.co.sofcograha.domain.modules.packages.admMenuStructure.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_menu_structure")
public class AdmMenuStructure implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "menu_id", unique = true)
	private String menuId;

	@Column(name = "menu_sequence", unique = true)
	private String menuSequence;

	@Column(name = "menu_desc")
	private String menuDesc;

	@Column(name = "id_adm_modul")
	private String idAdmModul;

	@Column(name = "menu_sort_number")
	private Integer menuSortNumber;

	@Column(name = "flag_b2b_principal")
	private String flagB2bPrincipal;
	
	@Column(name = "version")
	private Long version;
	
	@Column(name = "user_create")
	private String userCreate;
	
	@Column(name = "creation_time")
	private Date creationTime;
	
	@Column(name = "user_modify")
	private String userModify;
	
	@Column(name = "modification_time")
	private Date modificationTime;

}
