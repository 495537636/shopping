/*
SQLyog v10.2
MySQL - 5.6.35-log : Database - shopping
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

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `user_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型，0=普通，1=vip',
  `user_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户标识，0=正常，1=已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `userinfo` */

insert  into `userinfo`(`user_id`,`user_name`,`password`,`user_type`,`user_flag`,`create_time`) values ('1','admin','admin',0,0,'2017-07-03 21:29:43');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
