package spring.druid.junitTest;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * QueryAccesslog 
 *  
 * @author zhang.yifeng
 * @version 1.0.0
 * @since  1.0.0 
 * @see spring.druid.junitTest 
 *
 */
public class QueryAccesslog {

	private Long logId;
	
	/**
	 * 访问时间
	 */
	private String accesstime;
	
	/**
	 * 源IP地址
	 */
	private String srcip;

	/**
	 * 源端口
	 */
	private String srcport;
	
	/**
	 * 目的IP地址
	 */
	private String destip;

	/**
	 * 目的端口
	 */
	private String destport;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 持续时长
	 */
	private Long duration;

	/**
	 * 合作方
	 */
	private String partner;
	
	/**
	 * http状态码
	 */
	private String httpStatusCode;

	/**
	 * 传输层协议类型
	 */
	private String protocoltype;
	
	public QueryAccesslog() {}
	public QueryAccesslog(ResultSet rs) {
		try {
			this.logId = rs.getLong("logId");
			this.accesstime = rs.getString("accesstime");
			this.srcip = rs.getString("srcip");
			this.srcport = rs.getString("srcport");
			this.destip = rs.getString("destip");
			this.destport = rs.getString("destport");
			this.url = rs.getString("url");
			this.duration = rs.getLong("duration");
			this.partner = rs.getString("partner");
			this.httpStatusCode = rs.getString("httpStatusCode");
			this.protocoltype = rs.getString("protocoltype");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(String accesstime) {
		this.accesstime = accesstime;
	}

	public String getSrcip() {
		return srcip;
	}

	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}

	public String getSrcport() {
		return srcport;
	}

	public void setSrcport(String srcport) {
		this.srcport = srcport;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getProtocoltype() {
		return protocoltype;
	}

	public void setProtocoltype(String protocoltype) {
		this.protocoltype = protocoltype;
	}
	@Override
	public String toString() {
		return "QueryAccesslog [logId=" + logId + ", accesstime=" + accesstime + ", srcip=" + srcip + ", srcport="
				+ srcport + ", destip=" + destip + ", destport=" + destport + ", url=" + url + ", duration=" + duration
				+ ", partner=" + partner + ", httpStatusCode=" + httpStatusCode + ", protocoltype=" + protocoltype
				+ "]";
	}
	
}
