package com.emeims.service.purchaseService;

import com.emeims.dao.PurchaseMapper;
import com.emeims.entity.base.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private PurchaseMapper purchaseMapper;

    @Autowired
    public void setPurchaseMapper(PurchaseMapper purchaseMapper) {
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public void addPurchase(Map map) {
        purchaseMapper.addPurchase(map);
    }

    @Override
    public List<Purchase> getAllPurchase() {
        return purchaseMapper.getAllPurchase();
    }

    @Override
    public List<Purchase> getPurchaseByInfo(Map map) {
        return purchaseMapper.getPurchaseByInfo(map);
    }

    @Override
    public void updatePurchase(Map map) {
        purchaseMapper.updatePurchase(map);
    }
}
