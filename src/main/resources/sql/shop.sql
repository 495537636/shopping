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
  `attribute_id` varchar(32) NOT NULL COMMENT '商品属性ID',
  `name` varchar(50) DEFAULT NULL COMMENT '商品属性名称',
  `category_id` varchar(32) DEFAULT NULL COMMENT '商品分类ID',
  PRIMARY KEY (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性信息表';

/*Data for the table `attribute_info` */

/*Table structure for table `attribute_option_info` */

DROP TABLE IF EXISTS `attribute_option_info`;

CREATE TABLE `attribute_option_info` (
  `option_id` varchar(32) NOT NULL COMMENT '属性选项ID',
  `name` varchar(50) DEFAULT NULL COMMENT '属性选项名称',
  `attribute_id` varchar(32) DEFAULT NULL COMMENT '属性ID',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性选项信息表';

/*Data for the table `attribute_option_info` */

/*Table structure for table `brand_info` */

DROP TABLE IF EXISTS `brand_info`;

CREATE TABLE `brand_info` (
  `brand_id` varchar(32) NOT NULL COMMENT '品牌信息表',
  `name` varchar(50) DEFAULT NULL COMMENT '品牌名称',
  `introduce` varchar(100) DEFAULT NULL COMMENT '品牌介绍',
  `logo_url` varchar(100) DEFAULT NULL COMMENT '品牌LOGO',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌信息表';

/*Data for the table `brand_info` */

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

/*Table structure for table `goods_attribute_option_info` */

DROP TABLE IF EXISTS `goods_attribute_option_info`;

CREATE TABLE `goods_attribute_option_info` (
  `pkid` varchar(32) NOT NULL COMMENT '主键ID',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `option_id` varchar(32) DEFAULT NULL COMMENT '属性选项ID',
  `attribute_name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `option_name` varchar(50) DEFAULT NULL COMMENT '选项名称',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品-属性选项关联信息表';

/*Data for the table `goods_attribute_option_info` */

/*Table structure for table `goods_info` */

DROP TABLE IF EXISTS `goods_info`;

CREATE TABLE `goods_info` (
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `introduce` varchar(200) DEFAULT NULL COMMENT '商品简介',
  `details` varchar(500) DEFAULT NULL COMMENT '商品详情',
  `click_amount` int(11) DEFAULT NULL COMMENT '点击量',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `original_price` decimal(11,2) DEFAULT NULL COMMENT '商品原价',
  `promotion_price` decimal(11,2) DEFAULT NULL COMMENT '促销价',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `ishot` tinyint(1) DEFAULT '0' COMMENT '是否热门 0=否 1=是',
  `isnew` tinyint(1) DEFAULT '0' COMMENT '是否新品 0=否 1=是',
  `category_id` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `brand_id` varchar(32) DEFAULT NULL COMMENT '品牌ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '货品ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0=正常 1=删除',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

/*Data for the table `goods_info` */

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

/*Table structure for table `item_specification_info` */

DROP TABLE IF EXISTS `item_specification_info`;

CREATE TABLE `item_specification_info` (
  `pkid` varchar(32) NOT NULL COMMENT '关联ID',
  `item_id` varchar(32) DEFAULT NULL COMMENT '货品ID',
  `specification_id` varchar(32) DEFAULT NULL COMMENT '规格ID',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货品-规格关联表';

/*Data for the table `item_specification_info` */

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

/*Table structure for table `specification_info` */

DROP TABLE IF EXISTS `specification_info`;

CREATE TABLE `specification_info` (
  `specifications_id` varchar(32) NOT NULL COMMENT '规格ID',
  `name` varchar(50) DEFAULT NULL COMMENT '规格名称',
  PRIMARY KEY (`specifications_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格信息表';

/*Data for the table `specification_info` */

/*Table structure for table `specification_option_info` */

DROP TABLE IF EXISTS `specification_option_info`;

CREATE TABLE `specification_option_info` (
  `option_id` varchar(32) NOT NULL COMMENT '选项ID',
  `specification_id` varchar(32) DEFAULT NULL COMMENT '规格ID',
  `name` varchar(50) DEFAULT NULL COMMENT '选项名称',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格选项信息表';

/*Data for the table `specification_option_info` */

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
