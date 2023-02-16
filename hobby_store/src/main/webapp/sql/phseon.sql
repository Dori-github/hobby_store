--강의 장바구니
CREATE TABLE course_cart (
    cart_num number not null,
    quantity number(3) not null,
    mem_num number not null,
    course_num number not null,
    constraint course_cart_pk primary key (cart_num),
    constraint course_cart_fk1 foreign key (mem_num) 
                    references member (mem_num),
    constraint course_cart_fk2 foreign key (course_num) 
                    references member (mem_num)
);

create sequence course_cart_seq;

--상품 장바구니
CREATE TABLE item_cart (
    cart_num number not null,
    quantity number(3) not null,
    mem_num number not null,
    items_num number not null,
    constraint item_cart_pk primary key (cart_num),
    constraint item_cart_fk1 foreign key (mem_num) 
                    references member (mem_num),
    constraint item_cart_fk2 foreign key (items_num) 
                    references items (items_num)
);

create sequence item_cart_seq;

--주문
CREATE TABLE orders (
    order_num number not null,
    order_name varchar2(300) not null,
    order_status number(1) default'0' not null,
    receive_name varchar2(30),
    receive_post varchar2(5),
    receive_address1 varchar2(90),
    receive_address2 varchar2(90),
    receive_phone varchar2(15) not null,
    notice varchar2(300),
    order_date date not null,
    refund_status number(1),
    reserve_date varchar2(60) not null,
    reserve_time varchar2(30) not null,
    mem_num number not null,
    constraint order_pk primary key (order_num),
    constraint order_fk foreign key (mem_num) 
                    references member (mem_num)
);

create sequence orders_seq;

--주문 상세
CREATE TABLE orders_detail (
    detail_num number not null,
    detail_name varchar2(300) not null,
    price number(8) not null,
    quantity number(5) not null,
    order_num number not null,
    constraint order_detail_pk primary key (detail_num),
    constraint order_detail_fk foreign key (order_num) 
                    references orders (order_num)
);

create sequence orders_detail_seq;

--결제
CREATE TABLE pay (
    pay_num number not null,
    pay_date date not null,
    total_price number(8) not null,
    discount number(8) not null,
    pay_price number(8) not null,
    order_num number not null,
    constraint pay_pk primary key (pay_num),
    constraint pay_fk foreign key (order_num) 
                    references orders (order_num)
);

create sequence pay_seq;

--포인트
CREATE TABLE points (
    points_num number not null,
    saved_points number(8),
    used_points number(8),
    points_type number(1),
    saved_date date,
    expired_date varchar2(90),
    used_date date,
    mem_num number not null,
    constraint points_pk primary key (points_num),
    constraint points_fk foreign key (mem_num)
                    references member (mem_num)
);

create sequence points_seq;
