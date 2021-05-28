-- MySQL dump 10.13  Distrib 5.7.34, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: emcims
-- ------------------------------------------------------
-- Server version	5.7.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '公司编号',
  `company_name` varchar(10) NOT NULL COMMENT '公司名称',
  `company_address` varchar(30) NOT NULL COMMENT '公司地址',
  `company_email` varchar(20) NOT NULL COMMENT '公司邮箱',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'某某电机公司','xxx','123456789@xxx.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `position_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '职位编号',
  `position_name` varchar(10) NOT NULL COMMENT '职位名称',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'系统管理员'),(2,'经理'),(3,'员工');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `purchase_id` varchar(50) NOT NULL COMMENT '采购单',
  `purchase_supplier` varchar(30) NOT NULL COMMENT '电机供应商',
  `purchase_motor_type` varchar(30) NOT NULL COMMENT '电机类型',
  `purchase_motor_model` varchar(30) NOT NULL COMMENT '电机型号',
  `purchase_motor_quantity` int(10) NOT NULL COMMENT '电机数量',
  `purchase_motor_price` decimal(10,2) DEFAULT NULL COMMENT '电机价格',
  `purchase_operator_submit` varchar(10) NOT NULL COMMENT '操作员',
  `purchase_operator_confirm` varchar(10) DEFAULT NULL COMMENT '操作员_确认',
  `purchase_operator_cancel` varchar(10) DEFAULT NULL COMMENT '操作员_取消',
  `purchase_status` varchar(10) DEFAULT '已提交',
  `purchase_start_time` datetime NOT NULL COMMENT '订单提交时间',
  `purchase_end_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `purchase_return_time` datetime DEFAULT NULL COMMENT '取消订单时间',
  `purchase_total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES ('202104021824231','sup4','直流电机','zl234',150,200.00,'Jerry','Jerry',NULL,'已完成','2021-04-20 10:23:20','2021-05-10 10:23:20',NULL,30000.00),('202104022054131','sup','交流电机','jl123',300,150.00,'Jerry','Jerry','Jerry','已取消','2021-04-26 20:42:28','2021-05-16 20:42:28','2021-05-17 20:42:28',45000.00),('202104032032201','sup','交流电机','jl234',200,70.00,'Jerry','Jerry',NULL,'已完成','2021-04-21 12:14:12','2021-05-11 12:14:12',NULL,14000.00),('202104032041251','sup','直流电机','zl345',30,120.30,'Jerry','Jerry','Jerry','已取消','2021-04-22 12:14:00','2021-05-12 12:14:00','2021-05-15 12:14:00',3609.00),('202104041132521','sup','直流电机','zl001',300,120.00,'Jerry','Jerry',NULL,'已完成','2021-04-23 16:35:20','2021-05-13 16:35:20',NULL,36000.00),('202104041612551','ghj','uuuu','bjkbkj',878,79877.88,'Jerry','Jerry',NULL,'已完成','2021-04-24 17:55:48','2021-05-14 17:55:48',NULL,70132784.00),('202104041939051','张三','直流电机','zl002',200,100.00,'Jerry','Jerry',NULL,'已完成','2021-04-25 17:55:15','2021-05-15 17:55:15',NULL,20000.00),('202104051953061','sup','直流电机','zl003',100,150.00,'Jerry','Jerry',NULL,'已完成','2021-04-25 22:16:41','2021-05-15 22:16:41',NULL,15000.00),('202104051953101','sup','直流电机','zl003',100,150.00,'Jerry','Jerry',NULL,'已完成','2021-04-26 09:20:58','2021-05-16 09:20:58',NULL,15000.00),('202104062319261','12','直流电机','m1',10,1.12,'Jerry','Jerry',NULL,'已完成','2021-04-27 09:18:02','2021-05-17 09:18:02',NULL,11.22),('202104091627481','sup4','直流电机','zl008',50,100.00,'Jerry','Jerry',NULL,'已完成','2021-04-22 09:15:54','2021-05-12 09:15:54',NULL,5000.00),('202104091630281','sup01','直流电机','zl009',100,200.00,'Jerry','Jerry',NULL,'已完成','2021-04-22 23:22:25','2021-05-12 23:22:25',NULL,20000.00),('202104091634071','sup0010','交流电机','jl001',300,200.00,'Jerry','Jerry',NULL,'已提交','2021-04-24 22:28:06','2021-05-14 22:28:06',NULL,60000.00),('202104262039594','supp001','直流电机','zl001',159,100.00,'Jerry','Jerry',NULL,'已完成','2021-04-25 22:24:47','2021-05-15 22:24:47',NULL,15900.00),('202105232336021','sup0523','减速电机','js001',300,150.00,'admin','admin',NULL,'已完成','2021-05-23 23:36:05','2021-05-23 23:36:35',NULL,45000.00);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `sales_id` varchar(30) NOT NULL COMMENT '销售订单',
  `sales_client` varchar(30) NOT NULL COMMENT '客户',
  `sales_motor_supplier` varchar(30) DEFAULT NULL,
  `sales_motor_type` varchar(30) NOT NULL COMMENT '电机类型',
  `sales_motor_model` varchar(30) NOT NULL COMMENT '电机型号',
  `sales_motor_quantity` int(10) NOT NULL COMMENT '电机数量',
  `sales_motor_price` decimal(10,2) DEFAULT NULL COMMENT '电机价格',
  `sales_total_price` decimal(10,2) DEFAULT NULL COMMENT '订单总额',
  `sales_operator_submit` varchar(10) NOT NULL COMMENT '操作员_提交',
  `sales_operator_confirm` varchar(10) DEFAULT NULL COMMENT '操作员_确认',
  `sales_operator_cancel` varchar(10) DEFAULT NULL COMMENT '操作员_取消',
  `sales_status` varchar(10) DEFAULT '已提交',
  `sales_start_time` datetime NOT NULL COMMENT '订单提交时间',
  `sales_end_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `sales_return_time` datetime DEFAULT NULL COMMENT '取消订单时间',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES ('202104062216191','c001','s1','直流电机','m1',100,150.00,15000.00,'Jerry','Jerry','Jerry','已取消','2021-04-25 22:16:41','2021-05-13 22:16:41','2021-05-15 17:34:19'),('202104062220361','c001','s1','直流电机','m1',100,150.00,15000.00,'Jerry','Jerry','admin','已取消','2021-04-22 22:20:59','2021-05-12 22:20:59','2021-05-23 23:31:00'),('202104062224391','c001','s1','直流电机','m1',100,150.00,15000.00,'Jerry','Jerry','Jerry','已取消','2021-04-25 22:24:47','2021-05-13 22:24:47','2021-05-14 17:34:17'),('202104062227551','c001','s1','直流电机','m1',100,150.00,15000.00,'Jerry','Jerry','Jerry','已取消','2021-04-24 22:28:06','2021-05-14 22:28:06','2021-05-16 17:34:21'),('202104062321561','jiawen','12','直流电机','m1',2,6.67,13.34,'Jerry','Jerry','Jerry','已取消','2021-04-22 23:22:25','2021-05-12 23:22:25','2021-05-17 17:34:16'),('202104070915441','111','s2','交流电机','m1',10,150.00,1500.00,'Jerry','Jerry','','已完成','2021-04-22 09:15:54','2021-05-12 09:15:54',NULL),('202104070917551','11','s2','交流电机','m1',1,150.00,150.00,'Jerry','Jerry','','已完成','2021-04-27 09:18:02','2021-05-17 09:18:02',NULL),('202104070920541','11','s2','交流电机','m1',2,150.00,300.00,'Jerry','Jerry','','已完成','2021-04-26 09:20:58','2021-05-16 09:20:58',NULL),('202104071023131','1','s2','交流电机','m1',1,150.00,150.00,'Jerry','Jerry','','已完成','2021-04-20 10:23:20','2021-05-10 10:23:20',NULL),('202104081755051','c3','s3','直流电机','m1',3,100.00,300.00,'Jerry','Jerry',NULL,'已完成','2021-04-25 17:55:15','2021-05-15 17:55:15',NULL),('202104081755161','c5','s4','直流电机','m1',6,200.00,1200.00,'Jerry',NULL,NULL,'已完成','2021-04-25 17:55:28','2021-05-15 17:55:28',NULL),('202104081755411','c9','s4','直流电机','m1',10,200.00,2000.00,'Jerry','Jerry',NULL,'已完成','2021-04-24 17:55:48','2021-05-14 17:55:48',NULL),('202104091635041','client0010','sup0010','交流电机','jl001',200,150.00,30000.00,'Jerry','Jerry',NULL,'已完成','2021-04-23 16:35:20','2021-05-13 16:35:20',NULL),('2021041112134611','client001','s2','交流电机','m1',10,150.00,1500.00,'Jerry','Jerry',NULL,'已完成','2021-04-22 12:14:00','2021-05-12 12:14:00',NULL),('2021041112140111','client002','s3','直流电机','m1',30,100.00,3000.00,'Jerry','Jerry',NULL,'已完成','2021-04-21 12:14:12','2021-05-11 12:14:12',NULL),('2021041112141611','client003','sup0010','交流电机','jl001',90,150.00,13500.00,'Jerry',NULL,NULL,'已完成','2021-04-26 12:14:28','2021-05-16 12:14:28',NULL),('202104262042124','client001','supp001','直流电机','zl001',30,300.00,9000.00,'Jerry','Jerry',NULL,'已完成','2021-04-26 20:42:28','2021-05-16 20:42:28',NULL),('202105232337381','client524','sup0523','减速电机','js001',300,200.00,60000.00,'admin','admin','admin','已取消','2021-05-23 23:37:52','2021-05-23 23:38:20','2021-05-23 23:43:29'),('202105232348511','client','sup0523','减速电机','js001',100,200.00,20000.00,'admin',NULL,'admin','已取消','2021-05-23 23:49:02',NULL,'2021-05-23 23:49:24'),('202105240005001','张三','s2','交流电机','m1',10,150.00,1500.00,'admin','admin',NULL,'已完成','2021-05-24 00:05:14','2021-05-24 00:18:39',NULL),('202105240006411','张三','sup0523','减速电机','js001',300,200.00,60000.00,'admin',NULL,NULL,'已提交','2021-05-24 00:06:53',NULL,NULL),('202105240011231','李四','s2','交流电机','m1',166,150.00,24900.00,'admin',NULL,NULL,'已提交','2021-05-24 00:11:31',NULL,NULL);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` varchar(30) NOT NULL COMMENT '库存单号',
  `stock_supplier` varchar(10) NOT NULL COMMENT '电机供应商',
  `stock_motor_type` varchar(30) NOT NULL COMMENT '电机类型',
  `stock_motor_model` varchar(30) NOT NULL COMMENT '电机型号',
  `stock_motor_quantity` int(10) NOT NULL COMMENT '电机数量',
  `stock_motor_price_in` decimal(10,2) DEFAULT NULL COMMENT '电机入库价格',
  `stock_motor_price_out` decimal(10,2) DEFAULT NULL COMMENT '电机出库价格',
  `stock_status` varchar(10) DEFAULT '已入库',
  `stock_operator_up` varchar(10) DEFAULT NULL COMMENT '操作员_入库',
  `stock_operator_down` varchar(10) DEFAULT NULL COMMENT '操作员_出库',
  `stock_up_time` datetime DEFAULT NULL COMMENT '入库时间',
  `stock_down_time` datetime DEFAULT NULL COMMENT '出库时间',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('1617623422640202104021824231','s1','直流电机','m1',400,0.00,150.00,'已下架','sale1','sale1','2021-04-06 23:27:23','2021-04-06 23:27:25'),('1617623528273202104022054131','s2','交流电机','m1',0,16600.00,150.00,'已上架','admin','admin','2021-04-06 16:02:36','2021-04-06 15:00:42'),('1617623615574202104051953061','s3','直流电机','m1',267,26700.00,100.00,'已上架','admin','admin','2021-04-06 21:45:51','2021-04-06 14:49:19'),('1617712426361202104041939051','s4','直流电机','m1',384,38400.00,200.00,'已上架','admin',NULL,'2021-04-06 20:35:05',NULL),('1617722392210202104062319261','12','直流电机','m1',10,8.98,6.67,'已下架','admin','admin','2021-04-06 23:23:16','2021-04-08 19:44:27'),('1617956895382202104091627481','sup4','直流电机','zl008',50,5000.00,NULL,'已入库',NULL,NULL,NULL,NULL),('1617957051233202104091630281','sup01','直流电机','zl009',100,20000.00,NULL,'已入库',NULL,NULL,NULL,NULL),('1617957273879202104091634071','sup0010','交流电机','jl001',10,2000.00,150.00,'已上架','admin',NULL,'2021-04-09 16:34:57',NULL),('1618130576605202104032032201','sup','交流电机','jl234',200,14000.00,NULL,'已入库',NULL,NULL,NULL,NULL),('1618130578771202104032041251','sup','直流电机','zl345',30,3609.00,NULL,'已入库',NULL,NULL,NULL,NULL),('1619440809846202104262039594','supp001','直流电机','zl001',129,12900.00,300.00,'已上架','Jerry',NULL,'2021-04-26 20:41:49',NULL),('1621784194799202105232336021','sup0523','减速电机','js001',1,30000.00,200.00,'已上架','admin','admin','2021-05-23 23:45:29','2021-05-23 23:43:08');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号,帐号',
  `user_email` varchar(30) NOT NULL COMMENT '邮箱',
  `user_password` varchar(30) NOT NULL COMMENT '密码',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `user_gender` enum('男','女') DEFAULT NULL COMMENT '性别',
  `position_id` int(10) DEFAULT '3' COMMENT '所属部门编号',
  `user_status` varchar(5) DEFAULT '未激活' COMMENT '状态',
  `company_id` int(10) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','1','admin','男',1,'已激活',1),(2,'manager','2','manager','男',2,'已激活',1),(3,'employee@user.com','3','employee','男',3,'已激活',1),(4,'Jerry','3','Jerry','男',3,'已激活',1),(5,'user001@user.com','123456','User001',NULL,3,'已激活',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28 10:14:22
