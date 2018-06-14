package com.jshx.core.base.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
public class IsmIllegalinfoFilterLog extends BaseModel
{
	
	private static final String resultTimePattern = "yyyy-MM-dd HH:mm:ss";
	
	private static final String startTimePattern = "yyyyMMddHHmmss";
	
	private static final Logger LOGGER= LoggerFactory.getLogger(IsmIllegalinfoFilterLog.class);

	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	
	/**
	 * CDN许可证号
	 */
	private String cdnid;

	/**
	 * 过滤日志记录编号
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
	 * URL
	 */
	private String url;
	
	/**
	 * 所属合作方
	 */
    private String partnertype;

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

	
	@Override
	public String toString() {
		return "IsmIllegalinfoFilterLog [delFlag=" + delFlag + ", cdnid=" + cdnid + ", logid=" + logid + ", commandid="
				+ commandid + ", commandnum=" + commandnum + ", srcip=" + srcip + ", destip=" + destip + ", domain="
				+ domain + ", content=" + content + ", url=" + url + ", partnertype=" + partnertype + ", gathertime="
				+ gathertime + ", isupload=" + isupload + ", snapshotUrl=" + snapshotUrl + "]";
	}




	public IsmIllegalinfoFilterLog(){
	}
	
	


	public IsmIllegalinfoFilterLog(String cdnid, Long logid, Long commandid, Long commandnum, String srcip,
			String destip, String domain, String content, String partnertype, String url, String gathertime,
			String snapshotUrl) {
		super();
		this.cdnid = cdnid;
		this.logid = logid;
		this.commandid = commandid;
		this.commandnum = commandnum;
		this.srcip = srcip;
		this.destip = destip;
		this.domain = domain;
		this.content = content;
		this.url = url;
		this.partnertype = partnertype;
		this.gathertime = gathertime;
		this.snapshotUrl = snapshotUrl;
	}




	public IsmIllegalinfoFilterLog(String deptId, Integer delFlag, String cdnid, Long logid, Long commandid,
			Long commandnum, String srcip, String destip, String domain, String content, String url, String partnertype,
			String gathertime,String isupload) {
		super();
		this.deptId = deptId;
		this.delFlag = delFlag;
		this.cdnid = cdnid;
		this.logid = logid;
		this.commandid = commandid;
		this.commandnum = commandnum;
		this.srcip = srcip;
		this.destip = destip;
		this.domain = domain;
		this.content = content;
		this.url = url;
		this.partnertype = partnertype;
		this.gathertime = gathertime;
		this.isupload = isupload;
	}




	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	
	public String getCdnid()
	{
		return this.cdnid;
	}

	public void setCdnid(String cdnid)
	{
		this.cdnid = cdnid;
	}

	public Long getLogid()
	{
		return this.logid;
	}

	public void setLogid(Long logid)
	{
		this.logid = logid;
	}

	public Long getCommandid()
	{
		return this.commandid;
	}

	public void setCommandid(Long commandid)
	{
		this.commandid = commandid;
	}

	public Long getCommandnum()
	{
		return this.commandnum;
	}

	public void setCommandnum(Long commandnum)
	{
		this.commandnum = commandnum;
	}

	public String getSrcip()
	{
		return this.srcip;
	}

	public void setSrcip(String srcip)
	{
		this.srcip = srcip;
	}

	public String getDestip()
	{
		return this.destip;
	}

	public void setDestip(String destip)
	{
		this.destip = destip;
	}

	public String getDomain()
	{
		return this.domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getUrl()
	{
		return this.url;
	}
	public String getPartnertype() {
		return partnertype;
	}

	public void setPartnertype(String partnertype) {
		this.partnertype = partnertype;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getGathertime()
	{
		return this.gathertime;
	}

	public void setGathertime(String gathertime)
	{
		this.gathertime = gathertime;
	}
	
	public String getSnapshotUrl() {
		return snapshotUrl;
	}

	public void setSnapshotUrl(String snapshotUrl) {
		this.snapshotUrl = snapshotUrl;
	}
	

	public String getIsupload() {
		return isupload;
	}


	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}




	public IsmIllegalinfoFilterLog(String logdata[]) {
		super();
		this.delFlag = 0;
		try{
			this.logid = Long.valueOf(logdata[0]);
		}catch(Exception e){
			LOGGER.error("过滤日志id解析错误", e);
		}
		try{
			this.commandid = Long.valueOf(logdata[1]);
		}catch(Exception e){
			LOGGER.error("过滤指令id解析错误", e);
		}
		try{
			this.commandnum = Long.valueOf(logdata[2]);
		}catch(Exception e){
			LOGGER.error("触发指令次数解析错误", e);
		}
		this.srcip = logdata[3];
		this.destip = logdata[4];
		this.domain = logdata[5];
		this.content = logdata[6];
		this.url = logdata[7];
		this.isupload="0";
		if (10 == logdata.length) {
			this.snapshotUrl = logdata[9];
		}
	}

}
