package com.ly.tools.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.tools.entity.dto.Company;
import com.ly.tools.entity.dto.CompanyDTO;
import com.ly.tools.entity.dto.TitleMesDTO;
import com.ly.tools.entity.po.BiddingInfoPO;
import com.ly.tools.entity.vo.BiddingInfoVO;
import com.ly.tools.enums.EyesMesConstants;
import com.ly.tools.mapper.BiddingInfoMapper;
import com.ly.tools.service.EyesService;
import com.ly.tools.utils.HttpHelper;
import com.ly.tools.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: LY
 * @Date: 2021/4/12 15:44
 * @Description:
 **/
@Slf4j
@Service("EyesService")
public class EyesServiceImpl implements EyesService {

    @Value("${eyes.token}")
    private String token;

    @Value("${eyes.url}")
    private String url;

    @Value("${eyes.url1}")
    private String url1;

    // 仅过滤获取中文
    private static final String reg = "[^\u4e00-\u9fa5]";

    @Autowired
    private BiddingInfoMapper biddingInfoMapper;


    /**
     * 通过公司名称或ID获取企业专利信息
     */
    @Override
    public List<BiddingInfoVO> getEyesCompanyMes(CompanyDTO dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<BiddingInfoVO> list = new ArrayList<>();
        List<Company> companyMes = dto.getCompanyMes();
        String eUrl = "";
        String purchaser;
        String proxy;
        String companies;
        for (Company com : companyMes) {
            List<BiddingInfoPO> list1 = new ArrayList<>();
            BiddingInfoVO biddingInfoVO = new BiddingInfoVO();
            if (StringUtil.isNotBlank(com.getCompanyId())) {
                eUrl = url.concat("keyword=").concat(com.getCompanyId());
                biddingInfoVO.setCompanyId(com.getCompanyId());
            }
            if (StringUtil.isNotBlank(com.getCompanyName())) {
                eUrl = url.concat("keyword=").concat(com.getCompanyName());
                biddingInfoVO.setCompany(com.getCompanyName());
            }
            //根据公司名称或者公司ID获取天眼查返回数据
            //String result = executeGet(eUrl, token);
            String result = "{\"result\":{\"total\":\"1\",\"items\":[{\"publishTime\":\"1546876800000\",\"bidUrl\":\"https://m.tianyancha.com/app/h5/bid/4c03f675fbaf11e985737cd30aeb144c\",\"purchaser\":\"\",\"link\":\"http://www.bidizhaobiao.com/info-53204940.html?companyId=27\",\"title\":\"中国联合网络通信有限公司海南省分公司2018年创新业务合作伙伴公开市场第三次招募结果公示\",\"type\":\"中标公告\",\"uuid\":\"4c03f675fbaf11e985737cd30aeb144c\",\"content\":\"<div> \\n <div>\\n   中国联合网络通信有限公司海南省分公司2018年创新业务合作伙伴公开市场第三次招募结果公示 \\n </div> \\n <span> 招标编号：hn-2019-3 </span> \\n <div>\\n   发布时间：2019-01-07 \\n </div> \\n <div>\\n   项目名称：中国联合网络通信有限公司海南省分公司2018年创新业务合作伙伴公开市场第三次招募 \\n  <br>招募代理编号：07-10-04H-2018-D-C17744 \\n  <br>招募人：中国联合网络通信有限公司海南省分公司 \\n  <br>招募代理机构：公诚管理咨询有限公司 \\n  <br>根据招募文件载明的评审方法和标准，中国联合网络通信有限公司海南省分公司2018年创新业务合作伙伴公开市场第三次招募评审委员会对各申请人递交的申请文件进行了详细审查，根据评审结果，入围供应商公示如下（排名不分先后）： \\n  <br>标段一（智慧政务）： \\n  <br>1）海南盈科信联科技有限公司 \\n  <br>2）上海蜜度信息技术有限公司 \\n  <br>3）上海盛奕网络科技有限公司 \\n  <br>标段三（智慧旅游）： \\n  <br>1）海南波罗密信息科技有限公司 \\n  <br>2）海南云方信息技术有限公司 \\n  <br>3）上海蜜度信息技术有限公司 \\n  <br>4）威斯达信息科技（海南）有限公司 \\n  <br>标段四（智慧环保）： \\n  <br>1）海南盈科信联科技有限公司 \\n  <br>2）江西飞尚科技有限公司 \\n  <br>标段七（智慧农业）： \\n  <br>1）哈尔滨市东典智能科技开发有限责任公司海南分公司 \\n  <br>2）江西飞尚科技有限公司 \\n  <br>标段八（智慧公安军警）： \\n  <br>1）海南盈科信联科技有限公司 \\n  <br>2）深圳市泽视达科技发展有限公司 \\n  <br>3）西安征途网络科技有限公司 \\n  <br>标段十一（通用系统集成）： \\n  <br>1）北京广电音视科技发展有限公司 \\n  <br>2）哈尔滨市东典智能科技开发有限责任公司海南分公司 \\n  <br>3）海南益壮通信工程有限公司 \\n  <br>4）江西飞尚科技有限公司 \\n  <br>5）南威软件股份有限公司 \\n  <br>6）上海电信工程有限公司 \\n  <br>7）上海蜜度信息技术有限公司 \\n  <br>8）西安征途网络科技有限公司 \\n  <br>标段十二（ITO服务外包）： \\n  <br>1）北京广电音视科技发展有限公司 \\n  <br>2）广州太信信息科技有限公司 \\n  <br>现将该项目结果予以公示，接受社会各界监督，公示期自2019年1月7日至2019年1月10日，如有异议请于公示期内以书面形式（加盖单位公章）送至招募人或招募代理机构，逾期将不再受理。 \\n  <br>招募代理机构联系方式： \\n  <br>联系人：叶崇煌；联系电话：13006011752；邮箱：gczbhnlt@163.com \\n  <br>招募人联系方式： \\n  <br>受理部门：中国联合网络通信有限公司海南省分公司纪委；联系人：云林；联系电话：0898-36637010；邮箱：his-jjjcjbyx@chinaunicom.cn \\n  <br>特此公示。 \\n  <br>招募人：中国联合网络通信有限公司海南省分公司 \\n  <br>招募代理机构：公诚管理咨询有限公司 \\n  <br>2019年1月7日 \\n  <br> \\n </div> \\n <div>\\n   发布单位：中国联合网络通信有限公司海南省分公司 \\n </div> \\n</div>\",\"proxy\":\"\",\"abs\":\"\",\"province\":\"海南\",\"intro\":\"\",\"id\":\"27864258\"}]},\"reason\":\"ok\",\"error_code\":0}";
            // 获取天眼查数据存入数据库
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject result1 = JSONObject.parseObject(jsonObject.getString("result"));
            JSONArray items = result1.getJSONArray("items");
            for (int i = 0; i < items.size(); i++) {
                BiddingInfoPO po = new BiddingInfoPO();
                // 获取公司名称或者公司ID
                po.setCompany(com.getCompanyName());
                po.setCompanyId(com.getCompanyId());
                JSONObject jsonObject1 = items.getJSONObject(i);
                String content = jsonObject1.getString("content");
                // 数据过滤
                String s2 = content.replaceAll(reg, "");
                // 发布时间
                po.setPublishtime(jsonObject1.getString("publishTime"));
                // 标题
                po.setTitle(jsonObject1.getString("title"));
                // 公告类型
                po.setType(jsonObject1.getString("type"));
                // 采购⼈
                if (jsonObject1.getString("purchaser").length() == 0) {
                    // 根据"采购人"和"分公司"去匹配
                    purchaser = getPurchaser(s2, String.valueOf(EyesMesConstants.purchaser.PURCHASING_AGENT.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY_OTHER.getName()));
                    if (purchaser.length() == 0) {
                        // 根据"采购人"和"公司"去匹配
                        purchaser = getPurchaser(s2, String.valueOf(EyesMesConstants.purchaser.PURCHASING_AGENT.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY.getName()));
                        if (purchaser.length() == 0) {
                            // 根据"招募人"和"分公司"去匹配
                            purchaser = getPurchaser(s2, String.valueOf(EyesMesConstants.purchaser.Recruit_people.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY_OTHER.getName()));
                            if (purchaser.length() == 0) {
                                // 根据"招募人"和"公司"去匹配
                                purchaser = getPurchaser(s2, String.valueOf(EyesMesConstants.purchaser.Recruit_people.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY.getName()));
                            }
                        }
                    }
                    // 再次数据过滤
                    purchaser.replaceAll(reg, "");
                } else {
                    purchaser = jsonObject1.getString("purchaser");
                }
                po.setPurchaser(purchaser);
                // 详细信息链接
                po.setLink(jsonObject1.getString("link"));
                // 相关公司
                s2 = "你公司科技有限公司";
                companies = getRelatedCompany(s2);
                System.out.println("companies---->>>>>>>>>>>>" + companies);
                po.setCompanies(companies);
                // 摘要信息
                po.setAbs(jsonObject1.getString("abs"));
                // 省份地区
                po.setProvince(jsonObject1.getString("province"));
                // 正⽂简介
                po.setIntro(jsonObject1.getString("intro"));
                // 代理机构
                if (jsonObject1.getString("proxy").length() == 0) {
                    // 根据"代理机构"去匹配
                    proxy = getPurchaser(s2, String.valueOf(EyesMesConstants.Agency.PURCHASING_AGENT.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY_OTHER.getName()));
                    if (proxy.length() == 0) {
                        proxy = getPurchaser(s2, String.valueOf(EyesMesConstants.Agency.PURCHASING_AGENT.getName()), String.valueOf(EyesMesConstants.purchaser.COMPANY.getName()));
                    }
                    proxy.replaceAll(reg, "");
                } else {
                    proxy = jsonObject1.getString("proxy");
                }
                // 再次数据过滤
                po.setProxy(proxy);
                // 网页内容
                po.setContent(jsonObject1.getString("content"));
                // 创建时间
                try {
                    po.setCreatTime(sdf.parse(sdf.format(new Date())));
                } catch (ParseException e) {
                    log.error("时间转换异常", e);
                }
                // 查询数据是否存在
                QueryWrapper<BiddingInfoPO> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("company", com.getCompanyName());
                queryWrapper.eq("companyId", com.getCompanyId());
                queryWrapper.eq("type", jsonObject1.getString("type"));
                list1.add(po);
                List<BiddingInfoPO> biddingInfoPOS = biddingInfoMapper.selectList(queryWrapper);
                if (biddingInfoPOS.size() > 0) {
                    continue;
                }
                // 天眼查数据插入表
                biddingInfoMapper.insert(po);
            }
            biddingInfoVO.setCompanyMes(list1);
            list.add(biddingInfoVO);
        }
        return list;
    }

    /**
     * 获取采购人/招募人/代理机构
     */
    public String getPurchaser(String content, String s1, String s2) {
        String purchaser = "";
        // 根据采购人和公司去匹配
        int i1 = content.indexOf(s1);
        int i2 = content.indexOf(s2, i1);
        if (i1 >= 0 && i2 >= 0) {
            purchaser = content.substring(i1 + s1.length(), i2 + s2.length());
        }
        return purchaser;
    }

    /**
     * @Description: 需要对传入文本进行过滤, 只获取中文
     * 获取公司
     * @param: content: 需解析文本
     * s:需截取字段
     * @return:
     */
    public String getRelatedCompany(String content) {
        String company = "";
        String s = EyesMesConstants.purchaser.COMPANY_OTHER.getName() + "," + EyesMesConstants.purchaser.COMPANY.getName();
        String[] split = s.split(",");
        int i1;
        for (String s1 : split) {
            for (int j = 0; j < content.length(); j++) {
                // 根据公司去匹配
                int i3 = content.indexOf(s1);
                if (i3 < 0) {
                    break;
                }
                i1 = content.indexOf(s1, (j + s1.length()));
                if (i1 >= 0) {
                    // 逐字查询公司
                    for (int i = 2; i < 49; i++) {
                        if ((i > content.length())) {
                            break;
                        }
                        if ((i1 - i) >= 0) {
                            String com = content.substring(i1 - i, i1 + s1.length());
                            String result = checkCompany(com);
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            String errorCode = jsonObject.getString("error_code");
                            if ("0".equals(errorCode)) {
                                JSONObject result1 = JSONObject.parseObject(jsonObject.getString("result"));
                                String name = result1.getString("name");
                                int i2 = content.indexOf(name);
                                if (i2 >= 0 && name.length() > 4) {
                                    company = company.concat(com).concat(",");
                                }
                            }
                        }
                    }
                }
            }
        }
        // 结果处理
        if (company.length() > 0) {
            company = company.substring(0, company.length() - 1);
        }
        return company;
    }

    /**
     * @Description: 调用天眼查接口验证公司
     * @param:
     * @return:
     */
    public String checkCompany(String company) {
        String eUrl;
        eUrl = url1.concat("keyword=").concat(company);
        return executeGet(eUrl, token);
    }


    /**
     * http get请求
     *
     * @param url   接口url
     * @param token token
     * @return 返回接口数据
     */
    protected static String executeGet(String url, String token) {
        String result = null;
        try {
            HttpHead reqHeader = new HttpHead();
            // 设置header
            reqHeader.setHeader("Authorization", token);
            // 设置类型
            result = HttpHelper.httpGet(url, reqHeader.getAllHeaders());
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }


    @Override
    public void getEyesTitleMes(TitleMesDTO titleMesDTO) {

    }


}
