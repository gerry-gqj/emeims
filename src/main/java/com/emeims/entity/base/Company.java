package com.emeims.entity.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("公司实体")
public class Company {
    @ApiModelProperty("公司Id")
    private int companyId;
    @ApiModelProperty("公司名")
    private String companyName;
    @ApiModelProperty("公司地址")
    private String companyAddress;
    @ApiModelProperty("公司邮箱地址")
    private String companyEmail;
}
