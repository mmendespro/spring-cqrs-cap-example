/*
drop table books;
drop table events;
*/

create table if not exists books (
	book_id uuid primary key,
	title varchar(50) not null,
	author varchar(50) not null,
    book_year SMALLINT not null
);

create table if not exists events (
	event_id integer auto_increment primary key,
	content TEXT
);