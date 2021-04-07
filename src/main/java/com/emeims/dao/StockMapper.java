package com.emeims.dao;

import com.emeims.entity.base.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StockMapper {

    /* ***********************[Method(No.1)] addStock()******************************/
    /**
     * [Method(No.1)] addStock()
     * [Description]: 添加新的库存
     * @param map
     *
     * */
    void addStock(Map map);


    /* ***********************[Method(No.2)] getAllStock()******************************/
    /**
     * [Method(No.2)] getAllStock()
     * [Description]: 获取所有库存信息
     * @return List
     * 查询所有订单
     * */
    List<Stock> getAllStock();


    /* ***********************[Method(No.3)] getStockByInfo()******************************/
    /**
     * [Method(No.3)] getStockByInfo()
     * [Description]: 查询库存信息
     * @param map
     * @return List<Stock>
     * 模糊查询订单
     * */
    List<Stock> getStockByInfo(Map map);


    /* ***********************[Method(No.4)] getStockByInfo()******************************/
    /**
     * [Method(No.4)] updateStock()
     * [Description]: 查询库存信息
     * @param map
     * 更改订单信息(状态信息)
     * */
    void updateStock(Map map);

}
