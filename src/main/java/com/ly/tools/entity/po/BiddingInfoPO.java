package com.ly.tools.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司招投标信息表
 * 
 * @author liyao
 * @date 2021-04-14 09:21:36
 */
@Data
@TableName(value = "bidding_info")
@ApiModel(value = "BiddingInfo",description = "公司招投标信息表")
public class BiddingInfoPO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  
	 */
	@ApiModelProperty(value = "")
	@TableId(value = "id",type = IdType.INPUT)
	private Long id;

	/**
	 *  标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;

	/**
	 *  查询公司ID
	 */
	@ApiModelProperty(value = "查询公司ID")
	@TableField(value = "companyId")
	private String companyId;

	/**
	 *  公司名称
	 */
	@ApiModelProperty(value = "公司名称")
	private String company;

	/**
	 *  公告类型
	 */
	@ApiModelProperty(value = "公告类型")
	private String type;

	/**
	 *  采购⼈
	 */
	@ApiModelProperty(value = "采购⼈")
	private String purchaser;

	/**
	 *  发布时间
	 */
	@ApiModelProperty(value = "发布时间")
	private String publishtime;

	/**
	 *  详细信息链接
	 */
	@ApiModelProperty(value = "详细信息链接")
	private String link;

	/**
	 *  代理机构
	 */
	@ApiModelProperty(value = "代理机构")
	private String proxy;

	/**
	 *  相关公司
	 */
	@ApiModelProperty(value = "相关公司")
	private String companies;

	/**
	 *  摘要信息
	 */
	@ApiModelProperty(value = "摘要信息")
	private String abs;

	/**
	 *  省份地区
	 */
	@ApiModelProperty(value = "省份地区")
	private String province;

	/**
	 *  正⽂简介
	 */
	@ApiModelProperty(value = "正⽂简介")
	private String intro;

	/**
	 *  网页内容
	 */
	@ApiModelProperty(value = "网页内容")
	private String content;

	/**
	 *  创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(value = "creatTime")
	private Date creatTime;

}