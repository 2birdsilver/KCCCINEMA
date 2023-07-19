create table movie (
    movie_id number not null primary key,
    movie_title varchar2(20) not null,
    movie_category varchar2(20) not null,
    movie_score number not null,
    movie_poster blob not null,
    opening_date date not null,
    closing_date date not null,
    running_time number not null,
    movie_director varchar2(20) not null,
    movie_synopsis varchar2(50) not null,
    performer varchar2(50) not null
);

create table schedule (
    schedule_id number not null primary key,
    screen_date date not null,
    time_id number not null,
    movie_id number not null,
    theater_id number not null
);

alter table schedule
add constraint fk_time_id
foreign key (time_id) references time(time_id);

alter table schedule
add constraint fk_movie_id
foreign key (movie_id) references movie(movie_id);

alter table schedule
add constraint fk_theater
foreign key (theater_id) references theater(theater_id);


CREATE TABLE cinema (
	cinema_id	NUMBER	CONSTRAINT cinema_cinema_id_fk PRIMARY KEY,
	cinema_name	varchar2(20)	NOT NULL,
	cinema_location	varchar2(50)	NOT NULL,
	cinema_phone	varchar2(20) );
	
INSERT INTO cinema
VALUES (1, '혜화', '서울', '02-1111-2222');
 
INSERT INTO cinema
VALUES (2, '울산', '울산', '052-1111-2222');
    
INSERT INTO cinema
VALUES (3, '구미', '경북', '054-1111-2222');

INSERT INTO cinema
VALUES (4, '부천', '경기도', '031-1111-2222');

INSERT INTO cinema
VALUES (5, '인천', '인천', '032-1111-2222');


CREATE TABLE time (
	time_id NUMBER CONSTRAINT time_time_id_pk PRIMARY KEY,
	start_time	DATE NOT NULL
);


ALTER SESSION SET NLS_DATE_FORMAT = 'HH24:MI:SS';

INSERT INTO time values (1,'09:00:00');

INSERT INTO time values (2,'11:00:00');

INSERT INTO time values (3,'13:00:00');

INSERT INTO time values (4,'15:00:00');

INSERT INTO time values (5,'17:00:00');

INSERT INTO time values (6,'19:00:00');


CREATE TABLE seat (
	seat_id	NUMBER	CONSTRAINT seat_seat_id_pk PRIMARY KEY,
	theater_id	NUMBER CONSTRAINT seat_theater_id_fk REFERENCES theater(theater_id),
	seat_row_no	CHAR	NOT NULL,
	seat_col_no	NUMBER	NOT NULL
);


CREATE TABLE theater (
	theater_id	NUMBER	CONSTRAINT theater_theater_id_pk PRIMARY KEY,
	cinema_id	NUMBER	CONSTRAINT theater_cinema_id_fk REFERENCES cinema(cinema_id),
	theater_no	NUMBER	NOT NULL
);


INSERT into theater
VALUES
	(1, 1, 1);
    
    INSERT INTO theater
VALUES
	(2, 1, 2);
    
    INSERT INTO theater
VALUES
	(3, 1, 3);
    
    INSERT INTO theater
VALUES
	(4, 2, 1);
    
    INSERT INTO theater
VALUES
	(5, 2, 2);
    
    INSERT INTO theater
VALUES
	(6, 2, 3);
    
    INSERT INTO theater
VALUES
	(7, 3, 1);
    
    INSERT INTO theater
VALUES
	(8, 3, 2);
    
    INSERT INTO theater
VALUES
	(9, 3, 3);
    
    INSERT INTO theater
VALUES
	(10, 4, 1);
    
    INSERT INTO theater
VALUES
	(11, 4, 2);
    
    INSERT INTO theater
VALUES
	(12, 4, 3);
    
    INSERT INTO theater
VALUES
	(13, 5, 1);
    
    INSERT INTO theater
VALUES
	(14, 5, 2);
INSERT INTO theater 
    VALUES (15, 5, 3);

CREATE TABLE users (
    user_id VARCHAR2(20) NOT NULL PRIMARY KEY,
    user_name VARCHAR2(20) NOT NULL,
    user_password VARCHAR2(20) NOT NULL,
    user_age NUMBER NOT NULL,
    user_birth_date DATE NOT NULL,
    user_role NUMBER(1) NOT NULL
);

CREATE TABLE review (
    review_id NUMBER NOT NULL PRIMARY KEY,
    review_date DATE NOT NULL,
    review_content VARCHAR2(50) NOT NULL,
    grade NUMBER NOT NULL,
    user_id VARCHAR2(20) NOT NULL,
    movie_id NUMBER NOT NULL
);

ALTER TABLE review
ADD CONSTRAINT fk_user_id 
FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE review
ADD CONSTRAINT fk_review_movie_id 
FOREIGN KEY (movie_id) REFERENCES movie (movie_id);

create table ticket (
    ticket_id number  not null primary key,
    adult_number number not null,
    child_number  number not null,
    user_id varchar2(20) not null,
    schedule_id number not null
);

alter table ticket 
add constraint fk_ticket_user_id
foreign key (user_id)  references users(user_id);

alter table ticket 
add constraint fk_schedule_id
foreign key (schedule_id)  references schedule(schedule_id);

create table reserved_seat (
    schedule_id number not null,
    seat_id number not null,
    ticket_id number not null
);

alter table reserved_seat 
add constraint fk_seat_schedule_id
foreign key (schedule_id)  references schedule(schedule_id);

alter table reserved_seat 
add constraint fk_seat_seat_id
foreign key (seat_id)  references seat(seat_id);

alter table reserved_seat 
add constraint fk_seat_ticket_id
foreign key (ticket_id)  references ticket(ticket_id);

   