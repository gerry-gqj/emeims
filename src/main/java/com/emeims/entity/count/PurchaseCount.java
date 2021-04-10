package com.emeims.entity.count;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseCount {

    //统计基本属性
//    @ApiModelProperty("供应商")
//    private String purchaseSupplier;
//
//    @ApiModelProperty("电机类型")
//    private String purchaseMotorType;
//
//    @ApiModelProperty("电机型号")
//    private String purchaseMotorModel;

    //统计实体属性,用于聚合函数来接收数据

    //统计采购单的数量
    private Integer countPurchaseNumber;

    //统计库存单的价格
//    private Float countPurchasePrice;

    //时间统计
    private Date purchaseDate;

}
