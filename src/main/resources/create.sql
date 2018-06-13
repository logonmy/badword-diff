-- 
create database if not exists `hlx_badword`; -- comment '屏蔽关键词,用于审核帖子' ;

use `hlx_badword`;

create table if not exists `tb_wj_verb`(
	`id` int(11) unsigned not null auto_increment,
    `word` varchar(50) not null comment '动词',
    `type` tinyint(3) unsigned not null comment '动词分类,1 涉枪,2 涉爆,3 管制刀具...',
    `created` datetime comment '创建时间',
    primary key(`id`)
	-- ,unique key `uk_type_word`(`type`,`word`)
) default charset=utf8mb4 comment '网监提供动词,需与同type的名词组合';

create table if not exists `tb_wj_noun`(
	`id` int(11) unsigned not null auto_increment,
    `word` varchar(50) not null comment '名词',
    `type` tinyint(3) unsigned not null comment '名词分类,1 涉枪,2 涉爆,3 管制刀具...',
    `created` datetime comment '创建时间',
    primary key(`id`)
	-- ,unique key `uk_type_word`(`type`,`word`)
) default charset=utf8mb4 comment '网监提供名词,需与同type的动词组合';


create table if not exists `tb_wj_keyword`(
	`id` int(11) unsigned not null auto_increment,
    `word` varchar(50) not null comment '专有名词',
    `type` tinyint(3) unsigned not null comment '名词分类,1 涉枪,2 涉爆,3 管制刀具...',
    `created` datetime comment '创建时间',
    primary key(`id`)
	-- ,unique key `uk_type_word`(`type`,`word`)
) default charset=utf8mb4 comment '网监提供专有名词';