/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 26/04/2023 16:43:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123');

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作者id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名字',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '1男0女',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday_time` datetime NULL DEFAULT NULL COMMENT '生日',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作者' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (14, '余华', 1, '/img/authorPic/1680856130207800.jpg', '1960-04-03 00:00:00', '浙江杭州', '1983年开始创作，同年进入浙江省海盐县文化馆。1984年，开始发表小说。其作品长篇小说《活着》和《许三观卖血记》同时入选百位批评家和文学编辑评选的“九十年代最具有影响的十部作品”。1998年，获意大利格林扎纳·卡佛文学奖。2002年获澳大利亚悬念句子文学奖。2005年，获得中华图书特殊贡献奖。2008年凭借作品《兄弟》，获得第一届法国《国际信使》外国小说奖。2022年6月13日，其长篇小说《文城》荣获第十届“春风悦读榜”白金图书奖；9月17日，凭借长篇小说《兄弟》获第20届“亚斯纳亚·波利亚纳文学奖”最佳外语作品奖。');
INSERT INTO `author` VALUES (15, '刘慈欣', 1, '/img/authorPic/1682492130269刘慈欣.jpg', '1963-06-01 00:00:00', '山西', '1963年6月出生于北京，祖籍信阳市罗山，山西阳泉长大。\n\n1985年毕业于华北水利水电学院（现华北水利水电大学）水电工程系。\n\n1985年10月参加工作，后于山西娘子关电厂任计算机工程师。\n\n1989年创作科幻小说《超新星纪元》首版和《中国2185》，但未发表。\n\n20世纪90年代开始向《科幻世界》杂志社投稿。\n\n1999年6月，在《科幻世界》首次发表两篇作品《鲸歌》和《微观尽头》，同年首次凭借《带上她的眼睛》获得1999年中国科幻银河奖一等奖。\n\n2000年发表短篇小说《地火》、《流浪地球》，凭借《流浪地球》获得中国科幻银河奖特等奖。\n\n2001年发表短篇小说《乡村教师》、《微纪元》、《全频带阻塞干扰》等，凭借《全频带阻塞干扰》和《乡村教师》首度同时获得银河奖两项奖项。\n\n2002年至2005年间，刘慈欣凭借一系列中短篇小说连续获得中国科幻银河奖，期间的主要作品有《朝闻道》、《思想者》、《地球大炮》、《诗云》、《镜子》、《赡养人类》等。\n\n2003年1月和2004年6月先后出版长篇小说《超新星纪元》和《球状闪电》。');
INSERT INTO `author` VALUES (17, '今何在', 1, '/img/authorPic/1682492788808今何在.jpg', '1977-12-23 00:00:00', '江西', '今何在（1977年-）,原名曾雨，江西南昌人，毕业于厦门大学。[1]网络写手，畅销书作者。\n\n今何在代表作品有《悟空传》《若星汉天空》《九州·羽传说》等。今何在与张五常、黄仁宇、莫言等一同被内地媒体评选为“2001年中国最受传媒关注的十大著书人”。其作品《悟空传》获第二届网络原创文学作品“最佳小说奖”和“最佳人气小说奖”，入选新京报“网络文学十年十本书”，排名第一。');
INSERT INTO `author` VALUES (18, '基普·S·索恩', 1, '/img/authorPic/1682493395494基普索恩.jpg', '1940-06-01 01:00:00', '欧美', '基普·S·索恩（Kip Stephen Thorne，1940年6月1日-），出生于美国犹他州洛根，毕业于普林斯顿大学，美国理论物理学家，加州理工学院费曼理论物理学教授，2017年诺贝尔物理学奖获得者。\n\n他联合创建引力波天文台LIGO，在引力波理论和观测上做出了重大贡献，奠定了引力波探测的理论基础，开创了引力波波形计算以及数据分析的研究方向。');
INSERT INTO `author` VALUES (19, '鲁迅', 1, '/img/authorPic/1682493587616鲁迅.jpg', '1881-09-25 00:00:00', '浙江', '鲁迅（1881年9月25日－1936年10月19日），本名周树人，曾用名周樟寿，曾字豫山，后改豫才，浙江绍兴人，著名文学家、思想家、革命家、教育家、民主战士，五四新文化运动的重要参与者，中国现代文学的奠基人之一，代表作有《呐喊》《彷徨》等。');
INSERT INTO `author` VALUES (20, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '广东', 'test');
INSERT INTO `author` VALUES (21, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '重庆', 'test');
INSERT INTO `author` VALUES (22, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '四川', 'test');
INSERT INTO `author` VALUES (23, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '浙江', 'test');
INSERT INTO `author` VALUES (24, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '云南', 'test');
INSERT INTO `author` VALUES (25, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '青海', 'test');
INSERT INTO `author` VALUES (26, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '湖南', 'test');
INSERT INTO `author` VALUES (27, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '湖南', 'test');
INSERT INTO `author` VALUES (28, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '重庆', 'test');
INSERT INTO `author` VALUES (29, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '北京', 'test');
INSERT INTO `author` VALUES (30, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '广东', 'test');
INSERT INTO `author` VALUES (31, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '广西', 'test');
INSERT INTO `author` VALUES (32, 'test', 0, '/img/authorPic/hhh.jpg', '2023-04-08 00:00:00', '陕西', 'test');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书id',
  `author_id` int(11) NULL DEFAULT NULL COMMENT '作者id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `create_time` datetime NULL DEFAULT NULL COMMENT '书创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书封面',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书资源地址',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '上传资源的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (6, 14, '余华-活着', '《活着》讲述了在大时代背景下，随着内战、三反五反、大跃进、“文化大革命”等社会变革，徐福贵的人生和家庭不断经受着苦难，到了最后所有亲人都先后离他而去，仅剩下年老的他和一头老牛相依为命。小说以普通、平实的故事情节讲述了在急剧变革的时代中福贵的不幸遭遇和坎坷命运，在冷静的笔触中展现了生命的意义和存在的价值，揭示了命运的无奈，与生活的不可捉摸。', '2023-04-06 15:10:45', '2023-04-06 15:10:45', '/img/bookPic/1680764933061活着.jpg', '/book/1680765045351活着.pdf', NULL);
INSERT INTO `book` VALUES (19, 14, '余华-许三观卖血记', '《许三观卖血记》中讲述了一个悲喜交加的感人故事。许三观靠着卖血渡过了人生的一个个难关，战胜了命运强加给他的惊涛骇浪，而当他老了，知道自己的血再也没有人要时，精神却崩溃了。小说中平凡的小人物挣扎于浮沉的时代，用他们并不厚实的臂膀不断拍打出激荡的浪花，观望着模糊的前路，而其间的苦难与悲情却又清晰可见，平凡之家的悲剧喜剧在余华细腻的笔触下显得可笑而又可怜。在讽刺幽默的行文之间又可看出人物苦中作乐的无奈，以及现实与理想之间永远也无法抹平的鸿沟。', '2023-04-26 10:50:26', '2023-04-26 10:50:26', '/img/bookPic/1682477426336Snipaste_2023-04-26_10-49-49.png', '/book/1682477360522许三观卖血记 (余华) (Z-Library).pdf', NULL);
INSERT INTO `book` VALUES (20, 14, '余华-在细雨中呼喊', '在细雨中呼喊》是二十世纪九十年代的经典文本，是余华的第一部长篇力作。小说描述了一位江南少年的成长经历和心灵历程。', '2023-04-26 10:58:08', '2023-04-26 10:58:08', '/img/bookPic/1682477888839Snipaste_2023-04-26_10-57-47.png', '/book/1682477812458在细雨中呼喊 (余华) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (21, 15, '刘慈欣-全频带阻塞干扰', '这是一个场面浩大而惨烈的故事。21世纪的某年，以美国为首的北约发起了对俄罗斯的全面攻击。在残酷的国土保卫战中，俄军的坦克洪流仍旧无力抵挡北约军队由高科技电子制导武器支援下开展的进攻，节节败退。唯一的办法，是制造一场阻塞所有电子频带的电磁干扰，使双方的电子仪器同时失灵，拉回双方电子战领域的平衡。于是，俄军主将列夫森科元帅的儿子，俄宇航员米沙驾驶着“万年风雪号”飞船撞向太阳，引起了一场巨大的磁暴，致使地球上的通讯悉数中断。人类回到了马可尼之前的时代，两军士兵们再次以最原始的方式展开了战斗......', '2023-04-26 15:37:42', '2023-04-26 15:37:42', '/img/bookPic/1682494662715Snipaste_2023-04-26_15-37-25.png', '/book/1682494629069全频带阻塞干扰 (刘慈欣) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (22, 15, '刘慈欣-三体', '《三体》是刘慈欣创作的长篇科幻小说系列，由《三体》《三体2：黑暗森林》《三体3：死神永生》组成，第一部于2006年5月起在《科幻世界》杂志上连载，第二部于2008年5月首次出版，第三部则于2010年11月出版。', '2023-04-26 15:40:29', '2023-04-26 15:40:29', '/img/bookPic/1682494829202Snipaste_2023-04-26_15-39-07.png', '/book/1682494819662三体 (刘慈欣) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (23, 15, '刘慈欣-朝闻道', '《朝闻道》是当代作家刘慈欣创作的中篇科幻小说。\r\n《朝闻道》里，写了这样一群知识分子，在宇宙排险员到来后，愿意以死换取自己研究领域的终极真理。小说似乎是在叩问人生的目的，和宇宙、世界的目的，这与讨论物的意义和人类生存方式的“耗费”理论，在某种程度上，是可以相互阐释的。 \r\n《朝闻道》中充满了哲学意味和宗教意蕴，对于宇宙最深处真理的探寻，对于人类求知精神的礼赞，这些内涵丰富意味深长的内容都是建立在科学的外壳之下，进行思维的碰撞与纠葛。', '2023-04-26 15:42:40', '2023-04-26 15:42:40', '/img/bookPic/1682494960936Snipaste_2023-04-26_15-41-36.png', '/book/1682494948099朝闻道 (刘慈欣) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (24, 17, '今何在-悟空传', '当命运早已注定，而你却无法改变时，你会做些什么？是做一只望着月亮哭泣的猪？还是反抗到粉身碎骨的石猴？无论我们怎样努力，却终究得不到认可，世间的规则真是如此么？', '2023-04-26 15:50:06', '2023-04-26 15:50:06', '/img/bookPic/1682495406856Snipaste_2023-04-26_15-48-45.png', '/book/1682495394638悟空传 (今何在) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (25, 18, '基普·S·索恩-《 黑洞与时间弯曲》——爱因斯坦的幽灵', '《第一推动宇宙系列：黑洞与时间弯曲》：自1915年爱因斯坦广义相对论横空出世以来，本世纪一些最杰出的头脑就在探索着它的奥秘，其中有些东西—如黑洞和时间机器—是爱因斯坦自己都感到难以想象的。著名物理学家基普·S索恩也一直在探索着。现在，他以动人的记述描绘了这些现象，为我们解释了他们所认识的宇宙。', '2023-04-26 15:53:05', '2023-04-26 15:53:05', '/img/bookPic/1682495585665Snipaste_2023-04-26_15-51-43.png', '/book/1682495571667黑洞与时间弯曲.pdf', NULL);
INSERT INTO `book` VALUES (26, 19, '鲁迅-故事新编', '《故事新编》重新编写了一些神话故事和历史故事，作品内容从古代的神话传说到20世纪30年代的现实，涉及到许多“古人”“故事”，又穿插了不少现代人的生活情节', '2023-04-26 15:55:18', '2023-04-26 15:55:18', '/img/bookPic/1682495718925Snipaste_2023-04-26_15-54-42.png', '/book/1682495708965故事新编（果麦经典） (鲁迅) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (27, 19, '鲁迅-呐喊', '《呐喊》是现代文学家鲁迅的短篇小说集，收录鲁迅于1918年至1922年所作的14篇短篇小说，1923年由北京新潮社出版，现编入《鲁迅全集》第1卷。小说集真实地描绘了从辛亥革命到五四运动时期的社会生活，从革命民主主义出发，抱着启蒙主义目的和人道主义精神，揭示了种种深层次的社会矛盾，对旧时中国的制度及部分陈腐的传统观念进行了深刻的剖析和比较彻底的否定，表现出对民族生存浓重的忧患意识和对社会变革的强烈希望', '2023-04-26 15:57:16', '2023-04-26 15:57:16', '/img/bookPic/1682495836689Snipaste_2023-04-26_15-56-29.png', '/book/1682495827239呐喊 (鲁迅) (Z-Library).epub', NULL);
INSERT INTO `book` VALUES (28, 19, '鲁迅-朝花夕拾', '此文集作为“回忆的记事”，多侧面地反映了作者鲁迅青少年时期的生活，形象地反映了他的性格和志趣的形成经过。前七篇反映他童年时代在绍兴的家庭和私塾中的生活情景，后三篇叙述他从家乡到南京，又到日本留学，然后回国教书的经历；揭露了半封建半殖民地社会种种丑恶的不合理现象，同时反映了有抱负的青年知识分子在旧中国茫茫黑夜中，不畏艰险，寻找光明的困难历程，以及抒发了作者对往日亲友、师长的怀念之情', '2023-04-26 15:58:59', '2023-04-26 15:58:59', '/img/bookPic/1682495939790Snipaste_2023-04-26_15-58-21.png', '/book/1682495932175朝花夕拾 (鲁迅原著，周作人解说，止庵编订) (Z-Library).epub', NULL);

-- ----------------------------
-- Table structure for book_list
-- ----------------------------
DROP TABLE IF EXISTS `book_list`;
CREATE TABLE `book_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书单id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书单标题',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书单图片',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '风格',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建书单的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_list
-- ----------------------------
INSERT INTO `book_list` VALUES (25, '余华作品集', '/img/bookListPic/1680771890707800.jpg', '余华作品集', '文学', NULL);
INSERT INTO `book_list` VALUES (36, '鲁迅作品集', '/img/bookListPic/1682496230387鲁迅.jpg', '鲁迅作品集', '小说', NULL);
INSERT INTO `book_list` VALUES (37, '基普索恩作品', '/img/bookListPic/1682496258492基普索恩.jpg', '基普索恩作品', '科学', NULL);
INSERT INTO `book_list` VALUES (38, '今何在作品集', '/img/bookListPic/1682496280782今何在.jpg', '今何在', '小说', NULL);
INSERT INTO `book_list` VALUES (39, '刘慈欣作品集', '/img/bookListPic/1682496393580刘慈欣.jpg', '刘慈欣作品集', '小说', NULL);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0单本1书单2作者',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `book_list_id` int(11) NULL DEFAULT NULL COMMENT '书单id',
  `collect_time` datetime NULL DEFAULT NULL COMMENT '收藏时间',
  `author_id` int(11) NULL DEFAULT NULL COMMENT '作者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (7, 9, 0, 6, NULL, '2023-04-06 19:55:44', NULL);
INSERT INTO `collect` VALUES (16, 10, 2, NULL, NULL, '2023-04-12 16:20:38', 14);
INSERT INTO `collect` VALUES (17, 10, 1, NULL, 25, '2023-04-12 16:32:51', NULL);
INSERT INTO `collect` VALUES (18, 10, 0, 6, NULL, '2023-04-16 23:32:29', NULL);
INSERT INTO `collect` VALUES (19, 10, 1, NULL, 37, '2023-04-26 16:16:14', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0单本1书单',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书编号',
  `book_list_id` int(11) NULL DEFAULT NULL COMMENT '书单编号',
  `comment_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `up` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (5, 1, 0, 6, NULL, '2023-04-06 20:05:27', '这是一个test', 3);
INSERT INTO `comment` VALUES (7, 1, 1, NULL, 25, '2023-04-06 20:24:37', 'test', 0);
INSERT INTO `comment` VALUES (11, 9, 1, NULL, 25, '2023-04-10 21:01:00', 'test', 0);
INSERT INTO `comment` VALUES (12, 9, 1, NULL, 25, '2023-04-10 21:01:46', '这是一条评论', 0);
INSERT INTO `comment` VALUES (13, 9, 1, NULL, 25, '2023-04-10 21:15:06', '评论', 0);
INSERT INTO `comment` VALUES (14, 9, 1, NULL, 25, '2023-04-10 21:17:09', 'test', 0);
INSERT INTO `comment` VALUES (15, 9, 1, NULL, 25, '2023-04-10 21:20:03', 'ces', 0);
INSERT INTO `comment` VALUES (16, 9, 1, NULL, 25, '2023-04-10 21:20:32', 'zui', 0);
INSERT INTO `comment` VALUES (17, 9, 1, NULL, 25, '2023-04-10 21:22:32', 'wu', 0);
INSERT INTO `comment` VALUES (18, 9, 1, NULL, 25, '2023-04-10 21:22:41', '???', 0);
INSERT INTO `comment` VALUES (19, 10, 0, 6, NULL, '2023-04-11 22:21:01', '评论测试', 0);
INSERT INTO `comment` VALUES (20, 10, 1, NULL, 25, '2023-04-16 22:13:39', 'test', 0);
INSERT INTO `comment` VALUES (21, 10, 1, NULL, 25, '2023-04-16 22:13:42', 'test', 0);
INSERT INTO `comment` VALUES (22, 10, 1, NULL, 25, '2023-04-16 22:13:44', 'test', 0);
INSERT INTO `comment` VALUES (23, 10, 1, NULL, 25, '2023-04-16 22:13:46', 'test', 0);
INSERT INTO `comment` VALUES (24, 10, 1, NULL, 25, '2023-04-16 22:13:48', 'testt', 0);
INSERT INTO `comment` VALUES (25, 10, 1, NULL, 25, '2023-04-16 22:13:50', 'test', 0);
INSERT INTO `comment` VALUES (26, 10, 1, NULL, 25, '2023-04-16 22:13:52', 'test', 0);
INSERT INTO `comment` VALUES (27, 10, 0, 20, NULL, '2023-04-26 16:33:53', '这是评论', 0);

-- ----------------------------
-- Table structure for list_book
-- ----------------------------
DROP TABLE IF EXISTS `list_book`;
CREATE TABLE `list_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书单内容id',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '单本id',
  `book_list_id` int(11) NULL DEFAULT NULL COMMENT '书单id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_id`(`book_id`, `book_list_id`) USING BTREE COMMENT '书单书籍唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书单包含的单本的标识' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_book
-- ----------------------------
INSERT INTO `list_book` VALUES (10, 6, 25);
INSERT INTO `list_book` VALUES (40, 19, 25);
INSERT INTO `list_book` VALUES (41, 20, 25);
INSERT INTO `list_book` VALUES (46, 21, 39);
INSERT INTO `list_book` VALUES (48, 22, 39);
INSERT INTO `list_book` VALUES (47, 23, 39);
INSERT INTO `list_book` VALUES (49, 24, 38);
INSERT INTO `list_book` VALUES (45, 25, 37);
INSERT INTO `list_book` VALUES (42, 26, 36);
INSERT INTO `list_book` VALUES (44, 27, 36);
INSERT INTO `list_book` VALUES (43, 28, 36);

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'rank编号',
  `book_list_id` int(11) NULL DEFAULT NULL COMMENT '书单编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `score` int(11) NULL DEFAULT NULL COMMENT '评分',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '评分类型0单本1书单',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '书本id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`book_list_id`, `user_id`) USING BTREE COMMENT '歌单用户评分唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评分表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (2, NULL, 1, 5, 0, 6);
INSERT INTO `rank` VALUES (3, 25, 1, 5, 1, NULL);
INSERT INTO `rank` VALUES (19, 25, 7, 6, 1, NULL);
INSERT INTO `rank` VALUES (20, 25, 8, 8, 1, NULL);
INSERT INTO `rank` VALUES (23, 25, 9, 10, NULL, NULL);
INSERT INTO `rank` VALUES (24, NULL, 10, 9, NULL, 6);
INSERT INTO `rank` VALUES (25, 25, 10, 7, NULL, NULL);
INSERT INTO `rank` VALUES (27, 30, 10, 7, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `sex` tinyint(1) NOT NULL COMMENT '1男0女',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `avator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像（地址）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `test`(`username`) USING BTREE COMMENT '唯一约束'
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ceshi', '123', 0, '18083832551', '18083832551@test.com', '2004-03-05 00:00:00', 'test', '云南', '/img/userPic/1682340120223buyaoshuohua.jpg', '2023-03-17 21:00:39', '2023-04-26 09:02:07');
INSERT INTO `user` VALUES (7, 'zhangsan', '123', 1, '18083832551', '1739568597@qq.com', '2012-03-01 00:00:00', 'test', '江苏', '/img/userPic/16824757301632539871862623611.jpg', '2023-03-27 15:50:10', '2023-04-26 10:22:10');
INSERT INTO `user` VALUES (8, '测试员1', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (9, 'postman', '123', 1, '18083832551', '18083832551@163.com', '2002-11-28 00:00:00', '测试', '测试', '/img/userPic/1680760565204user.jpg', '2023-04-06 13:36:55', '2023-04-26 09:03:18');
INSERT INTO `user` VALUES (10, 'test', '123', 0, '18083832551', '18083832551@qq.com', '1996-09-13 00:00:00', 'test', '天津', '/img/userPic/16824708861203285340745228795.jpg', '2023-04-08 21:54:55', '2023-04-26 09:02:57');
INSERT INTO `user` VALUES (11, '测试员', '12345', 1, '15912435420', '15912435420@163.com', '1985-04-01 00:00:00', 'test', '云南', '/img/userPic/16824708962452539871862623611.jpg', '2023-04-11 14:19:27', '2023-04-26 09:01:36');
INSERT INTO `user` VALUES (12, 'test1', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (13, 'test2', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 10:07:21');
INSERT INTO `user` VALUES (19, 'test3', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (20, 'test4', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (21, 'test5', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (22, 'test6', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (23, 'test7', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (24, 'test8', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (25, 'test9', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (26, 'test10', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (27, 'test11', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (28, 'test12', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (29, 'test13', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (30, 'test14', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (31, 'test15', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (32, 'test16', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (33, 'test17', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (34, 'test18', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (35, 'test19', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (36, 'test20', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (37, 'test21', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (38, 'test22', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (39, 'test23', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (40, 'test24', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (41, 'test25', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (42, 'test26', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (43, 'test27', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (44, 'test28', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (45, 'test29', '123', 0, '1234', '123@qq.com', '1982-03-02 00:00:00', 'test', '内蒙古', '/img/userPic/168033853387318878614648960788.jpg', '2023-03-27 17:01:19', '2023-04-26 09:02:35');
INSERT INTO `user` VALUES (46, 'test123', '123', 1, '1234', '122@qq.com', '2023-04-26 00:00:00', 'test', '内蒙古', '/img/userPic/16824757996831560025319294L5.jpg', '2023-04-26 10:23:07', '2023-04-26 10:23:19');
INSERT INTO `user` VALUES (47, 'test12344', '123', 1, '1234', '1234@qq.com', '2023-04-27 00:00:00', '123', '吉林', '/img/userPic/16824760127221560025319294L5.jpg', '2023-04-26 10:26:40', '2023-04-26 10:26:52');
INSERT INTO `user` VALUES (48, '1111111111', '123', 0, '11111', '11111@qqq.com', '2023-04-24 00:00:00', '11111', '广西', '/img/userPic/16824761913231598961549702zenuo.jpeg', '2023-04-26 10:28:45', '2023-04-26 10:29:51');

SET FOREIGN_KEY_CHECKS = 1;
