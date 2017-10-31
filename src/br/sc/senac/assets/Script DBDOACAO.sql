DROP DATABASE IF EXISTS DBDOACAO; 
CREATE DATABASE DBDOACAO;
USE DBDOACAO;

CREATE TABLE IF NOT EXISTS Endereco (
	idEndereco INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	estado VARCHAR(45),
    cidade VARCHAR(45),
    cep VARCHAR(45),
	bairro VARCHAR(45),
    rua VARCHAR(45),
    numero INT(15),
	complemento VARCHAR(45)	
);

CREATE TABLE IF NOT EXISTS Pessoa (
	idPessoa INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	email VARCHAR(30),
	tipoPessoa INT(5),
	documento VARCHAR(14), 
	telefone VARCHAR(30),
	senha VARCHAR(45),
	nome VARCHAR(45), 
    idEndereco INT NOT NULL,
    FOREIGN KEY(idEndereco) REFERENCES Endereco(idEndereco)
);
   
CREATE TABLE IF NOT EXISTS Doacao (
	idDoacao INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	dtInicial DATETIME,
	dtFinal DATETIME,
	statusDoacao VARCHAR(45),
	idEndereco INT,
	idPessoa INT NOT NULL,
	FOREIGN KEY (idEndereco) REFERENCES Endereco (idEndereco),
	FOREIGN KEY (idPessoa) REFERENCES Pessoa (idPessoa)  
);
  
CREATE TABLE IF NOT EXISTS Necessidade (
	idNecessidade INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	dtInicial DATETIME,
	dtFinal DATETIME,
	statusNecessidade VARCHAR(45),
	idEndereco INT,
	idPessoa INT,
	FOREIGN KEY (idEndereco) REFERENCES Endereco (idEndereco),
	FOREIGN KEY (idPessoa) REFERENCES Pessoa (idPessoa)  
);

CREATE TABLE IF NOT EXISTS Categoria (
	idCategoria INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nomeCategoria VARCHAR(45)
);
  
CREATE TABLE IF NOT EXISTS Item (
	idItem INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	descricao VARCHAR(45),
	quantidade INT(20),
	condicao VARCHAR(45),
	idNecessidade INT,
	idDoacao INT,
    idCategoria INT NOT NULL,
	FOREIGN KEY (idNecessidade) REFERENCES Necessidade (idNecessidade),
	FOREIGN KEY (idDoacao) REFERENCES Doacao (idDoacao),
    FOREIGN KEY (idCategoria) REFERENCES Categoria (idCategoria)
);

  