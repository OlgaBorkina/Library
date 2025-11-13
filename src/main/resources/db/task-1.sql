DROP TABLE IF EXISTS books, readers, borrowed_books CASCADE;
create table if not exists books(
                                    id SERIAL PRIMARY key,
                                    title VARCHAR(255) NOT null,
    author VARCHAR(255) NOT null,
    published_year INT CHECK (published_year > 0),
    genre VARCHAR(100)
    );
create table if not exists readers(
                                      id SERIAL PRIMARY key,
                                      name VARCHAR(100) NOT null,
    email VARCHAR(255) UNIQUE NOT null,
    phone VARCHAR(15) unique
    );
create table if not exists borrowed_books(
                                             id SERIAL PRIMARY key ,
                                             book_id INT NOT NULL ,
                                             reader_id INT NOT NULL ,
                                             borrow_date DATE NOT NULL ,
                                             return_date DATE ,
                                             status VARCHAR(20) CHECK (status IN ('borrowed', 'returned')),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    CONSTRAINT fk_reader FOREIGN KEY (reader_id) REFERENCES readers (id) ON DELETE CASCADE
    );
CREATE INDEX idx_books_title ON books (title);
INSERT INTO books (title, author, published_year, genre) VALUES
                                                             ('Преступление и наказание', 'Фёдор Достоевский', 1866, 'Роман'),
                                                             ('Мастер и Маргарита', 'Михаил Булгаков', 1967, 'Фантастика'),
                                                             ('Война и мир', 'Лев Толстой', 1869, 'Исторический роман'),
                                                             ('Три товарища', 'Эрих Мария Ремарк', 1936, 'Роман'),
                                                             ('1984', 'Джордж Оруэлл', 1949, 'Антиутопия'),
                                                             ('Алхимик', 'Пауло Коэльо', 1988, 'Роман');
INSERT INTO readers (name, email, phone) VALUES
                                             ('Иван Петров', 'ivan.petrov@example.com', '+9791234567'),
                                             ('Анна Сидорова', 'anna.sidorova@example.com', '+992345678'),
                                             ('Дмитрий Иванов', 'dmitry.ivanov@example.com', '+37993456789'),
                                             ('Ольга Кузнецова', 'olga.kuznetsova@example.com', '+694567890'),
                                             ('Сергей Смирнов', 'sergey.smirnov@example.com', '+895678901');
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status) VALUES
                                                                                      (2, 2,'2025-09-01', '2025-09-10', 'returned'),
                                                                                      (1, 3,'2025-09-05', '2025-12-10', 'borrowed'),
                                                                                      (6, 1,'2025-09-07', '2025-09-15', 'returned'),
                                                                                      (4, 2,'2025-09-10', '2025-11-30', 'borrowed'),
                                                                                      (5, 1,'2025-09-12', '2026-01-01', 'borrowed');