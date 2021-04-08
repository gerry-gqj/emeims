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

    @RequestMapping(value = "/countPurchaseByDay",method = RequestMethod.GET)
    public List<PurchaseCount> countPurchaseByDay(){
        Integer past = 7;
        Date thisDate = new Date();

        //日历实例
        Calendar instance = Calendar.getInstance();
        //设置日历的值
        instance.setTime(thisDate);
        //想要更改的值
        instance.set(Calendar.DATE,instance.get(Calendar.DATE)-past);

        Date startDate = instance.getTime();

        String today = new SimpleDateFormat("yyyy-MM-dd").format(thisDate);
        String sD = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

        Map map = new HashMap<>();
            map.put("startTime",sD);
            map.put("endTime",today);
        return countService.countPurchaseByDay(map);
    }


    @RequestMapping(value = "/countSalesByDay",method = RequestMethod.GET)
    public List<SalesCount> countSalesByDay(){
        Integer past = 7;
        Date thisDate = new Date();

        //日历实例
        Calendar instance = Calendar.getInstance();
        //设置日历的值
        instance.setTime(thisDate);
        //想要更改的值
        instance.set(Calendar.DATE,instance.get(Calendar.DATE)-past);

        Date startDate = instance.getTime();

        String today = new SimpleDateFormat("yyyy-MM-dd").format(thisDate);
        String sD = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

        Map map = new HashMap<>();
            map.put("startTime",sD);
            map.put("endTime",today);
        return countService.countSalesByDay(map);
    }


    @RequestMapping(value = "/countStockByType",method = RequestMethod.GET)
    public List<StockCount> countStockByType(){
        return countService.countStockByType();
    }




}
