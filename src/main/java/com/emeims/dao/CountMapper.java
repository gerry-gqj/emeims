package com.emeims.dao;

import com.emeims.entity.count.PurchaseCount;
import com.emeims.entity.count.SalesCount;
import com.emeims.entity.count.StockCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CountMapper {

    /**
     * 计算每天完成的订单数,时间区域(七天内)
     * */
    List<PurchaseCount> countPurchaseByDay(Map map);

    List<SalesCount> countSalesByDay(Map map);

    List<StockCount> countStockByType();


}
