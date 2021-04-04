package com.emeims.dao;

import com.emeims.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PurchaseMapper {


    /**
     * @param map
     * 添加订单
     * */
    void addPurchase(Map map);


    /**
     * 没有订单删除,可以通过改变订单转态为(已取消)状态,以此达到同样效果,订单一旦提交就无法删除,永久存在于数据库中
     * */

    /**
     * @return List
     * 查询所有订单
     * */
    List<Purchase> getAllPurchase();

    /**
     * @param map
     * @return List
     * 模糊查询订单
     * */
    List<Purchase> getPurchaseByInfo(Map map);


    /**
     * @param map
     * 更改订单信息(状态信息)
     * */
    void updatePurchase(Map map);


}
