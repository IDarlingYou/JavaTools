package com.ly.tools.enums;

/**
 * @Author: LY
 * @Date: 2021/4/14 17:39
 * @Description: 天眼查枚举类
 **/
public class EyesMesConstants {

    public enum purchaser{

        /**
        * 采购
        */
        PURCHASING_AGENT("采购人"),
        Recruit_people("招募人"),
        COMPANY("公司"),
        COMPANY_OTHER("分公司");
        private String name;
        purchaser(String name){
            this.name = name ;
        }
        public String getName(){
            return this.name;
        }
    }

    public enum Agency{

        /**
         * 代理机构
         */
        PURCHASING_AGENT("代理机构");

        private String name;
        Agency(String name){
            this.name = name ;
        }
        public String getName(){
            return this.name;
        }
    }



    public enum label{

        /**
         *   标签
         */
        DIV_TAG("<div>"),
        DIV_TAG_END("</div>"),

        BR_TAG("<br>"),
        BR_TAG_END("</br>"),

        P_TAG("<p>"),
        P_TAG_END("</p>"),

        SPAN_TAG("<span>"),
        SPAN_TAG_END("</span>");

        private String name;
        label(String name){
            this.name = name ;
        }
        public String getName(){
            return this.name;
        }
    }



}
