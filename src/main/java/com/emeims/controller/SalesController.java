package com.emeims.controller;

import com.emeims.entity.base.Sales;
import com.emeims.entity.base.Stock;
import com.emeims.service.salesService.SalesServiceImpl;
import com.emeims.service.stockService.StockServiceImpl;
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
    private StockServiceImpl stockService;
    /**
     * [Description]: 使用set注入
     * @param salesService (SalesServiceImpl)
     * */
    @Autowired
    public void setSalesService(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    @Autowired
    public void setStockService(StockServiceImpl stockService) {
        this.stockService = stockService;
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
    public Map addSales(Sales sales,String stockId){

        Map returnMap = new HashMap<>();
        /* 检查订单是否重复, !(return isExist)*/
        Map checkMap = new HashMap<>();
        /* 检查库存是否充足, !(return NotAdequate)*/
        Map checkStockMap = new HashMap<>();

        checkMap.put("salesId",sales.getSalesId());
        List<Sales> salesList = salesService.getSalesByInfo(checkMap);
        System.out.println(salesList);

        checkStockMap.put("stockId",stockId);
        List<Stock> stockList = stockService.getStockByInfo(checkStockMap);
        System.out.println(stockList);

        if(salesList.isEmpty()){
            Integer stockMotorQuantity = stockList.get(0).getStockMotorQuantity();
            Float stockMotorPriceIn = stockList.get(0).getStockMotorPriceIn();
            Float stockMotorAvgPrice = stockList.get(0).getStockMotorAvgPrice();

            Float outStockValue = sales.getSalesMotorQuality()*stockMotorAvgPrice;
            Float stockRemainingValue = stockMotorPriceIn-outStockValue;

            System.out.println("库存剩余价值"+stockRemainingValue);
            Integer salesMotorQuality = sales.getSalesMotorQuality();
            if(stockMotorQuantity.compareTo(salesMotorQuality)>=0){
                Map map = new HashMap<>();
                map.put("salesId",sales.getSalesId());
                map.put("salesClient",sales.getSalesClient());
                map.put("salesMotorSupplier",sales.getSalesMotorSupplier());
                map.put("salesMotorType",sales.getSalesMotorType());
                map.put("salesMotorModel",sales.getSalesMotorModel());
                map.put("salesMotorQuality",sales.getSalesMotorQuality());
                map.put("salesMotorPrice",sales.getSalesMotorPrice());
                map.put("salesOperatorSubmit",sales.getSalesOperatorSubmit());
                Float salesTotalPrice = sales.getSalesMotorPrice() * sales.getSalesMotorQuality();
                map.put("salesTotalPrice",salesTotalPrice);
                map.put("salesStartTime",new Date());
                Map mapStock = new HashMap<>();
                mapStock.put("stockId",stockId);
                mapStock.put("stockMotorQuantity",stockMotorQuantity-salesMotorQuality);
                mapStock.put("stockMotorPriceIn",stockRemainingValue);
//                if (stockMotorQuantity-salesMotorQuality==0){
//                    mapStock.put("stockStatus","已下架");
//                }
                stockService.updateStock(mapStock);
                System.out.println(map);
                salesService.addSales(map);
                returnMap.put("status","ok");

            }else {
                returnMap.put("status","NotAdequate");
            }
        }else {
            returnMap.put("status","isExist");
        }
        return returnMap;
    }

    /* ********************[Method:No.3]: addAllSales()**********************/
    /**
     * 添加订单
     * [Method:No.3]: addAllSales()
     * [Description]: 添加订单
     * [URL]: (Post) http://localhost:9090/sales/addAllSales/
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
    @RequestMapping(value ="/addAllSales",method = RequestMethod.POST)
    public Map addAllSales(Sales sales,String creatorId){

        Map returnMap = new HashMap<>();
        Map checkMap = new HashMap<>();

        String salesId = String.valueOf(new Date().getTime())+creatorId;
        checkMap.put("salesId",salesId);
        List<Sales> salesList = salesService.getSalesByInfo(checkMap);
        if(salesList.isEmpty()){
            Map map = new HashMap<>();
            map.put("salesId",salesId);
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


    /* ********************[Method:No.4]: allSales()**********************/
    /**
     * [Method:No.4]: allSales()
     * [Description]: 查询所有销售订单
     * [URL]: (Post) http://localhost:9090/sales/allSales
     * @return list List(Sales)
     * */
    @ApiOperation("Description: 查询所有销售订单入口")
    @RequestMapping(value = "/allSales",method = RequestMethod.GET)
    public List<Sales> allSales(){
        return salesService.getAllSales();
    }


    /* ********************[Method:No.5]: getSalesByInfo()**********************/
    /**
     * 获取订单信息
     * [Method:No.5]: getSalesByInfo()
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

    /* ********************[Method:No.6]: updateSales()**********************/
    /**
     * [Method:No.6]: updateSales()
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
    public Map updateSales(Sales sales, String supplier, String type,String model){

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


            Map mapStockCheck = new HashMap<>();
                mapStockCheck.put("stockSupplier",supplier);
                mapStockCheck.put("stockMotorType",type);
                mapStockCheck.put("stockMotorModel",model);

            List<Stock> stockList = stockService.getStockByInfo(mapStockCheck);
            String stockId = stockList.get(0).getStockId();
            Float stockMotorPriceIn = stockList.get(0).getStockMotorPriceIn();
            Integer motorQuantity = stockList.get(0).getStockMotorQuantity();

            Integer salesMotorQuality = sales.getSalesMotorQuality();
            Float salesTotalPrice = sales.getSalesTotalPrice();

            Map mapUpdateStock = new HashMap<>();
                mapUpdateStock.put("stockId",stockId);
                mapUpdateStock.put("stockMotorQuantity",motorQuantity+salesMotorQuality);
                mapUpdateStock.put("salesTotalPrice",stockMotorPriceIn+salesTotalPrice);

            System.out.println(stockMotorPriceIn+salesTotalPrice);

            stockService.updateStock(mapUpdateStock);

            mapReturn.put("status","cancel success");
        }else {
            mapReturn.put("status","error");
        }
        salesService.updateSales(map);
        return mapReturn;
    }

}
