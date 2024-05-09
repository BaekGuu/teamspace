create database if not exists Baek9;
use Baek9;

CREATE TABLE IF not exists member(
	id varchar(30) primary key,
    nickName varchar(30) unique not null,
    password varchar(30) not null,
    email varchar(30) not null
);

insert into member(id,nickName,password,email) 
values
("jaeseung","Jason","qwer1234","jaeseung@ssafy.com"),
("haram","Baek9","frontmaster","haram@gmail.com");

#insert into member(id,nickName,password,email) 
#values
#("a","Jason","b","jaeseung@ssafy.com");

update member set nickName="재승" where id="jaeseung" and password="qwer1234";
delete from member where id="a" and password="b";
select * from member;

create table if not exists board(
	board_id int auto_increment primary key,
    writer_id varchar(30) not null,
    title varchar(50) not null,
    content varchar(300) not null,
    writing_time datetime default current_timestamp, 
	foreign key (writer_id) references member(id) on delete cascade
);


insert into board(writer_id,title,content)
values
("jaeseung","frontend 담당 하람 누나","버스 감사요"),
("haram","front 1등","front 정도야 껌이지");

select * from board;