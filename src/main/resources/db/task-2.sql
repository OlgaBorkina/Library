/* Task2.1 INSERT */
INSERT INTO library.books (title, author, published_year, genre) VALUES
                                                                     ('Преступление и наказание', 'Фёдор Достоевский', 1866, 'Роман'),
                                                                     ('Мастер и Маргарита', 'Михаил Булгаков', 1967, 'Фантастика'),
                                                                     ('Война и мир', 'Лев Толстой', 1869, 'Исторический роман'),
                                                                     ('Три товарища', 'Эрих Мария Ремарк', 1936, 'Роман'),
                                                                     ('1984', 'Джордж Оруэлл', 1949, 'Антиутопия'),
                                                                     ('Алхимик', 'Пауло Коэльо', 1988, 'Роман');
INSERT INTO library.readers (name, email, phone) VALUES
                                                     ('Иван Петров', 'ivan.petrov@example.com', '+9791234567'),
                                                     ('Анна Сидорова', 'anna.sidorova@example.com', '+992345678'),
                                                     ('Дмитрий Иванов', 'dmitry.ivanov@example.com', '+37993456789'),
                                                     ('Ольга Кузнецова', 'olga.kuznetsova@example.com', '+694567890'),
                                                     ('Сергей Смирнов', 'sergey.smirnov@example.com', '+895678901');
INSERT INTO library.borrowed_books (book_id, reader_id, borrow_date, return_date, status) VALUES
                                                                                              (2, 2,'2025-09-01', '2025-09-10', 'returned'),
                                                                                              (1, 3,'2025-09-05', '2025-12-10', 'borrowed'),
                                                                                              (6, 1,'2025-09-07', '2025-09-15', 'returned'),
                                                                                              (4, 2,'2025-09-10', '2025-11-30', 'borrowed'),
                                                                                              (6, 1,'2025-09-12', '2026-01-01', 'borrowed');

/* UPDATE: Изменить информацию по одной из книг (например, исправить год издания или жанр) */
update library.books set published_year = 1888 where title = 'Преступление и наказание';
update library.books set genre = 'Фантастика' where id = 4;
update library.borrowed_books set status = 'returned', return_date = now() where book_id = 1 and reader_id = 3;

/*DELETE: */
delete FROM library.readers where id = 4;
delete from library.borrowed_books where book_id = 6 ;



/* Task 4.1 Написать запрос, объединяющий таблицы books и borrowed_books с использованием INNER JOIN... */
select books.title ,books.author ,borrowed_books.reader_id , borrowed_books.borrow_date
from library.books
         join library.borrowed_books on books.id = borrowed_books.book_id
where borrowed_books.status = 'borrowed';

/* Task 4.2 Написать запрос, подсчитывающий количество книг, выданных каждым читателем, с использованием GROUP BY и функции COUNT(). */
select r.name, count(*) as total_borrowed
from library.readers r
         join library.borrowed_books bb on r.id = bb.reader_id
where bb.status = 'borrowed'
group by r.name

/* Task 4.3 Написать запрос, выводящий только тех читателей, у которых количество выданных книг больше двух, с использованием HAVING. */
select r.name, count(*) as total_borrowed
from library.readers r
         join library.borrowed_books bb on r.id = bb.reader_id
where bb.status = 'borrowed'
group by r.name
having Count(*) >2

/* Task 4.4 Написать запрос с использованием LEFT JOIN, чтобы вывести список всех книг и соответствующую информацию о выдаче  */
select b.title , b.author , br.reader_id , br.borrow_date
from library.books b
         left join library.borrowed_books br on b.id = br.book_id
where b.genre ='Роман'