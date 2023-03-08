create table event_detail(
 event_num number not null,
 mem_num number not null,
 event_detail clob not null,
 constraint event_detail_pk primary key (event_num),
 constraint event_detail_fk foreign key (mem_num) references member (mem_num) 
);

create sequence event_seq;

create table event(
 event_num number not null,
 mem_num number not null,
 course_num number not null,
 items_num number not null,
 event_title varchar2(50) not null,
 event_attr number not null,
 event_end date not null,
 event_hit number default 0 not null,
 event_content varchar2(300) not null,
 event_photo varchar2(150) not null,
 event_rdate date not null,
 event_date date default SYSDATE not null,
 constraint event_pk primary key (event_num),
 constraint event_fk1 foreign key (event_num) references event_detail (event_num),
 constraint event_fk2 foreign key (mem_num) references member (mem_num),
 constraint event_fk3 foreign key (course_num) references course (course_num),
 constraint event_fk4 foreign key (items_num) references items (items_num)
);

create table event_apply(
 event_a_num number not null,
 event_num number not null,
 mem_num number not null,
 event_a_win number not null,
 event_a_date date not null,
 constraint event_apply_pk primary key (event_a_num),
 constraint event_apply_fk1 foreign key (event_num) references event (event_num),
 constraint event_apply_fk2 foreign key (mem_num) references member (mem_num),
);

create sequence event_apply_seq;

create table mypage_class(
 mypage_c_num number not null,
 course_num number not null,
 fav_num number not null,
 mem_num number not null,
 constraint mypage_class_pk primary key (mypage_c_num),
 constraint mypage_class_fk1 foreign key (course_num) references course (course_num),
 constraint mypage_class_fk2 foreign key (fav_num) references course_fav (fav_num),
 constraint mypage_class_fk3 foreign key (mem_num) references member (mem_num),
);

create table mypage_items(
 mypage_i_num number not null,
 items_num number not null,
 fav_num number not null,
 mem_num number not null,
 constraint mypage_items_pk primary key (mypage_i_num),
 constraint mypage_items_fk1 foreign key (items_num) references items (items_num),
 constraint mypage_items_fk2 foreign key (fav_num) references items_fav (fav_num),
 constraint mypage_items_fk3 foreign key (mem_num) references member (mem_num),
);