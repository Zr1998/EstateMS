package com.hnwlxy.zr.EstateMS.common.model;


import io.swagger.annotations.ApiModelProperty;

public class AdvancedQuery {
    @ApiModelProperty("字段名称")
    private String fieldName;//字段名称

    @ApiModelProperty("关系运算符（>、<、=、like、>=、<=）")
    private String relationOperator;//关系运算符（>、<、=、like、>=、<=）

    @ApiModelProperty("临时关系运算符")
    private String tempOperator;//临时关系运算符

    @ApiModelProperty("字段值")
    private String fieldValue;//字段值

    @ApiModelProperty("字段类别（如果是时间类型需要传date）")
    private String fieldType="string";//字段类别（如果是时间类型需要传date）

    @ApiModelProperty("逻辑运算符（and、or）")
    private String logicalOperator="AND";//逻辑运算符（and、or）

    @ApiModelProperty("排序（升序、降序）")
    private String sort;//排序（升序、降序）

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRelationOperator() {
        return relationOperator;
    }

    public void setRelationOperator(String relationOperator) {
        this.relationOperator = relationOperator;
    }

    public String getTempOperator() {
        return tempOperator;
    }

    public void setTempOperator(String tempOperator) {
        this.tempOperator = tempOperator;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
