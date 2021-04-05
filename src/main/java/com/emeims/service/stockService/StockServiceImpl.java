package com.emeims.service.stockService;

import com.emeims.dao.StockMapper;
import com.emeims.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService{

    private StockMapper stockMapper;

    @Autowired
    public void setStockMapper(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    @Override
    public void addStock(Map map) {
        stockMapper.addStock(map);
    }

    @Override
    public List<Stock> getAllStock() {
        return stockMapper.getAllStock();
    }

    @Override
    public List<Stock> getStockByInfo(Map map) {
        return stockMapper.getStockByInfo(map);
    }

    @Override
    public void updateStock(Map map) {
        stockMapper.updateStock(map);
    }
}
