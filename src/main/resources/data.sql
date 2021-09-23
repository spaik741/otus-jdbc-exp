insert into authors (id, `name`) values (1, 'Stephen King'),
(2, 'Suzanne Collins');
insert into genres (id, genre) values (1, 'Fantasy'),
(2, 'Horror'),
(3, 'Teaching');
insert into books (id, `name`, id_author, id_genre) values (1, 'The Hunger Games', 2, 1),
(2, 'Pet Sematary', 1, 1),
(3, 'Shine', 1, 2);
