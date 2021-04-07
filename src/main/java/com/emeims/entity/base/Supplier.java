package com.emeims.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("供应商")
public class Supplier {
    @ApiModelProperty("供应商编号")
    private Integer supplierId;
    @ApiModelProperty("供应商")
    private String supplierName;
    @ApiModelProperty("供应商地址")
    private String supplierAddress;
    @ApiModelProperty("供应商邮箱地址")
    private String supplierEmail;


}
