/*
Navicat MySQL Data Transfer

Source Server         : tang
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : shoppingmall

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-07-04 19:16:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `price` double(10,2) NOT NULL,
  `picUrl` varchar(255) NOT NULL,
  `detail` text NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '哈登欧洲步', '可以啊', '666.00', 'http://n.sinaimg.cn/sports/transform/20170316/Z36J-fychtth0888126.jpg', '66666', '2017-03-30 22:17:38', '2017-03-30 22:17:38', '1');
INSERT INTO `goods` VALUES ('2', '??', '????', '12.00', 'http://n.sinaimg.cn/sports/transform/20170316/Z36J-fychtth0888126.jpg', '??123', '2017-03-17 13:18:05', '2017-03-17 13:18:05', '0');
INSERT INTO `goods` VALUES ('3', '99', '99', '100.00', 'http://n.sinaimg.cn/sports/transform/20170316/Z36J-fychtth0888126.jpg', '99', '2017-03-30 20:36:31', '2017-03-30 20:36:31', '1');
INSERT INTO `goods` VALUES ('4', '唐俊2', '唐俊摘要', '3.00', 'http://img1.selfimg.com.cn/uedselfcms/2015/04/15/1429096246_wagmrK.jpg', '测试乱码1', '2017-03-21 22:19:00', '2017-03-21 22:19:00', '1');
INSERT INTO `goods` VALUES ('5', '6666', '?6', '66667.00', 'http://n.sinaimg.cn/news/1_img/cfp/8437149d/20170329/3SVo-fyctevp9151678.jpg', '编辑', '2017-03-30 20:04:44', '2017-03-30 20:04:44', '0');
INSERT INTO `goods` VALUES ('6', '新品', '2017-03-29', '112.00', 'http://n.sinaimg.cn/news/1_img/cfp/8437149d/20170329/3SVo-fyctevp9151678.jpg', '新品', '2017-03-29 21:35:05', '2017-03-29 21:35:05', '1');
INSERT INTO `goods` VALUES ('7', '小米6', '小米6稳坐1999价位 配置优秀存储挨刀', '1999.00', 'http://cms-bucket.nosdn.127.net/catchpic/f/fe/fee45227446929787260c92b0216d9eb.jpg?imageView&thumbnail=550x0', '       小米6系列预计将在4月份与我们见面，这款手机将继续其性价比旗舰的路线。从此前各方面的消息暗示，小米6应该国内骁龙835机型的首发，显然这款手机还是我们所熟悉的配方。\n      另据各渠道消息爆料，小米6的售价依旧保持在1999元价位，不过小米也抗不住目前存储芯片的价格上涨，因此在小米6的存储上砍了一刀。\n小米6存储版本与价格：\n4GB RAM + 32GB ROM   1999元\n4GB RAM + 64GB ROM   2299元\n6GB RAM + 128GB ROM  2699元\n小米米6 Plus存储版本与价格：\n4GB RAM + 64GB ROM   2599元\n6GB RAM + 128GB ROM  2999元\n8GB RAM + 256GB ROM  3499元\n与目前的小米5S相比，小米6除了常规的硬件升级之外，主要区别还是在于存储规格，尤其是低价位的1999元上，小米6的内部存储空间，要比小米5S小了一半。\n据此前的消息称，小米6将在4月11日发布，但是小米6 Plus是否能在此时发布，则还是未知之数。\n编辑点评：关于小米6的各种规格，目前还没有什么靠谱的消息。不过若是小米6想要卖1999元的话，在成本上扬的2017年，其必然会进行某种妥协以控制成本，存储空间的缩水，似乎也就在所难免了。', '2017-03-30 20:41:13', '2017-03-30 20:41:13', '1');
INSERT INTO `goods` VALUES ('9', '来个新产品', '代码review', '2.50', 'https://images2015.cnblogs.com/news/24442/201703/24442-20170330061647311-1799825275.png', '最终测试hahahaasdf', '2017-07-01 11:47:32', '2017-07-01 11:47:32', '1');
INSERT INTO `goods` VALUES ('10', '面试技巧', '技术类应届生，简历制作与面试技巧', '89.00', 'https://static.nowcoder.com/live/4/217x145.png', '针对技术类应届生的简历制作技巧，面试中经常遇到的问题及解答，校招面试中常犯的错误，在校招技术类面试中面试官看中的细节等。目前该课程参与限时特惠，89元即可购买此课程以及另外三门课程组成的套餐', '2017-07-01 12:01:52', '2017-07-01 12:01:52', '1');
INSERT INTO `goods` VALUES ('11', '周琦', '周琦，路还远，走稳每一步', '12.22', 'https://i3.hoopchina.com.cn/blogfile/201707/02/BbsImg149898462514257_18770201179323_450x243.jpg?x-oss-process=image/resize,w_800/format,webp', '从去年夏天被火箭在第43顺位选中后，下个赛季，我们就可以在NBA赛场上看到又一个中国人的身影了。\n当然，周琦能登陆NBA是一件振奋人心的事情，但也希望大家能给他一定的成长空间，因为毕竟他的实力还没到在NBA稳稳站稳脚跟的地步。\n那先来说说对周琦有利的几个消息吧。\n火箭为了交易来保罗，送走了年轻内线哈勒尔，并且目前与内内的合同中产生了一定的分歧，而且安德森由于是一个投射型大前锋，不会长时间站在内线中。所以，周琦与欧努阿库，是目前卡佩拉身后的替补人选。\n当然，内内有可能与火箭谈妥，而欧努阿库在上赛季仅出场了5场，其中只在最后无关紧要的常规赛中出场了3场，而他大部分时间都在发展联盟打球。所以综上所述，周琦在火箭队中的位置，大概是内线的第三号人选的样子。\n并且，由于这次签下的是四年的合同，所以给周琦锻炼的时间还是有很多的，他跟在球队中的训练中，会比在国内进步的更多。\n\n火箭目前阵中有两个顶级控卫，这对于周琦是个好消息。本赛季可以看到，卡佩拉在与哈登的挡拆配合中如鱼得水，而周琦在挡拆方面也很不错，更有了保罗这位挡拆大师的存在，周琦就会得到很多他们制造出来的机会从而得分。\n哈登在赛季中说过，有时候他知道自己有机会去进攻，但看到队友已经跑到了空位上，为了能让大家参与到进攻中，即使冒着失误的风险也要将球传给位置更好的队友，因为这样才能让队友感受到自己的付出能有回报。\n这是优秀控卫的一种态度，要将球队融合在一起，让大家都参与到比赛中来。而相比哈登，保罗的助攻失误比控制的极好，所以相信在他们俩的带领下，周琦融入火箭的体系的速度会快得多。\n两位明星控卫，这可是当时姚明都没有的待遇。', '2017-07-04 19:09:35', '2017-07-04 19:09:35', '1');

-- ----------------------------
-- Table structure for myorder
-- ----------------------------
DROP TABLE IF EXISTS `myorder`;
CREATE TABLE `myorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `goodsId` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `goodsPrice` double(10,2) NOT NULL,
  `totalPrice` double(10,2) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myorder
-- ----------------------------
INSERT INTO `myorder` VALUES ('1', '1', '4', '1', '1.00', '1.00', '2017-03-17 09:48:29');
INSERT INTO `myorder` VALUES ('2', '1', '3', '2', '99.00', '198.00', '2017-03-20 23:00:34');
INSERT INTO `myorder` VALUES ('3', '1', '6', '1', '112.00', '112.00', '2017-03-30 22:16:20');
INSERT INTO `myorder` VALUES ('4', '1', '7', '6', '1999.00', '11994.00', '2017-07-01 11:57:25');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `goodsIds` varchar(255) NOT NULL,
  `goodsNums` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('1', '1', '', '', '2017-07-01 11:57:25', '2017-07-01 11:57:25');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'buyer', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `user` VALUES ('2', 'seller', 'e10adc3949ba59abbe56e057f20f883e', '1');
