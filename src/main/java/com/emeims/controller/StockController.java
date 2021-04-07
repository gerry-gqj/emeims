package com.emeims.controller;

import com.emeims.entity.base.Stock;
import com.emeims.service.stockService.StockServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockController {

    private StockServiceImpl stockService;

    @Autowired
    public void setStockService(StockServiceImpl stockService) {
        this.stockService = stockService;
    }


//    /* ********************[Method:No.1]: createStockId()**********************/
//    /**
//     * [Method:No.1]: createStockId()
//     * [Description]: 销售订单号创建
//     * [URL]: (Post) http://localhost:9090/stock/createStockId/
//     * @return map(saleId) (Map)
//     * */
//    @ApiOperation("Description: 订单号创建入口")
//    @RequestMapping(value ="/createStockId",method = RequestMethod.POST)
//    public Map createStockId(String creatorId){
//        Map map = new HashMap<>();
//        map.put("stockId",new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
//                .format(new Date()).toString()
//                .replaceAll("-","")+creatorId);
//        return map;
//    }
//
//
//    /* ********************[Method:No.2]: addStock()**********************/
//    /**
//     * 添加订单
//     * [Method:No.2]: addStock()
//     * [Description]: 添加订单
//     * [URL]: (Post) http://localhost:9090/stock/addStock/
//     * @param stock Object[
//     *              stockId;
//     *              stockClient;
//     *              stockMotorType;
//     *              stockMotorModel;
//     *              stockMotorQuality;
//     *              stockMotorPrice;
//     *              stockTotalPrice;
//     *              stockOperatorSubmit;
//     *              stockStartTime;
//     *              ]
//     * @return map(stockId) (Map)
//     * */
//    @ApiOperation("Description: 销售订单添加入口")
//    @RequestMapping(value ="/addStock",method = RequestMethod.POST)
//    public Map addStock(Stock stock){
//
//        Map returnMap = new HashMap<>();
//        Map checkMap = new HashMap<>();
//        checkMap.put("stockId",stock.getStockId());
//        List<Stock> stockList = stockService.getStockByInfo(checkMap);
//        if(stockList.isEmpty()){
//            Map map = new HashMap<>();
//            map.put("stockId",stock.getStockId());
//            map.put("stockClient",stock.getStockClient());
//            map.put("stockMotorType",stock.getStockMotorType());
//            map.put("stockMotorModel",stock.getStockMotorModel());
//            map.put("stockMotorQuality",stock.getStockMotorQuality());
//            map.put("stockMotorPrice",stock.getStockMotorPrice());
//            map.put("stockOperatorSubmit",stock.getStockOperatorSubmit());
//            Float stockTotalPrice = stock.getStockMotorPrice() * stock.getStockMotorQuality();
//            map.put("stockTotalPrice",stockTotalPrice);
//            map.put("stockStartTime",new Date());
//            stockService.addStock(map);
//            System.out.println(map);
//            returnMap.put("status","ok");
//        }else {
//            returnMap.put("status","isExist");
//        }
//        return returnMap;
//    }
//
    /* ********************[Method:No.1]: allStock()**********************/
    /**
     * [Method:No.1]: allStock()
     * [Description]: 查询所有库存
     * [URL]: (Post) http://localhost:9090/stock/allStock
     * @return list List(Stock)
     * */
    @ApiOperation("Description: 查询所有库存入口")
    @RequestMapping(value = "/allStock",method = RequestMethod.GET)
    public List<Stock> allStock(){
        return stockService.getAllStock();
    }


    /* ********************[Method:No.4]: getStockByInfo()**********************/
    /**
     * 获取订单信息
     * [Method:No.4]: getStockByInfo()
     * [Description]: 获取订单信息
     * [URL]: (Post) http://localhost:9090/stock/getStockByInfo
     * @param stock Object[
     *              stockId;
     *              stockClient;
     *              stockStatus;
     *              ]
     * @return map(stockId) (Map)
     * */
    @ApiOperation("Description: 获取库存信息入口")
    @RequestMapping(value = "/getStockByInfo",method = RequestMethod.POST)
    public List<Stock> getStockByInfo(Stock stock){
        System.out.println(stock);
        Map map = new HashMap<>();
        map.put("stockId",stock.getStockId());
        map.put("stockSupplier",stock.getStockSupplier());
        map.put("stockStatus",stock.getStockStatus());
        map.put("stockMotorType",stock.getStockMotorType());
        map.put("stockMotorModel",stock.getStockMotorModel());
        return stockService.getStockByInfo(map);
    }

    /* ********************[Method:No.5]: updateStock()**********************/
    /**
     * [Method:No.5]: updateStock()
     * [Description]: 更新销售订单信息
     * [URL]: (Post) http://localhost:9090/stock/updateStock
     * @param stock Object[
     *              stockId;
     *              stockStatus;
     *              stockOperatorConfirm or stockOperatorCancel
     *              ]
     * @return map(stockId) (Map)
     * */
    @ApiOperation("Description: 更新库存入口")
    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public Map updateStock(Stock stock){

        Map mapReturn = new HashMap<>();
        Map map = new HashMap<>();
        map.put("stockId",stock.getStockId());
        map.put("stockStatus",stock.getStockStatus());
        if(stock.getStockStatus().equals("已上架")){
            map.put("stockUpTime",new Date());
            map.put("stockStatus",stock.getStockStatus());
            map.put("stockOperatorUp",stock.getStockOperatorUp());
            map.put("stockMotorPriceOut",stock.getStockMotorPriceOut());
            mapReturn.put("status","up success");
        }else if(stock.getStockStatus().equals("已下架")){
            map.put("stockDownTime",new Date());
            map.put("stockStatus",stock.getStockStatus());
            map.put("stockOperatorDown",stock.getStockOperatorDown());
            mapReturn.put("status","down success");
        }else {
            mapReturn.put("status","error");
        }
        stockService.updateStock(map);
        return mapReturn;
    }
}
