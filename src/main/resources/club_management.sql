drop database if exists club_management;

create database club_management;
use club_management;

#用户表
DROP TABLE IF EXISTS User;
CREATE TABLE User
(
    user_id     INT AUTO_INCREMENT COMMENT '用户主键'
        PRIMARY KEY,
    username    VARCHAR(32) UNIQUE NOT NULL COMMENT '用户名',
    password    VARCHAR(32)        NOT NULL COMMENT '密码',
    email       VARCHAR(32) COMMENT '邮箱',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_username_password (username, password) COMMENT '用户名-密码索引'
);
INSERT INTO User (username, password, email)
VALUES ('张三', 'zhangsan1234', 'zhangsan@example.com'),
       ('李四', 'lisi1234', 'lisi@example.com'),
       ('王五', 'wangwu1234', 'wangwu@example.com'),
       ('小红', 'xiaohong1234', 'xiaohong@example.com'),
       ('小明', 'xiaoming1234', 'xiaoming@example.com'),
       ('小花', 'xiaohua1234', 'xiaohua@example.com'),
       ('刘备', 'liubei1234', 'liubei@example.com'),
       ('关羽', 'guanyu1234', 'guanyu@example.com'),
       ('张飞', 'zhangfei1234', 'zhangfei@example.com'),
       ('赵云', 'zhaoyun1234', 'zhaoyun@example.com'),
       ('大乔', 'daqiao1234', 'daqiao@example.com'),
       ('小乔', 'xiaoqiao1234', 'xiaoqiao@example.com'),
       ('孙权', 'sunquan1234', 'sunquan@example.com'),
       ('周瑜', 'zhouyu1234', 'zhouyu@example.com'),
       ('诸葛亮', 'zhugeliang1234', 'zhugeliang@example.com'),
       ('孙尚香', 'sunshangxiang1234', 'sunshangxiang@example.com'),
       ('曹操', 'caocao1234', 'caocao@example.com'),
       ('司马懿', 'simayi1234', 'simayi@example.com'),
       ('貂蝉', 'diaochan1234', 'diaochan@example.com'),
       ('周仓', 'zhoucang1234', 'zhoucang@example.com'),
       ('马超', 'machao1234', 'machao@example.com'),
       ('黄忠', 'huangzhong1234', 'huangzhong@example.com'),
       ('孙坚', 'sunjian1234', 'sunjian@example.com'),
       ('太史慈', 'taishici1234', 'taishici@example.com'),
       ('张角', 'zhangjiao1234', 'zhangjiao@example.com'),
       ('张宝', 'zhangbao1234', 'zhangbao@example.com'),
       ('于吉', 'yuji1234', 'yuji@example.com'),
       ('庞德', 'pangde1234', 'pangde@example.com'),
       ('徐庶', 'xushu1234', 'xushu@example.com');


#管理员表
DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin
(
    admin_id    INT AUTO_INCREMENT COMMENT '管理员主键'
        PRIMARY KEY,
    username    VARCHAR(32) UNIQUE NOT NULL COMMENT '管理员名',
    password    VARCHAR(32)        NOT NULL COMMENT '密码',
    email       VARCHAR(24) COMMENT '邮箱',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
);
INSERT INTO Admin (username, password, email)
VALUES ('admin', 'admin1234', 'admin@example.com');


#社团表
DROP TABLE IF EXISTS Club;
CREATE TABLE Club
(
    club_id        INT AUTO_INCREMENT COMMENT '社团主键'
        PRIMARY KEY,
    club_name      VARCHAR(16) NOT NULL COMMENT '社团名称',
    description    TEXT        NOT NULL COMMENT '简介描述',
    contact_info   VARCHAR(64) NOT NULL COMMENT '联系方式',
    activity_space varchar(32) NOT NULL COMMENT '活动场地',
    president_id   INT         NOT NULL COMMENT '社长ID',
    INDEX idx_president_id (president_id) COMMENT '社长ID索引'
);
INSERT INTO Club (club_name, description, contact_info, activity_space, president_id)
VALUES ('篮球俱乐部', '我们是一个热爱篮球的团体，不论你是初学者还是高手，都欢迎加入我们的大家庭！',
        'basketball_club@example.com', '体育馆', 12),
       ('读书会', '欢迎喜欢阅读的同学加入我们的读书会，每月举办书评交流和阅读分享活动，一起探讨文学之美！',
        'bookclub@example.com', '图书馆', 9),
       ('音乐会', '我们是一个热爱乐曲的社团，致力于分享自己喜欢的音乐！',
        'environment_association@example.com', '校园周边', 7),
       ('舞蹈团', '舞蹈团是一个让舞者展现自我风采的舞台，无论你是街舞还是古典舞的爱好者，都欢迎加入我们！',
        'dance_group@example.com', '舞蹈室', 17),
       ('游戏社', '游戏社是一个汇聚游戏爱好者的集体，我们举办各类游戏比赛和主题活动，让游戏之乐无处不在！',
        'gaming_club@example.com', '游戏厅', 1),
       ('艺术团', '艺术团是一个展示学生才艺的平台，我们举办音乐会、舞台剧等各类文艺活动，欢迎喜欢表演的同学加入！',
        'art_group@example.com', '多功能厅', 8),
       ('志愿者协会', '志愿者协会是一个关爱社会、热心公益的团体，我们定期组织各类公益活动，积极参与社区建设！',
        'volunteer_association@example.com', '社区中心', 2);


#社团注册表
DROP TABLE IF EXISTS Club_application;
CREATE TABLE Club_application
(
    application_id INT AUTO_INCREMENT COMMENT '社团申请表主键'
        PRIMARY KEY,
    president_id   INT                NOT NULL COMMENT '社长ID',
    club_name      VARCHAR(32) UNIQUE NOT NULL COMMENT '社团名称',
    description    TEXT               NOT NULL COMMENT '简介描述',
    contact_info   VARCHAR(32)        NOT NULL COMMENT '联系方式',
    activity_space varchar(32)        NOT NULL COMMENT '活动场地',
    status         ENUM ('apply','agree','reject') COMMENT '审核状态',
    INDEX idx_club_status (status) COMMENT '审核状态索引'
);
INSERT INTO Club_application (president_id, club_name, description, contact_info, activity_space, status)
VALUES (24, '编程爱好者', '致力于提高编程技能和解决问题能力的社团', 'code_club@example.com', '图书馆一楼', 'apply'),
       (4, '摄影协会', '记录美好瞬间，分享摄影技巧和作品的社团', 'photoclub@example.com', '校园各处', 'apply');

#活动表
DROP TABLE IF EXISTS Activity;
CREATE TABLE Activity
(
    activity_id INT AUTO_INCREMENT COMMENT '活动主键'
        PRIMARY KEY,
    club_id     INT                NOT NULL COMMENT '社团ID',
    theme       VARCHAR(16) UNIQUE NOT NULL COMMENT '活动主题',
    description TEXT COMMENT '活动描述',
    start_time  TIMESTAMP          NOT NULL COMMENT '开始时间',
    end_time    TIMESTAMP          NOT NULL COMMENT '结束时间',
    location    VARCHAR(16)        NOT NULL COMMENT '活动场地',
    amount      decimal(10, 2)     NOT NULL COMMENT '活动缴费',
    INDEX idx_club_id (club_id) COMMENT '社团ID索引'
);
INSERT INTO Activity (club_id, theme, description, start_time, end_time, location, amount)
VALUES (1, '篮球比赛', '与其他社团进行友谊赛', '2024-04-05 10:00:00', '2024-04-05 12:00:00', '体育馆', 50.00),
       (2, '经典作品交流会', '分享阅读心得，推荐好书', '2024-04-30 15:00:00', '2024-04-30 17:00:00', '图书馆', 5.00),
       (3, '音乐会', '展示社团成员的音乐才华', '2024-04-10 18:00:00', '2024-04-10 21:00:00', '音乐厅', 30.00),
       (4, '舞蹈表演', '展示社团成员的舞蹈能力', '2024-04-20 13:00:00', '2024-04-20 15:00:00', '华山操场', 7.00),
       (5, '华农KPL', '五五开黑节，校内KPL', '2024-04-25 19:00:00', '2024-04-25 21:00:00', '活动交流中心', 30.00),
       (6, '美术展览', '展示社团成员的艺术作品', '2024-04-15 14:00:00', '2024-04-15 17:00:00', '美术馆', 20.00),
       (7, '志愿者活动', '参与社区清洁活动', '2024-05-05 09:00:00', '2024-05-05 12:00:00', '五山社区公园', 3.00);


#活动参与表
DROP TABLE IF EXISTS Activity_Participation;
CREATE TABLE Activity_Participation
(
    participation_Id INT AUTO_INCREMENT COMMENT '活动参与表ID'
        PRIMARY KEY,
    activity_id      INT     NOT NULL COMMENT '活动ID',
    user_id          INT     NOT NULL COMMENT '用户ID',
    is_signed        boolean NOT NULL COMMENT '是否签到',
    INDEX idx_aid_uid (activity_id, user_id) COMMENT '活动id-用户id索引'
);
INSERT INTO Activity_Participation (activity_id, user_id, is_signed)
VALUES (1, 12, false),
       (2, 9, false),
       (3, 7, false),
       (4, 17, false),
       (5, 1, false),
       (6, 8, false),
       (7, 2, false),  #以上社长报名活动

       (1, 14, false), #报名篮球比赛
       (1, 19, false),
       (1, 18, false),
       (1, 21, false),
       (1, 6, false),

       (2, 10, false), #作品交流会
       (2, 21, false),
       (2, 22, false),
       (2, 29, false),

       (3, 23, false), #音乐会
       (3, 25, false),
       (3, 29, false),
       (3, 15, false),

       (4, 18, false), #舞蹈表演
       (4, 20, false),
       (4, 24, false),
       (4, 19, false),

       (5, 4, false),  #华农kpl
       (5, 28, false),
       (5, 24, false),
       (5, 18, false),

       (6, 5, false),  #美术展览
       (6, 17, false),
       (6, 27, false),
       (6, 24, false),

       (7, 7, false),  #志愿者活动
       (7, 26, false),
       (7, 20, false),
       (7, 25, false);

#费用表
DROP TABLE IF EXISTS Fee;
CREATE TABLE Fee
(
    fee_id      INT AUTO_INCREMENT COMMENT '费用主键'
        PRIMARY KEY,
    activity_id INT            NOT NULL COMMENT '活动ID',
    user_id     INT            NOT NULL COMMENT '用户ID',
    amount      DECIMAL(10, 2) NOT NULL COMMENT '缴费金额',
    is_paid     BOOLEAN        NOT NULL COMMENT '缴费状态',
    INDEX idx_aid_uid (activity_id, user_id) COMMENT '活动id-用户id索引'
);
INSERT INTO Fee (activity_id, user_id, amount, is_paid)
VALUES (1, 12, 50.00, false),
       (2, 9, 5.00, false),
       (3, 7, 30.00, false),
       (4, 17, 7.00, false),
       (5, 1, 30.00, false),
       (6, 8, 20.00, false),
       (7, 2, 3.00, false),   #以上社长报名活动

       (1, 14, 50.00, false), #报名篮球比赛
       (1, 19, 50.00, false),
       (1, 18, 50.00, false),
       (1, 21, 50.00, false),
       (1, 6, 50.00, false),

       (2, 10, 5.00, false),  #作品交流会
       (2, 21, 5.00, false),
       (2, 22, 5.00, false),
       (2, 29, 5.00, false),

       (3, 23, 30.00, false), #音乐会
       (3, 25, 30.00, false),
       (3, 29, 30.00, false),
       (3, 15, 30.00, false),

       (4, 18, 7.00, false),  #舞蹈表演
       (4, 20, 7.00, false),
       (4, 24, 7.00, false),
       (4, 19, 7.00, false),

       (5, 4, 30.00, false),  #华农kpl
       (5, 28, 30.00, false),
       (5, 24, 30.00, false),
       (5, 18, 30.00, false),

       (6, 5, 20.00, false),  #美术展览
       (6, 17, 20.00, false),
       (6, 27, 20.00, false),
       (6, 24, 20.00, false),

       (7, 7, 3.00, false),   #志愿者活动
       (7, 26, 3.00, false),
       (7, 20, 3.00, false),
       (7, 25, 3.00, false);


#成员表
DROP TABLE IF EXISTS Member;
CREATE TABLE Member
(
    member_id INT AUTO_INCREMENT COMMENT '成员主键'
        PRIMARY KEY,
    user_id   INT                                         NOT NULL COMMENT '用户ID',
    club_id   INT                                         NOT NULL COMMENT '社团ID',
    position  ENUM ('alreadyQuit','applyQuit','applyJoin',
        'member','cadreMan', 'vicePresident','president') NOT NULL COMMENT '社团职位/状态',
    join_date TIMESTAMP                                   NOT NULL COMMENT '加入时间',
    INDEX idx_user_id (user_id) COMMENT '用户id索引'
);
INSERT INTO Member (user_id, club_id, position, join_date)
VALUES (12, 1, 'president', '2022-08-01 00:00:00'),
       (9, 2, 'president', '2023-07-01 00:00:00'),
       (7, 3, 'president', '2023-06-01 00:00:00'),
       (17, 4, 'president', '2023-01-01 00:00:00'),
       (1, 5, 'president', '2023-05-01 00:00:00'),
       (8, 6, 'president', '2023-03-01 00:00:00'),
       (2, 7, 'president', '2023-10-01 00:00:00'),  #以上是插入社长数据

       (11, 1, 'member', '2023-11-02 00:00:00'),    #向篮球俱乐部加入数据
       (14, 1, 'applyJoin', '2023-03-23 00:00:00'),
       (16, 1, 'applyJoin', '2023-06-14 00:00:00'),
       (19, 1, 'cadreMan', '2023-12-28 00:00:00'),
       (18, 1, 'member', '2023-09-10 00:00:00'),
       (21, 1, 'vicePresident', '2023-01-21 00:00:00'),
       (6, 1, 'member', '2023-06-22 00:00:00'),
       (22, 1, 'applyQuit', '2023-09-05 00:00:00'),

       (7, 2, 'applyQuit', '2023-09-28 00:00:00'),  #向读书会加入数据
       (8, 2, 'applyJoin', '2023-01-11 00:00:00'),
       (10, 2, 'cadreMan', '2023-12-12 00:00:00'),
       (21, 2, 'member', '2023-09-05 00:00:00'),
       (22, 2, 'vicePresident', '2023-07-01 00:00:00'),
       (29, 2, 'member', '2023-08-08 00:00:00'),

       (2, 3, 'applyQuit', '2023-10-09 00:00:00'),  #向音乐会加入数据
       (13, 3, 'applyJoin', '2023-01-02 00:00:00'),
       (23, 3, 'cadreMan', '2023-04-18 00:00:00'),
       (25, 3, 'member', '2023-09-08 00:00:00'),
       (29, 3, 'vicePresident', '2023-12-09 00:00:00'),
       (15, 3, 'member', '2023-02-06 00:00:00'),

       (3, 4, 'applyQuit', '2023-10-14 00:00:00'),  #向舞蹈团加入数据
       (10, 4, 'applyJoin', '2023-07-21 00:00:00'),
       (18, 4, 'cadreMan', '2023-02-08 00:00:00'),
       (20, 4, 'member', '2023-07-01 00:00:00'),
       (24, 4, 'vicePresident', '2023-09-17 00:00:00'),
       (19, 4, 'member', '2023-09-28 00:00:00'),

       (21, 5, 'applyQuit', '2023-09-14 00:00:00'), #向游戏社加入数据
       (14, 5, 'applyJoin', '2023-08-16 00:00:00'),
       (4, 5, 'cadreMan', '2023-12-27 00:00:00'),
       (28, 5, 'member', '2023-04-19 00:00:00'),
       (24, 5, 'vicePresident', '2023-08-15 00:00:00'),
       (18, 5, 'member', '2023-09-28 00:00:00'),

       (29, 6, 'applyQuit', '2023-07-21 00:00:00'), #向艺术团加入数据
       (15, 6, 'applyJoin', '2023-03-12 00:00:00'),
       (5, 6, 'cadreMan', '2023-05-28 00:00:00'),
       (17, 6, 'member', '2023-01-26 00:00:00'),
       (27, 6, 'vicePresident', '2023-10-22 00:00:00'),
       (24, 6, 'member', '2023-11-15 00:00:00'),

       (4, 7, 'applyQuit', '2023-06-12 00:00:00'),  #向志愿者协会加入数据
       (6, 7, 'applyJoin', '2023-09-15 00:00:00'),
       (7, 7, 'cadreMan', '2023-11-26 00:00:00'),
       (26, 7, 'member', '2023-04-19 00:00:00'),
       (20, 7, 'vicePresident', '2023-12-09 00:00:00'),
       (25, 7, 'member', '2023-05-04 00:00:00');


#活动总结表
DROP TABLE IF EXISTS Summary;
CREATE TABLE Summary
(
    summary_id  int AUTO_INCREMENT COMMENT '总结表主键'
        PRIMARY KEY,
    activity_id INT COMMENT '活动ID',
    info        text NOT NULL COMMENT '总结内容'
);
INSERT INTO Summary (activity_id, info)
VALUES (1, '篮球比赛圆满结束，各队伍展现了出色的团队合作和竞技水平。'),
       (2, '经典作品交流会成功举办，参与者积极分享了各自的阅读体验和心得。'),
       (3, '音乐会吸引了大量观众，社团成员的表演受到了一致好评。'),
       (4, '舞蹈表演展现了社团成员的多样性和舞蹈才华，活动获得巨大成功。'),
       (5, '华农KPL活动吸引了众多电竞爱好者参与，活动现场气氛热烈。'),
       (6, '美术展览展出了社团成员的众多优秀作品，参观者对作品给予了高度评价。'),
       (7, '志愿者活动得到了社区的广泛支持，参与者共同努力为社区环境做出了贡献。');

#获取我参加的活动信息
DROP VIEW IF EXISTS my_activity_participation_view;
CREATE VIEW my_activity_participation_view AS
SELECT
    u.user_id,
    u.email,
    a.activity_id,
    a.theme,
    a.description,
    a.start_time AS activity_start_time,
    a.end_time AS activity_end_time,
    a.location,
    ap.is_signed,
    f.is_paid,
    f.amount
FROM
    Activity_Participation ap
        JOIN Activity a ON ap.activity_id = a.activity_id
        JOIN Fee f ON ap.activity_id = f.activity_id AND ap.user_id = f.user_id
        JOIN User u ON ap.user_id = u.user_id;

#获取我的社团信息
DROP VIEW IF EXISTS my_clubs_view;
CREATE VIEW my_clubs_view AS
SELECT
    c.club_id,
    c.club_name,
    c.description,
    c.contact_info,
    c.activity_space,
    m.user_id,
    m.position
FROM
    Club c
        LEFT JOIN Member m ON c.club_id = m.club_id;

#获取我的社团成员信息
DROP VIEW IF EXISTS my_club_members_view ;
CREATE VIEW my_club_members_view AS
SELECT
    c.club_id,
    u.username,
    u.email,
    c.club_name,
    m.position,
    m.join_date
FROM
    Member m
        LEFT JOIN  Club c ON c.club_id = m.club_id
        LEFT JOIN User u ON u.user_id = m.user_id;