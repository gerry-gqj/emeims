package com.emeims.service.salesService;

import com.emeims.entity.base.Sales;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SalesService {

    /**
     * @param map
     * 添加订单
     * */
    void addSales(Map map);


    /**
     * 没有订单删除,可以通过改变订单转态为(已取消)状态,以此达到同样效果,订单一旦提交就无法删除,永久存在于数据库中
     * */

    /**
     * @return List
     * 查询所有订单
     * */
    List<Sales> getAllSales();

    /**
     * @param map
     * @return List
     * 模糊查询订单
     * */
    List<Sales> getSalesByInfo(Map map);


    /**
     * @param map
     * 更改订单信息(状态信息)
     * */
    void updateSales(Map map);

}
