package com.hnwlxy.zr.EstateMS.common.model;


import io.swagger.annotations.ApiModelProperty;

public class QueryParams {
    @ApiModelProperty("当前页码")
    private Integer curr_page=1;
    @ApiModelProperty("每页显示的记录数")
    private Integer page_size=10;
    @ApiModelProperty("查询条件")
    private String where="";
    @ApiModelProperty("排序")
    private String order;

        public Integer getCurr_page() {
            return curr_page;
        }

        public void setCurr_page(Integer curr_page) {
            this.curr_page = curr_page;
        }

        public Integer getPage_size() {
            return page_size;
        }

        public void setPage_size(Integer page_size) {
            this.page_size = page_size;
        }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

