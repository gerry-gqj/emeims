package com.emeims.service.countService;

import com.emeims.entity.count.PurchaseCount;
import com.emeims.entity.count.SalesCount;
import com.emeims.entity.count.StockCount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CountService {
    List<PurchaseCount> countPurchaseByDay(Map map);
    List<SalesCount> countSalesByDay(Map map);
    List<StockCount> countStockByType();

    List<PurchaseCount> countPurchaseByOperatorSubmit(Map map);
    List<PurchaseCount> countPurchaseByOperatorComfirm(Map map);
    List<PurchaseCount> countPurchaseByOperatorCancel(Map map);

    List<SalesCount> countSalesByOperatorSubmit(Map map);
    List<SalesCount> countSalesByOperatorComfirm(Map map);
    List<SalesCount> countSalesByOperatorCancel(Map map);

}
