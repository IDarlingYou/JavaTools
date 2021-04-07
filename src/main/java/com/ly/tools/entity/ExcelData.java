package com.ly.tools.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
public class ExcelData implements Serializable {

    /**
     * 账期
     */
    private String paymentDays;

    /**
     * yiji表头
     */
    private List<String> titles;

    /**
     * erji表头
     */
    private List<String> erji;

    /**
     * sanji表头
     */
    private List<String> sanji;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * 页面标签
     */
    private String name;

}
