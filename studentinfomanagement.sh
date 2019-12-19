#!/bin/bash
mysql -u 'root' -p 'password' <<EOF
DROP DATABASE IF EXISTS studentinfomanagement;
CREATE DATABASE studentinfomanagement DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE studentinfomanagement;

DROP TABLE IF EXISTS course;
CREATE TABLE course (
  cno int(11) NOT NULL AUTO_INCREMENT,
  cname char(12) DEFAULT NULL,
  cteacher char(8) DEFAULT NULL,
  ccredit smallint(6) DEFAULT NULL,
  maxnumofentrolled smallint(6) DEFAULT NULL,
  nownumofentrolled smallint(6) DEFAULT '0',
  PRIMARY KEY (cno)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO course VALUES (1,'线性代数','张算账',2,34,14),(2,'计算机组成原理','李指令',3,100,14),(3,'数据库系统','王安全',2,100,13),(4,'嵌入式系统','马硬件',3,50,13),(5,'计算机导论','周开发',3,400,0),(6,'计算机结构','宋富有',3,200,0),(7,'企业管理','何必',2,200,5),(8,'网络程序设计','孙武功',3,400,59),(9,'信息安全技术','张有钱',4,150,45);

DROP TABLE IF EXISTS department;
CREATE TABLE department (
  dno char(12) NOT NULL,
  dname char(12) DEFAULT NULL,
  PRIMARY KEY (dno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO department VALUES ('hfut0001','软件学院'),('hfut0002','计算机学院'),('hfut0003','机械学院'),('hfut0004','土木学院'),('hfut0005','电子信息学院'),('hfut0006','美术学院'),('hfut0007','广告学院');

DROP TABLE IF EXISTS class;
CREATE TABLE class (
  clno int(11) NOT NULL AUTO_INCREMENT,
  clname char(12) DEFAULT NULL,
  dno char(12) DEFAULT NULL,
  PRIMARY KEY (clno),
  KEY fk_class_department (dno),
  CONSTRAINT fk_class_department FOREIGN KEY (dno) REFERENCES department (dno)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

INSERT INTO class VALUES (1,'英语3班','hfut0004'),(2,'软件2016-2班','hfut0001'),(3,'计科2016-1班','hfut0002'),(4,'计科2016-2班','hfut0002'),(5,'机械2016-1班','hfut0003'),(6,'机械2016-2班','hfut0003'),(7,'土木2016-1班','hfut0004'),
(8,'软件2016-1班','hfut0001'),(9,'软件2016-3班','hfut0001'),(10,'计科2016-1班','hfut0002'),(11,'计科2016-2班','hfut0002'),(12,'机械2017-1班','hfut0003'),(13,'机械2017-2班','hfut0003'),(14,'土木2017-1班','hfut0004'),(15,'土木2017-2班','hfut0004'),
(16,'软件2017-1班','hfut0001'),(17,'软件2017-2班','hfut0001'),(18,'计科2017-1班','hfut0002'),(19,'计科2017-2班','hfut0002'),(20,'机械2018-1班','hfut0003'),(21,'机械2018-2班','hfut0003'),(22,'土木2018-1班','hfut0004'),(23,'土木2018-2班','hfut0004'),(24,'软件2018-1班','hfut0001');


DROP TABLE IF EXISTS student;
CREATE TABLE student (
  sno int(11) NOT NULL AUTO_INCREMENT,
  sname char(8) DEFAULT NULL,
  clno int(11) DEFAULT NULL,
  ssex enum('男','女'),
  sage smallint(6) DEFAULT NULL,
  PRIMARY KEY (sno),
  KEY fk_student_class (clno),
  CONSTRAINT fk_student_class FOREIGN KEY (clno) REFERENCES class (clno)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

INSERT INTO student VALUES 
(1,'向好看',1,'男',23),
(2,'周爱钱',1,'女',20),
(3,'李漂亮',1,'男',20),
(4,'丁地雷',1,'男',19),
(5,'刘芬芳',1,'女',20),
(6,'欧阳状',1,'男',23),
(7,'文开心',1,'男',20),
(8,'韦大宝',1,'男',20),
(9,'纪铁齿',1,'女',18),
(10,'花钢牙',2,'男',20),
(11,'马软件',2,'男',20),
(12,'郭智慧',2,'女',22),
(13,'张宝宝',2,'男',20),
(14,'邓灯灯',2,'男',18),
(15,'刘健身',2,'男',20),
(16,'周机灵',2,'女',20),
(17,'骆驼',2,'男',20),
(18,'司马泡',2,'男',19),
(19,'孙鼓励',3,'女',19),
(20,'周美好',3,'男',19),
(21,'邰国宝',3,'男',19),
(22,'毛苗苗',3,'男',19),
(23,'陈速度',3,'女',21),
(24,'陶核桃',3,'男',20),
(25,'李黑豆',3,'男',22),
(26,'王芝马',3,'女',20),
(27,'李东西',3,'男',21),
(28,'章帐帐',4,'男',20),
(29,'向心文',4,'男',22),
(30,'李美丽',4,'女',20),
(31,'孙骁骁',4,'男',20),
(32,'李多惠',4,'男',20),
(33,'何必娜',4,'女',19),
(34,'朱七杰',4,'男',20),
(35,'唐生生',4,'男',19),
(36,'孙大声',5,'男',20),
(37,'周二二',5,'女',20),
(38,'周三七',5,'男',22),
(39,'甄美丽',5,'男',22),
(40,'孙答应',5,'女',22),
(41,'刘常在',5,'男',22),
(42,'安小鸟',5,'男',23),
(43,'周一大',6,'男',22),
(44,'周二小',6,'女',23),
(45,'周三非',6,'男',23),
(46,'周四夸',6,'男',23),
(47,'周五棒',6,'女',22),
(48,'周六好',6,'男',22),
(49,'周天帅',6,'男',22),
(50,'周一雷',6,'男',20),
(51,'周二朋',6,'女',21),
(52,'周三壮',6,'男',22),
(53,'周四蓝',7,'男',20),
(54,'周五',7,'女',20),
(55,'周六',7,'男',20),
(56,'温煮饭',7,'男',23),
(57,'向太阳',7,'男',23),
(58,'鲤鱼',7,'男',22),
(59,'新年',7,'男',21),
(60,'利于',7,'男',22);

DROP TABLE IF EXISTS sc;
CREATE TABLE sc (
  sno int(11) NOT NULL,
  cno int(11) NOT NULL,
  taskgrade decimal(5,2) DEFAULT 0,
  expgrade decimal(5,2) DEFAULT 0,
  examgrade decimal(5,2) DEFAULT 0,
  grade decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (sno,cno),
  KEY fk_sc_course (cno),
  CONSTRAINT fk_sc_course FOREIGN KEY (cno) REFERENCES course (cno),
  CONSTRAINT fk_sc_student FOREIGN KEY (sno) REFERENCES student (sno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sc VALUES 
(1,1,80.00,85.00,90.00,100.00),
(1,2,50.00,78.00,90.00,98.00),
(2,1,10.00,10.00,10.00,10.00),
(2,2,20.00,20.00,20.00,20.00),
(2,5,NULL,NULL,NULL,NULL),
(2,4,NULL,NULL,NULL,NULL),
(3,1,70.00,98.00,56.00,71.40),
(3,2,45.00,98.00,89.00,82.90),
(3,5,70.00,88.00,100.00,90.40),
(3,4,70.00,88.00,100.00,90.40),
(4,1,83.00,98.00,89.00,90.50),
(4,2,60.00,91.00,98.00,88.30),
(4,5,85.00,98.00,67.00,79.90),
(4,4,89.00,98.00,61.00,77.70),
(5,1,30.00,30.00,30.00,30.00),
(5,2,10.00,10.00,10.00,10.00),
(5,3,80.00,80.00,80.00,90.00),
(5,8,70.00,83.00,100.00,88.90),
(6,1,NULL,NULL,NULL,NULL),
(6,2,NULL,NULL,NULL,NULL),
(6,3,NULL,NULL,NULL,NULL),
(6,4,80.00,67.00,87.00,79.60),
(7,1,NULL,NULL,NULL,NULL),
(7,7,NULL,NULL,NULL,NULL),
(7,3,NULL,NULL,NULL,NULL),
(7,4,NULL,NULL,NULL,NULL),
(8,1,NULL,NULL,NULL,NULL),
(8,2,NULL,NULL,NULL,NULL),
(8,4,10.00,10.00,10.00,10.00),
(8,3,20.00,20.00,20.00,20.00),
(9,1,70.00,98.00,56.00,71.40),
(9,2,45.00,98.00,89.00,82.90),
(9,3,70.00,88.00,100.00,90.40),
(9,4,70.00,88.00,100.00,90.40),
(10,4,89.00,98.00,61.00,77.70),
(10,1,30.00,30.00,30.00,30.00),
(10,2,10.00,10.00,10.00,10.00),
(10,3,NULL,NULL,NULL,NULL),
(11,4,70.00,83.00,100.00,88.90),
(12,1,NULL,NULL,NULL,NULL),
(13,2,NULL,NULL,NULL,NULL),
(14,3,NULL,NULL,NULL,NULL),
(15,4,80.00,67.00,87.00,79.60),
(16,1,NULL,NULL,NULL,NULL),
(17,2,NULL,NULL,NULL,NULL),
(18,3,NULL,NULL,NULL,NULL),
(18,4,NULL,NULL,NULL,NULL),
(19,1,10.00,10.00,10.00,10.00),
(19,2,20.00,20.00,20.00,20.00),
(19,5,NULL,NULL,NULL,NULL),
(20,4,NULL,NULL,NULL,NULL),
(20,1,70.00,98.00,56.00,71.40),
(20,2,45.00,98.00,89.00,82.90),
(20,5,70.00,88.00,100.00,90.40),
(21,4,70.00,88.00,100.00,90.40),
(21,1,83.00,98.00,89.00,90.50),
(21,2,60.00,91.00,98.00,88.30),
(22,5,85.00,98.00,67.00,79.90),
(22,4,89.00,98.00,61.00,77.70),
(22,1,30.00,30.00,30.00,30.00),
(23,2,10.00,10.00,10.00,10.00),
(23,3,NULL,NULL,NULL,NULL),
(23,8,70.00,83.00,100.00,88.90),
(23,7,NULL,NULL,NULL,NULL),
(25,2,NULL,NULL,NULL,NULL),
(26,3,NULL,NULL,NULL,NULL),
(26,4,80.00,67.00,87.00,79.60),
(27,1,NULL,NULL,NULL,NULL),
(27,7,NULL,NULL,NULL,NULL),
(27,3,NULL,NULL,NULL,NULL),
(27,4,97.00,NULL,NULL,NULL),
(28,1,10.00,10.00,10.00,10.00),
(28,2,20.00,20.00,20.00,20.00),
(28,3,NULL,NULL,NULL,NULL),
(29,1,70.00,98.00,56.00,71.40),
(29,2,45.00,98.00,89.00,82.90),
(29,3,70.00,88.00,100.00,90.40),
(29,4,70.00,88.00,100.00,90.40),
(30,4,89.00,98.00,61.00,77.70),
(30,1,30.00,30.00,30.00,30.00),
(30,2,10.00,10.00,10.00,10.00),
(30,3,NULL,NULL,NULL,NULL),
(31,4,70.00,83.00,100.00,88.90),
(32,1,NULL,NULL,NULL,NULL),
(33,2,NULL,NULL,NULL,NULL),
(34,3,NULL,NULL,NULL,NULL),
(35,4,80.00,67.00,87.00,79.60),
(36,1,NULL,NULL,NULL,NULL),
(37,2,NULL,NULL,NULL,NULL),
(38,3,NULL,NULL,NULL,NULL),
(38,4,NULL,NULL,NULL,NULL),
(39,1,70.00,83.00,100.00,88.90),
(39,2,NULL,NULL,NULL,NULL),
(39,3,NULL,NULL,NULL,NULL),
(39,4,80.00,67.00,87.00,79.60),
(40,1,NULL,NULL,NULL,NULL),
(40,2,NULL,NULL,NULL,NULL),
(40,3,NULL,NULL,NULL,NULL),
(40,4,NULL,NULL,NULL,NULL),
(41,4,70.00,83.00,100.00,88.90),
(42,1,NULL,NULL,NULL,NULL),
(43,2,NULL,NULL,NULL,NULL),
(44,3,NULL,NULL,NULL,NULL),
(45,4,80.00,67.00,87.00,79.60),
(46,1,NULL,NULL,NULL,NULL),
(47,2,NULL,NULL,NULL,NULL),
(48,3,NULL,NULL,NULL,NULL),
(48,4,NULL,NULL,NULL,NULL),
(49,1,70.00,83.00,100.00,88.90),
(49,4,80.00,67.00,87.00,79.60);


DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) DEFAULT NULL,
  username char(12) NOT NULL,
  password char(12) NOT NULL,
  create_time datetime DEFAULT NULL,
  level char(6) DEFAULT NULL,
  PRIMARY KEY (username),
  KEY fk_user_student (id),
  CONSTRAINT fk_user_student FOREIGN KEY (id) REFERENCES student (sno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user VALUES (1,'user','password','2019-07-31 23:23:44','学生'),(2,'root','password','2019-07-31 21:39:59','教师');

DROP VIEW IF EXISTS class_view;
CREATE
  ALGORITHM = UNDEFINED
  VIEW class_view
  AS
  SELECT
    s1.sno, s1.sname, s2.sno AS sno_temp
  FROM
    (student s1
      JOIN student s2)
  WHERE
    (s2.clno = s1.clno);

DROP VIEW IF EXISTS course_view;
CREATE
  ALGORITHM = UNDEFINED
  VIEW course_view
  AS
  SELECT
    c.cno,
    c.cname,
    c.cteacher,
    c.ccredit,
    sc.sno
  FROM
    (course c
      JOIN sc)
  WHERE
    (c.cno = sc.cno);

DROP VIEW IF EXISTS grade_view;
CREATE
  ALGORITHM = UNDEFINED
  VIEW grade_view
  AS
  SELECT
    sc.cno,
    course.cname,
    sc.taskgrade,
    sc.expgrade,
    sc.examgrade,
    sc.grade,
    sc.sno
  FROM
    (course
      JOIN sc)
  WHERE
    (course.cno = sc.cno);

DROP VIEW IF EXISTS student_view;
CREATE
  ALGORITHM = UNDEFINED
  VIEW student_view
  AS
  SELECT
    s.sno,
    s.sname,
    c.dno,
    d.dname,
    s.clno,
    c.clname,
    s.ssex,
    s.sage
  FROM
    ((class c
      JOIN student s)
      JOIN department d)
  WHERE
    ((s.clno = c.clno) AND (c.dno = d.dno));

DROP TRIGGER IF EXISTS sc_course_nownumofentrolled_delete;
CREATE trigger sc_course_nownumofentrolled_delete after delete on sc for each row 
begin 
  update course set nownumofentrolled=nownumofentrolled-1 where cno = old.cno ;
end;

DROP TRIGGER IF EXISTS sc_course_nownumofentrolled_insert;
CREATE trigger sc_course_nownumofentrolled_insert before insert on sc for each row 
begin 
  update course set nownumofentrolled=nownumofentrolled+1 where cno = new.cno;
end;

DROP TRIGGER IF EXISTS sc_grade;
CREATE trigger sc_grade before update on sc for each row 
begin
  set new.grade=0.2*new.taskgrade+0.3*new.expgrade+0.5*new.examgrade;
end;

DROP TRIGGER IF EXISTS user_date;
CREATE trigger user_date before insert on user for each row 
begin 
  set new.create_time = now();
end;

DROP TRIGGER IF EXISTS SC_UPDATE;
CREATE trigger SC_UPDATE after update on sc for each row 
begin
  IF new.taskgrade>100 OR new.taskgrade<0 OR new.expgrade>100 OR new.expgrade<0 OR	new.examgrade>100 OR new.examgrade<0
	THEN  
        delete from sc where sno=new.sno AND cno=new.cno;
  END IF;
end;

EOF
