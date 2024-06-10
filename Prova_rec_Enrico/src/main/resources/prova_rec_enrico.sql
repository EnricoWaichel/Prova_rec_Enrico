DROP DATABASE IF EXISTS prova_rec_enrico;

CREATE DATABASE prova_rec_enrico;

USE prova_rec_enrico;

CREATE TABLE Montadora (
id INT NOT NULL auto_increment primary KEY,
nome varchar(200) NOT NULL,
paisFundacao varchar(200) not null,
nomePresidente varchar(200) not null,
dataFundacao date not null
);
Create table Carro (
id int not null auto_increment ,
modelo varchar(200) not null,
placa varchar(15) not null,
idMontadora int not null,
ano int not null,
valor double,
PRIMARY KEY (id),
  CONSTRAINT idMontadora FOREIGN KEY (idMontadora) REFERENCES Montadora(id)
);


-- Inserindo dados na tabela Montadora
INSERT INTO Montadora (nome, paisFundacao, nomePresidente, dataFundacao)
VALUES 
('Toyota', 'Japão', 'Akio Toyoda', '1937-08-28'),
('Volkswagen', 'Alemanha', 'Herbert Diess', '1937-05-28'),
('Ford', 'Estados Unidos', 'Jim Farley', '1903-06-16');

-- Inserindo dados na tabela Carro
INSERT INTO Carro (modelo, placa, idMontadora, ano, valor)
VALUES 
('Corolla', 'ABC-1234', 1, 2020, 90000.00),
('Golf', 'DEF-5678', 2, 2019, 110000.00),
('Mustang', 'GHI-9012', 3, 2021, 250000.00);
