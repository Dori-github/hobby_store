--공지사항(noti) 게시판
create table notice_board(
 noti_num number,
 noti_title varchar2(90) not null,
 noti_content clob not null,
 noti_hit number(8) default 0 not null,
 noti_date date default sysdate not null,
 noti_mdate date,
 noti_end date,
 status number(1) not null,
 photo1 blob not null,
 photo_name1 varchar2(90) not null,
 photo2 blob not null,
 photo_name2 varchar2(90) not null,
 mem_num number not null,
 constraint notice_board_pk primary key (noti_num),
 constraint notice_board_fk1 foreign key (mem_num)
                    references member (mem_num)
);

create sequence notice_board_seq;

--공지사항 좋아요
create table notice_fav(
 fav_num number,
 noti_num number not null,
 mem_num number not null,
 constraint notice_fav_pk primary key (fav_num),
 constraint notice_fav_fk1 foreign key (noti_num)
                      references notice_board (noti_num),
 constraint notice_fav_fk2 foreign key (mem_num)
                      references member (mem_num)
);
create sequence notice_fav_seq;


----------------------------------------------------------



--자유 게시판
create table free_board(
 free_num number,
 free_title varchar2(90) not null,
 free_content clob not null,
 free_hit number(8) default 0 not null,
 free_date date default sysdate not null,
 free_mdate_date date,
 free_photo varchar2(150),
 mem_num number not null,
 constraint free_board_pk primary key (free_num),
 constraint free_board_fk1 foreign key (mem_num)
                    references member (mem_num)
);

create sequence free_board_seq;

--자유게시판 좋아요
create table free_fav(
 fav_num number,
 free_num number not null,
 mem_num number not null,
 constraint free_fav_pk primary key (fav_num),
 constraint free_fav_fk1 foreign key (free_num)
                      references spboard (free_num),
 constraint free_fav_fk2 foreign key (mem_num)
                      references member (mem_num)
);
create sequence free_fav_seq;



--자유게시판 댓글
create table free_reply(
 reply_num number,
 reply_content varchar2(900) not null,
 reply_date date default sysdate not null,
 reply_mdate date,
 reply_ip varchar2(40) not null,
 free_num number not null,
 mem_num number not null,
 constraint free_reply_pk primary key (reply_num),
 constraint free_reply_fk1 foreign key (free_num)
                        references free_board (free_num),
 constraint free_reply_fk2 foreign key (mem_num)
                        references member (mem_num)
);
create sequence free_reply_seq;

--자유게시판 글 댓글 좋아요
create table free_reply_fav(
 fav_num number,
 reply_num number not null,
 mem_num number not null,
 constraint free_reply_fav_pk primary key (fav_num),
 constraint free_reply_fav_fk1 foreign key (reply_num)
                      references free_reply (reply_num),
 constraint free_reply_fav_fk2 foreign key (mem_num)
                      references member (mem_num)
);
create sequence free_reply_fav_seq;


----------------------------------------------------------

Q&A 게시판 수정함!!!!

-Q&A게시판
create table qna(
 qna_num number,
 qna_title varchar2(150) not null,
 qna_content clob not null,
 qna_hit number(8) default 0 not null,
 qna_date date default sysdate not null,
 qna_mdate date,
 items_num number,
 course_num number,
 space_num number,
 mem_num number not null,
 constraint qna_pk primary key (qna_num),
 constraint qna_fk1 foreign key (mem_num)
                    references member (mem_num),
 constraint qna_fk2 foreign key (items_num)
                    references items (items_num)
 constraint qna_fk3 foreign key (course_num)
                    references course (course_num),
 constraint qna_fk4 foreign key (space_num)
                    references space (space_num)                                      
);

create sequence qna_seq;

-Q&A 댓글 
create table qna_reply(
 re_num number,
 re_content varchar2(900) not null,
 re_date date default sysdate not null,
 re_mdate date,
 qna_num number not null,
 mem_num number not null,
 constraint qna_reply_pk primary key (re_num),
 constraint qna_reply_fk1 foreign key (qna_num)
                        references qna (qna_num),
 constraint qna_reply_fk2 foreign key (mem_num)
                        references member (mem_num)
);

create sequence qna_reply_seq;




이 아래는 사용 안함!!!



-------------------------------------------------------

-강의 Q&A게시판
create table course_qna_detail(
 qna_num number,
 qna_title varchar2(150) not null,
 qna_passwd varchar2(15),
 qna_secret number(1) not null,
 qna_state number(1) not null,
 qna_content clob not null,
 qna_hit number(8) default 0 not null,
 qna_date date default sysdate not null,
 qna_mdate date,
 qna_photo varchar2(150),
 reply_secret number(1) not null,
 reply_content varchar2(900),
 reply_date date default sysdate,
 reply_mdate date,
 mem_num number not null,
 constraint course_qna_detail_pk primary key (qna_num),
 constraint course_qna_detail_fk1 foreign key (mem_num)
                    references member (mem_num)
);

create sequence course_qna_detail_seq;


----------------------------------------------------------
-상품 Q&A게시판
create table items_qna_detail(
 qna_num number,
 qna_title varchar2(150) not null,
 qna_passwd varchar2(15),
 qna_secret number(1) not null,
 qna_state number(1) not null,
 qna_content clob not null,
 qna_hit number(8) default 0 not null,
 qna_date date default sysdate not null,
 qna_mdate date,
 qna_photo varchar2(150),
 reply_secret number(1) not null,
 reply_content varchar2(900),
 reply_date date default sysdate,
 reply_mdate date,
 mem_num number not null,
 constraint items_qna_detail_pk primary key (qna_num),
 constraint items_qna_detail_fk1 foreign key (mem_num)
                    references member (mem_num)
);

create sequence items_qna_detail_seq;


----------------------------------------------------------
-상품 Q&A게시판
create table items_qna_detail(
 qna_num number,
 qna_title varchar2(150) not null,
 qna_passwd varchar2(15),
 qna_secret number(1) not null,
 qna_state number(1) not null,
 qna_content clob not null,
 qna_hit number(8) default 0 not null,
 qna_date date default sysdate not null,
 qna_mdate date,
 qna_photo varchar2(150),
 reply_secret number(1) not null,
 reply_content varchar2(900),
 reply_date date default sysdate,
 reply_mdate date,
 mem_num number not null,
 constraint items_qna_detail_pk primary key (qna_num),
 constraint items_qna_detail_fk1 foreign key (mem_num)
                    references member (mem_num)
);

create sequence items_qna_detail_seq;