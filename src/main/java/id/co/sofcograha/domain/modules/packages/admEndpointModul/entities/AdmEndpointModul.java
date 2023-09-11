package id.co.sofcograha.domain.modules.packages.admEndpointModul.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import id.co.sofcograha.domain.modules.packages.admEndpoint.entities.AdmEndpoint;
import id.co.sofcograha.domain.modules.packages.admModul.entities.AdmModul;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_endpoint_modul")
@Setter
@Getter
public class AdmEndpointModul implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_adm_endpoint")
	private AdmEndpoint endpoint;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_adm_modul")
	private AdmModul modul;
	
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
