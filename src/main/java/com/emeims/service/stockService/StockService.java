package com.emeims.service.stockService;

import com.emeims.entity.base.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StockService {

    /**
     * @param map
     * 库存添加
     * */
    void addStock(Map map);


    /**
     * @return List
     * 所有库存
     * */
    List<Stock> getAllStock();

    /**
     * @param map
     * @return List
     * 库存查询
     * */
    List<Stock> getStockByInfo(Map map);


    /**
     * @param map
     * 更改库存
     * */
    void updateStock(Map map);

}
