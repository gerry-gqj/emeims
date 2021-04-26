package com.emeims.controller;

import com.emeims.entity.count.PurchaseCount;
import com.emeims.entity.count.SalesCount;
import com.emeims.entity.count.StockCount;
import com.emeims.service.countService.CountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/count")
public class CountController {

    private CountServiceImpl countService;

    @Autowired
    public void setCountService(CountServiceImpl countService) {
        this.countService = countService;
    }

    private Map dateSelector(){
        Integer past = 7;
        Date thisDate = new Date();

        //日历实例
        Calendar instance = Calendar.getInstance();
        //设置日历的值
        instance.setTime(thisDate);
        //想要更改的值
        instance.set(Calendar.DATE,instance.get(Calendar.DATE)-past);

        Date startDate = instance.getTime();

        instance.set(Calendar.DATE,instance.get(Calendar.DATE)+past+1);
        Date time = instance.getTime();

        String today = new SimpleDateFormat("yyyy-MM-dd").format(time);
        String sD = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

        Map map = new HashMap<>();
        map.put("startTime",sD);
        map.put("endTime",today);
        return map;
    }

    @RequestMapping(value = "/countPurchaseByDay",method = RequestMethod.GET)
    public List<PurchaseCount> countPurchaseByDay(){
        Map map = dateSelector();
        return countService.countPurchaseByDay(map);
    }


    @RequestMapping(value = "/countSalesByDay",method = RequestMethod.GET)
    public List<SalesCount> countSalesByDay(){
        Map map = dateSelector();
        return countService.countSalesByDay(map);
    }

    @RequestMapping(value = "/countStockByType",method = RequestMethod.GET)
    public List<StockCount> countStockByType(){
        return countService.countStockByType();
    }



    @RequestMapping(value = "/countOrderByOperator",method = RequestMethod.POST)
    public Map countOrderByOperator(String operator){

        Map map = dateSelector();
        map.put("operator",operator);
        List<PurchaseCount> countPurchaseByOperatorSubmit = countService.countPurchaseByOperatorSubmit(map);
        List<PurchaseCount> countPurchaseByOperatorComfirm = countService.countPurchaseByOperatorComfirm(map);
        List<PurchaseCount> countPurchaseByOperatorCancel = countService.countPurchaseByOperatorCancel(map);

        List<SalesCount> countSalesByOperatorSubmit = countService.countSalesByOperatorSubmit(map);
        List<SalesCount> countSalesByOperatorComfirm = countService.countSalesByOperatorComfirm(map);
        List<SalesCount> countSalesByOperatorCancel = countService.countSalesByOperatorCancel(map);

        Map returnMap = new HashMap<>();
        returnMap.put("countPurchaseByOperatorSubmit",countPurchaseByOperatorSubmit);
        returnMap.put("countPurchaseByOperatorComfirm",countPurchaseByOperatorComfirm);
        returnMap.put("countPurchaseByOperatorCancel",countPurchaseByOperatorCancel);

        returnMap.put("countSalesByOperatorSubmit",countSalesByOperatorSubmit);
        returnMap.put("countSalesByOperatorComfirm",countSalesByOperatorComfirm);
        returnMap.put("countSalesByOperatorCancel",countSalesByOperatorCancel);

        return returnMap;
    }

}
