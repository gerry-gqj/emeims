package com.emeims.service.SalesService;

import com.emeims.dao.SalesMapper;
import com.emeims.entity.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService{

    private SalesMapper salesMapper;

    @Autowired
    public void setSalesMapper(SalesMapper salesMapper) {
        this.salesMapper = salesMapper;
    }

    @Override
    public void addSales(Map map) {
        salesMapper.addSales(map);
    }

    @Override
    public List<Sales> getAllSales() {
        return salesMapper.getAllSales();
    }

    @Override
    public List<Sales> getSalesByInfo(Map map) {
        return salesMapper.getSalesByInfo(map);
    }

    @Override
    public void updateSales(Map map) {
        salesMapper.updateSales(map);
    }
}
