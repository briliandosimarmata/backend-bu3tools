package id.co.sofcograha.domain.modules.packages.admModul.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_modul")
@Setter
@Getter
public class AdmModul implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "modul_name")
	private String modulName;

	@Column(name = "modul_type")
	private String modulType;

	@Column(name = "service_id")
	private String serviceId;

	@Column(name = "modul_access_level")
	private String modulAccessLevel;

	@Column(name = "default_menu_flag")
	private String defaultMenuFlag;

	@Column(name = "menu_title_flag")
	private String menuTitleFlag;

	@Column(name = "version")
	private Long version;

	@Column(name = "user_create")
	private String userCreate;

	@CreationTimestamp
	@Column(name = "creation_time", nullable = false, updatable = false)
	private Date creationTime;

	@Column(name = "user_modify")
	private String userModify;

	@UpdateTimestamp
	@Column(name = "modification_time")
	private Date modificationTime;
}
