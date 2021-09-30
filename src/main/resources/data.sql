insert into authors (f_name, l_name) values ('Stephen', ' King'),
('Suzanne', 'Collins');
insert into genres (genre) values ('Fantasy'),
('Horror'),
('Teaching');
insert into books (`name`, id_author, id_genre) values ('The Hunger Games', 2, 1),
('Pet Sematary', 1, 1),
('Shine', 1, 2);
insert into comments (message, date_message, id_book) values ('Could not tear myself away', '2019-01-15', 1),
('So the book is great', '2021-09-10', 2),
('I was bored sometimes, but overall ok', '2020-12-03', 3);