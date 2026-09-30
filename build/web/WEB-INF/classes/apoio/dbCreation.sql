/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  jonat
 * Created: Sep 3, 2026
 */

CREATE DATABASE achados_perdidos;

\c achados_perdidos;

CREATE TABLE Categoria (
    ID SERIAL PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL
);

CREATE TABLE Achado_Perdido (
    ID SERIAL PRIMARY KEY,
    DATA_HORA TIMESTAMP NOT NULL,
    DESCRICAO_ITEM VARCHAR(255) NOT NULL,
    NOME_PESSOA_CONTATO VARCHAR(100) NOT NULL,
    CONTATO VARCHAR(50) NOT NULL,
    CATEGORIA_ID INT NOT NULL,
    FOREIGN KEY (CATEGORIA_ID) REFERENCES Categoria(ID)
);

INSERT INTO Categoria (DESCRICAO) VALUES ('Achado');
INSERT INTO Categoria (DESCRICAO) VALUES ('Perdido');