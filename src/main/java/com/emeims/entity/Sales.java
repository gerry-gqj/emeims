package com.emeims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("销售订单")
public class Sales {

    @ApiModelProperty("采购单号")
    private String salesId;

    @ApiModelProperty("客户")
    private String salesClient;

    @ApiModelProperty("电机类型")
    private String salesMotorType;

    @ApiModelProperty("电机型号")
    private String salesMotorModel;

    @ApiModelProperty("电机数量")
    private Integer salesMotorQuality;

    @ApiModelProperty("电机价格")
    private Float salesMotorPrice;

    @ApiModelProperty("订单总价")
    private Float salesTotalPrice;

    @ApiModelProperty("采购单_操作员_提交")
    private String salesOperatorSubmit;

    @ApiModelProperty("采购单_操作员_完成")
    private String salesOperatorConfirm;

    @ApiModelProperty("采购单_操作员_取消")
    private String salesOperatorCancel;

    @ApiModelProperty("采购单状态")
    private String salesStatus;

    @ApiModelProperty("订单开始时间")
    private Date salesStartTime;

    @ApiModelProperty("订单结束时间")
    private Date salesEndTime;

    @ApiModelProperty("订单取消时间")
    private Date salesReturnTime;

}
