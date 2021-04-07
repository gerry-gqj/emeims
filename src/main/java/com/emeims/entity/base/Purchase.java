package com.emeims.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("采购单")
public class Purchase {

    @ApiModelProperty("采购单号")
    private String purchaseId;

    @ApiModelProperty("供应商")
    private String purchaseSupplier;

    @ApiModelProperty("电机类型")
    private String purchaseMotorType;

    @ApiModelProperty("电机型号")
    private String purchaseMotorModel;

    @ApiModelProperty("电机数量")
    private Integer purchaseMotorQuality;

    @ApiModelProperty("电机价格")
    private Float purchaseMotorPrice;

    @ApiModelProperty("订单总价")
    private Float purchaseTotalPrice;

    @ApiModelProperty("采购单_操作员_提交")
    private String purchaseOperatorSubmit;

    @ApiModelProperty("采购单_操作员_完成")
    private String purchaseOperatorConfirm;

    @ApiModelProperty("采购单_操作员_取消")
    private String purchaseOperatorCancel;

    @ApiModelProperty("采购单状态")
    private String purchaseStatus;

    @ApiModelProperty("订单开始时间")
    private Date purchaseStartTime;

    @ApiModelProperty("订单结束时间")
    private Date purchaseEndTime;

    @ApiModelProperty("订单取消时间")
    private Date purchaseReturnTime;

}
