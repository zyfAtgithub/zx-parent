package spring.druid.junitTest;


/**
 * 实体类模板
 * @author zhang.yifeng
 *
 */
public class IsmSeq
{
	/**
	 * 编号所属类型（C：客户,CS：客户应用服务,CD:客户域名,SN:子网,N:节点,H:机房,FI:机架,GI:链路,IP:IP)
	 */
	private String noType;

	/**
	 * 编号所属类型描述（C：客户,CS：客户应用服务,CD:客户域名,SN:子网,N:节点,H:机房,FF:机架,G:链路,IP:IP)
	 */
	private String typeName;

	/**
	 * 客户单位名称
	 */
	private Long curVal;

	/**
	 * 删除标记
	 */
	private Integer delFlag;
	
	public IsmSeq(){}

	public IsmSeq(String noType, String typeName, Long curVal, Integer delFlag) {
		super();
		this.noType = noType;
		this.typeName = typeName;
		this.curVal = curVal;
		this.delFlag = delFlag;
	}


	public String getNoType() {
		return noType;
	}

	public void setNoType(String noType) {
		this.noType = noType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getCurVal() {
		return curVal;
	}

	public void setCurVal(Long curVal) {
		this.curVal = curVal;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "IsmSeq [noType=" + noType + ", typeName=" + typeName + ", curVal=" + curVal + ", delFlag=" + delFlag
				+ "]";
	}

}
