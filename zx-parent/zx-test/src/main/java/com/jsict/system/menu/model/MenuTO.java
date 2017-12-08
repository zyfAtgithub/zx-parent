package com.jsict.system.menu.model;

public class MenuTO {

	/**菜单id 
	 * 对应 cdn_resource.security_unit.id 
	 */
	private Long unitid;

	/**菜单id 
	 * 对应 cdn_resource.sys_menu.id 
	 */
	private Long menuid;
	
	/** 菜单模块 
	 * 对应 cdn_resource.security_unit.sid 
	 * 和 cdn_resource.sys_menu.security_units 
	 */
	private String sid;
	
	/** 菜单url路径 */
	private String mrl;
	
	/** 父级  */
	private Integer parent;
	
	/** 菜单url参数 */
	private String param;
	
	/** 菜单名称 */
	private String name;

	/** 菜单描述 */
	private String description;
	
	/** 菜单权限表达式 */
	private String perm_exp;
	
	/** 是否是按钮 */
	private Boolean isButton;

	public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMrl() {
		return mrl;
	}

	public void setMrl(String mrl) {
		this.mrl = mrl;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
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

	public String getPerm_exp() {
		return perm_exp;
	}

	public void setPerm_exp(String perm_exp) {
		this.perm_exp = perm_exp;
	}

	public Boolean getIsButton() {
		return isButton;
	}

	public void setIsButton(Boolean isButton) {
		this.isButton = isButton;
	}

}
