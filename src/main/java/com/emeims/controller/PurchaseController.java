package com.emeims.controller;

import com.emeims.entity.Purchase;
import com.emeims.entity.Stock;
import com.emeims.service.purchaseService.PurchaseService;
import com.emeims.service.purchaseService.PurchaseServiceImpl;
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


/**
 * [Description]: swagger API测试
 * [URL]:http://localhost:9090/swagger-ui.html#/
 */

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private PurchaseServiceImpl purchaseService;
    private StockServiceImpl stockService;
    /**
     * [Description]: 使用set注入
     * @param purchaseService (PurchaseServiceImpl)
     * */
    @Autowired
    public void setPurchaseService(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Autowired
    public void setStockService(StockServiceImpl stockService) {
        this.stockService = stockService;
    }

    /* ********************[Method:No.1]: createPurchaseId()**********************/
    /**
     * [Method:No.1]: createPurchaseId()
     * [Description]: 采购订单号创建
     * [URL]: (Post) http://localhost:9090/purchase/createPurchaseId/
     * @return map(purchaseID) (Map)
     * */
    @ApiOperation("Description: 采购订单号创建入口")
    @RequestMapping(value ="/createPurchaseId",method = RequestMethod.POST)
    public Map createPurchaseId(String creatorId){
        Map map = new HashMap<>();
            map.put("purchaseId",new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
                    .format(new Date()).toString()
                    .replaceAll("-","")+creatorId);
        return map;
    }


    /* ********************[Method:No.2]: addPurchase()**********************/
    /**
     * [Method:No.2] addPurchase()
     * [Description]: 订单添加
     * [URL]: (POST) http://localhost:9090/purchase/addPurchase/
     * @param purchase Object[
     *                 purchaseId;
     *                 supplier;
     *                 purchaseMotorType;
     *                 purchaseMotorModel;
     *                 purchaseMotorQuality;
     *                 purchaseMotorPrice;
     *                 purchaseTotalPrice;
     *                 operator;
     *                 purchaseStatus;
     *                 purchaseStartTime;
     *                 purchaseEndTime;
     *                 purchaseReturnTime;
     *                 ]
     * @return returnMap(status) (Map)
     * */
    @ApiOperation("Description: 采购订单添加入口")
    @RequestMapping(value ="/addPurchase",method = RequestMethod.POST)
    public Map addPurchase(Purchase purchase){
        /* 数据库设计
         * 1. 不设计数据库函数
         * 2. 数据统计由后台处理完成后再插入数据库里面
         * 3. 前端创建的订单统计的只是假数据, 真正的数据是在后台统计完成后存放在数据库里面
         * 4. 用户一旦创建订单后, 所有数据都无法更改, 用户只能看到请求到的数据
         * */
        Map returnMap = new HashMap<>();
        /* 添加订单之前：判断数据库中是否存在次此订单号
         * true:@return(Map returnMap("status",""inExist))
         * false:@return(Map returnMap("status","success"))
         * */
        Map checkMap = new HashMap<>();
            checkMap.put("purchaseId",purchase.getPurchaseId());
        List<Purchase> purchaseList = purchaseService.getPurchaseByInfo(checkMap);

        /* 查询为空判断*/
        if (purchaseList.isEmpty()){
            /* 采用Map对象赋值,直接越过实体类*/
            Map map = new HashMap<>();
            /* 数据库变量赋值(Map), key(purchaseMapper.xml),value(@param入口参数和)*/
            map.put("purchaseId",purchase.getPurchaseId());
            map.put("purchaseSupplier",purchase.getPurchaseSupplier());
            map.put("purchaseOperatorSubmit",purchase.getPurchaseOperatorSubmit());
            map.put("purchaseMotorType",purchase.getPurchaseMotorType());
            map.put("purchaseMotorModel",purchase.getPurchaseMotorModel());
            map.put("purchaseMotorQuality",purchase.getPurchaseMotorQuality());
            map.put("purchaseMotorPrice",purchase.getPurchaseMotorPrice());
            /* 订单价格统计*/
            Float purchase_total_price=purchase.getPurchaseMotorPrice()* purchase.getPurchaseMotorQuality();
            map.put("purchaseTotalPrice",purchase_total_price);
            /* 自动生成当前时间
             * 时间格式化在application.yaml已经自动配置好
             * date-format: yyyy-MM-dd HH:mm:ss
             * */
            map.put("purchaseStartTime",new Date());
            /* 调用ModelService执行数据库命令*/
            purchaseService.addPurchase(map);
            System.out.println(map);
            returnMap.put("status","ok");
        }else {
            returnMap.put("status","isExist");
        }
        return returnMap;
    }


    /* ********************[Method:No.3]: allPurchase()**********************/
    /**
     * [Method:No.3] allPurchase()
     * [Description]: 查询所有订单
     * [URL] (GET) http://localhost:9090/purchase/allPurchase/
     * @return list (List<Purchase>)
     * */
    @ApiOperation("(GET) Description: 查询所有订单入口")
    @RequestMapping(value = "/allPurchase",method = RequestMethod.GET)
    public List<Purchase> allPurchase(){
        return purchaseService.getAllPurchase();
    }


    /* ********************[Method:No.4]: getPurchaseByInfo()**********************/
    /**
     * [Method:No.4] getPurchaseByInfo()
     * [Description]: 获取订单信息
     * [URL] (POST) http://localhost:9090/purchase/getPurchaseByInfo
     * @param purchase Object[
     *                  purchaseId;
     *                  supplier;
     *                  purchaseStatus;
     *                 ]
     * @param purchaseStartTimeFrom (Date)
     * @param purchaseStartTimeTo (Date)
     * @param purchaseEndTimeFrom (Date)
     * @param purchaseEndTimeTo (Date)
     * @param purchaseReturnTimeFrom (Date)
     * @param purchaseReturnTimeTo (Date)
     * @return list (List<Purchase>)
     * */
    @ApiOperation("Description: 订单查询入口")
    @RequestMapping(value = "/getPurchaseByInfo/",method = RequestMethod.POST)
    public List<Purchase> getPurchaseByInfo(Purchase purchase,
                                            Date purchaseStartTimeFrom,
                                            Date purchaseStartTimeTo,
                                            Date purchaseEndTimeFrom,
                                            Date purchaseEndTimeTo,
                                            Date purchaseReturnTimeFrom,
                                            Date purchaseReturnTimeTo){
        Map map = new HashMap<>();
            map.put("purchaseId",purchase.getPurchaseId());
            map.put("purchaseSupplier",purchase.getPurchaseSupplier());
            map.put("purchaseStatus",purchase.getPurchaseStatus());

            map.put("purchaseOperatorSubmit",purchase.getPurchaseOperatorSubmit());
            map.put("purchaseOperatorConfirm",purchase.getPurchaseOperatorConfirm());
            map.put("purchaseOperatorCancel",purchase.getPurchaseOperatorCancel());

            map.put("purchaseStartTimeFrom",purchaseStartTimeFrom);
            map.put("purchaseStartTimeTo",purchaseStartTimeTo);
            map.put("purchaseEndTimeFrom",purchaseEndTimeFrom);
            map.put("purchaseEndTimeTo",purchaseEndTimeTo);
            map.put("purchaseReturnTimeFrom",purchaseReturnTimeFrom);
            map.put("purchaseReturnTimeTo",purchaseReturnTimeTo);
        System.out.println(map);
        return purchaseService.getPurchaseByInfo(map);
    }




    /* ********************[Method:No.5]: updatePurchase()**********************/
    /**
     * [Method:No.5] updatePurchase()
     * [Description]: 更新订单信息 (purchaseStatus)=>{"已完成";"已取消"}
     * [URL] http://localhost:9090/purchase/updatePurchase/
     * @param purchase Object[
     *                  purchaseId;
     *                  purchaseStatus;
     *                  purchaseOperatorConfirm or purchaseOperatorCancel
     *                 ]
     * @return mapReturn(status) (Map)
     * */
    @ApiOperation("Description: 订单修改")
    @RequestMapping(value = "/updatePurchase/",method = RequestMethod.POST)
    public Map updatePurchase(Purchase purchase){
        Map mapReturn = new HashMap<>();
        Map map = new HashMap<>();
            map.put("purchaseId",purchase.getPurchaseId());
        if(purchase.getPurchaseStatus().equals("已完成")){
            /* 更新采购订单状态*/
            map.put("purchaseEndTime",new Date());
            map.put("purchaseStatus",purchase.getPurchaseStatus());
            map.put("purchaseOperatorConfirm",purchase.getPurchaseOperatorConfirm());
            mapReturn.put("status","complete success");

            /* 查询库存是否存在此电机的库存
            * 如果有,直接更新此库存的数量和价值
            * 如果没有,添加新的库存单
            * */

            /* 查询采购单信息*/
            Map mapGetPurchase = new HashMap<>();
                mapGetPurchase.put("purchaseId",purchase.getPurchaseId());
            List<Purchase> purchaseList = purchaseService.getPurchaseByInfo(mapGetPurchase);
                /* 各字段信息*/
                String supplier = purchaseList.get(0).getPurchaseSupplier();
                String motorType = purchaseList.get(0).getPurchaseMotorType();
                String motorModel = purchaseList.get(0).getPurchaseMotorModel();

                Integer quality = purchaseList.get(0).getPurchaseMotorQuality();
                Float totalPrice = purchaseList.get(0).getPurchaseTotalPrice();
            /* 根据采购单的信息查询库存是否存在同种电机型号电机(supplier,motorType,motorModel)*/
            Map mapGetStock = new HashMap<>();
                mapGetStock.put("stockSupplier",supplier);
                mapGetStock.put("stockMotorType",motorType);
                mapGetStock.put("stockMotorModel",motorModel);
            List<Stock> stockList = stockService.getStockByInfo(mapGetStock);
            /* 没有检索到库存中有此电机的存在
            *  直接向添加新的库存
            * */
            if(stockList.isEmpty()){
                Map mapAddStock = new HashMap<>();
                    mapAddStock.put("stockId",String.valueOf(new Date().getTime())+purchase.getPurchaseId());
                    mapAddStock.put("stockSupplier",supplier);
                    mapAddStock.put("stockMotorType",motorType);
                    mapAddStock.put("stockMotorModel",motorModel);
                    mapAddStock.put("stockMotorQuantity",quality);
                    mapAddStock.put("stockMotorPriceIn",totalPrice);
                stockService.addStock(mapAddStock);
            }else {
                Map mapUpdateStock = new HashMap<>();
                System.out.println(stockList);
                Float stockMotorPriceIn= stockList.get(0).getStockMotorPriceIn();
                Integer stockMotorQuantity = stockList.get(0).getStockMotorQuantity();
                System.out.println(stockMotorPriceIn);
                System.out.println(stockMotorQuantity);
                    mapUpdateStock.put("stockSupplier",supplier);
                    mapUpdateStock.put("stockMotorType",motorType);
                    mapUpdateStock.put("stockMotorModel",motorModel);

                Float newStockMotorPriceIn = stockMotorPriceIn + totalPrice;
                Integer newStockMotorQuantity = stockMotorQuantity+quality;
                System.out.println(newStockMotorPriceIn);
                System.out.println(newStockMotorQuantity);
                    mapUpdateStock.put("stockMotorPriceIn",newStockMotorPriceIn);
                    mapUpdateStock.put("stockMotorQuantity",newStockMotorQuantity);
                stockService.updateStock(mapUpdateStock);
            }
        }
        if(purchase.getPurchaseStatus().equals("已取消")){
            map.put("purchaseReturnTime",new Date());
            map.put("purchaseStatus",purchase.getPurchaseStatus());
            map.put("purchaseOperatorCancel",purchase.getPurchaseOperatorCancel());
            mapReturn.put("status","cancel success");
        }
        purchaseService.updatePurchase(map);
        return mapReturn;
    }
}
