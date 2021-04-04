package com.emeims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("电机")
public class Motor {
    @ApiModelProperty("电机编号")
    private Integer motorId;
    @ApiModelProperty("电机型号")
    private String motorCategory;
    @ApiModelProperty("电机规格")
    private String motorModel;
}
