--회원관리 테이블
--회원
create table member(
 mem_num number not null,
 mem_id varchar2(12) unique not null,
 mem_nickname varchar2(30) unique,
 mem_auth number(1) default 1 not null, --0정지회원,1탈퇴회원,2일반회원,3강사,9관리자
 mem_au_id varchar2(36), --자동 로그인에 사용되는 식별값
 constraint member_pk primary key (mem_num)
);

--회원상세 테이블 
create table member_detail(
 mem_num number not null,
 mem_name varchar2(30) not null,
 mem_photo blob,
 mem_pname varchar2(100),
 mem_pw varchar2(12) not null,
 mem_cell varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(5) not null,
 mem_address1 varchar2(90) not null,
 mem_address2 varchar2(90) not null,
 country_num number(1) not null,
 like_num number(1) not null,
 mem_date date default sysdate not null, --등록일
 mem_mdate date, --수정일
 constraint member_detail_pk primary key (mem_num),
 constraint member_detail_fk foreign key (mem_num) references member (mem_num),
 constraint member_detail_fk2 foreign key (country_num) references member_country (country_num),
 constraint member_detail_fk3 foreign key (like_num) references member_like (like_num)
);

create sequence member_seq;

-- 선호클래스 지역 테이블
create table member_country(
 country_num number(1) not null,
 country_detail varchar(20),
 constraint country_num_pk primary key (country_num)
 );
 
 --관심사 테이블 
 create table member_like(
  like_num number(1) not null,
  like_detail varchar2(20),
  constraint like_num_pk primary key (like_num)
 );
 
--채팅
--채팅방
create table sptalkroom(
 talkroom_num number,
 talkroom_name varchar2(900) not null,
 talkroom_date date default sysdate not null,
 constraint sptalkroom_pk primary key (talkroom_num)
);
create sequence sptalkroom_seq;

--채팅방 멤버
create table sptalk_member(
 talkroom_num number not null,
 mem_num number not null,
 constraint sptalkmember_fk1 foreign key (talkroom_num)
                    references sptalkroom (talkroom_num),
 constraint sptalkmember_fk2 foreign key (mem_num)
                    references member (mem_num)
);

--채팅 메시지
create table sptalk(
 talk_num number,
 talkroom_num number not null, -- 수신그룹
 mem_num number not null, -- 발신자
 message varchar2(4000) not null,
 chat_date date default sysdate not null,
 constraint sptalk_pk primary key (talk_num),
 constraint sptalk_fk1 foreign key (talkroom_num)
                references sptalkroom (talkroom_num),
 constraint sptalk_fk2 foreign key (mem_num)
                references member (mem_num)
);
create sequence sptalk_seq;

--채팅 메시지 읽기
create table sptalk_read(
 talkroom_num number not null,
 talk_num number not null,
 mem_num number not null,
 constraint read_fk foreign key (talkroom_num)
              references sptalkroom (talkroom_num),
 constraint read_fk2 foreign key (talk_num)
                     references sptalk (talk_num),
 constraint read_fk3 foreign key (mem_num)
                     references member (mem_num)           
);

