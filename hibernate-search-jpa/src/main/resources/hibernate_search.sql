
CREATE DATABASE  IF NOT EXISTS `hibernate_search` DEFAULT CHARACTER SET utf8 ;

USE `hibernate_search`;

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

insert  into `author`(`id`,`name`) values (1,'潘爱民'),(2,'徐宝文'),(3,'李志'),(4,'谢孟军'),(5,'蔡斌'),(6,'陈湘萍'),(7,'佘建伟'),(8,'赵凯'),(9,'李刚'),(10,'淘宝前端团队'),(11,'武欣'),(12,'高洛峰'),(13,'刘铭'),(14,'朱舸'),(15,'周庆成'),(16,'邓强'),(17,'武海峰'),(18,'陈晓亮'),(19,'王寒'),(20,'周雪彬'),(21,'屈光辉'),(22,'周庆成');

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

insert  into `book`(`id`,`description`,`name`,`publicationDate`) values (1,'《windows内核原理与实现》从操作系统原理的角度，详细解析了windows如何实现现代操作系统的各个关键部件，包括进程、线程、物理内存和虚拟内存的管理，windows中的同步和并发性支持，以及windows的i/o模型。在介绍这些关键部件时，本书直接以windows的源代码（wrk，windows research kernel）为参照，因而读者可以了解像windows这样的复杂操作系统是如何在x86处理器上运行的。','Windows内核原理与实现','2013-05-13'),(2,'本书是由c语言的设计者brian w. kernighan和dennis m. ritchie编写的一部介绍标准c语言及其程序设计方法的权威性经典著作。全面、系统地讲述了c语言的各个特性及程序设计的基本方法，包括基本概念、类型和表达式、控制流、函数与程序结构、指针与数组、结构、输入与输出、unix系统接口、标准库等内容。\r\n　　本书的讲述深入浅出，配合典型例证，通俗易懂，实用性强，适合作为大专院校计算机专业或非计算机专业的c语言教材，也可以作为从事计算机相关软硬件开发的技术人员的参考书。 在计算机发展的历','C程序设计语言(第2版·新版)','2003-11-07'),(3,'《go web编程》介绍如何用go语言进行web应用的开发，将go语言的特性与web开发实战组合到一起，帮读者成功地构建跨平台的应用程序，节省go语言开发web的宝贵时间。有了这些针对真实问题的解决方案放在手边，大多数编程难题都会迎刃而解。\r\n　　 在《go web编程》中，读者可以更加方便地找到各种编程问题的解决方案，内容涵盖文本处理、表单处理、session管理、数据库交互、加/解密、国际化和标准化，以及程序的部署维护等运维方面的知识，最后还介绍了一个快速开发的框架帮助您迅速进入go语言的web开发。','Go Web编程','2013-05-13'),(4,'“hadoop技术内幕”共两册，分别从源代码的角度对“common+hdfs”和mapreduce的架构设计与实现原理进行了极为详细的分析。《hadoop技术内幕：深入解析hadoop common和hdfs架构设计与实现原理》由腾讯数据平台的资深hadoop专家、x-rime的作者亲自执笔，对common和hdfs的源代码进行了分析，旨在为hadoop的优化、定制和扩展提供原理性的指导。除此之外，本书还从源代码实现中对分布式技术的精髓、分布式系统设计的优秀思想和方法，以及java语言的编码技巧、编程规范','Hadoop技术内幕：深入解析Hadoop Common和HDFS架构设计与实现原理','2013-04-22'),(5,'《Android 4高级编程(第3版)》由Android权威专家编写，涵盖了所有最新的内容，是学习使用Android 4 SDK开发移动应用程序的理想指南。本书见解深刻，帮助经验丰富的Android开发人员充分挖掘Android 4的新特性的潜力，同时讲解了Android开发的基础知识，使初学者也可以借助本书入门。作为一本以实用性为目的的指导图书，本书带领您逐步完成复杂程度越来越高的Android项目，每个项目中都引入一种新的Android平台特性，并着重指出有助于编写引人入胜的应用程序的技术和最佳实践。','Android 4高级编程(第3版)','2013-04-23'),(6,'移动互联网已经成为当今世界发展最快、市场潜力最大、前景最诱人的业务，而android则是移动互联网上市场占有率最高的平台（已远超ios，最新统计数据：android占53.7%，ios占35%）；与此同时，android应用选择了java作为其开发语言，这对于java来说也是一次极好的机会。 本书是《疯狂android讲义》的第2版。本书基于最新的android 4.2，android sdk、adt都基于android 4.2，书中每个案例、每个截图都全面升级到android 4.2。本书全面地介绍了a','疯狂Android讲义(第2版)','2013-03-15'),(7,'《javascript权威指南(第6版)》要讲述的内容涵盖javascript语言本身，以及web浏览器所实现的javascript api。本书第6版涵盖了 html5 和 ecmascript 5，很多章节完全重写，增加了当今 web 开发的最佳实践的内容，新增的章节包括 jquery 、服务器端 javascript、图形编程以及javascript式的面向对象。本书不仅适合初学者系统学习，也适合有经验的 javascript 开发者随手翻阅。','JavaScript权威指南(第6版)','2012-05-07'),(8,'本书将php开发与mysql应用相结合，分别对php和mysql做了深入浅出的分析，不仅介绍php和mysql的一般概念，而且对php和mysql的web应用做了较全面的阐述，并包括几个经典且实用的例子。\r\n　　 本书是第4版，经过了全面的更新、重写和扩展，包括php 5.3最新改进的特性（例如，更好的错误和异常处理），mysql的存储过程和存储引擎，ajax技术与web 2.0以及web应用需要注意的安全问题。 \r\n　　 php平ieimysql是非常流行的开源技术，它们非常适合快速开发数据库驱动的we','PHP和MySQL Web开发(原书第4版)','2009-04-15'),(9,'php是开发web应用系统最理想的工具，易于使用、功能强大、成本低廉、高安全性、开发速度快且执行灵活。全书以实用为目标设计，包含php开发最主流的各项技术，对每一个知识点都进行了深入详细的讲解，并附有大量的实例代码，图文并茂。系统地介绍了php的相关技术及其在实际web开发中的应用。\r\n　　 《细说php(第2版)》共六个部分，分为30个章节，每一章都是php独立知识点的总结。内容涵盖了动态网站开发的前台技术（html+css）、php编程语言的语法、php的常用功能模块和实用技巧、mysql数据库的设计','细说PHP(第2版)','2012-10-08'),(10,'《ios 6应用开发实战》是目前ios 6领域最全面系统和易于阅读的著作之一，有两大特点：第一，技术新颖，基于最新ios 6技术撰写，系统讲解开发iphone和ipad应用所需掌握的基础技术和高级技巧，以及其流程和方法；第二，易于阅读，从认知学角度进行内容规划，一个案例贯穿全书，不仅能从很大程度上降低学习的时间成本，降低阅读门槛，而且能至始至终让读者在动手实践中保持学习的热情，坚持把这《ios 6应用开发实战》读完。\r\n　　 全书共22章，可分为两个部分：基础部分（1~13章）分别介绍了开发ios应用前应','iOS 6应用开发实战','2013-04-25'),(11,'ios 平台不断发展变化，本书基于此进行了全面更新，分4 部分深入介绍ios 6 开发。第一部分主要介绍ios 6 新功能。第二部分带你熟练掌握常用工具（含表视图通知和动画图层），内容涉及cocoa 设计模式与苹果利用其解决问题的思路、通过objective-c 的arc 管理内存、正确使用表视图（含解决无限滚动等问题）、集合视图与自动布局、自定义绘图、视图动画与core animation 框架、错误处理、位置服务。第三部分介绍特定情况下要使用的工具与相关技术，涉及表视图、多任务、rest 式服务、安全','iOS 6编程实战','2013-04-08'),(12,'《cocos2d权威指南》是目前cocos2d领域内容最全面、系统和深入的一本著作，也是技术版本最新的一本著作。由国内ios和cocos2d领域的先驱和资源专家撰写，不仅系统讲解了cocos2d的使用方法、技术要点、工作原理、高级知识、开发技巧、最佳实践和性能优化，而且通过精心设计的典型案例详细讲解了cocos2d游戏设计与开发的完整过程，极具启发性和可操作性。 此外，还介绍了如何进行应用的测试与发布，以及cocos3d、cocos2d-x、cocos2d-html5、cocos2d-python等衍生技','Cocos2D权威指南','2013-03-22'),(13,'ObjectiveC 是扩展C 的面向对象编程语言，也是iPhone 开发用到的主要语言。《ObjectiveC基础教程(第2版)》结合理论知识与示例程序，全面而系统地介绍了ObjectiveC 编程的相关内容，包括ObjectiveC 在C 的基础上引入的特性、Cocoa 工具包的功能及框架，以及继承、复合、源文件组织等众多重要的面向对象编程技术。附录中还介绍了如何从其他语言过渡到ObjectiveC。 \r\n　　《ObjectiveC基础教程(第2版)》适合各类开发人员阅读。 ','ObjectiveC基础教程(第2版)','2013-04-28');

DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `book_author` (
  `book_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FK1A9A0FA16C791283` (`author_id`),
  KEY `FK1A9A0FA1A54F4403` (`book_id`),
  CONSTRAINT `FK1A9A0FA1A54F4403` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK1A9A0FA16C791283` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `book_author`(`book_id`,`author_id`) values (1,1),(2,2),(2,3),(3,4),(4,5),(4,6),(5,7),(5,8),(6,9),(7,10),(8,11),(9,12),(10,13),(10,14),(11,15),(11,16),(11,17),(11,18),(12,19),(12,20),(12,21),(13,22);
