package org.yf.zx.core;

/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * 
 * @author
 *
 */
public class QueryAccesslog {

	private Long logId;

	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 源IP地址
	 */
	private String srcip;

	/**
	 * URL
	 */
	private String url;

	private String domain;

	/**
	 * 目的IP地址
	 */
	private String destip;

	/**
	 * 目的端口
	 */
	private String destport;

	/**
	 * 源端口
	 */
	private String srcport;

	/**
	 * 访问时间
	 */
	private String accesstime;

	/**
	 * 持续时长
	 */
	private Long duration;

	/**
	 * 传输层协议类型
	 */
	private String protocoltype;

	/**
	 * 合作方
	 */
	private String partner;

	/**
	 * 来源文件名
	 */
	private String srcfilename;

	/**
	 * http状态码
	 */
	private String httpStatusCode;

	public QueryAccesslog() {
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDestip() {
		return destip;
	}

	public void setDestip(String destip) {
		this.destip = destip;
	}

	public String getDestport() {
		return destport;
	}

	public void setDestport(String destport) {
		this.destport = destport;
	}

	public String getSrcport() {
		return srcport;
	}

	public void setSrcport(String srcport) {
		this.srcport = srcport;
	}

	public String getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(String accesstime) {
		this.accesstime = accesstime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getProtocoltype() {
		return protocoltype;
	}

	public void setProtocoltype(String protocoltype) {
		this.protocoltype = protocoltype;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSrcfilename() {
		return srcfilename;
	}

	public void setSrcfilename(String srcfilename) {
		this.srcfilename = srcfilename;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	@Override
	public String toString() {
		return "QueryAccesslog [logId=" + logId + ", deptId=" + deptId + ", delFlag=" + delFlag + ", srcip=" + srcip
				+ ", url=" + url + ", domain=" + domain + ", destip=" + destip + ", destport=" + destport + ", srcport="
				+ srcport + ", accesstime=" + accesstime + ", duration=" + duration + ", protocoltype=" + protocoltype
				+ ", partner=" + partner + ", srcfilename=" + srcfilename + ", httpStatusCode=" + httpStatusCode + "]";
	}

}
