package id.co.sofcograha.domain.modules.packages.admEndpoint.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_endpoint")
@Setter
@Getter
public class AdmEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "uri", unique = true)
	private String uri;

	@Column(name = "http_method", unique = true)
	private String httpMethod;

	@Column(name = "permission", unique = true)
	private String permission;

	@Column(name = "description")
	private String description;

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
