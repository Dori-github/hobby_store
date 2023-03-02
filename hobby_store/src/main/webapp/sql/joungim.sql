--강의
create table course(
    course_num number not null,
    mem_num number not null,
    cate_nums varchar2(3) not null,
    course_name varchar2(30) not null,
    course_content clob not null,
    course_photo1 blob not null,
    course_photo2 blob,
    course_photo3 blob,
    course_photo_name1 varchar2(150) not null,
    course_photo_name2 varchar2(150),
    course_photo_name3 varchar2(150),
    course_month number(3),
    course_count number(3),
    course_price number(10) not null,
    course_limit number,
    course_zipcode varchar2(5),
    course_address1 varchar2(90),
    course_address2 varchar2(90),
    course_onoff varchar2(12) not null,
    course_oneweek varchar2(9),
    course_hit number default 0 not null,
    course_date date default sysdate not null,
    course_mdate date,
    constraint course_pk primary key (course_num),
    constraint course_fk foreign key (mem_num) references member (mem_num)                     
);     
create sequence course_seq;


--강의 날짜/시간
create table course_time(
	ctime_num number,
    course_num number not null,
    mem_num number not null,
    course_reg_date varchar2 not null,
    course_reg_time varchar2 not null,
    constraint course_time_pk primary key (ctime_num),
    constraint course_time_fk foreign key (course_num) references course (course_num)
);
create sequence course_time_seq;

--강의 카테고리
create table course_cate(
    cate_num number not null,
    cate_parent number(1),
    cate_name varchar2(30) not null,
    constraint course_cate_pk primary key (cate_num)                          
);
create sequence course_cate_seq;


--강의 게시물 좋아요
create table course_fav(
    fav_num number not null,
    course_num number not null,
    mem_num number not null,
    constraint course_fav_pk primary key (fav_num),
    constraint course_fav_fk1 foreign key (course_num) references course (course_num),
    constraint course_fav_fk2 foreign key (mem_num) references member (mem_num)                           
);
create sequence course_fav_seq;



--강의 후기
create table course_reply(
    reply_num number not null,
    course_num number not null,
    mem_num number not null,
    order_num number not null,
    reply_content varchar2(900) not null,
    reply_photo varchar2(150),
    reply_date date default sysdate not null,
    reply_mdate date,
    constraint course_reply_pk primary key (reply_num),
    constraint course_reply_fk1 foreign key (course_num) references course (course_num),
    constraint course_reply_fk2 foreign key (mem_num) references member (mem_num),
    constraint course_reply_fk3 foreign key (order_num) references orders (order_num)
);
create sequence course_reply_seq;


--강의 별점
create table course_star(
    star_num number not null,
    course_num number not null,
    mem_num number not null,
    star_auth number(1) not null,
    constraint course_star_pk primary key (star_num),
    constraint course_star_fk1 foreign key (course_num) references course (course_num),
    constraint course_star_fk2 foreign key (mem_num) references member (mem_num)                           
);
create sequence course_star_seq;



--강의 후기 좋아요
create table course_reply_fav(
    fav_num number not null,
    reply_num number not null,
    mem_num number not null,
    constraint course_reply_fav_pk primary key (fav_num),
    constraint course_reply_fav_fk1 foreign key (reply_num) references course_reply (reply_num),
    constraint course_reply_fav_fk2 foreign key (mem_num) references member (mem_num)
);
create sequence course_reply_fav_seq;
