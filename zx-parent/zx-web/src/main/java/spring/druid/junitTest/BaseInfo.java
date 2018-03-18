package spring.druid.junitTest;

public class BaseInfo {

	private String cdnnetid;
	private String content;
	private String domainip;
	private String domainid;
	private String source;
	private String regid;
	private String topdomain;
	private String type;
	public String getCdnnetid() {
		return cdnnetid;
	}
	public void setCdnnetid(String cdnnetid) {
		this.cdnnetid = cdnnetid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDomainip() {
		return domainip;
	}
	public void setDomainip(String domainip) {
		this.domainip = domainip;
	}
	public String getDomainid() {
		return domainid;
	}
	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getTopdomain() {
		return topdomain;
	}
	public void setTopdomain(String topdomain) {
		this.topdomain = topdomain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BaseInfo [cdnnetid=" + cdnnetid + ", content=" + content + ", domainip=" + domainip + ", domainid="
				+ domainid + ", source=" + source + ", regid=" + regid + ", topdomain=" + topdomain + ", type=" + type
				+ "]";
	}
}
