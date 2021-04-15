package com.ly.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
class ToolsApplicationTests5 {


    static String aa = "{\"result\":{\"total\":\"1\",\"items\":[{\"publishTime\":\"1513872000000\",\"bidUrl\":\"https://m.tianyancha.com/app/h5/bid/ed686cc0d75d11e985737cd30aeb144c\",\"purchaser\":\"\",\"link\":\"http://www.bidizhaobiao.com/info-29748066.html?companyId=27\",\"title\":\"2018年深圳联通“百度”广告投放项目单一来源采购公示\",\"type\":\"中标公告\",\"uuid\":\"ed686cc0d75d11e985737cd30aeb144c\",\"content\":\"<div> \\n <p><span>2018年深圳联通“百度”广告投放项目单一来源采购公示</span></p> \\n <table width=\\\"100%\\\" border=\\\"1\\\" style=\\\"border-collapse:collapse;\\\"> \\n  <tbody> \\n   <tr> \\n    <td><span>招标编号：SZ-ST-17-117 </span></td> \\n    <td></td> \\n   </tr> \\n  </tbody> \\n </table> \\n <hr> \\n <p><span>&nbsp;&nbsp;&nbsp;&nbsp;采购人中国联合网络通信有限公司深圳市分公司的《2018年深圳联通“百度”广告投放项目》（编号：SZ-ST-17-117）拟进行单一来源采购，现将有关事项公示如下：<br>1.采购项目概况：<br>1.1&nbsp;采购内容及主要需求：采购百度SEM搜索引擎营销和DSP精准营销广告<br>1.2&nbsp;预算金额：27万元（不含税）<br>1.3&nbsp;服务时间：1年<br>2.拟定单一来源采购供应商的名称、地址<br>2.1.&nbsp;名称：百度国际科技（深圳）有限公司<br>2.2.&nbsp;地址：深圳市南山区科技园高新中二道5号生产力大楼D单元三层301<br>3.单一来源采购论证情况<br>本项目属于“专业化资源仅唯一供应商可提供的采购”。<br>根据相关信息，百度控股有限公司是百度广告资源的拥有方，百度国际科技（深圳）有限公司是百度控股有限公司全资拥有的子公司，其负责百度的广告销售业务。<br>4.发布公示的媒介<br>本次单一来源采购公示在中国联通采购与招标（www.chinaunicombidding.cn）<br>5.公示期限：<br>自2017年12月22日至2017年12月27日止。任何供应商、单位或个人对采用单一来源方式公示有异议的，可以向采购人以书面形式实名反映。&nbsp;<br>6.联系方式：<br>联系人：林松<br>电话：18676656391<br>电子邮件：ls@szunicom.com<br>深圳联通违规违纪举报联系方式如下：<br>1、电话：（0755）36505874，（0755）36506677<br>2、电子邮箱：gds-szjjjc@chinaunicom.cn（公司纪委监察室）<br>3、信箱：设立在中国联通深圳市分公司联通大厦18楼1803号会议室门口<br>4、寄信地址：深圳市福田区深南大道4005号中国联通深圳市分公司纪委，邮编：518000<br>日期：2017年12月22日 <br></span></p> \\n <div> \\n  <span>发布单位 :深圳联通</span> \\n </div> \\n <p></p> \\n <p></p> \\n <hr> \\n</div>\",\"proxy\":\"\",\"abs\":\"\",\"province\":\"广东\",\"intro\":\"\",\"id\":\"25654089\"}]},\"reason\":\"ok\",\"error_code\":0}";
    // 留下所有汉字
    static String reg = "[^\u4e00-\u9fa5]";

    static String reg1 = "[(a-zA-Z0-9\\\\u4e00-\\\\u9fa5)]";

    static String reg2 = "[^\\u4e00-\\u9fa5^a-z^A-Z^0-9]";


    @Test
    public void testJson1() {
        int i = aa.indexOf("<span>");
        int i1 = aa.indexOf("</span>");

        String jieguo = aa.substring(i, i1);
        System.out.println(jieguo);
    }

    //    @Test
    public static void main(String[] args) {
        String s = "采购人";
        String s2 = "公司";
        String str = aa.replaceAll(reg2, "");
        System.out.println("咚咚咚：" + str);

        String jieguo = str.substring(str.indexOf(s) + s.length(), str.indexOf(s2) + s2.length());
        System.out.println(jieguo);
    }



}
