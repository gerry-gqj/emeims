package com.emeims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("库存单")
public class Stock {

    @ApiModelProperty("库存单号")
    private String stockId;
    @ApiModelProperty("电机供应商")
    private String stockSupplier;
    @ApiModelProperty("电机类型")
    private String stockMotorType;
    @ApiModelProperty("电机型号")
    private String stockMotorModel;
    @ApiModelProperty("电机数量")
    private Integer stockMotorQuantity;
    @ApiModelProperty("电机库存价值")
    private Float stockMotorPriceIn;
    @ApiModelProperty("电机销售价格")
    private Float stockMotorPriceOut;
    @ApiModelProperty("订单状态")
    private String stockStatus;
    @ApiModelProperty("操作员_入库")
    private String stockOperatorUp;
    @ApiModelProperty("操作员_出库")
    private String stockOperatorDown;
    @ApiModelProperty("入库时间")
    private String stockUpTime;
    @ApiModelProperty("出库时间")
    private String stockDownTime;

}
