package com.yf.zx.biz.sys.menu.entity;

import com.yf.zx.core.base.vo.BaseVo;

public class MenuVo extends BaseVo {

	private Long id;

    private String menuname;

    private String menuurl;

    private Long parentid;

    private String menuicon;

    private Integer level;

    private String permexp;
    
    private Boolean isbtn;
    
    private String treeiconskin;
    
    private Integer menuorder;

    private Boolean isshow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getMenuicon() {
		return menuicon;
	}

	public void setMenuicon(String menuicon) {
		this.menuicon = menuicon;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPermexp() {
		return permexp;
	}

	public void setPermexp(String permexp) {
		this.permexp = permexp;
	}

	public Boolean getIsbtn() {
		return isbtn;
	}

	public void setIsbtn(Boolean isbtn) {
		this.isbtn = isbtn;
	}

	public String getTreeiconskin() {
		return treeiconskin;
	}

	public void setTreeiconskin(String treeiconskin) {
		this.treeiconskin = treeiconskin;
	}

	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}

	public Boolean getIsshow() {
		return isshow;
	}

	public void setIsshow(Boolean isshow) {
		this.isshow = isshow;
	}

	@Override
	public String toString() {
		return "MenuVo [id=" + id + ", menuname=" + menuname + ", menuurl=" + menuurl + ", parentid=" + parentid
				+ ", menuicon=" + menuicon + ", level=" + level + ", permexp=" + permexp + ", isbtn=" + isbtn
				+ ", treeiconskin=" + treeiconskin + ", menuorder=" + menuorder + ", isshow=" + isshow + "]";
	}
    
}
