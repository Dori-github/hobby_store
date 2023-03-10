-- 공간대여
create table space(
   SPACE_NUM NUMBER NOT NULL , 
	MEM_NUM NUMBER NOT NULL , 
	SPACE_DATE DATE DEFAULT sysdate NOT NULL , 
	SPACE_CONTENT CLOB NOT NULL , 
	SPACE_HIT NUMBER DEFAULT 0 NOT NULL , 
	SPACE_LIMIT NUMBER(30,0), 
	SPACE_PHOTO_NAME VARCHAR2(20 BYTE) NOT NULL , 
	SPACE_PHOTO_NAME2 VARCHAR2(20 BYTE), 
	SPACE_PHOTO_NAME3 VARCHAR2(20 BYTE), 
	SPACE_PHOTO_NAME1 VARCHAR2(20 BYTE), 
	SPACE_ZIPCODE VARCHAR2(5 BYTE) NOT NULL , 
	SPACE_ADDRESS1 VARCHAR2(90 BYTE) NOT NULL , 
	SPACE_ADDRESS2 VARCHAR2(90 BYTE) NOT NULL , 
	SPACE_NP NUMBER(30,0) NOT NULL , 
	SPACE_PRICE NUMBER(10,0) NOT NULL , 
	CATE_NUM NUMBER NOT NULL , 
	SPACE_PHOTO BLOB NOT NULL , 
	SPACE_PHOTO1 BLOB, 
	SPACE_PHOTO2 BLOB, 
	SPACE_PHOTO3 BLOB, 
	SPACE_NAME VARCHAR2(20 BYTE) NOT NULL , 
	SPACE_MDATE DATE,

    constraint space_pk primary key (space_num),
    constraint space_fk foreign key (mem_num)
                        references member (mem_num)                    
     
     
);      
    
create sequence space_seq;


--공간대여 후기 좋아요
create table space_reply_fav(
    fav_num number not null,
    space_num number not null,
    mem_num number not null,
    
    constraint space_reply_fav_pk primary key (fav_num),
    constraint space_reply_fav_fk foreign key (space_num)
                           references space (space_num),
    constraint space_reply_fav_fk2 foreign key (mem_num)
                           references member (mem_num)

	  
);
create sequence space_reply_fav_seq;


--공간대여 예약
create table space_time(
    space_num number not null,
    space_reg_date varchar2(100) not null,
    space_reg_time varchar2(100) not null,
   
    constraint space_time_fk foreign key (space_num)
                           references space (space_num)
);



--공간대여 후기
create table space_reply(
    reply_num number not null,
    space_num number not null,
    mem_num number not null,
    reply_content varchar2(900) not null,
    reply_date date default sysdate not null,
    order_num number not null,
    reply_photo varchar2(150) null,
    reply_mdate date,
    star_auth number(1,1) not null,
   
    constraint space_reply_pk primary key (reply_num),
    constraint space_reply_fk1 foreign key (space_num)
                           references space (space_num),
    constraint space_reply_fk2 foreign key (mem_num)
                           references member (mem_num),
    constraint space_reply_fk3 foreign key (order_num)
                           references orders (order_num)
);


--공간대여 후기 좋아요
create table space_reply_fav(
    fav_num number not null,
    reply_num number not null,
    mem_num number not null,
   
    constraint space_reply_fav_pk primary key (fav_num),
    constraint space_reply_fav_fk1 foreign key (reply_num)
                           references space_reply (reply_num),
    constraint space_reply_fav_fk2 foreign key (mem_num)
                           references member (mem_num)

     
);
create sequence space_reply_fav_seq;

--공간대여별점
create table space_star(
    star_num number not null,
    space_num number not null,
    mem_num number not null,
    star_auth number(1) not null,
   
    constraint space_star_pk primary key (star_num),
    constraint space_star_fk1 foreign key (space_num)
                           references space (space_num),
    constraint space_star_fk2 foreign key (mem_num)
                           references member (mem_num)                          
);

--공간대여 게시물 좋아요
create table space_fav(
    fav_num number not null,
    space_num number not null,
    mem_num number not null,
    
    constraint space_fav_pk primary key (fav_num),
    constraint space_fav_fk1 foreign key (space_num)
                           references space (space_num),
    constraint space_fav_fk2 foreign key (mem_num)
                           references member (mem_num)                           
);

create sequence space_fav_seq;

--공간대여 카테고리--
create table space_cate(
    cate_num number not null,
    cate_name varchar2(30) not null,
    cate_parent number not null,
    
    constraint space_cate_pk primary key (cate_num)                          
);
create sequence space_cate_seq;

