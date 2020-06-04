
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: security
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attack_model`
--

DROP TABLE IF EXISTS `attack_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `attack_model` (
  `vuln_id` int NOT NULL,
  `vuln_name` varchar(45) DEFAULT NULL,
  `pre_privilege_id` int DEFAULT NULL,
  `fin_privilege_id` int DEFAULT NULL,
  `used_flag` int DEFAULT NULL,
  PRIMARY KEY (`vuln_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attack_model`
--
-- ORDER BY:  `vuln_id`

LOCK TABLES `attack_model` WRITE;
/*!40000 ALTER TABLE `attack_model` DISABLE KEYS */;
INSERT INTO `attack_model` VALUES (1,'弱口令',4,1,0),(2,'sql注入',3,5,0);
/*!40000 ALTER TABLE `attack_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attack_path`
--

DROP TABLE IF EXISTS `attack_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `attack_path` (
  `id` int NOT NULL AUTO_INCREMENT,
  `src` varchar(45) DEFAULT NULL,
  `sport` varchar(45) DEFAULT NULL,
  `dst` varchar(45) DEFAULT NULL,
  `dport` varchar(45) DEFAULT NULL,
  `protocol` varchar(45) DEFAULT NULL,
  `privilege` varchar(45) DEFAULT NULL,
  `vuln_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attack_path`
--
-- ORDER BY:  `id`

LOCK TABLES `attack_path` WRITE;
/*!40000 ALTER TABLE `attack_path` DISABLE KEYS */;
/*!40000 ALTER TABLE `attack_path` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `device_id` int NOT NULL AUTO_INCREMENT,
  `device_name` varchar(20) DEFAULT NULL,
  `device_type` varchar(20) DEFAULT NULL,
  `device_deploy` int DEFAULT NULL,
  `device_value` double DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--
-- ORDER BY:  `device_id`

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'外部计算机','业务设备',0,1),(2,'互联网安全网关','网络安全设备',0,2),(3,'NAS服务器','业务设备',0,3),(4,'YK网关','网络安全设备',0,4),(5,'WEB服务器','业务设备',0,5),(6,'数据库','业务数据',0,6);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_connect`
--

DROP TABLE IF EXISTS `device_connect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_connect` (
  `device_id` int NOT NULL,
  `interface_id` int NOT NULL,
  `peer_device_id` int DEFAULT NULL,
  `peer_interface_id` int DEFAULT NULL,
  PRIMARY KEY (`device_id`,`interface_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_connect`
--
-- ORDER BY:  `device_id`,`interface_id`

LOCK TABLES `device_connect` WRITE;
/*!40000 ALTER TABLE `device_connect` DISABLE KEYS */;
INSERT INTO `device_connect` VALUES (1,1,2,1),(2,1,1,1),(2,2,3,1),(2,3,4,2),(3,1,2,2),(4,1,5,1),(4,2,2,3),(4,3,6,1),(5,1,4,1),(6,1,4,3);
/*!40000 ALTER TABLE `device_connect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_dataflow`
--

DROP TABLE IF EXISTS `device_dataflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_dataflow` (
  `device_id` int NOT NULL,
  `type` varchar(20) NOT NULL,
  `privilege_id` int NOT NULL,
  `src` varchar(20) NOT NULL,
  `sport` varchar(20) NOT NULL,
  `dst` varchar(20) NOT NULL,
  `dport` varchar(20) NOT NULL,
  `protocol` varchar(20) NOT NULL,
  `variant_id` int NOT NULL,
  `t_privilege_id` int DEFAULT NULL,
  `t_src` varchar(20) DEFAULT NULL,
  `t_sport` varchar(20) DEFAULT NULL,
  `t_dst` varchar(20) DEFAULT NULL,
  `t_dport` varchar(20) DEFAULT NULL,
  `t_protocol` varchar(20) DEFAULT NULL,
  `t_variant_id` int DEFAULT NULL,
  `priority` int DEFAULT NULL,
  PRIMARY KEY (`device_id`,`type`,`privilege_id`,`src`,`sport`,`dst`,`dport`,`protocol`,`variant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_dataflow`
--
-- ORDER BY:  `device_id`,`type`,`privilege_id`,`src`,`sport`,`dst`,`dport`,`protocol`,`variant_id`

LOCK TABLES `device_dataflow` WRITE;
/*!40000 ALTER TABLE `device_dataflow` DISABLE KEYS */;
INSERT INTO `device_dataflow` VALUES (1,'accept',-1,'*','*','*','*','*',-1,-1,'-','-','-','-','-',-1,1),(2,'accept',-1,'*','*','*','*','*',-1,-1,'-','-','-','-','-',-1,1),(3,'accept',-1,'192.168.1.2','*','*','*','*',-1,-1,'-','-','-','-','-',-1,3),(3,'accept',1,'*','*','192.168.1.2','-','-',-1,-1,'-','-','-','-','-',-1,1),(3,'accept',1,'*','*','192.168.1.2','22','SSH',-1,-1,'-','-','-','-','-',-1,2),(3,'accept',4,'*','*','192.168.1.2','-','-',-1,-1,'-','-','-','-','-',-1,1),(3,'accept',4,'*','*','192.168.1.2','22','SSH',-1,-1,'-','-','-','-','-',-1,2),(4,'accept',-1,'*','*','*','*','*',-1,-1,'-','-','-','-','-',-1,1),(5,'accept',-1,'192.168.2.2','*','*','*','*',-1,-1,'-','-','-','-','-',-1,2),(5,'accept',2,'*','*','192.168.2.2','80','HTTP',-1,-1,'-','-','-','-','-',-1,1),(6,'accept',-1,'192.168.2.3','*','*','*','*',-1,-1,'-','-','-','-','-',-1,2),(6,'accept',3,'*','*','192.168.2.3','3306','TCP',-1,-1,'-','-','-','-','-',-1,1);
/*!40000 ALTER TABLE `device_dataflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_network`
--

DROP TABLE IF EXISTS `device_network`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_network` (
  `device_id` int NOT NULL,
  `interface_id` int NOT NULL,
  `address` varchar(20) DEFAULT NULL,
  `netmask` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`device_id`,`interface_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_network`
--
-- ORDER BY:  `device_id`,`interface_id`

LOCK TABLES `device_network` WRITE;
/*!40000 ALTER TABLE `device_network` DISABLE KEYS */;
INSERT INTO `device_network` VALUES (1,1,'220.181.38.148','0.0.0.0'),(2,1,'-','-'),(2,2,'192.168.1.1','255.255.255.0'),(2,3,'-','-'),(3,1,'192.168.1.2','255.255.255.0'),(4,1,'192.168.2.1','255.255.255.0'),(4,2,'192.168.1.3','255.255.255.0'),(4,3,'-','-'),(5,1,'192.168.2.2','255.255.255.0'),(6,1,'192.168.2.3','255.255.255.0');
/*!40000 ALTER TABLE `device_network` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_privilege`
--

DROP TABLE IF EXISTS `device_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_privilege` (
  `device_id` int NOT NULL,
  `service_id` int NOT NULL,
  `privilege_id` int NOT NULL,
  `data` varchar(20) DEFAULT NULL,
  `comment` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`privilege_id`,`device_id`,`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_privilege`
--
-- ORDER BY:  `privilege_id`,`device_id`,`service_id`

LOCK TABLES `device_privilege` WRITE;
/*!40000 ALTER TABLE `device_privilege` DISABLE KEYS */;
INSERT INTO `device_privilege` VALUES (3,1,1,'-','3-linux-root'),(3,2,1,'-','3-ssh-root'),(5,1,2,'-','5-httpd-user'),(6,1,3,'-','6-mysqld-user'),(3,2,4,'-','3-ssh-user'),(6,1,5,'-','6-mysqld-root');
/*!40000 ALTER TABLE `device_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_route`
--

DROP TABLE IF EXISTS `device_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_route` (
  `device_id` int NOT NULL,
  `destination` varchar(20) NOT NULL,
  `genmask` varchar(20) NOT NULL,
  `gateway` varchar(20) DEFAULT NULL,
  `interface_id` int DEFAULT NULL,
  PRIMARY KEY (`device_id`,`destination`,`genmask`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_route`
--
-- ORDER BY:  `device_id`,`destination`,`genmask`

LOCK TABLES `device_route` WRITE;
/*!40000 ALTER TABLE `device_route` DISABLE KEYS */;
INSERT INTO `device_route` VALUES (1,'0.0.0.0','255.255.255.0','192.168.1.1',1),(2,'0.0.0.0','0.0.0.0','0.0.0.0',1),(2,'192.168.1.2','255.255.255.0','0.0.0.0',2),(2,'192.168.1.3','255.255.255.0','0.0.0.0',3),(2,'192.168.2.0','255.255.255.0','192.168.2.1',3),(3,'0.0.0.0','255.255.255.0','192.168.1.1',1),(4,'0.0.0.0','255.255.255.0','192.168.1.1',2),(4,'192.168.2.2','255.255.255.0','0.0.0.0',1),(4,'192.168.2.3','255.255.255.0','0.0.0.0',3),(5,'0.0.0.0','255.255.255.0','192.168.2.1',1),(6,'0.0.0.0','255.255.255.0','192.168.2.1',1);
/*!40000 ALTER TABLE `device_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_security`
--

DROP TABLE IF EXISTS `device_security`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_security` (
  `device_id` int NOT NULL,
  `function_id` int NOT NULL,
  `owner_id` int NOT NULL,
  PRIMARY KEY (`device_id`,`function_id`,`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_security`
--
-- ORDER BY:  `device_id`,`function_id`,`owner_id`

LOCK TABLES `device_security` WRITE;
/*!40000 ALTER TABLE `device_security` DISABLE KEYS */;
INSERT INTO `device_security` VALUES (2,1,2),(2,2,2),(2,3,2),(2,6,2),(2,7,2),(2,8,2),(2,10,2),(3,7,2),(4,1,4),(4,2,4),(4,3,4);
/*!40000 ALTER TABLE `device_security` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_service`
--

DROP TABLE IF EXISTS `device_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_service` (
  `device_id` int NOT NULL,
  `service_id` int NOT NULL,
  `environment` varchar(20) DEFAULT NULL,
  `port` varchar(20) DEFAULT NULL,
  `service` varchar(20) DEFAULT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`device_id`,`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_service`
--
-- ORDER BY:  `device_id`,`service_id`

LOCK TABLES `device_service` WRITE;
/*!40000 ALTER TABLE `device_service` DISABLE KEYS */;
INSERT INTO `device_service` VALUES (3,1,'Linux','-','linux','-'),(3,2,'Linux','22','ssh','SSH'),(5,1,'Linux','80','httpd','HTTP'),(6,1,'Linux','3306','mysqld','TCP');
/*!40000 ALTER TABLE `device_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_threat`
--

DROP TABLE IF EXISTS `device_threat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `device_threat` (
  `device_id` int NOT NULL,
  `service_id` int NOT NULL,
  `privilege_id` int NOT NULL,
  `threat_id` int NOT NULL,
  `threat_weight` double DEFAULT NULL,
  PRIMARY KEY (`device_id`,`service_id`,`privilege_id`,`threat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_threat`
--
-- ORDER BY:  `device_id`,`service_id`,`privilege_id`,`threat_id`

LOCK TABLES `device_threat` WRITE;
/*!40000 ALTER TABLE `device_threat` DISABLE KEYS */;
INSERT INTO `device_threat` VALUES (3,2,4,1,1),(6,1,3,2,1);
/*!40000 ALTER TABLE `device_threat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_function`
--

DROP TABLE IF EXISTS `security_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `security_function` (
  `function_id` int NOT NULL,
  `function_name` varchar(20) DEFAULT NULL,
  `security_class` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_function`
--
-- ORDER BY:  `function_id`

LOCK TABLES `security_function` WRITE;
/*!40000 ALTER TABLE `security_function` DISABLE KEYS */;
INSERT INTO `security_function` VALUES (1,'抗网络探测','网络安全'),(2,'抗拒绝服务','网络安全'),(3,'抗病毒传播','网络安全'),(4,'流量加密','网络安全'),(5,'抗网络欺骗','网络安全'),(6,'深度包检测','网络安全'),(7,'漏洞扫描','主机安全'),(8,'Web安全防护','应用安全'),(9,'抗暴力猜解','应用安全'),(10,'抗网站挂马','应用安全'),(11,'抗恶意代码','主机安全'),(12,'抗恶意软件','主机安全'),(13,'防高危操作','主机安全'),(14,'防恶意电子邮件','主机安全'),(15,'防恶意移动介质','主机安全'),(16,'防栈溢出攻击','主机安全'),(17,'代码审计','数据安全'),(18,'数据加密','数据安全'),(19,'容灾恢复','数据安全'),(20,'病毒扫描','主机安全');
/*!40000 ALTER TABLE `security_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_threat`
--

DROP TABLE IF EXISTS `security_threat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `security_threat` (
  `threat_id` int NOT NULL,
  `threat_name` varchar(20) DEFAULT NULL,
  `function_id` int DEFAULT NULL,
  PRIMARY KEY (`threat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_threat`
--
-- ORDER BY:  `threat_id`

LOCK TABLES `security_threat` WRITE;
/*!40000 ALTER TABLE `security_threat` DISABLE KEYS */;
INSERT INTO `security_threat` VALUES (1,'端口探测',1),(2,'主机探测',1),(3,'漏洞探测',1),(4,'协议缺陷拒绝服务攻击',2),(5,'服务缺陷拒绝服务攻击',2),(6,'资源占用拒绝服务攻击',2),(7,'病毒传播',3),(8,'流量劫持',4),(9,'网络欺骗',5),(10,'恶意流量',6),(11,'SQL注入',8),(12,'跨站脚本',8),(13,'跨站请求伪造',8),(14,'网络爬虫',8),(15,'暴力猜解密码',9),(16,'网站目录探测',9),(17,'敏感信息探测',9),(18,'网站挂马',10),(19,'恶意代码',11),(20,'恶意软件',12),(21,'高危操作',13),(22,'恶意电子邮件',14),(23,'恶意移动介质',15),(24,'栈溢出攻击',16),(25,'高危操作',17),(26,'敏感明文数据泄露',18),(27,'删库破坏',19),(28,'提权漏洞',7),(29,'勒索病毒',20);
/*!40000 ALTER TABLE `security_threat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_variant`
--

DROP TABLE IF EXISTS `security_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `security_variant` (
  `variant_id` int NOT NULL,
  `variant_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`variant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_variant`
--
-- ORDER BY:  `variant_id`

LOCK TABLES `security_variant` WRITE;
/*!40000 ALTER TABLE `security_variant` DISABLE KEYS */;
/*!40000 ALTER TABLE `security_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_testcase`
--

DROP TABLE IF EXISTS `service_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `service_testcase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `src` varchar(20) DEFAULT NULL,
  `sport` varchar(20) DEFAULT NULL,
  `dst` varchar(20) DEFAULT NULL,
  `dport` varchar(20) DEFAULT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  `privileges` varchar(20) DEFAULT NULL,
  `variant_id` int DEFAULT NULL,
  `threat_id` int DEFAULT NULL,
  `record` double DEFAULT NULL,
  `detect` double DEFAULT NULL,
  `alarm` double DEFAULT NULL,
  `block` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_testcase`
--
-- ORDER BY:  `id`

LOCK TABLES `service_testcase` WRITE;
/*!40000 ALTER TABLE `service_testcase` DISABLE KEYS */;
INSERT INTO `service_testcase` VALUES (1,'220.181.38.148','65535','192.168.1.2','22','SSH','1',0,1,0.18,0.34,0.35,0.82),(2,'220.181.38.148','65535','192.168.2.2','80','HTTP','2',0,1,0.34,0.51,0.8,0.99),(3,'220.181.38.148','65535','192.168.2.3','3306','TCP','3',0,1,0.34,0.51,0.8,0.99),(4,'192.168.1.2','-','192.168.1.2','-','-','1',0,28,0.04,0.17,0.41,0.46),(5,'192.168.1.2','65535','192.168.1.2','22','SSH','1',0,1,0,0,0,0),(6,'192.168.1.2','65535','192.168.2.2','80','HTTP','2',0,1,0.34,0.51,0.8,0.99),(7,'192.168.1.2','65535','192.168.2.3','3306','TCP','3',0,1,0.34,0.51,0.8,0.99),(8,'192.168.2.2','65535','192.168.1.2','22','SSH','1',0,1,0.34,0.51,0.8,0.99),(9,'192.168.2.2','65535','192.168.2.2','80','HTTP','2',0,1,0,0,0,0),(10,'192.168.2.2','65535','192.168.2.3','3306','TCP','3',0,1,0.19,0.25,0.69,0.94),(11,'192.168.2.3','65535','192.168.1.2','22','SSH','1',0,1,0.34,0.51,0.8,0.99),(12,'192.168.2.3','65535','192.168.2.2','80','HTTP','2',0,1,0.19,0.25,0.69,0.94),(13,'192.168.2.3','65535','192.168.2.3','3306','TCP','3',0,1,0,0,0,0);
/*!40000 ALTER TABLE `service_testcase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `single_test`
--

DROP TABLE IF EXISTS `single_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `single_test` (
  `device_id` int NOT NULL,
  `function_id` int NOT NULL,
  `threat_id` int NOT NULL,
  `variant_id` int NOT NULL,
  `record` double DEFAULT NULL,
  `detect` double DEFAULT NULL,
  `alarm` double DEFAULT NULL,
  `block` double DEFAULT NULL,
  PRIMARY KEY (`device_id`,`function_id`,`threat_id`,`variant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `single_test`
--
-- ORDER BY:  `device_id`,`function_id`,`threat_id`,`variant_id`

LOCK TABLES `single_test` WRITE;
/*!40000 ALTER TABLE `single_test` DISABLE KEYS */;
INSERT INTO `single_test` VALUES (2,1,1,0,0.18,0.34,0.35,0.82),(2,1,2,0,0.15,0.38,0.39,0.42),(2,1,3,0,0.07,0.09,0.5,0.69),(2,2,4,0,0.26,0.32,0.85,0.95),(2,2,5,0,0.5,0.52,0.53,0.69),(2,2,6,0,0.06,0.2,0.43,0.7),(2,3,7,0,0.06,0.15,0.45,0.95),(2,6,10,0,0.06,0.26,0.6,0.88),(2,7,28,0,0.04,0.17,0.41,0.46),(2,8,11,0,0.29,0.51,0.56,0.95),(2,8,12,0,0.05,0.52,0.86,0.91),(2,8,13,0,0.29,0.39,0.83,0.95),(2,8,14,0,0.71,0.78,0.93,0.93),(2,10,18,0,0.14,0.16,0.34,0.73),(4,1,1,0,0.19,0.25,0.69,0.94),(4,1,2,0,0.12,0.41,0.47,0.94),(4,1,3,0,0.05,0.47,0.61,0.67),(4,2,4,0,0.27,0.42,0.71,0.77),(4,2,5,0,0.37,0.37,0.38,0.78),(4,2,6,0,0.55,0.57,0.65,0.85),(4,3,7,0,0.04,0.17,0.41,0.46);
/*!40000 ALTER TABLE `single_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_privilege`
--

DROP TABLE IF EXISTS `user_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `user_privilege` (
  `device_id` int NOT NULL,
  `service_id` int NOT NULL,
  `privilege_id` int NOT NULL,
  PRIMARY KEY (`privilege_id`,`device_id`,`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_privilege`
--
-- ORDER BY:  `privilege_id`,`device_id`,`service_id`

LOCK TABLES `user_privilege` WRITE;
/*!40000 ALTER TABLE `user_privilege` DISABLE KEYS */;
INSERT INTO `user_privilege` VALUES (6,1,3),(3,2,4);
/*!40000 ALTER TABLE `user_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'security'
--

--
-- Dumping routines for database 'security'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-10 16:46:11
