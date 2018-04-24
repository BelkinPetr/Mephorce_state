-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: anketa
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `client_id` int(11) NOT NULL,
  `cl_family` varchar(45) DEFAULT NULL,
  `cl_first_name` varchar(45) DEFAULT NULL,
  `cl_sec_name` varchar(45) DEFAULT NULL,
  `cl_password` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `e_mail` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `pos` varchar(100) DEFAULT NULL,
  `hometown` varchar(45) DEFAULT NULL,
  `birthdate` varchar(100) DEFAULT NULL,
  `first_in` int(5) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (0,'Кожевников','Дмитрий','Евгеньевич','holm','89807276988','holm@mil.com','','','','',1),(1,'Фамилия','Имя','Отчество','pass',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Баранов','Михаил','Евгеньевич','pass',NULL,NULL,NULL,NULL,NULL,NULL,1),(3,'Заказов','Заказ','Заказович','pwd111','Нет данных','Нет данных','Нет данных','Нет данных','Нет данных','Нет данных',1),(4,'Новый','Заказчик','Заказович','pwd','Нет данных','Нет данных','Нет данных','Нет данных','Нет данных','Нет данных',1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `text` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `news_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `community_news`
--

DROP TABLE IF EXISTS `community_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `community_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(255) NOT NULL,
  `date` varchar(45) NOT NULL,
  `project` varchar(255) NOT NULL,
  `text` varchar(1000) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community_news`
--

LOCK TABLES `community_news` WRITE;
/*!40000 ALTER TABLE `community_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `community_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `idfiles` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `mod_id` int(11) DEFAULT NULL,
  `pr_id` int(11) DEFAULT NULL,
  `st_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idfiles`),
  KEY `prj_id_idx` (`pr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (1,'df',NULL,0,NULL,NULL,NULL),(2,'eff',NULL,1,NULL,NULL,NULL),(3,'hhh',NULL,0,NULL,NULL,NULL),(4,'Аннотация.docx','C:\\loadFiles\\Аннотация.docx',0,-1,-1,-1),(5,'Тазиева.docx','C:\\loadFiles\\Тазиева.docx',0,-1,-1,-1),(6,'1.docx','C:\\loadFiles\\1.docx',0,-1,-1,-1),(7,'Use_cases_Тазиева_ред.1.docx','C:\\loadFiles\\Use_cases_Тазиева_ред.1.docx',0,-1,-1,-1),(8,'Use_cases_Тазиева_ред.1.docx','C:\\path\\loadFiles\\loadFiles\\Use_cases_Тазиева_ред.1.docx',0,-1,-1,-1),(9,'ТТП_Тазиева.docx','C:\\path\\loadFiles\\loadFiles\\ТТП_Тазиева.docx',0,-1,-1,-1),(10,'Bezymyanny.png','C:\\path\\loadFiles\\loadFiles\\Bezymyanny.png',0,-1,-1,-1),(11,'Документ Microsoft Word.docx','C:\\server\\file_storage\\files\\loadFiles4\\Документ Microsoft Word.docx',-1,0,4,-1),(12,'KozhevnikovDE.jpg','C:\\server\\file_storage\\files\\loadFiles4\\KozhevnikovDE.jpg',-1,0,4,-1);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (0,'У82-13','2013'),(1,'У82-14','2014'),(2,'У82-15','2015');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitations`
--

DROP TABLE IF EXISTS `invitations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitations` (
  `match_id` varchar(45) NOT NULL,
  `proj_id` int(11) NOT NULL,
  `stud_id` int(11) NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitations`
--

LOCK TABLES `invitations` WRITE;
/*!40000 ALTER TABLE `invitations` DISABLE KEYS */;
INSERT INTO `invitations` VALUES ('4_1',4,1,-1),('4_11',4,11,-1),('4_12',4,12,3),('4_13',4,13,3),('4_14',4,14,2),('4_15',4,15,2),('4_4',4,4,2),('4_5',4,5,2),('4_6',4,6,2),('4_7',4,7,2),('4_8',4,8,0),('4_9',4,9,3),('5_0',5,0,-1),('5_1',5,1,-1),('5_2',5,2,1),('5_4',5,4,2),('5_6',5,6,1),('5_7',5,7,2),('5_8',5,8,1);
/*!40000 ALTER TABLE `invitations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitations_client`
--

DROP TABLE IF EXISTS `invitations_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitations_client` (
  `match_id` varchar(56) NOT NULL,
  `proj_id` int(11) NOT NULL,
  `mod_id` int(11) NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`match_id`),
  KEY `invitations_client_projects_pr_id_fk` (`proj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitations_client`
--

LOCK TABLES `invitations_client` WRITE;
/*!40000 ALTER TABLE `invitations_client` DISABLE KEYS */;
INSERT INTO `invitations_client` VALUES ('4_0',4,0,3),('4_1',4,1,2),('4_2',4,2,2),('4_4',4,4,2),('4_6',4,6,2),('4_7',4,7,2),('4_8',4,8,2),('5_0',5,0,3),('5_1',5,1,2),('5_2',5,2,2),('5_8',5,8,2),('6_0',6,0,0),('8_2',8,2,2),('8_3',8,3,2),('8_8',8,8,2),('9_0',9,0,3);
/*!40000 ALTER TABLE `invitations_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `md_pr`
--

DROP TABLE IF EXISTS `md_pr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `md_pr` (
  `id` int(11) NOT NULL,
  `taskId` int(11) DEFAULT NULL,
  `prId` int(11) DEFAULT NULL,
  `modId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `md_pr_moderators_mod_id_fk` (`modId`),
  KEY `md_pr_projects_pr_id_fk` (`prId`),
  CONSTRAINT `md_pr_moderators_mod_id_fk` FOREIGN KEY (`modId`) REFERENCES `moderators` (`mod_id`),
  CONSTRAINT `md_pr_projects_pr_id_fk` FOREIGN KEY (`prId`) REFERENCES `projects` (`pr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `md_pr`
--

LOCK TABLES `md_pr` WRITE;
/*!40000 ALTER TABLE `md_pr` DISABLE KEYS */;
/*!40000 ALTER TABLE `md_pr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mod_skill`
--

DROP TABLE IF EXISTS `mod_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mod_skill` (
  `mod_sk_id` varchar(10) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `mod_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`mod_sk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mod_skill`
--

LOCK TABLES `mod_skill` WRITE;
/*!40000 ALTER TABLE `mod_skill` DISABLE KEYS */;
INSERT INTO `mod_skill` VALUES ('0_0',0,0,0),('1_0',0,1,0),('2_0',0,2,0),('3_0',0,3,0),('4_0',0,4,0),('5_0',0,5,0),('6_1',1,6,0),('7_1',1,7,0),('8_1',1,8,0),('9_1',1,9,0);
/*!40000 ALTER TABLE `mod_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderators`
--

DROP TABLE IF EXISTS `moderators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moderators` (
  `mod_id` int(11) NOT NULL,
  `mod_family` varchar(45) DEFAULT NULL,
  `mod_first_name` varchar(45) DEFAULT NULL,
  `mod_sec_name` varchar(45) DEFAULT NULL,
  `mod_password` varchar(45) DEFAULT NULL,
  `mod_birthdate` varchar(255) DEFAULT '11.11.2011',
  `mod_email` varchar(255) DEFAULT 'pochta@mail.ru',
  `mod_hometown` varchar(255) DEFAULT 'Город',
  `mod_phone` varchar(255) DEFAULT '0000000000',
  `first_in` int(5) DEFAULT NULL,
  PRIMARY KEY (`mod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderators`
--

LOCK TABLES `moderators` WRITE;
/*!40000 ALTER TABLE `moderators` DISABLE KEYS */;
INSERT INTO `moderators` VALUES (0,'Кожевников','Дмитрий','Евгеньевич','holm','22.11.1990','emaple@gmail.com','Москва','78787878',1),(1,'Королев','Антон','Сергеевич','ant','','','','',1),(2,'Баранов','Михаил','Иванович','password',NULL,NULL,NULL,NULL,NULL),(3,'Лыско','Илья','Павлович','parol',NULL,NULL,NULL,NULL,NULL),(4,'Баранов','Илья','Павлович','pass',NULL,NULL,NULL,NULL,NULL),(5,'Аккаунт','Аккаунт','Аккаунт','ac',NULL,NULL,NULL,NULL,NULL),(6,'Новый','Аккаунт','Тест','test',NULL,NULL,NULL,NULL,NULL),(7,'Тест','Тест','Тестович','test',NULL,NULL,NULL,NULL,0),(8,'Кукуруку','Кукур','Кукурович','kuku','Нет данных','Нет данных','Нет данных','Нет данных',1),(9,'Баринов','Клей','Деревьевич','pwd111','Нет данных','Нет данных','Нет данных','Нет данных',1),(10,'Заказов','Заказ','Заказович','pwd11','','','','',1),(11,'Тестя','Тест','Тестович','pwd1','Нет данных','Нет данных','Нет данных','Нет данных',1),(12,'Тестя','Тестерович','Тестович','pwd1','','','','',1),(13,'Модер','Уебиш','Пиздатов','xyu','','','','',1);
/*!40000 ALTER TABLE `moderators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photos`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `photo_name` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `mod_id` int(11) DEFAULT NULL,
  `st_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_photo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` VALUES (3,'photo','C:\\server\\file_storage\\files\\0\\prof_pic.jpg',-1,0,-1),(4,'photo','C:\\server\\file_storage\\files\\0\\KozhevnikovDE.jpg',-1,0,-1),(5,'photo','C:\\server\\file_storage\\files\\0\\TdHK4rbBEvA.jpg',-1,0,-1),(6,'photo','C:\\server\\file_storage\\files\\0\\KozhevnikovDE.jpg',-1,0,-1),(7,'photo','C:\\server\\file_storage\\files\\0\\DAHORHF3FKc.jpg',-1,0,-1),(8,'photo','C:\\server\\file_storage\\files\\0\\KozhevnikovDE.jpg',-1,0,-1),(9,'photo','C:\\server\\file_storage\\files\\0\\пп.png',-1,0,-1),(10,'photo','C:\\server\\file_storage\\files\\0\\KozhevnikovDE.jpg',-1,0,-1);
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_mod_skill`
--

DROP TABLE IF EXISTS `project_mod_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_mod_skill` (
  `project_id` int(11) NOT NULL,
  `skil_id` int(11) NOT NULL,
  `slave_id` int(11) NOT NULL,
  `rating` int(11) DEFAULT NULL,
  `pms_id` varchar(45) NOT NULL,
  `skill_name` varchar(100) NOT NULL,
  PRIMARY KEY (`pms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_mod_skill`
--

LOCK TABLES `project_mod_skill` WRITE;
/*!40000 ALTER TABLE `project_mod_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_mod_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_skil_cat`
--

DROP TABLE IF EXISTS `project_skil_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_skil_cat` (
  `project_id` int(11) NOT NULL,
  `skil_cat_id` int(11) NOT NULL,
  `pcs_id` varchar(45) NOT NULL,
  PRIMARY KEY (`pcs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_skil_cat`
--

LOCK TABLES `project_skil_cat` WRITE;
/*!40000 ALTER TABLE `project_skil_cat` DISABLE KEYS */;
INSERT INTO `project_skil_cat` VALUES (10,1,'10_0'),(4,0,'4_0'),(5,0,'5_0'),(6,1,'6_1'),(7,0,'7_0'),(8,0,'8_0'),(9,0,'9_0');
/*!40000 ALTER TABLE `project_skil_cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `pr_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` text,
  `date_of_creation` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date_of_ready` date DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `mod_id` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `jsondata` varchar(255) DEFAULT '[]',
  `moneyDiv` int(4) DEFAULT NULL,
  `personNumber` int(4) DEFAULT NULL,
  PRIMARY KEY (`pr_id`),
  KEY `pr_modFK_idx` (`mod_id`),
  KEY `FK_ffb6klvqmnwust69pdfgau69i` (`client_id`),
  CONSTRAINT `FK_ffb6klvqmnwust69pdfgau69i` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `pr_modFK` FOREIGN KEY (`mod_id`) REFERENCES `moderators` (`mod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (4,'Проект рыбалка','Рыба́лка — занятие выуживанием рыбы из водоёма с целью прокорма или просто для развлечения. В некоторых странах рыба не погибает от удушья, а отпускается обратно в водоем спустя какое-то время. Чаще всего из повреждений остаются лишь проколы от крючка в губе или жабрах рыбы.','2015-09-15','подтвержден','2017-09-15',300,0,0,NULL,0,5),(5,'Охота','Охота — деятельность, связанная с поиском, выслеживанием, преследованием охотничьих ресурсов, их добычей, первичной переработкой и транспортировкой.Промысловая охота — охота, осуществляемая юридическими лицами и индивидуальными предпринимателями в целях заготовки, производства и продажи продукции охоты; служит для того, чтобы воспользоваться продуктами животного происхождения (мясом, жиром, мехом, кожей, костью, рогом, пухом, пером и проч. Её целью может быть также уничтожение опасных, вредных либо чрезмерно расплодившихся животных. К охоте относится и отлов животных живыми для разведения, расселения в другой местности, для использования в цирках и зоопарках, для научных исследований и т. п. Любительская и спортивная охота — охота, осуществляемая физическими лицами в целях личного потребления продукции охоты и в рекреационных целях.','2017-02-17','Есть просроченные задачи','2017-05-17',55555555,0,0,NULL,NULL,NULL),(6,'Курсовая работа  \"Теория и технология програм','Теория языка программирования (англ. programming language theory, PLT) — это область информатики, связанная с проектированием, анализом, определением характеристик и классификацией языков программирования и изучением их индивидуальных особенностей[1].Она тесно связана с другими направлениями информатики, её результаты используются в математике, в программной инженерии и лингвистике. Будучи важным направлением информатики, в теории языков программирования ведутся активные исследования, научные результаты публикуются как в специальных многочисленных журналах, посвящённых PLT, так и в общих журналах, посвящённых информатике и компьютерной обработке данных.','2016-10-09','Завершен','2017-10-09',0,0,0,NULL,NULL,NULL),(7,'Курсовая работа \"Системная инженерия\"','Системный анализ возник в эпоху разработки компьютерной техники. Успех его применения при решении сложных задач во многом определяется современными возможностями информационных технологий. Н. Н. Моисеев приводит, по его выражению, довольно узкое определение системного анализа: «Системный анализ — это совокупность методов, основанных на использовании ЭВМ и ориентированных на исследование сложных систем — технических, экономических, экологических и т.д. Результатом системных исследований является, как правило, выбор вполне определенной альтернативы: плана развития региона, параметров конструкции и т. д. Поэтому истоки системного анализа, его методические концепции лежат в тех дисциплинах, которые занимаются проблемами принятия решений: исследование операций и общая теория управления».','2016-02-16','Завершен','2016-10-09',0,0,0,NULL,NULL,NULL),(8,'ildfhiaew','nhfdskj','2016-02-16',NULL,NULL,0,0,0,NULL,0,NULL),(9,'ildfhiaew','nhfdskj','2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(10,'ildfhiaew','nhfdskj','2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(11,'ildfhiaew','nhfdskj','2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(12,'ildfhiaew','nhfdskj','2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(13,NULL,NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(14,NULL,NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(15,NULL,NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(16,NULL,NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(17,'',NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(18,'',NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(19,'',NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(20,'',NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(21,'',NULL,'2016-02-17',NULL,NULL,0,0,0,NULL,NULL,NULL),(22,'dfg','dfgdfgdfg','2016-02-25',NULL,NULL,566,0,0,NULL,NULL,NULL),(23,'Называние','Описание','2016-02-25','модератор','2016-02-29',665,0,0,NULL,NULL,NULL),(24,'Тест11111','Описание111111','2016-03-05','Есть просроченные задачи','2016-03-02',333,NULL,1,'[{\"moder_id\":0,\"status\":1},{\"moder_id\":1,\"status\":0}]',NULL,NULL),(25,'kjk','jkhkjhkj','2016-03-26','опубликован',NULL,5657,NULL,1,'[{\"moder_id\":0,\"status\":0},{\"moder_id\":1,\"status\":2},{\"moder_id\":0,\"status\":0}]',NULL,NULL),(26,'hgjhjh','ghjhj','2016-03-25','опубликован',NULL,0,NULL,1,'[{\"moder_id\":0,\"status\":0},{\"moder_id\":1,\"status\":0},{\"moder_id\":1,\"status\":0}]',NULL,NULL),(27,'uioui','ouiouio','2016-03-26','опубликован',NULL,111,NULL,1,'[{\"moder_id\":0,\"status\":3},{\"moder_id\":1,\"status\":0}]',NULL,NULL),(28,'1','11111111111','2016-03-26','опубликован',NULL,122,NULL,1,'[{\"moder_id\":0,\"status\":3}]',NULL,NULL),(29,'орл','орлорлорл','2016-03-26','опубликован',NULL,676,0,0,NULL,NULL,NULL),(30,'dfg','rftg','2016-03-26','невидим',NULL,1222,NULL,1,'[{\"moder_id\":0,\"status\":3},{\"moder_id\":1,\"status\":1},{\"moder_id\":0,\"status\":0},{\"moder_id\":0,\"status\":0},{\"moder_id\":0,\"status\":0},{\"moder_id\":0,\"status\":0},{\"moder_id\":0,\"status\":0},{\"moder_id\":1,\"status\":0}]',NULL,NULL),(31,'yu','tutyuyu','2016-04-06','опубликован',NULL,567,NULL,1,'[{\"moder_id\":0,\"status\":0},{\"moder_id\":1,\"status\":0}]',NULL,NULL),(32,'11','1111','2016-04-06','опубликован',NULL,111,NULL,1,'[{\"moder_id\":0,\"status\":0}]',NULL,NULL);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regulations`
--

DROP TABLE IF EXISTS `regulations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regulations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text CHARACTER SET utf8mb4,
  `project` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `author` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regulations`
--

LOCK TABLES `regulations` WRITE;
/*!40000 ALTER TABLE `regulations` DISABLE KEYS */;
/*!40000 ALTER TABLE `regulations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semantic_tasks`
--

DROP TABLE IF EXISTS `semantic_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semantic_tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `desctiption` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `prioritet` varchar(45) DEFAULT NULL,
  `date_of_creation` timestamp NULL DEFAULT NULL,
  `date_of_finish` timestamp NULL DEFAULT NULL,
  `proj_id` int(11) DEFAULT NULL,
  `stud_id` int(11) DEFAULT NULL,
  `results` text,
  `student` varchar(255) DEFAULT NULL,
  `statusModer` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semantic_tasks`
--

LOCK TABLES `semantic_tasks` WRITE;
/*!40000 ALTER TABLE `semantic_tasks` DISABLE KEYS */;
INSERT INTO `semantic_tasks` VALUES (1,'словарь','описать слооварь','в работе','важно',NULL,NULL,6,0,'','',1),(2,'Критерии','раскрыть критерии','в работе','важно',NULL,NULL,6,0,NULL,'',0),(3,'нормы','установить нормы','в работе','важно',NULL,NULL,7,0,'yfddr hgffiu pout fxdxet ','',1),(4,'Инструменты','какие инстументы применяются','в работе','важно',NULL,NULL,7,0,'yhtehtrjyuj','',1),(5,'Исходный текст','обозначить исходный текст','в работе','важно',NULL,NULL,6,0,NULL,'',0),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `semantic_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skil_cat`
--

DROP TABLE IF EXISTS `skil_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skil_cat` (
  `skil_cat_id` int(11) NOT NULL,
  `skil_cat_name` varchar(100) NOT NULL,
  PRIMARY KEY (`skil_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skil_cat`
--

LOCK TABLES `skil_cat` WRITE;
/*!40000 ALTER TABLE `skil_cat` DISABLE KEYS */;
INSERT INTO `skil_cat` VALUES (0,'Dance'),(1,'Fence'),(2,'Sex');
/*!40000 ALTER TABLE `skil_cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skil_list`
--

DROP TABLE IF EXISTS `skil_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skil_list` (
  `skil_id` int(11) NOT NULL,
  `skil_name` varchar(100) NOT NULL,
  `cat_id` int(11) NOT NULL,
  PRIMARY KEY (`skil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skil_list`
--

LOCK TABLES `skil_list` WRITE;
/*!40000 ALTER TABLE `skil_list` DISABLE KEYS */;
INSERT INTO `skil_list` VALUES (0,'Trip',0),(1,'Traz',0),(2,'Tttt',1),(3,'Trippo',1),(4,'Tripww',1),(5,'Treraq',1),(6,'Trewa',1),(7,'Trtey',1),(8,'Treuiy',1),(9,'Ytyerrw',1),(10,'Yrte',1),(11,'Treee',1),(12,'Trump',0);
/*!40000 ALTER TABLE `skil_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skil_match`
--

DROP TABLE IF EXISTS `skil_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skil_match` (
  `skil_cat_id` int(11) NOT NULL,
  `skil_id` int(11) NOT NULL,
  `match_id` varchar(45) NOT NULL,
  `skill_name` varchar(100) NOT NULL,
  PRIMARY KEY (`match_id`),
  KEY `match_id` (`skil_cat_id`,`skil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skil_match`
--

LOCK TABLES `skil_match` WRITE;
/*!40000 ALTER TABLE `skil_match` DISABLE KEYS */;
/*!40000 ALTER TABLE `skil_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `st_mod_task_rating`
--

DROP TABLE IF EXISTS `st_mod_task_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `st_mod_task_rating` (
  `st_mod_task_rating_id` int(11) NOT NULL,
  `students_id` int(11) DEFAULT NULL,
  `skil_cat_id` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  `mod_id` int(11) DEFAULT NULL,
  `send_to_mod_amount` int(11) DEFAULT NULL,
  `returned_by_mod_amount` int(11) DEFAULT NULL,
  `accept_by_mod` tinyint(1) DEFAULT '0',
  `task_work_rate` int(5) DEFAULT NULL,
  `abandonment` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`st_mod_task_rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `st_mod_task_rating`
--

LOCK TABLES `st_mod_task_rating` WRITE;
/*!40000 ALTER TABLE `st_mod_task_rating` DISABLE KEYS */;
INSERT INTO `st_mod_task_rating` VALUES (0,0,0,1,0,3,2,1,5,1),(1,1,1,2,0,2,1,1,4,0),(2,1,0,3,0,1,0,0,3,1),(3,0,1,4,0,4,1,0,2,0),(5,0,0,5,0,3,2,1,5,0);
/*!40000 ALTER TABLE `st_mod_task_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `st_pr`
--

DROP TABLE IF EXISTS `st_pr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `st_pr` (
  `id` int(11) NOT NULL,
  `st_id` int(11) DEFAULT NULL,
  `pr_id` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  `semtask_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stFK_idx` (`st_id`),
  KEY `prFK_idx` (`pr_id`),
  CONSTRAINT `prFK` FOREIGN KEY (`pr_id`) REFERENCES `projects` (`pr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stFK` FOREIGN KEY (`st_id`) REFERENCES `students` (`students_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `st_pr`
--

LOCK TABLES `st_pr` WRITE;
/*!40000 ALTER TABLE `st_pr` DISABLE KEYS */;
INSERT INTO `st_pr` VALUES (0,0,6,1,1),(1,0,7,3,2),(2,0,4,8,5),(3,0,7,4,3),(4,1,6,5,NULL),(7,0,6,2,4),(8,0,6,NULL,2),(9,0,23,NULL,NULL),(10,0,24,NULL,NULL),(11,0,25,NULL,NULL),(12,0,26,NULL,NULL),(13,0,27,NULL,NULL),(14,0,28,NULL,NULL),(15,0,29,NULL,NULL),(16,0,30,NULL,NULL),(17,0,31,NULL,NULL),(18,0,32,NULL,NULL);
/*!40000 ALTER TABLE `st_pr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `students_id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `st_family` varchar(45) DEFAULT NULL,
  `st_first_name` varchar(45) DEFAULT NULL,
  `st_sec_name` varchar(45) DEFAULT NULL,
  `st_pk_number` varchar(45) DEFAULT NULL,
  `bithdate` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `e_mail` varchar(100) DEFAULT NULL,
  `hometown` varchar(45) DEFAULT NULL,
  `first_in` int(5) DEFAULT NULL,
  PRIMARY KEY (`students_id`),
  KEY `stud_groupFK_idx` (`group_id`),
  CONSTRAINT `stud_groupFK` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (0,0,'Абдулова','Лилия','Лифкатовна','111','','89807276988','','',1),(1,0,'Важинский','Виталий','Александрович','222','','','','',1),(2,0,'Гудыма','Денис','Алексеевич','333',NULL,NULL,NULL,NULL,1),(3,0,'Дождев','Алексей','Игоревич',NULL,NULL,NULL,NULL,NULL,NULL),(4,0,'Исмаилов','Тургут','Гадраддин оглы',NULL,NULL,NULL,NULL,NULL,NULL),(5,0,'Карнулина','Анна','Максимовна',NULL,NULL,NULL,NULL,NULL,NULL),(6,0,'Кубарская','Алена','Александровна',NULL,NULL,NULL,NULL,NULL,NULL),(7,0,'Куц','Олеся','Олеговна',NULL,NULL,NULL,NULL,NULL,NULL),(8,0,'Мальков','Леонид','Витальевич',NULL,NULL,NULL,NULL,NULL,NULL),(9,0,'Пелых','Анастасия','Максимовна',NULL,NULL,NULL,NULL,NULL,NULL),(10,0,'Саутенкова','Татьяна','Юрьевна',NULL,NULL,NULL,NULL,NULL,NULL),(11,0,'Титов','Станислав','Вадимович',NULL,NULL,NULL,NULL,NULL,NULL),(12,0,'Шохина ','Ольга ','Ивановна',NULL,NULL,NULL,NULL,NULL,NULL),(13,0,'Яровой','Роман','Игоревич',NULL,NULL,NULL,NULL,NULL,NULL),(14,1,'Андронова','Вероника','Владимировна',NULL,NULL,NULL,NULL,NULL,NULL),(15,1,'Баранов','Михаил','Иванович',NULL,NULL,NULL,NULL,NULL,NULL),(16,1,'Белкин','Петр','Андреевич',NULL,NULL,NULL,NULL,NULL,NULL),(17,1,'Бурдаев','Павел','Олегович',NULL,NULL,NULL,NULL,NULL,NULL),(18,1,'Голубева','Анастасия','Игоревна',NULL,NULL,NULL,NULL,NULL,NULL),(19,1,'Губанов','Александр','Николаевич',NULL,NULL,NULL,NULL,NULL,NULL),(20,1,'Ларионова','Анастасия','Андреевна',NULL,NULL,NULL,NULL,NULL,NULL),(21,1,'Леонов','Никита','Алексеевич',NULL,NULL,NULL,NULL,NULL,NULL),(22,1,'Лыско','Илья','Павлович',NULL,NULL,NULL,NULL,NULL,NULL),(23,1,'Марченков','Максим','Андреевич',NULL,NULL,NULL,NULL,NULL,NULL),(24,1,'Пивоваров','Тимур','Константинович',NULL,NULL,NULL,NULL,NULL,NULL),(25,1,'Подугольников','Александр','Витальевич',NULL,NULL,NULL,NULL,NULL,NULL),(26,1,'Тазиева','Ирина','Албековна',NULL,NULL,NULL,NULL,NULL,NULL),(27,2,'Баль','Наталья','Александровна',NULL,NULL,NULL,NULL,NULL,NULL),(28,2,'Белоногова','Алёна','Дмитриевна',NULL,NULL,NULL,NULL,NULL,NULL),(29,2,'Болтасов','Александр','Максимович',NULL,NULL,NULL,NULL,NULL,NULL),(30,2,'Герасимов','Иван','Андреевич',NULL,NULL,NULL,NULL,NULL,NULL),(31,2,'Егоров','Илья','Игоревич',NULL,NULL,NULL,NULL,NULL,NULL),(32,2,'Ершов','Виктор','Ильич',NULL,NULL,NULL,NULL,NULL,NULL),(33,2,'Жордания','Георгий','Роинович',NULL,NULL,NULL,NULL,NULL,NULL),(34,2,'Ишмуратова','Элина','Альбертовна',NULL,NULL,NULL,NULL,NULL,NULL),(35,2,'Кузьмина','Виктория','Александровна',NULL,NULL,NULL,NULL,NULL,NULL),(36,2,'Кюрчева','София','Геннадьевна',NULL,NULL,NULL,NULL,NULL,NULL),(37,2,'Марченко','Анна','Сергеевна',NULL,NULL,NULL,NULL,NULL,NULL),(38,2,'Праскова','Диана','Витальевна',NULL,NULL,NULL,NULL,NULL,NULL),(39,2,'Соколов','Максим','Ильич',NULL,NULL,NULL,NULL,NULL,NULL),(40,2,'Михайлов','Ярослав','Игоревич',NULL,NULL,NULL,NULL,NULL,NULL),(41,0,'Раб','Кафедра','Зло','xyu','','','','',1);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_rate_log`
--

DROP TABLE IF EXISTS `students_rate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_rate_log` (
  `students_rate_log_id` int(11) NOT NULL,
  `students_id` int(11) DEFAULT NULL,
  `skil_cat_id` int(11) DEFAULT NULL,
  `mod_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`students_rate_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_rate_log`
--

LOCK TABLES `students_rate_log` WRITE;
/*!40000 ALTER TABLE `students_rate_log` DISABLE KEYS */;
INSERT INTO `students_rate_log` VALUES (0,0,1,0,6,'great');
/*!40000 ALTER TABLE `students_rate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_rating`
--

DROP TABLE IF EXISTS `students_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_rating` (
  `students_rating_id` int(11) NOT NULL,
  `students_id` int(11) DEFAULT NULL,
  `skil_cat_id` int(11) DEFAULT NULL,
  `informal_minus_amount` int(11) DEFAULT NULL,
  `informal_plus_amount` int(11) DEFAULT NULL,
  `informal_zero_amount` int(11) DEFAULT NULL,
  `formal_rating` float(8,3) DEFAULT NULL,
  PRIMARY KEY (`students_rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_rating`
--

LOCK TABLES `students_rating` WRITE;
/*!40000 ALTER TABLE `students_rating` DISABLE KEYS */;
INSERT INTO `students_rating` VALUES (0,0,0,1,9,0,41.766),(1,0,1,0,5,3,10.182),(2,1,0,4,3,3,27.600),(3,1,1,0,3,2,27.600);
/*!40000 ALTER TABLE `students_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_skill`
--

DROP TABLE IF EXISTS `students_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_skill` (
  `st_sk_id` varchar(10) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `stud_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`st_sk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_skill`
--

LOCK TABLES `students_skill` WRITE;
/*!40000 ALTER TABLE `students_skill` DISABLE KEYS */;
INSERT INTO `students_skill` VALUES ('0_0',0,0,0),('16_0',2,16,0),('17_0',2,17,0),('18_0',2,18,0),('1_0',0,1,0),('4_0',0,4,0),('5_0',0,5,0),('7_0',0,7,0);
/*!40000 ALTER TABLE `students_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `desctiption` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `difficulty_level` int(11) DEFAULT NULL,
  `prioritet` varchar(45) DEFAULT NULL,
  `date_of_creation` timestamp NULL DEFAULT NULL,
  `date_of_finish` timestamp NULL DEFAULT NULL,
  `proj_id` int(11) DEFAULT NULL,
  `stud_id` int(11) DEFAULT NULL,
  `results` text,
  `student` varchar(255) DEFAULT NULL,
  `statusModer` int(11) DEFAULT '0',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'Сделать словарь',NULL,'в работе',0,NULL,NULL,NULL,6,0,'Какой-то текст','Абдулова Л.',1),(2,'сделать конспект',NULL,'в работе',1,NULL,NULL,NULL,7,0,NULL,'Абдулова Л.',0),(3,'сделать словарь',NULL,'в работе',2,NULL,NULL,NULL,7,0,'Что-то ввели','Абдулова Л.',1),(4,'сделать конспект',NULL,'в работе',1,NULL,NULL,NULL,6,0,NULL,'Абдулова Л.',0),(5,'перевести текст',NULL,'в работе',3,NULL,NULL,NULL,6,1,NULL,'Смирнов С.',0),(6,'создать систему',NULL,'в работе',1,NULL,NULL,NULL,4,2,NULL,'Баранов М.',0),(7,'редактировать систему',NULL,'в рабботе',1,NULL,NULL,NULL,5,3,NULL,'Белкин П.',0);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_login` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'исполнитель','student'),(1,'заказчик','client'),(2,'модератор','prep');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-03 13:55:54
