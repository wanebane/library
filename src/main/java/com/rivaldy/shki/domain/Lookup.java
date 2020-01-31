package com.rivaldy.shki.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="lookup")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createdDate", "updatedDate"})
public class Lookup {

	@Id
	@Column(name="id", length=32)
	private String id;
	
	@NotNull
	@Column(name="code", length=15)
	private String code;
	
	@Column(name="description", length=150)
	private String description;
	
	@NotNull
	@Column(name="name", length=20)
	private String name;
	
	@NotNull
	@Column(name="priority")
	private Integer priority;
	
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
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="id", nullable=false)
	private Status status;

	@Override
	public String toString() {
		return "Lookup [id=" + id + ", code=" + code + ", description=" + description + ", name=" + name + ", priority="
				+ priority + ", type=" + type + ", createdDate=" + createdDate + ", version=" + version
				+ ", updatedDate=" + updatedDate + ", status=" + status + "]";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
