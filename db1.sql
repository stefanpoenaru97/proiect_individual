-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2023 at 03:47 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db1`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `book_title` varchar(128) NOT NULL,
  `book_author` varchar(128) NOT NULL,
  `book_genre` enum('Fantasy','Sci-Fi','Mystery','Thriller','Romance','Westerns','Dystopian','Contemporary') NOT NULL,
  `book_rented` tinyint(1) NOT NULL DEFAULT 0,
  `book_rented_by` int(11) NOT NULL DEFAULT 0,
  `book_rented_from` date DEFAULT NULL,
  `book_rented_until` date DEFAULT NULL,
  `book_booked_count` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `book_title`, `book_author`, `book_genre`, `book_rented`, `book_rented_by`, `book_rented_from`, `book_rented_until`, `book_booked_count`) VALUES
(2, 'Things Fall Apart', 'Chinua Achebe', 'Westerns', 0, 0, NULL, NULL, 7),
(3, 'Fairy tales', 'Hans Christian Andersen', 'Romance', 0, 0, NULL, NULL, 6),
(4, 'The Divine Comedy', 'Dante Alighieri', 'Dystopian', 0, 1, '2023-01-11', '2023-01-19', 4),
(5, 'Pride and Prejudice', 'Jane Austen', 'Westerns', 0, 0, NULL, NULL, 3),
(6, 'Le Père Goriot', 'Honoré de Balzac', 'Sci-Fi', 1, 1, '2023-01-11', '2023-01-14', 3),
(7, 'The Decameron', 'Giovanni Boccaccio', 'Romance', 0, 0, NULL, NULL, 0),
(8, 'Ficciones', 'Jorge Luis Borges', 'Fantasy', 0, 0, NULL, NULL, 0),
(9, 'Wuthering Heights', 'Emily Brontë', 'Romance', 0, 0, NULL, NULL, 0),
(10, 'The Stranger', 'Albert Camus', 'Fantasy', 0, 0, NULL, NULL, 0),
(11, 'Poems', 'Paul Celan', 'Westerns', 1, 1, '2023-01-11', '2023-01-26', 1),
(12, 'Journey to the End of the Night', 'Louis-Ferdinand Céline', 'Romance', 0, 0, NULL, NULL, 0),
(13, 'Don Quijote De La Mancha', 'Miguel de Cervantes', 'Romance', 0, 0, NULL, NULL, 0),
(14, 'The Canterbury Tales', 'Geoffrey Chaucer', 'Romance', 0, 0, NULL, NULL, 0),
(15, 'Stories', 'Anton Chekhov', 'Fantasy', 0, 0, NULL, NULL, 0),
(16, 'Nostromo', 'Joseph Conrad', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(17, 'Great Expectations', 'Charles Dickens', 'Romance', 0, 0, NULL, NULL, 0),
(18, 'Jacques the Fatalist', 'Denis Diderot', 'Dystopian', 0, 0, NULL, NULL, 0),
(19, 'Berlin Alexanderplatz', 'Alfred Döblin', 'Contemporary', 0, 0, NULL, NULL, 0),
(20, 'Crime and Punishment', 'Fyodor Dostoevsky', 'Romance', 0, 0, NULL, NULL, 0),
(21, 'The Idiot', 'Fyodor Dostoevsky', 'Romance', 0, 0, NULL, NULL, 0),
(22, 'The Possessed', 'Fyodor Dostoevsky', 'Contemporary', 0, 0, NULL, NULL, 0),
(23, 'The Brothers Karamazov', 'Fyodor Dostoevsky', 'Romance', 0, 0, NULL, NULL, 0),
(24, 'Middlemarch', 'George Eliot', 'Contemporary', 0, 0, NULL, NULL, 0),
(25, 'Invisible Man', 'Ralph Ellison', 'Romance', 0, 0, NULL, NULL, 0),
(26, 'Medea', 'Euripides', 'Mystery', 0, 0, NULL, NULL, 0),
(27, 'The Sound and the Fury', 'William Faulkner', 'Romance', 0, 0, NULL, NULL, 0),
(28, 'Madame Bovary', 'Gustave Flaubert', 'Dystopian', 0, 0, NULL, NULL, 0),
(29, 'Sentimental Education', 'Gustave Flaubert', 'Romance', 0, 0, NULL, NULL, 0),
(30, 'Gypsy Ballads', 'Federico García Lorca', 'Contemporary', 0, 0, NULL, NULL, 0),
(31, 'One Hundred Years of Solitude', 'Gabriel García Márquez', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(32, 'Love in the Time of Cholera', 'Gabriel García Márquez', 'Dystopian', 0, 0, NULL, NULL, 0),
(33, 'Faust', 'Johann Wolfgang von Goethe', 'Romance', 0, 0, NULL, NULL, 0),
(34, 'Dead Souls', 'Nikolai Gogol', 'Romance', 0, 1, '2023-01-11', '2023-01-27', 0),
(35, 'The Tin Drum', 'Günter Grass', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(36, 'The Devil to Pay in the Backlands', 'João Guimarães Rosa', 'Romance', 0, 0, NULL, NULL, 0),
(37, 'Hunger', 'Knut Hamsun', 'Westerns', 0, 0, NULL, NULL, 0),
(38, 'The Old Man and the Sea', 'Ernest Hemingway', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(39, 'Iliad', 'Homer', 'Romance', 0, 0, NULL, NULL, 0),
(40, 'Odyssey', 'Homer', 'Fantasy', 0, 0, NULL, NULL, 0),
(41, 'A Doll\'s House', 'Henrik Ibsen', 'Romance', 0, 0, NULL, NULL, 0),
(42, 'Ulysses', 'James Joyce', 'Mystery', 0, 0, NULL, NULL, 0),
(43, 'Stories', 'Franz Kafka', 'Contemporary', 0, 0, NULL, NULL, 0),
(44, 'The Trial', 'Franz Kafka', 'Romance', 0, 0, NULL, NULL, 0),
(45, 'The Castle', 'Franz Kafka', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(46, 'The recognition of Shakuntala', 'Kālidāsa', 'Dystopian', 0, 0, NULL, NULL, 0),
(47, 'The Sound of the Mountain', 'Yasunari Kawabata', 'Romance', 0, 0, NULL, NULL, 0),
(48, 'Zorba the Greek', 'Nikos Kazantzakis', 'Fantasy', 0, 0, NULL, NULL, 0),
(49, 'Sons and Lovers', 'D. H. Lawrence', 'Fantasy', 0, 0, NULL, NULL, 0),
(50, 'Independent People', 'Halldór Laxness', 'Romance', 0, 0, NULL, NULL, 0),
(51, 'Poems', 'Giacomo Leopardi', 'Fantasy', 0, 0, NULL, NULL, 0),
(52, 'The Golden Notebook', 'Doris Lessing', 'Romance', 0, 0, NULL, NULL, 0),
(53, 'Pippi Longstocking', 'Astrid Lindgren', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(54, 'Diary of a Madman', 'Lu Xun', 'Dystopian', 0, 0, NULL, NULL, 0),
(55, 'Children of Gebelawi', 'Naguib Mahfouz', 'Romance', 0, 0, NULL, NULL, 0),
(56, 'Buddenbrooks', 'Thomas Mann', 'Romance', 0, 0, NULL, NULL, 0),
(57, 'The Magic Mountain', 'Thomas Mann', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(58, 'Moby Dick', 'Herman Melville', 'Mystery', 0, 0, NULL, NULL, 0),
(59, 'Essays', 'Michel de Montaigne', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(60, 'History', 'Elsa Morante', 'Romance', 0, 0, NULL, NULL, 0),
(61, 'Beloved', 'Toni Morrison', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(62, 'The Tale of Genji', 'Murasaki Shikibu', 'Romance', 0, 0, NULL, NULL, 0),
(63, 'The Man Without Qualities', 'Robert Musil', 'Thriller', 0, 0, NULL, NULL, 0),
(64, 'Lolita', 'Vladimir Nabokov', 'Mystery', 0, 0, NULL, NULL, 0),
(65, 'Nineteen Eighty-Four', 'George Orwell', 'Romance', 0, 0, NULL, NULL, 0),
(66, 'Metamorphoses', 'Ovid', 'Fantasy', 0, 0, NULL, NULL, 0),
(67, 'The Book of Disquiet', 'Fernando Pessoa', 'Romance', 0, 0, NULL, NULL, 0),
(68, 'Tales', 'Edgar Allan Poe', 'Romance', 0, 0, NULL, NULL, 0),
(69, 'In Search of Lost Time', 'Marcel Proust', 'Fantasy', 0, 0, NULL, NULL, 0),
(70, 'Gargantua and Pantagruel', 'François Rabelais', 'Mystery', 0, 0, NULL, NULL, 0),
(71, 'Pedro Páramo', 'Juan Rulfo', 'Contemporary', 0, 0, NULL, NULL, 0),
(72, 'The Masnavi', 'Rumi', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(73, 'Midnight\'s Children', 'Salman Rushdie', 'Thriller', 0, 0, NULL, NULL, 0),
(74, 'Bostan', 'Saadi', 'Romance', 0, 0, NULL, NULL, 0),
(75, 'Season of Migration to the North', 'Tayeb Salih', 'Dystopian', 0, 0, NULL, NULL, 0),
(76, 'Blindness', 'José Saramago', 'Contemporary', 0, 0, NULL, NULL, 0),
(77, 'Hamlet', 'William Shakespeare', 'Romance', 0, 0, NULL, NULL, 0),
(78, 'King Lear', 'William Shakespeare', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(79, 'Othello', 'William Shakespeare', 'Thriller', 0, 0, NULL, NULL, 0),
(80, 'Oedipus the King', 'Sophocles', 'Romance', 0, 0, NULL, NULL, 0),
(81, 'The Red and the Black', 'Stendhal', 'Thriller', 0, 0, NULL, NULL, 0),
(82, 'The Life And Opinions of Tristram Shandy', 'Laurence Sterne', 'Sci-Fi', 0, 0, NULL, NULL, 0),
(83, 'Confessions of Zeno', 'Italo Svevo', 'Romance', 0, 0, NULL, NULL, 0),
(84, 'Gulliver\'s Travels', 'Jonathan Swift', 'Thriller', 0, 0, NULL, NULL, 0),
(85, 'War and Peace', 'Leo Tolstoy', 'Romance', 0, 0, NULL, NULL, 0),
(86, 'Anna Karenina', 'Leo Tolstoy', 'Contemporary', 0, 0, NULL, NULL, 0),
(87, 'The Death of Ivan Ilyich', 'Leo Tolstoy', 'Romance', 0, 0, NULL, NULL, 0),
(88, 'The Adventures of Huckleberry Finn', 'Mark Twain', 'Dystopian', 0, 0, NULL, NULL, 0),
(89, 'Ramayana', 'Valmiki', 'Romance', 0, 0, NULL, NULL, 0),
(90, 'The Aeneid', 'Virgil', 'Dystopian', 0, 0, NULL, NULL, 0),
(91, 'Mahabharata', 'Vyasa', 'Contemporary', 0, 0, NULL, NULL, 0),
(92, 'Leaves of Grass', 'Walt Whitman', 'Romance', 0, 0, NULL, NULL, 0),
(93, 'Mrs Dalloway', 'Virginia Woolf', 'Dystopian', 0, 0, NULL, NULL, 0),
(97, 'Carolinaa', 'test', 'Mystery', 1, 1, '2023-01-11', '2173-01-31', 2),
(98, 'rest', '123', 'Dystopian', 0, 0, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genre_id` int(11) NOT NULL,
  `genre_title` varchar(128) NOT NULL,
  `genre_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genre_id`, `genre_title`, `genre_count`) VALUES
(2, 'Fantasy', 12),
(4, 'Sci-Fi', 6),
(5, 'Mystery', 7),
(6, 'Thriller', 4),
(7, 'Romance', 3),
(8, 'Westerns', 3),
(9, 'Dystopian', 1),
(10, 'Contemporary', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `user_email` varchar(128) NOT NULL,
  `user_password` varchar(128) NOT NULL,
  `user_role` enum('customer','admin') NOT NULL DEFAULT 'customer',
  `user_firstname` varchar(128) DEFAULT NULL,
  `user_lastname` varchar(128) DEFAULT NULL,
  `user_gender` varchar(128) DEFAULT NULL,
  `user_books_read` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_email`, `user_password`, `user_role`, `user_firstname`, `user_lastname`, `user_gender`, `user_books_read`) VALUES
(1, 'st3f101', 'stef_stef101@yahoo.com', 'parola1', 'customer', 'stefan1', 'poenaru', 'Male', 10),
(2, 'admin', 'stefan.poenaru97@e-uvt.ro', 'parola1', 'admin', NULL, NULL, NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genre_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`user_name`,`user_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
