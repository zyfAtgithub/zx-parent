package com.yf.zx.biz.sys.role.entity;

import java.util.Arrays;

public class Role {
	private Long id;

	private String role;

	private String description;

	private Boolean available;

	private Long[] permids;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long[] getPermids() {
		return permids;
	}

	public void setPermids(Long[] permids) {
		this.permids = permids;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
				+ ", permids=" + Arrays.toString(permids) + "]";
	}
}