package com.emeims.entity.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesCount {

    //统计库存单的数量
    private Integer countSalesNumber;

    //统计库存单的价格
//    private Float countPurchasePrice;

    //时间统计
    private Date salesDate;

}
