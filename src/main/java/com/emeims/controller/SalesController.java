package com.emeims.controller;

import com.emeims.entity.Sales;
import com.emeims.service.salesService.SalesServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SalesController {

    /**
     * 用户API,用户注册登录,查询更改用户的信息
     * swagger API测试
     *http://localhost:9090/swagger-ui.html#/
     */
    private SalesServiceImpl salesService;
    /**
     * [Description]: 使用set注入
     * @param salesService (SalesServiceImpl)
     * */
    @Autowired
    public void setSalesService(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    /* ********************[Method:No.1]: createSalesId()**********************/
    /**
     * [Method:No.1]: createSalesId()
     * [Description]: 销售订单号创建
     * [URL]: (Post) http://localhost:9090/sales/createSalesId/
     * @return map(saleId) (Map)
     * */
    @ApiOperation("Description: 订单号创建入口")
    @RequestMapping(value ="/createSalesId",method = RequestMethod.POST)
    public Map createSalesId(String creatorId){
        Map map = new HashMap<>();
        map.put("salesId",new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
                .format(new Date()).toString()
                .replaceAll("-","")+creatorId);
        return map;
    }


    /* ********************[Method:No.2]: addSales()**********************/
    /**
     * 添加订单
     * [Method:No.2]: addSales()
     * [Description]: 添加订单
     * [URL]: (Post) http://localhost:9090/sales/addSales/
     * @param sales Object[
     *              salesId;
     *              salesClient;
     *              salesMotorType;
     *              salesMotorModel;
     *              salesMotorQuality;
     *              salesMotorPrice;
     *              salesTotalPrice;
     *              salesOperatorSubmit;
     *              salesStartTime;
     *              ]
     * @return map(salesId) (Map)
     * */
    @ApiOperation("Description: 销售订单添加入口")
    @RequestMapping(value ="/addSales",method = RequestMethod.POST)
    public Map addSales(Sales sales){

        Map returnMap = new HashMap<>();
        Map checkMap = new HashMap<>();
        checkMap.put("salesId",sales.getSalesId());
        List<Sales> salesList = salesService.getSalesByInfo(checkMap);
        if(salesList.isEmpty()){
            Map map = new HashMap<>();
            map.put("salesId",sales.getSalesId());
            map.put("salesClient",sales.getSalesClient());
            map.put("salesMotorType",sales.getSalesMotorType());
            map.put("salesMotorModel",sales.getSalesMotorModel());
            map.put("salesMotorQuality",sales.getSalesMotorQuality());
            map.put("salesMotorPrice",sales.getSalesMotorPrice());
            map.put("salesOperatorSubmit",sales.getSalesOperatorSubmit());
            Float salesTotalPrice = sales.getSalesMotorPrice() * sales.getSalesMotorQuality();
            map.put("salesTotalPrice",salesTotalPrice);
            map.put("salesStartTime",new Date());
            salesService.addSales(map);
            System.out.println(map);
            returnMap.put("status","ok");
        }else {
            returnMap.put("status","isExist");
        }
        return returnMap;
    }


    /* ********************[Method:No.3]: allSales()**********************/
    /**
     * [Method:No.3]: allSales()
     * [Description]: 查询所有销售订单
     * [URL]: (Post) http://localhost:9090/sales/allSales
     * @return list List(Sales)
     * */
    @ApiOperation("Description: 查询所有销售订单入口")
    @RequestMapping(value = "/allSales",method = RequestMethod.GET)
    public List<Sales> allSales(){
        return salesService.getAllSales();
    }


    /* ********************[Method:No.4]: getSalesByInfo()**********************/
    /**
     * 获取订单信息
     * [Method:No.4]: getSalesByInfo()
     * [Description]: 获取订单信息
     * [URL]: (Post) http://localhost:9090/sales/getSalesByInfo
     * @param sales Object[
     *              salesId;
     *              salesClient;
     *              salesStatus;
     *              ]
     * @param salesStartTimeFrom (Date)
     * @param salesStartTimeTo (Date)
     * @param salesEndTimeFrom (Date)
     * @param salesEndTimeTo (Date)
     * @param salesReturnTimeFrom (Date)
     * @param salesReturnTimeTo (Date)
     * @return map(salesId) (Map)
     * */
    @ApiOperation("Description: 获取订单信息入口")
    @RequestMapping(value = "/getSalesByInfo",method = RequestMethod.POST)
    public List<Sales> getSalesByInfo(Sales sales,
                                      Date salesStartTimeFrom,
                                      Date salesStartTimeTo,
                                      Date salesEndTimeFrom,
                                      Date salesEndTimeTo,
                                      Date salesReturnTimeFrom,
                                      Date salesReturnTimeTo){
        Map map = new HashMap<>();
        map.put("salesId",sales.getSalesId());
        map.put("salesClient",sales.getSalesClient());
        map.put("salesStatus",sales.getSalesStatus());

        map.put("salesOperatorSubmit",sales.getSalesOperatorSubmit());
        map.put("salesOperatorConfirm",sales.getSalesOperatorConfirm());
        map.put("salesOperatorCancel",sales.getSalesOperatorCancel());

        map.put("salesStartTimeFrom",salesStartTimeFrom);
        map.put("salesStartTimeTo",salesStartTimeTo);
        map.put("salesEndTimeFrom",salesEndTimeFrom);
        map.put("salesEndTimeTo",salesEndTimeTo);
        map.put("salesReturnTimeFrom",salesReturnTimeFrom);
        map.put("salesReturnTimeTo",salesReturnTimeTo);

        return salesService.getSalesByInfo(map);
    }

    /* ********************[Method:No.5]: updateSales()**********************/
    /**
     * [Method:No.5]: updateSales()
     * [Description]: 更新销售订单信息
     * [URL]: (Post) http://localhost:9090/sales/updateSales
     * @param sales Object[
     *              salesId;
     *              salesStatus;
     *              salesOperatorConfirm or salesOperatorCancel
     *              ]
     *
     * @return map(salesId) (Map)
     * */
    @ApiOperation("Description: 更新销售订单信息入口")
    @RequestMapping(value = "/updateSales", method = RequestMethod.POST)
    public Map updateSales(Sales sales){

        Map mapReturn = new HashMap<>();
        Map map = new HashMap<>();
        map.put("salesId",sales.getSalesId());
        map.put("salesStatus",sales.getSalesStatus());
        if(sales.getSalesStatus().equals("已完成")){
            map.put("salesEndTime",new Date());
            map.put("salesStatus",sales.getSalesStatus());
            map.put("salesOperatorConfirm",sales.getSalesOperatorConfirm());
            mapReturn.put("status","complete success");
        }else if(sales.getSalesStatus().equals("已取消")){
            map.put("salesReturnTime",new Date());
            map.put("salesStatus",sales.getSalesStatus());
            map.put("salesOperatorCancel",sales.getSalesOperatorCancel());
            mapReturn.put("status","cancel success");
        }else {
            mapReturn.put("status","error");
        }
        salesService.updateSales(map);
        return mapReturn;
    }

}
