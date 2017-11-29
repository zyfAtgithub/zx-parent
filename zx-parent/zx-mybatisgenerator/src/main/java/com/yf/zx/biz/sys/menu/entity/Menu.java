package com.yf.zx.biz.sys.menu.entity;

public class Menu {
    private Long id;

    private String menuname;

    private String menuurl;

    private Long parentid;

    private String menuicon;

    private Integer menuorder;

    private Integer level;

    private Boolean isbtn;

    private String treeiconskin;

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
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
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
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public Integer getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Integer menuorder) {
        this.menuorder = menuorder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        this.treeiconskin = treeiconskin == null ? null : treeiconskin.trim();
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }
}