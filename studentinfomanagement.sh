#!/bin/sh
mysql -u 'root' -p'password' <<EOF

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

INSERT INTO course VALUES (1,'线性代数','张三',2,34,14),(2,'计算机组成原理','李四',3,100,14),(3,'数据库系统','王五',2,100,13),(4,'嵌入式系统','马六',3,50,13),(6,'计算机导论','周七',3,400,0),(7,'计算机结构','支教',3,200,0);

DROP TABLE IF EXISTS department;
CREATE TABLE department (
  dno char(12) NOT NULL,
  dname char(12) DEFAULT NULL,
  PRIMARY KEY (dno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO department VALUES ('hfut0001','软件学院'),('hfut0002','计算机学院'),('hfut0003','机械学院'),('hfut0004','土木学院'),('hfut0005','电子信息学院'),('hfut0006','美术学院');

DROP TABLE IF EXISTS class;
CREATE TABLE class (
  clno int(11) NOT NULL AUTO_INCREMENT,
  clname char(12) DEFAULT NULL,
  dno char(12) DEFAULT NULL,
  PRIMARY KEY (clno),
  KEY fk_class_department (dno),
  CONSTRAINT fk_class_department FOREIGN KEY (dno) REFERENCES department (dno)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

INSERT INTO class VALUES (1,'英语3班','hfut0004'),(2,'软件2016-2班','hfut0001'),(3,'计科2016-1班','hfut0002'),(4,'计科2016-2班','hfut0002'),(5,'机械2016-1班','hfut0003'),(6,'机械2016-2班','hfut0003'),(7,'土木2016-1班','hfut0004'),(11,'软件2016-1班','hfut0001'),(12,'软件2016-2班','hfut0001'),(13,'计科2016-1班','hfut0002'),(14,'计科2016-2班','hfut0002'),(15,'机械2016-1班','hfut0003'),(16,'机械2016-2班','hfut0003'),(17,'土木2016-1班','hfut0004'),(18,'土木2016-2班','hfut0004'),(19,'软件2016-1班','hfut0001'),(20,'软件2016-2班','hfut0001'),(21,'计科2016-1班','hfut0002'),(22,'计科2016-2班','hfut0002'),(23,'机械2016-1班','hfut0003'),(24,'机械2016-2班','hfut0003'),(25,'土木2016-1班','hfut0004'),(26,'土木2016-2班','hfut0004'),(27,'软件2016-1班','hfut0001'),(28,'软件2016-2班','hfut0001'),(29,'计科2016-1班','hfut0002'),(30,'计科2016-2班','hfut0002'),(31,'机械2016-1班','hfut0003'),(32,'机械2016-2班','hfut0003'),(33,'土木2016-1班','hfut0004'),(34,'土木2016-2班','hfut0004'),(35,'软件2016-1班','hfut0001'),(36,'软件2016-2班','hfut0001'),(37,'计科2016-1班','hfut0002'),(38,'计科2016-2班','hfut0002'),(39,'机械2016-1班','hfut0003'),(40,'机械2016-2班','hfut0003'),(41,'土木2016-1班','hfut0004'),(42,'土木2016-2班','hfut0004'),(43,'软件2016-1班','hfut0001'),(44,'软件2016-2班','hfut0001'),(45,'计科2016-1班','hfut0002'),(47,'机械2016-1班','hfut0003'),(48,'机械2016-2班','hfut0003'),(49,'土木2016-1班','hfut0004'),(50,'土木2016-2班','hfut0004'),(51,'软件2016-1班','hfut0001'),(52,'软件2016-2班','hfut0001'),(53,'计科2016-1班','hfut0002'),(54,'计科2016-2班','hfut0002'),(55,'机械2016-1班','hfut0003'),(56,'机械2016-2班','hfut0003'),(57,'土木2016-1班','hfut0004'),(58,'土木2016-2班','hfut0004'),(59,'软件2016-1班','hfut0001'),(60,'软件2016-2班','hfut0001'),(61,'计科2016-1班','hfut0002'),(62,'计科2016-2班','hfut0002'),(63,'机械2016-1班','hfut0003'),(64,'机械2016-2班','hfut0003'),(65,'土木2016-1班','hfut0004'),(66,'土木2016-2班','hfut0004'),(67,'软件2016-1班','hfut0001'),(68,'软件2016-2班','hfut0001'),(69,'计科2016-1班','hfut0002'),(70,'计科2016-2班','hfut0002'),(71,'机械2016-1班','hfut0003'),(72,'机械2016-2班','hfut0003'),(73,'土木2016-1班','hfut0004'),(74,'土木2016-2班','hfut0004'),(83,'英语2班','hfut0004');

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

INSERT INTO student VALUES (1,'向右',1,'男',23),(2,'周二',1,'女',20),(3,'周三',2,'男',20),(4,'周四',2,'男',20),(5,'周五',3,'女',20),(6,'周六',3,'男',20),(7,'周天',4,'男',20),(8,'周一',1,'男',20),(9,'周二',1,'女',20),(10,'周三',2,'男',20),(11,'周四',2,'男',20),(12,'周五',3,'女',20),(13,'周六',3,'男',20),(14,'周天',4,'男',20),(15,'周一',1,'男',20),(16,'周二',1,'女',20),(17,'周三',2,'男',20),(18,'周四',2,'男',20),(19,'周五',3,'女',20),(20,'周六',3,'男',20),(21,'周天',4,'男',20),(22,'周一',1,'男',20),(23,'周二',1,'女',20),(24,'周三',2,'男',20),(25,'周四',2,'男',20),(26,'周五',3,'女',20),(27,'周六',3,'男',20),(28,'周天',4,'男',20),(29,'周一',1,'男',20),(30,'周二',1,'女',20),(31,'周三',2,'男',20),(32,'周四',2,'男',20),(33,'周五',3,'女',20),(34,'周六',3,'男',20),(35,'周天',4,'男',20),(36,'周一',1,'男',20),(37,'周二',1,'女',20),(38,'周三',2,'男',20),(39,'周四',2,'男',20),(40,'周五',3,'女',20),(41,'周六',3,'男',20),(42,'周天',4,'男',20),(43,'周一',1,'男',20),(44,'周二',1,'女',20),(45,'周三',2,'男',20),(46,'周四',2,'男',20),(47,'周五',3,'女',20),(48,'周六',3,'男',20),(49,'周天',4,'男',20),(50,'周一',1,'男',20),(51,'周二',1,'女',20),(52,'周三',2,'男',20),(53,'周四',2,'男',20),(54,'周五',3,'女',20),(55,'周六',3,'男',20),(57,'xzm',1,'男',23),(58,'鲤鱼',4,'男',34),(59,'新年',34,'男',45),(60,'利于',1,'男',45),(61,'123',1,'男',234);

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

INSERT INTO sc VALUES (1,1,NULL,NULL,NULL,NULL),(1,2,NULL,NULL,NULL,NULL),(2,1,10.00,10.00,10.00,10.00),(2,2,20.00,20.00,20.00,20.00),(2,3,NULL,NULL,NULL,NULL),(2,4,NULL,NULL,NULL,NULL),(3,1,70.00,98.00,56.00,71.40),(3,2,45.00,98.00,89.00,82.90),(3,3,70.00,88.00,100.00,90.40),(3,4,70.00,88.00,100.00,90.40),(4,1,83.00,98.00,89.00,90.50),(4,2,60.00,91.00,98.00,88.30),(4,3,85.00,98.00,67.00,79.90),(4,4,89.00,98.00,61.00,77.70),(5,1,30.00,30.00,30.00,30.00),(5,2,10.00,10.00,10.00,10.00),(5,3,NULL,NULL,NULL,NULL),(5,4,70.00,83.00,100.00,88.90),(6,1,NULL,NULL,NULL,NULL),(6,2,NULL,NULL,NULL,NULL),(6,3,NULL,NULL,NULL,NULL),(6,4,80.00,67.00,87.00,79.60),(7,1,NULL,NULL,NULL,NULL),(7,2,NULL,NULL,NULL,NULL),(7,3,NULL,NULL,NULL,NULL),(7,4,NULL,NULL,NULL,NULL);

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

INSERT INTO user VALUES (1,'xxx','123','2019-07-31 23:23:44','学生'),(2,'yyy','123','2019-07-31 21:39:59','教师');

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
