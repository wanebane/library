package com.rivaldy.shki.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="status")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler", "createdDate", "updatedDate"}, allowGetters=true)
public class Status {

	@Id
	@Column(name="id", length=32)
	private String id;
	
	@NotNull
	@Column(name="code", length=15)
	private String code;
	
	@NotNull
	@Column(name="name", length=20)
	private String name;
	
	@Column(name="description", length=150)
	private String description;
	
	@NotNull
	@Column(name="type", length=20)
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="created_date", nullable=false, updatable=false)
	private Date createdDate;
	
	@Version
	@Column(name="version")
	private Integer version;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;	
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", type="
				+ type + ", createdDate=" + createdDate + ", version=" + version + ", updatedDate=" + updatedDate + "]";
	}
	/*Getter & Setter*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
