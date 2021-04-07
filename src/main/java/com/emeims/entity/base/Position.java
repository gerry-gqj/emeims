package com.emeims.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("职位实体")
public class Position {
    @ApiModelProperty("职位Id")
    private int postId;
    @ApiModelProperty("职位")
    private String postName;
}
