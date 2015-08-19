insert into library (id, name) values (1, 'Oui bibliotheka')
insert into library (id, name) values (2, 'Zweite bibliotheka')
insert into library (id, name) values (3, 'Tres bib')
insert into library (id, name) values (4, 'Fourth lib')
insert into library (id, name) values (5, 'Fourth lib')

insert into book (id, title, library_id) values (1, 'Pierwsza książka',1);
insert into book (id, title, library_id) values (2, 'Druga książka',2);
insert into book (id, title, library_id) values (3, 'Trzecia książka',1);
insert into book (id, title, library_id) values (4, 'Czwarta książka',2);
insert into book (id, title, library_id) values (5, 'Piąta książka',3);
insert into book (id, title, library_id) values (6, 'Szósta książka',4);
insert into book (id, title, library_id) values (7, 'Siódma książka',2);
insert into book (id, title, library_id) values (8, 'Ósma książka',4);
insert into book (id, title, library_id) values (9, 'Dziewiąta książka',3);
insert into book (id, title, library_id) values (10, 'Dziewiąta książka',2);
insert into book (id, title, library_id) values (11, 'Dziewiąta książka',5);
insert into book (id, title, library_id) values (12, 'Dziewiąta książka',5);

insert into author (id, first_name, last_name) values (4, 'Łukasz', 'Nieważne');
insert into author (id, first_name, last_name) values (5, 'Paweł', 'Stefan');
insert into author (id, first_name, last_name) values (6, 'Jarosław', 'Mirosław');
insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
insert into book_author(book_id, author_id) values (4, 4);
insert into book_author(book_id, author_id) values (5, 5);
insert into book_author(book_id, author_id) values (6, 6);
insert into book_author(book_id, author_id) values (7, 4);
insert into book_author(book_id, author_id) values (8, 4);
insert into book_author(book_id, author_id) values (9, 6);
insert into book_author(book_id, author_id) values (10, 7);
insert into book_author(book_id, author_id) values (11, 7);
insert into book_author(book_id, author_id) values (12, 7);