/*
SQLyog v10.2
MySQL - 5.7.14 : Database - shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopping` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `shopping`;

/*Table structure for table `address_management_info` */

DROP TABLE IF EXISTS `address_management_info`;

CREATE TABLE `address_management_info` (
  `address_id` varchar(32) NOT NULL COMMENT '地址ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `addressee` varchar(20) DEFAULT NULL COMMENT '收件人',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '收件地址',
  `post_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0=正常 1=删除',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址管理信息表';

/*Data for the table `address_management_info` */

/*Table structure for table `attribute_info` */

DROP TABLE IF EXISTS `attribute_info`;

CREATE TABLE `attribute_info` (
  `attribute_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品属性ID',
  `name` varchar(50) DEFAULT NULL COMMENT '商品属性名称',
  `category_id` varchar(32) DEFAULT NULL COMMENT '商品分类ID',
  PRIMARY KEY (`attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='商品属性信息表';

/*Data for the table `attribute_info` */

insert  into `attribute_info`(`attribute_id`,`name`,`category_id`) values (1,'屏幕尺寸','1'),(2,'运行内存','1'),(3,'网络','1'),(4,'机身内存','1'),(5,'操作系统','1'),(6,'电池容量','1'),(7,'机身颜色','1');

/*Table structure for table `attribute_option_info` */

DROP TABLE IF EXISTS `attribute_option_info`;

CREATE TABLE `attribute_option_info` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性选项ID',
  `name` varchar(50) DEFAULT NULL COMMENT '属性选项名称',
  `value` varchar(20) DEFAULT NULL COMMENT '属性选项值',
  `attribute_id` int(11) DEFAULT NULL COMMENT '属性ID',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COMMENT='属性选项信息表';

/*Data for the table `attribute_option_info` */

insert  into `attribute_option_info`(`option_id`,`name`,`value`,`attribute_id`) values (1,'3.0英寸及以下','1',1),(2,'4.5-3.1英寸','2',1),(3,'5.0-4.6英寸','3',1),(4,'5.5-5.1英寸','4',1),(5,'5.6英寸及以上','5',1),(6,'其他','6',1),(7,'2GB','1',2),(8,'3GB','2',2),(9,'4GB','3',2),(10,'6GB','4',2),(11,'8GB','5',2),(12,'其他','6',2),(13,'双卡单4G','1',3),(14,'双卡双4G','2',3),(15,'电信4G','3',3),(16,'移动4G','4',3),(17,'移动4G/联通4G/电信4G','5',3),(18,'联通4G','6',3),(19,'其他','7',3),(20,'8GB','1',4),(21,'16GB','2',4),(22,'32GB','3',4),(23,'64GB','4',4),(24,'128GB','5',4),(25,'其他','6',4),(26,'安卓（Android）','1',5),(27,'苹果（IOS）','2',5),(28,'其他','3',5),(29,'1200mAh以下','1',6),(30,'1200mAh-1999mAh','2',6),(31,'2000mAh-2999mAh','3',6),(32,'3000mAh-3999mAh','4',6),(33,'4000mAh-5999mAh','5',6),(34,'其他','6',6),(35,'灰色','1',7),(36,'白色','2',7),(37,'粉色','3',7),(38,'紫色','4',7),(39,'红色','5',7),(40,'蓝色','6',7),(41,'金色','7',7),(42,'银色','8',7),(43,'黑色','9',7),(44,'其他','10',7);

/*Table structure for table `brand_info` */

DROP TABLE IF EXISTS `brand_info`;

CREATE TABLE `brand_info` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌信息表',
  `name` varchar(50) DEFAULT NULL COMMENT '品牌名称',
  `introduce` varchar(100) DEFAULT NULL COMMENT '品牌介绍',
  `logo_url` varchar(100) DEFAULT NULL COMMENT '品牌LOGO',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='品牌信息表';

/*Data for the table `brand_info` */

insert  into `brand_info`(`brand_id`,`name`,`introduce`,`logo_url`) values (1,'Apple','苹果公司（Apple Inc. ）是美国的一家高科技公司。由史蒂夫·乔布斯、斯蒂夫·沃兹尼亚克和罗·韦恩(Ron Wayne)等人于1976年4月1日创立，并命名为美国苹果电脑公司（Apple Com','http://image.loving1314.com/filestore/logo/apple.jpg'),(2,'小米','北京小米科技有限责任公司，成立于2010年4月，是一家专注于智能硬件和电子产品研发的移动互联网公司。“为发烧而生”是小米的产品概念。小米公司首创了用互联网模式开发手机操作系统、发烧友参与开发改进的模式','http://image.loving1314.com/filestore/logo/xiaomi.jpg');

/*Table structure for table `category_info` */

DROP TABLE IF EXISTS `category_info`;

CREATE TABLE `category_info` (
  `category_id` varchar(32) NOT NULL COMMENT '商品分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `introduce` varchar(100) DEFAULT NULL COMMENT '分类简介',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父分类ID',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类信息表';

/*Data for the table `category_info` */

insert  into `category_info`(`category_id`,`name`,`introduce`,`parent_id`) values ('0','全部商品分类','全部商品分类','-1'),('1','手机、数码','手机、数码','0'),('10','家居、家具','家居、家具','0'),('2','电脑办公','电脑办公','0'),('3','家用电器','家用电器','0'),('4','男装、女装','男装、女装','0'),('5','图书、音像','图书、音像','0'),('6','汽车、汽车用品','汽车、汽车用品','0'),('7','母婴、玩具乐器','母婴、玩具乐器','0'),('8','钟表、珠宝','钟表、珠宝','0'),('9','食品、饮料','食品、饮料','0');

/*Table structure for table `goods_attribute_option_info` */

DROP TABLE IF EXISTS `goods_attribute_option_info`;

CREATE TABLE `goods_attribute_option_info` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `option_id` int(11) DEFAULT NULL COMMENT '属性选项ID',
  `attribute_name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `option_name` varchar(50) DEFAULT NULL COMMENT '选项名称',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='商品-属性选项关联信息表';

/*Data for the table `goods_attribute_option_info` */

insert  into `goods_attribute_option_info`(`pkid`,`goods_id`,`option_id`,`attribute_name`,`option_name`) values (1,'7f7da1438dd2431bbe99ffb0a152b48e',2,'屏幕尺寸','4.5-3.1英寸'),(2,'7f7da1438dd2431bbe99ffb0a152b48e',12,'运行内存',NULL),(3,'7f7da1438dd2431bbe99ffb0a152b48e',17,'网络',NULL),(4,'7f7da1438dd2431bbe99ffb0a152b48e',22,'机身内存',NULL),(5,'7f7da1438dd2431bbe99ffb0a152b48e',27,'操作系统',NULL),(6,'7f7da1438dd2431bbe99ffb0a152b48e',30,'电池容量',NULL),(7,'7f7da1438dd2431bbe99ffb0a152b48e',35,'机身颜色',NULL),(8,'235397fa959a4295ad19f1d412d8b90c',3,'屏幕尺寸',NULL),(9,'235397fa959a4295ad19f1d412d8b90c',10,'运行内存',NULL),(10,'235397fa959a4295ad19f1d412d8b90c',17,'网络',NULL),(11,'235397fa959a4295ad19f1d412d8b90c',23,'机身内存',NULL),(12,'235397fa959a4295ad19f1d412d8b90c',26,'操作系统',NULL),(13,'235397fa959a4295ad19f1d412d8b90c',32,'电池容量',NULL),(14,'235397fa959a4295ad19f1d412d8b90c',44,'机身颜色',NULL);

/*Table structure for table `goods_info` */

DROP TABLE IF EXISTS `goods_info`;

CREATE TABLE `goods_info` (
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `introduce` varchar(200) DEFAULT NULL COMMENT '商品简介',
  `details` varchar(500) DEFAULT NULL COMMENT '商品详情',
  `click_amount` int(11) DEFAULT '0' COMMENT '点击量',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `original_price` decimal(11,2) DEFAULT NULL COMMENT '商品原价',
  `promotion_price` decimal(11,2) DEFAULT NULL COMMENT '促销价',
  `sales_volume` int(11) DEFAULT '0' COMMENT '销量',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `ishot` tinyint(1) DEFAULT '0' COMMENT '是否热门 0=否 1=是',
  `isnew` tinyint(1) DEFAULT '0' COMMENT '是否新品 0=否 1=是',
  `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  `item_id` int(11) DEFAULT NULL COMMENT '货品ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0=正常 1=删除',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

/*Data for the table `goods_info` */

insert  into `goods_info`(`goods_id`,`name`,`introduce`,`details`,`click_amount`,`img_url`,`original_price`,`promotion_price`,`sales_volume`,`stock`,`ishot`,`isnew`,`category_id`,`brand_id`,`item_id`,`create_time`,`status`) values ('235397fa959a4295ad19f1d412d8b90c','小米6 全网通 6GB+64GB 亮黑色 移动联通电信4G手机 双卡双待','小米6 全网通 6GB+64GB 亮黑色 移动联通电信4G手机 双卡双待','8.24日 10:00抢购！骁龙835 旗舰处理器， 6GB 大内存，5.15”四曲面机身！ 8月22日10点，红米Note5A新品首发，戳我预约赢大奖！',0,'http://image.loving1314.com/filestore/phoneImage/597ae1b4N07915872.jpg','2499.00',NULL,0,0,0,0,1,2,1,'2017-08-22 16:48:11',0),('7f7da1438dd2431bbe99ffb0a152b48e','Apple iPhone 6 32GB 金色 移动联通电信4G手机','Apple iPhone 6 32GB 金色 移动联通电信4G手机','【开学迎新季，就要换新机！】预定商品按预计发货时间陆续发货！iPhone为你，诚意不止！\r\n选择下方购买方式的【移动】【电信】【联通】优惠购，套餐有优惠，还有话费返还！',0,'http://image.loving1314.com/filestore/phoneImage/542d0798N19d42ce3.jpg','2499.00',NULL,0,0,0,0,1,1,1,'2017-08-22 15:05:11',0);

/*Table structure for table `goods_specification_option_info` */

DROP TABLE IF EXISTS `goods_specification_option_info`;

CREATE TABLE `goods_specification_option_info` (
  `pkid` varchar(32) NOT NULL COMMENT '主键ID',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `option_id` varchar(32) DEFAULT NULL COMMENT '规格选项ID',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品-规格选项关联信息表';

/*Data for the table `goods_specification_option_info` */

/*Table structure for table `item_info` */

DROP TABLE IF EXISTS `item_info`;

CREATE TABLE `item_info` (
  `item_id` varchar(32) NOT NULL COMMENT '货品ID',
  `name` varchar(50) DEFAULT NULL COMMENT '货品名称',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货品信息表';

/*Data for the table `item_info` */

insert  into `item_info`(`item_id`,`name`) values ('1','手机'),('2','电脑'),('3','裤子'),('4','手表');

/*Table structure for table `item_specification_info` */

DROP TABLE IF EXISTS `item_specification_info`;

CREATE TABLE `item_specification_info` (
  `pkid` varchar(32) NOT NULL COMMENT '关联ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '货品ID',
  `specification_id` varchar(32) DEFAULT NULL COMMENT '规格ID',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货品-规格关联表';

/*Data for the table `item_specification_info` */

insert  into `item_specification_info`(`pkid`,`item_id`,`specification_id`) values ('1','1','1'),('2','1','2'),('3','1','3'),('4','1','4'),('5','1','5'),('6','1','6'),('7','1','7');

/*Table structure for table `login_image` */

DROP TABLE IF EXISTS `login_image`;

CREATE TABLE `login_image` (
  `image_id` varchar(32) NOT NULL COMMENT '主键ID',
  `image_url` varchar(100) NOT NULL COMMENT '图片路径',
  `image_title` varchar(50) NOT NULL COMMENT '图片标题',
  `image_comment` varchar(300) NOT NULL COMMENT '图片介绍',
  `image_order` int(10) NOT NULL COMMENT '排序',
  `image_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '图片标识，0=正常，1=删除',
  `image_active` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认，0=否，1=是',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆页图片信息表';

/*Data for the table `login_image` */

insert  into `login_image`(`image_id`,`image_url`,`image_title`,`image_comment`,`image_order`,`image_flag`,`image_active`) values ('032fcc2f5f5148cf91a15bd7d588e66f','http://image.loving1314.com/filestore/loginPage/17e095beb42e46b695e6384cbcf32567.jpg','','建筑1',0,0,1),('11a93ce8a57f4ec48982a1cbc2222684','http://image.loving1314.com/filestore/loginPage/dabacecd9b41433c91a6b4565cadeb32.jpg','','建筑2',1,0,0),('14b9932918984048afa51fe047c077ba','http://image.loving1314.com/filestore/loginPage/jay.jpg','','周杰伦',2,0,0),('29d377aa856b46a080dde222273e6f6e','http://image.loving1314.com/filestore/loginPage/lbjn.jpg','','兰博基尼',3,0,0);

/*Table structure for table `service_area_info` */

DROP TABLE IF EXISTS `service_area_info`;

CREATE TABLE `service_area_info` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `area_name` varchar(20) DEFAULT NULL COMMENT '地区名称',
  `area_code` varchar(10) DEFAULT NULL COMMENT '地区代码',
  `area_num` int(11) DEFAULT NULL COMMENT '编码',
  `area_flag` int(1) DEFAULT '0' COMMENT '0=正常服务 1=停止服务',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='服务地区信息表';

/*Data for the table `service_area_info` */

insert  into `service_area_info`(`pkid`,`area_name`,`area_code`,`area_num`,`area_flag`) values (1,'北京','110000',1,0),(2,'上海','310000',2,0),(3,'天津','120000',3,0),(4,'重庆','500000',4,0),(5,'河北','130000',5,0),(6,'山西','140000',6,0),(7,'河南','410000',7,0),(8,'辽宁','210000',8,0),(9,'吉林','220000',9,0),(10,'黑龙江','230000',10,0),(11,'内蒙古','150000',11,0),(12,'江苏','320000',12,0),(13,'山东','370000',13,0),(14,'安徽','340000',14,0),(15,'浙江','330000',15,0),(16,'福建','350000',16,0),(17,'湖北','420000',17,0),(18,'湖南','430000',18,0),(19,'广东','440000',19,0),(20,'广西','450000',20,0),(21,'江西','360000',21,0),(22,'四川','510000',22,0),(23,'海南','460000',23,0),(24,'贵州','520000',24,0),(25,'云南','530000',25,0),(26,'西藏','540000',26,0),(27,'陕西','610000',27,0),(28,'甘肃','620000',28,0),(29,'青海','630000',29,0),(30,'宁夏','640000',30,0),(31,'新疆','650000',31,0),(32,'港澳','52993',52993,0),(33,'台湾','710000',32,0),(34,'钓鱼岛','84',84,0),(35,'海外','53283',53283,0);

/*Table structure for table `specification_info` */

DROP TABLE IF EXISTS `specification_info`;

CREATE TABLE `specification_info` (
  `specifications_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格ID',
  `name` varchar(50) DEFAULT NULL COMMENT '规格名称',
  PRIMARY KEY (`specifications_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='规格信息表';

/*Data for the table `specification_info` */

insert  into `specification_info`(`specifications_id`,`name`) values (1,'屏幕尺寸'),(2,'运行内存'),(3,'网络'),(4,'机身内存'),(5,'操作系统'),(6,'电池容量'),(7,'机身颜色');

/*Table structure for table `specification_option_info` */

DROP TABLE IF EXISTS `specification_option_info`;

CREATE TABLE `specification_option_info` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选项ID',
  `specification_id` int(11) DEFAULT NULL COMMENT '规格ID',
  `name` varchar(50) DEFAULT NULL COMMENT '选项名称',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='规格选项信息表';

/*Data for the table `specification_option_info` */

insert  into `specification_option_info`(`option_id`,`specification_id`,`name`) values (1,1,'3.0英寸及以下'),(2,1,'4.5-3.1英寸'),(3,1,'5.0-4.6英寸'),(4,1,'5.5-5.1英寸'),(5,1,'5.6英寸及以上'),(6,2,'2GB'),(7,2,'3GB'),(8,2,'4GB'),(9,2,'6GB'),(10,2,'8GB'),(11,3,'双卡单4G'),(12,3,'双卡双4G'),(13,3,'电信4G'),(14,3,'移动4G'),(15,3,'移动4G/联通4G/电信4G'),(16,3,'联通4G'),(17,4,'8GB'),(18,4,'16GB'),(19,4,'32GB'),(20,4,'64GB'),(21,4,'128GB'),(22,5,'安卓（Android）'),(23,5,'苹果（IOS）'),(24,6,'1200mAh以下'),(25,6,'1200mAh-1999mAh'),(26,6,'2000mAh-2999mAh'),(27,6,'3000mAh-3999mAh'),(28,6,'4000mAh-5999mAh'),(29,7,'灰色'),(30,7,'白色'),(31,7,'粉色'),(32,7,'紫色'),(33,7,'红色'),(34,7,'蓝色'),(35,7,'金色'),(36,7,'银色'),(37,7,'黑色');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_phone` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `user_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型，0=普通，1=vip',
  `user_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户标识，0=正常，1=已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_name`,`user_phone`,`password`,`user_type`,`user_flag`,`create_time`) values ('1','admin','13522058663','4c992b064dab618477b6b856697ec377',0,0,'2017-07-04 11:34:39'),('512470ded3a240b79f54fa4838d23da1','天王巨星','15666666666','li6818141',0,0,'2017-07-26 10:43:49'),('9b31971738c8418a82f38084de7bc0b8','sunshine','13777777777','abc123',0,0,'2017-07-27 11:38:34'),('c35dea75505a4856b21277d2caf49893','limingguang','15555555555','103679827c67cebfd77478e68e1d8478',0,0,'2017-07-26 17:16:11'),('c6f3d94907224a199c2ede940f19221d','????','18888888888','li6818141',0,0,'2017-07-26 10:24:35'),('dba66cb4b41d4b7e9c1348ffd96a5d40','abc123','13888888888','abc123',0,0,'2017-07-27 11:36:23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
