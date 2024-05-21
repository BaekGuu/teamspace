create database if not exists Baek9;
use Baek9;

-- 저장할 여행지
create table if not exists place(
	content_id int primary key,
    address varchar(300),
    title varchar(150),
    image varchar(150),
    tel varchar(30)
);

insert into place 
values
(130869,"강원특별자치도 평창군 대관령면 경강로 5754","강원 신재생에너지전시관","http://tong.visitkorea.or.kr/cms/resource/50/2365250_image2_1.jpg",""),
(1857095,"강원특별자치도 영월군 김삿갓면 옥동장터길 36","강원 영월 김삿갓면 [슬로시티]","http://tong.visitkorea.or.kr/cms/resource/85/2026085_image2_1.jpg",""),
(130664,"강원특별자치도 춘천시 신북읍 신샘밭로 361","강원경찰박물관","http://tong.visitkorea.or.kr/cms/resource/93/2549593_image2_1.jpg",""),
(129840,"강원특별자치도 춘천시 강원대학길 1","강원대학교중앙박물관","http://tong.visitkorea.or.kr/cms/resource/33/700633_image2_1.jpg",""),
(895963,"강원특별자치도 영월군 북면 밤재로 351", "강원도 탄광문화촌","http://tong.visitkorea.or.kr/cms/resource/22/1583322_image2_1.jpg",""),
(131456,"강원특별자치도 춘천시 신샘밭로 89","강원도청소년수련관","http://tong.visitkorea.or.kr/cms/resource/27/1583527_image2_1.jpg","033-255-6601~3"),
(131676,"강원특별자치도 정선군 사북읍 하이원길 265","강원랜드 카지노","http://tong.visitkorea.or.kr/cms/resource/91/1982091_image2_1.jpg","")
;

select * 
from place ;-- join barrierFree using(content_id);

--  무장에 정보 항목값의 파라미터

create table if not exists barrierFreeParam(
	contentName varchar(50) primary key,
    parameter varchar(30)
);

insert into barrierFreeParam
values
("parking","지체장애"),
("route","지체장애"),
("publictransport","지체장애"),
("ticketoffice","지체장애"),
("promotion","지체장애"),
("wheelchair","지체장애"),
("exit" ,"지체장애"),
("elevator" ,"지체장애"),
("restroom" ,"지체장애"),
("auditorium","지체장애"),
("room","지체장애"),
("handicapetc","지체장애"),
("braileblock","시각장애"),
("helpdog","시각장애"),
("guidehuman","시각장애"),
("audioguide","시각장애"),
("bigprint","시각장애"),
("brailepromotion","시각장애"),
("guidesystem","시각장애"),
("blindhandicapetc","시각장애"),
("signguide","청각장애"),
("videoguide","청각장애"),
("hearingroom","청각장애"),
("hearinghandicapetc","청각장애"),
("stroller","영유아가족"),
("lactationroom","영유아가족"),
("babysparechair","영유아가족"),
("infantsfamilyetc","영유아가족");

select * from barrierFreeParam order by parameter;

-- 여행지 무장애 정보 테이블
create table if not exists barrierFree(
content_id int primary key,
parking varchar(300) default "",
route varchar(300) default "",
publictransport varchar(300) default "",
ticketoffice varchar(300) default "",
promotion varchar(300) default "",
wheelchair varchar(300) default "",
`exit` varchar(300) default "",
elevator varchar(300) default "",
restroom varchar(300) default "",
auditorium varchar(300) default "",
room varchar(300) default "",
handicapetc varchar(300) default "",
braileblock varchar(300) default "",
helpdog varchar(300) default "",
guidehuman varchar(300) default "",
audioguide varchar(300) default "",
bigprint varchar(300) default "",
brailepromotion varchar(300) default "",
guidesystem varchar(300) default "",
blindhandicapetc varchar(300) default "",
signguide varchar(300) default "",
videoguide varchar(300) default "",
hearingroom varchar(300) default "",
hearinghandicapetc varchar(300) default "",
stroller varchar(300) default "",
lactationroom varchar(300) default "",
babysparechair varchar(300) default "",
infantsfamilyetc varchar(300) default "",
foreign key (content_id) references place(content_id) on delete cascade
);

insert into barrierFree
values
(130869,"","","출입구까지 경사로가 설치되어 있음(수동문)_무장애 편의시설","","","","주출입구는 경사로가 있어 휠체어 접근 가능함","","","","","","","","","","","","","","","","","","","","",""),
(1857095,"장애인 주차장 있음","","","","","","","","","","","","","","","","","","","","","","","","","","",""),
(130664,"","","주차장에서 출입구까지 경사로가 설치되어 있음_무장애 편의시설","","","","주출입구는 경사로가 있어 휠체어 접근 가능함","","","","","","","","","","","","","","","","","","","","",""),
(129840,"장애인 전용 주차가능(대학교와 공용,대학본부 주로 주차)_무장애 편의시설","","출입구까지 단차 없이 연결 됨","","","","주출입구 휠체어 접근 가능함","","장애인 전용 화장실 있음(1층 로비)","","","4층 건물이나 엘리베이터가 없음","점자블록 있음(외부~주출입구 앞)_시각장애인 편의시설","","","","","","","","","","","","","","",""),
(895963,"장애인 주차장 있음(5대)_무장애 편의시설","","출입구까지 턱이 없어 휠체어 접근 가능함(우회도 이용)","","","대여가능(1대/무료)","주출입구는 턱이 없어 휠체어 접근 가능함","","장애인 화장실 있음","","","","","","","","","","","","","","","","","","",""),
(131456,"장애인 전용 주차구역 있음(2대)_무장애 편의시설","","출입구까지 경사로가 설치되어 있음","","","","주출입구는 경사로가 있어 휠체어 접근 가능함","","","","","","","","","","","","","","","","","", "","","",""),
(131676,"장애인 전용 주차구역 있음(대형주차장,주차타워,호텔 및 식음업장 주차장-카지노와 가장 가까운 장애인주차장은 운임정 주차장(6대) 및 호텔카지노 주차장)_무장애 편의시설","","","","","대여가능","단차가 없어 휠체어 접근 가능함","엘리베이터 있음","장애인 전용 화장실 있음","","장애인 전용 객실 있음(호텔카지노)","리조트 내 복지관 있음","점자유도로 있음_시각장애인 편의시설","","","","","","","","","","","카지노객실 청각장애인용 초인종 있음","대여가능(호텔에서 대여 가능하며 투숙객이 아닌 경우에도 대여 가능하나 신분증 보관이 필요함,사전문의필요)","수유실 있음(호텔 5층,카지노 이용객도 이용가능함)","","");
insert into place(content_id) values(1234);
insert into barrierFree(content_id) values(1234);

update place set
    address="a",
    title="b",
    image="c",
    tel="d"
where content_id=1234;
delete from place where content_id=1234;

select * from barrierFree;

-- 여행 계획 개요
create table if not exists plan(
	id int primary key auto_increment,
    member_id varchar(30) not null,
    plan_title varchar(50) not null,
    `description` varchar(500) not null,
    foreign key (member_id) references member(id) on delete cascade
); 

insert into plan
(member_id,plan_title,`description`)
values
("jaeseung","강원도 여행 1","강원도 여행 1 description");

insert into plan
(member_id,plan_title,`description`)
values
("haram","여행 계획 테스트","여행 계획 테스트 description");

update plan
set plan_title="update title", `description`="update description"
where id="2";

delete from plan
where member_id="jaeseung" and id="2";

select id,member_id,plan_title,description 
from plan
;
-- 여행 N일차 정보


create table if not exists plan_date(
	id int primary key auto_increment,
    plan_id int not null,
    foreign key (plan_id) references plan(id) on delete cascade
);

insert into plan_date (plan_id)
values
(1),
(1); 

insert into plan_date(plan_id) values(3);

select * from plan_date where plan_id;
delete from plan_date where id=8;

select d.id as dayId,d.plan_id,p.member_id,p.plan_title,p.description from 
plan_date as d join plan as p
on d.plan_id=p.id;
-- 여행 N일차 계획  


create table if not exists plan_detail(
	id int primary key auto_increment,
    content_id  int not null,
    date_id int not null,
    priority int not null,
    foreign key(content_id) references place(content_id) on delete cascade,
    foreign key(date_id) references plan_date(id) on delete cascade
);

insert into plan_detail (content_id,date_id,priority)
values
(130869,1,1),
(1857095,1,2),
(130664,2,2),
(129840,2,4),
(130869,3,1),
(1857095,3,2),
(130664,3,3),
(129840,3,4);

select plan_id,a.*
from plan_date join 
(select * from plan_detail join (place join barrierFree using (content_id)) using (content_id)) as a
on plan_date.id=a.date_id
where date_id=2
order by priority asc;


delete from plan_detail where id=4;

update plan_detail 
set date_id=2,priority=1
where id=2;

select plan_id,a.*
from plan_date join 
(select * from plan_detail join (place join barrierFree using (content_id)) using (content_id)) as a
on plan_date.id=a.date_id
order by date_id asc, priority asc
-- where plan_id=3;