package org.yf.zx.core;

/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * 
 * @author
 *
 */
public class IsmIllegalinfoMonitoLog  {

	/**
	 * CDN许可证号
	 */
	private String cdnid;

	/**
	 * 监测日志记录编号
	 */
	private Long logid;

	/**
	 * 触发指令编号
	 */
	private Long commandid;

	/**
	 * 触发指令次数
	 */
	private Long commandnum;

	/**
	 * 源IP
	 */
	private String srcip;

	/**
	 * 目的IP
	 */
	private String destip;

	/**
	 * 域名
	 */
	private String domain;

	/**
	 * 违法信息
	 */
	private String content;

	/**
	 * 所属合作方
	 */
	private String partnertype;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 采集时间
	 */
	private String gathertime;

	/**
	 * 是否已上报
	 */
	private String isupload;

	/**
	 * 页面快照地址
	 */
	private String snapshotUrl;

	public String getCdnid() {
		return cdnid;
	}

	public void setCdnid(String cdnid) {
		this.cdnid = cdnid;
	}

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public Long getCommandid() {
		return commandid;
	}

	public void setCommandid(Long commandid) {
		this.commandid = commandid;
	}

	public Long getCommandnum() {
		return commandnum;
	}

	public void setCommandnum(Long commandnum) {
		this.commandnum = commandnum;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}

	public String getDestip() {
		return destip;
	}

	public void setDestip(String destip) {
		this.destip = destip;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPartnertype() {
		return partnertype;
	}

	public void setPartnertype(String partnertype) {
		this.partnertype = partnertype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGathertime() {
		return gathertime;
	}

	public void setGathertime(String gathertime) {
		this.gathertime = gathertime;
	}

	public String getIsupload() {
		return isupload;
	}

	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}

	public String getSnapshotUrl() {
		return snapshotUrl;
	}

	public void setSnapshotUrl(String snapshotUrl) {
		this.snapshotUrl = snapshotUrl;
	}

	@Override
	public String toString() {
		return "IsmIllegalinfoMonitoLog [cdnid=" + cdnid + ", logid=" + logid + ", commandid=" + commandid
				+ ", commandnum=" + commandnum + ", srcip=" + srcip + ", destip=" + destip + ", domain=" + domain
				+ ", content=" + content + ", partnertype=" + partnertype + ", url=" + url + ", gathertime="
				+ gathertime + ", isupload=" + isupload + ", snapshotUrl=" + snapshotUrl + "]";
	}

}
