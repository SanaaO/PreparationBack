-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 07 oct. 2021 à 18:33
-- Version du serveur :  10.4.19-MariaDB
-- Version de PHP : 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `project`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `categid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`categid`, `name`) VALUES
(1, 'Jean'),
(2, 'Swimsuit'),
(3, 'Lingerie'),
(4, 'Pyjamas'),
(5, 'Skirt'),
(6, 'Short'),
(7, 'Dress'),
(8, 'Jacket & Coat'),
(9, 'Sweater');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `prodid` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `instock` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `category_categid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`prodid`, `description`, `instock`, `name`, `picture`, `price`, `size`, `category_categid`) VALUES
(1, 'O-ring Zip Front Corduroy Straight Skit Camel Color', 20, 'Skirt Camel', 'src/main/resources/uploads/b292be6e-4d67-4884-b4d0-b9ac8f747e43.png', 15, 'S', 5),
(2, 'Pearls Detail Twist Front Sweater', 30, 'Sweater ', 'src/main/resources/uploads/61ad6c9f-8bde-4e31-a0ec-d556cc8a679b.png', 19.99, 'XS', 9),
(3, 'Cartoon (cat) Graphic 2 pieces Pyjamas Set', 100, 'Cat Pyjama', 'src/main/resources/uploads/76cc32c6-0bdf-428c-8fcd-e51e109c20f6.png', 15.5, 'M', 4),
(4, 'Solid Lapel Collar Double Breasted Overcoat Camel Color', 30, 'Coat B', 'src/main/resources/uploads/a5f96b2a-6243-49b9-be3b-b12217b7fbbb.png', 47.99, 'L', 8),
(5, 'Ruffle Neck Pocket Patched Plaid Tweed Dress.', 30, 'Black and white Dress', 'src/main/resources/uploads/bf3ef41f-6e74-48d2-8e5e-7af7ccbdca77.png', 35, 'S', 7),
(6, 'Super High Waist Mini Jean Skirt', 40, 'Jean Skirt', 'src/main/resources/uploads/ce4536d2-58c6-4022-b80f-ea901c160f5f.png', 25, 'XS', 5),
(7, '2 pieces Pyjamas Set Pink and Black', 20, 'VS Pyjama', 'src/main/resources/uploads/cca329c2-4b1f-48be-b548-7317f4694e76.png', 14.99, 'S', 4),
(8, '2 pieces Pink Swimsuit (High Waist)', 20, 'Swimsuit ', 'src/main/resources/uploads/aa4b99dc-44a0-4a98-8d75-8c54d73656a4.png', 19.99, 'XS', 2),
(9, 'Flap Detail Thermal Lined Jacked Pink Color', 100, 'Jacket Pink', 'src/main/resources/uploads/0f848b76-3d46-4d4b-be3e-2fe76430a7c3.png', 37, 'L', 8),
(10, 'Super High Waist Light Beige Jean Short', 100, 'Short ', 'src/main/resources/uploads/e310a9fd-71bb-4910-99d5-4bfae070067f.png', 13, 'M', 6),
(11, '3 Pieces Satin Pyjamas Set ', 20, 'Pyjama Set', 'src/main/resources/uploads/feb3300e-31e6-402f-90ed-82ab2e73c2bb.png', 45, 'M', 4),
(12, 'Super Hight Waist Blue Jean', 20, 'Jean HW', 'src/main/resources/uploads/147f2d5e-b150-4af4-afa6-484df04b370e.png', 19.99, 'M', 1),
(13, 'Pompom Ruffle Armhole Geo Embroidered White Dress', 100, 'Dress W', 'src/main/resources/uploads/ea5af2a5-9560-4fa6-8f8f-6077c3431136.png', 50, 'S', 7),
(14, 'Mini Red dress ', 20, 'Red Dress', 'src/main/resources/uploads/bc908ef5-b24d-4aa7-b58c-dc695d214ec3.png', 15.99, 'XS', 7),
(15, 'Blue and White Dress ', 20, 'Dress BW', 'src/main/resources/uploads/cb701998-e14a-46d1-bf45-dfdbd4293824.png', 35.99, 'S', 7);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `birthday`, `email`, `name`, `password`, `role`) VALUES
(25, '1994-09-01 00:00:00', 'sana.oueslati@gmail.com', 'Sana Oueslati', '$2a$10$pbDDthDz68v9u4NJDyR9TuysjMIAAPTkIyd0F2aAJSEccvsmp3GOm', 'Administrator'),
(26, '1993-09-15 00:00:00', 'test.customer@gmail.com', 'customer', '$2a$10$whC4Rc/CxVV4Rpx09QSr9uKXmpUuJT1dmsX1k44NC8tUCU/e4eB9K', 'Customer'),
(30, '1989-06-23 00:00:00', 'oussama@gmail.com', 'Oussama Fatmi', '$2a$10$S4Oqvbq4mCWpnyyPBKwwR.tYXSpwaD1ambumXxGO.QQ.eR2AWqPK2', 'Customer');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categid`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`prodid`),
  ADD KEY `FK1bto86uv43uvpnjvmqj90mbdr` (`category_categid`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `categid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `prodid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1bto86uv43uvpnjvmqj90mbdr` FOREIGN KEY (`category_categid`) REFERENCES `category` (`categid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
