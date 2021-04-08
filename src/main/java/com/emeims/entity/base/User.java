package com.emeims.entity.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体")
public class User {
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("邮箱地址")
    private String userEmail;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String userPassword;
    @ApiModelProperty("性别")
    private String userGender;
    @ApiModelProperty("帐号状态")
    private String userStatus;

    private Integer companyId;
    private Integer positionId;

    private Position position;
    private Company company;

    private Integer userNumber;
}
