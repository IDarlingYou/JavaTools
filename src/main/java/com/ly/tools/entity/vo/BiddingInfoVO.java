package com.ly.tools.entity.vo;

import com.ly.tools.entity.po.BiddingInfoPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 公司招投标信息表
 * 
 * @author liyao
 * @date 2021-04-14 09:21:36
 */
@Data
@ApiModel(value = "BiddingInfo",description = "公司招投标信息表")
public class BiddingInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;



	/**
	 *  公司名称
	 */
	@ApiModelProperty(value = "公司名称")
	private String company;

	/**
	 *  公司ID
	 */
	@ApiModelProperty(value = "公司ID")
	private String companyId;

	/**
	 *  相关信息
	 */
	@ApiModelProperty(value = "相关信息")
	private List<BiddingInfoPO> companyMes;

	
}