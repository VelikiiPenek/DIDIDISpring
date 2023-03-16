-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Сен 21 2022 г., 15:29
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Salon`
--

-- --------------------------------------------------------

--
-- Структура таблицы `adres_salona`
--

CREATE TABLE `adres_salona` (
  `id` bigint(20) NOT NULL,
  `adres_salona` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `adres_salona`
--

INSERT INTO `adres_salona` (`id`, `adres_salona`) VALUES
(1, 'Ул. Пушкина д.30'),
(2, 'Нахимовский проспект д.2');

-- --------------------------------------------------------

--
-- Структура таблицы `doljnosti`
--

CREATE TABLE `doljnosti` (
  `id` bigint(20) NOT NULL,
  `name_post` varchar(255) DEFAULT NULL,
  `post_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `doljnosti`
--

INSERT INTO `doljnosti` (`id`, `name_post`, `post_price`) VALUES
(3, 'Парикмахер', 30000),
(4, 'Стилист', 35000);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22);

-- --------------------------------------------------------

--
-- Структура таблицы `klient`
--

CREATE TABLE `klient` (
  `id` bigint(20) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numberphone` varchar(255) DEFAULT NULL,
  `surename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `klient`
--

INSERT INTO `klient` (`id`, `middlename`, `name`, `numberphone`, `surename`) VALUES
(15, 'Иванович', 'Иван', '+79778772782', 'Иванов'),
(16, 'Алексеевич', 'Пётр', '+79786452288', 'Петров');

-- --------------------------------------------------------

--
-- Структура таблицы `nabori`
--

CREATE TABLE `nabori` (
  `id` bigint(20) NOT NULL,
  `name_kit` varchar(255) DEFAULT NULL,
  `adress_sklada_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `nabori`
--

INSERT INTO `nabori` (`id`, `name_kit`, `adress_sklada_id`) VALUES
(9, 'Парикмахерский', 5),
(10, 'Краски', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `salon`
--

CREATE TABLE `salon` (
  `id` bigint(20) NOT NULL,
  `kol_vo_mest` int(11) NOT NULL,
  `naimenovanie_salona` varchar(255) DEFAULT NULL,
  `ploshyad` varchar(255) DEFAULT NULL,
  `vremya_raboti` varchar(255) DEFAULT NULL,
  `adres_salona_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `salon`
--

INSERT INTO `salon` (`id`, `kol_vo_mest`, `naimenovanie_salona`, `ploshyad`, `vremya_raboti`, `adres_salona_id`) VALUES
(13, 50, 'Салон \"Салон\"', '30 кв.м.', 'С 10:00 до 23:00', 1),
(14, 50, 'Салон \"Красота\"', '30 кв.м', 'С 8:00 до 20:00', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `sklad`
--

CREATE TABLE `sklad` (
  `id` bigint(20) NOT NULL,
  `adres_sklada` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sklad`
--

INSERT INTO `sklad` (`id`, `adres_sklada`) VALUES
(5, 'Ломоносовский проспект д.24'),
(6, 'Ул. Минская д.8');

-- --------------------------------------------------------

--
-- Структура таблицы `sotrydniki`
--

CREATE TABLE `sotrydniki` (
  `id` bigint(20) NOT NULL,
  `adress_employee` varchar(255) DEFAULT NULL,
  `born_date` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numberphone` varchar(255) DEFAULT NULL,
  `surename` varchar(255) DEFAULT NULL,
  `doljnost_id` bigint(20) DEFAULT NULL,
  `salon_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sotrydniki`
--

INSERT INTO `sotrydniki` (`id`, `adress_employee`, `born_date`, `middlename`, `name`, `numberphone`, `surename`, `doljnost_id`, `salon_id`) VALUES
(17, 'Ул. Нежинская д.1', '25.07.1997', 'Алексеевич', 'Александр', '+79872376655', 'Смирнов', 3, 14),
(18, 'Нахимовский проспект д.2', '25.07.1999', 'Иванович', 'Иван', '+79778772782', 'Иванов', 3, 13);

-- --------------------------------------------------------

--
-- Структура таблицы `tip_yslygi`
--

CREATE TABLE `tip_yslygi` (
  `id` bigint(20) NOT NULL,
  `tip_yslygi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tip_yslygi`
--

INSERT INTO `tip_yslygi` (`id`, `tip_yslygi`) VALUES
(7, 'Стрижка'),
(8, 'Покрас');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `password`, `username`) VALUES
(1, b'1', '$2a$08$txaYGrJKFcqwA/Cx.HW2kebYNEfPU2ZAhbUwLgABbGB5YlDt9/2Ou', 'admin'),
(2, b'1', '$2a$08$Cxm3yw9uWPw0PT9xx6xLM.Hm3Hcz0IMULjt/dRoKFUSm.A8xrZsfO', 'qwerty');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ADMIN'),
(2, 'EMPLOYEE');

-- --------------------------------------------------------

--
-- Структура таблицы `yslygi`
--

CREATE TABLE `yslygi` (
  `id` bigint(20) NOT NULL,
  `cost_yslygi` int(11) NOT NULL,
  `name_yslygi` varchar(255) DEFAULT NULL,
  `time_of_event` varchar(255) DEFAULT NULL,
  `nabori_id` bigint(20) DEFAULT NULL,
  `tip_yslygi_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `yslygi`
--

INSERT INTO `yslygi` (`id`, `cost_yslygi`, `name_yslygi`, `time_of_event`, `nabori_id`, `tip_yslygi_id`) VALUES
(11, 500, '\"Модная\" стрижка', '20 минут', 9, 7),
(12, 1500, '\"Модная\" покраска', '1 час', 10, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `zapisi`
--

CREATE TABLE `zapisi` (
  `id` bigint(20) NOT NULL,
  `data_zapisi` varchar(255) DEFAULT NULL,
  `visit` varchar(255) DEFAULT NULL,
  `vremya_nachala` varchar(255) DEFAULT NULL,
  `klient_id` bigint(20) DEFAULT NULL,
  `sotrydniki_id` bigint(20) DEFAULT NULL,
  `yslygi_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `adres_salona`
--
ALTER TABLE `adres_salona`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `doljnosti`
--
ALTER TABLE `doljnosti`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `nabori`
--
ALTER TABLE `nabori`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoe9sr1g0go6lbihkehmr77v3b` (`adress_sklada_id`);

--
-- Индексы таблицы `salon`
--
ALTER TABLE `salon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKifgnquyq8rwk6pu3nwa5g5r30` (`adres_salona_id`);

--
-- Индексы таблицы `sklad`
--
ALTER TABLE `sklad`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sotrydniki`
--
ALTER TABLE `sotrydniki`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmkkpa1q5mdv0640315u8685ba` (`doljnost_id`),
  ADD KEY `FKblff91autugqna7xd68q041o2` (`salon_id`);

--
-- Индексы таблицы `tip_yslygi`
--
ALTER TABLE `tip_yslygi`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- Индексы таблицы `yslygi`
--
ALTER TABLE `yslygi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn985heeacx7o6cs1p7r513uox` (`nabori_id`),
  ADD KEY `FK8jat7n7jcaxng8l2d2yc37yn8` (`tip_yslygi_id`);

--
-- Индексы таблицы `zapisi`
--
ALTER TABLE `zapisi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9k2f40n4chkecggueelorw9ts` (`klient_id`),
  ADD KEY `FK70djw38fcax9mwwg7scux9yr7` (`sotrydniki_id`),
  ADD KEY `FKlyjqgxxxl4e1e02srxsu6nvb1` (`yslygi_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `nabori`
--
ALTER TABLE `nabori`
  ADD CONSTRAINT `FKoe9sr1g0go6lbihkehmr77v3b` FOREIGN KEY (`adress_sklada_id`) REFERENCES `sklad` (`id`);

--
-- Ограничения внешнего ключа таблицы `salon`
--
ALTER TABLE `salon`
  ADD CONSTRAINT `FKifgnquyq8rwk6pu3nwa5g5r30` FOREIGN KEY (`adres_salona_id`) REFERENCES `adres_salona` (`id`);

--
-- Ограничения внешнего ключа таблицы `sotrydniki`
--
ALTER TABLE `sotrydniki`
  ADD CONSTRAINT `FKblff91autugqna7xd68q041o2` FOREIGN KEY (`salon_id`) REFERENCES `salon` (`id`),
  ADD CONSTRAINT `FKmkkpa1q5mdv0640315u8685ba` FOREIGN KEY (`doljnost_id`) REFERENCES `doljnosti` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `yslygi`
--
ALTER TABLE `yslygi`
  ADD CONSTRAINT `FK8jat7n7jcaxng8l2d2yc37yn8` FOREIGN KEY (`tip_yslygi_id`) REFERENCES `tip_yslygi` (`id`),
  ADD CONSTRAINT `FKn985heeacx7o6cs1p7r513uox` FOREIGN KEY (`nabori_id`) REFERENCES `nabori` (`id`);

--
-- Ограничения внешнего ключа таблицы `zapisi`
--
ALTER TABLE `zapisi`
  ADD CONSTRAINT `FK70djw38fcax9mwwg7scux9yr7` FOREIGN KEY (`sotrydniki_id`) REFERENCES `sotrydniki` (`id`),
  ADD CONSTRAINT `FK9k2f40n4chkecggueelorw9ts` FOREIGN KEY (`klient_id`) REFERENCES `klient` (`id`),
  ADD CONSTRAINT `FKlyjqgxxxl4e1e02srxsu6nvb1` FOREIGN KEY (`yslygi_id`) REFERENCES `yslygi` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
