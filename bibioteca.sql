-- --------------------------------------------------------
-- Anfitrião:                    127.0.0.1
-- Versão do servidor:           8.0.30 - MySQL Community Server - GPL
-- SO do servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- A despejar estrutura da base de dados para biblioteca
CREATE DATABASE IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;

-- A despejar estrutura para tabela biblioteca.emprestimos
CREATE TABLE IF NOT EXISTS `emprestimos` (
  `idestado` int DEFAULT NULL,
  `idlivro` int DEFAULT NULL,
  `idleitor` int DEFAULT NULL,
  `idemprestimo` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idemprestimo`),
  KEY `FK__estado` (`idestado`),
  KEY `FK_emprestimos_livros` (`idlivro`),
  KEY `FK_emprestimos_leitores` (`idleitor`),
  CONSTRAINT `FK__estado` FOREIGN KEY (`idestado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `FK_emprestimos_leitores` FOREIGN KEY (`idleitor`) REFERENCES `leitores` (`id`),
  CONSTRAINT `FK_emprestimos_livros` FOREIGN KEY (`idlivro`) REFERENCES `livros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- A despejar dados para tabela biblioteca.emprestimos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `emprestimos` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprestimos` ENABLE KEYS */;

-- A despejar estrutura para tabela biblioteca.estado
CREATE TABLE IF NOT EXISTS `estado` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- A despejar dados para tabela biblioteca.estado: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` (`id_estado`, `descricao`) VALUES
	(1, 'disponivel'),
	(2, 'indisponivel');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;

-- A despejar estrutura para tabela biblioteca.leitores
CREATE TABLE IF NOT EXISTS `leitores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `nleitor` varchar(10) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- A despejar dados para tabela biblioteca.leitores: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `leitores` DISABLE KEYS */;
/*!40000 ALTER TABLE `leitores` ENABLE KEYS */;

-- A despejar estrutura para tabela biblioteca.livros
CREATE TABLE IF NOT EXISTS `livros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `editora` varchar(255) DEFAULT NULL,
  `anolancamento` varchar(4) DEFAULT NULL,
  `estado_id_estado` int NOT NULL,
  PRIMARY KEY (`id`,`estado_id_estado`),
  KEY `fk_livros_estado1_idx` (`estado_id_estado`),
  CONSTRAINT `fk_livros_estado1` FOREIGN KEY (`estado_id_estado`) REFERENCES `estado` (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- A despejar dados para tabela biblioteca.livros: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
