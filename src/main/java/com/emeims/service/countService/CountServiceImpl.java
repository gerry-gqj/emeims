package com.emeims.service.countService;

import com.emeims.dao.CountMapper;
import com.emeims.entity.count.PurchaseCount;
import com.emeims.entity.count.SalesCount;
import com.emeims.entity.count.StockCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CountServiceImpl implements CountService{

    private CountMapper countMapper;

    @Autowired
    public void setCountMapper(CountMapper countMapper) {
        this.countMapper = countMapper;
    }

    @Override
    public List<PurchaseCount> countPurchaseByDay(Map map) {
        return countMapper.countPurchaseByDay(map);
    }

    @Override
    public List<SalesCount> countSalesByDay(Map map) {
        return countMapper.countSalesByDay(map);
    }

    @Override
    public List<StockCount> countStockByType() {
        return countMapper.countStockByType();
    }

}
