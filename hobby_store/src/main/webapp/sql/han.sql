--상품 정보 테이블 시작--
create table items(
items_num number not null,
cate_num varchar2(3) not null,
//items_name varchar2(30) not null,
//items_price number(10) not null,
//items_quantity number(5) not null,
//items_photo1 varchar2(150) not null,
//items_photo2 varchar2(150),
//items_photo3 varchar2(150),
//items_content clob not null,
//items_hit number not null,
//items_date date default sysdate not null,
//items_mdate date,
mem_num number not null,
constraint items_pk primary key (items_num),
constraint items_fk1 foreign key (mem_num) references member (mem_num)
);

create sequence items_seq;
--상품 정보 테이블 끝 --

--상품 카테고리 테이블 시작--
create table items_cate(
cate_num number not null,
cate_parent number(1,0),
cate_name varchar2(30) not null,
constraint itmes_cate_pk primary key (cate_num)
);

create sequence items_cate_seq;
--상품 카테고리 테이블 끝--

--상품 좋아요 테이블 시작--
create table items_fav (
fav_num number not null,
items_num number not null,
mem_num number not null,
constraint items_fav_pk primary key(fav_num),
constraint items_fav_fk1 foreign key(items_num) references items (items_num),
constraint items_fav_fk2 foreign key(mem_num) references member (mem_num)
);

create sequence items_fav_seq;
--상품 좋아요 테이블 끝--

--상품 후기 저장 테이블 시작--
create table items_reply(
reply_num number not null,
items_num number not null,
mem_num number not null,
order_num number not null,
reply_content varchar2(900) not null,
reply_date date default sysdate not null,
reply_mdate date,
reply_photo varchar2(150),
constraint items_reply primary key(reply_num),
constraint items_reply_fk1 foreign key(items_num) references items (items_num),
constraint items_reply_fk2 foreign key(mem_num) references member (mem_num),
constraint items_reply_fk3 foreign key(order_num) references orders (order_num)
);

create sequence items_reply_seq;
--상품 후기 저장 테이블 끝--

--상품 후기 좋아요 테이블 시작--
create table items_reply_fav (
fav_num number not null,
items_num number not null,
mem_num number not null,
constraint items_reply_fav_pk primary key(fav_num),
constraint items_reply_fav_fk1 foreign key(items_num) references items (items_num),
constraint items_reply_fav_fk2 foreign key(mem_num) references member (mem_num)
);

create sequence items_reply_fav_seq;
--상품 후기 좋아요 테이블 끝--

--상품 후기 별점 테이블 시작--
create table items_star(
star_num number not null,
items_num number not null,
mem_num number not null,
star_auth number(1) not null,
constraint items_star_pk primary key(star_num),
constraint items_star_fk1 foreign key(items_num) references items (items_num),
constraint items_star_fk2 foreign key(mem_num) references member (mem_num)
);

create sequence items_star_seq;
--상품 후기 별점 테이블 끝--


