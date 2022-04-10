CREATE DATABASE aula2103
GO
USE aula2103

/*
USER DEFINED FUNCTIONS (UDF)
Tipos:
Scalar Funciton (Retorna Valor Escalar)
Inline Table (Retorna a saída de um select - comportamento semelhante a view)
Multi Statement Table (Retornam uma tabela montada dentro da UDF)

Limitações:
Não permitem DDL
Não permitem Raise Error
Características:
São acessadas por SELECT (Retornam ResultSet)
Pode fazer Join com qualquer SELECT
Sintaxe:
CREATE (ALTER / DROP) FUNCTION fn_nome(params)
RETURNS tipo
AS
BEGIN
Programação
RETURN (variável se escalar)
END
*/

CREATE TABLE aluno (
cod INT NOT NULL,
nome VARCHAR(100) NOT NULL,
altura DECIMAL(7, 2) NOT NULL,
peso DECIMAL(7, 2) NOT NULL,
PRIMARY KEY (cod)
)
GO
CREATE TABLE imc (
id			INT				NOT NULL,
faixa		VARCHAR(40)		NOT NULL,
condicao	VARCHAR(60)		NOT NULL
PRIMARY KEY(id)
)

INSERT INTO aluno VALUES
(1, 'João', 1.80, 70.0),
(2, 'Pedro', 1.78, 90.0),
(3, 'Gabriela', 1.66, 70.0)

INSERT INTO imc VALUES 
(1, 'mto abaixo', 'abaixo de 16,9 kg/m²'),
(2, 'abaixo', '17 a 18,4 kg/m²'),
(3, 'normal', '18,5 a 24,9 kg/m²'),
(4, 'acima', '25 a 29,9 kg/m²'),
(5, 'obesidade I', '30 a 34,9 kg/m²'),
(6, 'obesidade II', '35 a 40 kg/m²'),
(7, 'obesidade III', 'acima de 40 kg/m²')

SELECT * FROM aluno
SELECT * FROM imc

/*
Exemplo 1:
Criar uma Scalar Function que dado o código do aluno, retorne seu IMC.
IMC = PESO / Altura²

Exemplo 2:
Criar uma Multi Statement Table Function que retorne:
(cod | nome | altura | peso | IMC | Condição)
*/

--Exemplo 1
CREATE FUNCTION fn_imc(@cod INT)
RETURNS DECIMAL(7, 1)
AS
BEGIN
DECLARE @alt DECIMAL(7, 2),
@peso DECIMAL(7, 2),
@imc DECIMAL(7, 1)
/*
SET @alt = (SELECT altura FROM aluno WHERE cod = @cod)
SET @peso = (SELECT peso FROM aluno WHERE cod = @cod)
*/

IF (@cod > 0)
BEGIN
SELECT @alt = altura, @peso = peso FROM aluno WHERE cod = @cod
-- SET @imc = @peso / (@alt * @alt)
SET @imc = @peso / POWER(@alt, 2)
END
RETURN (@imc)
END

SELECT dbo.fn_imc(1) AS IMC

--Exemplo 2
CREATE FUNCTION fn_tableimc()
RETURNS @tabela TABLE (
cod INT ,
nome VARCHAR(100) ,
altura DECIMAL(7, 2) ,
peso DECIMAL(7, 2) ,
imc DECIMAL(7, 1) ,
condicao VARCHAR(22)
)
AS
BEGIN
INSERT INTO @tabela (cod, nome, altura, peso)
SELECT cod, nome, altura, peso FROM aluno

UPDATE @tabela
SET imc = (SELECT dbo.fn_imc(cod))

UPDATE @tabela
SET condicao = 'mto abaixo'
WHERE imc < 17

UPDATE @tabela
SET condicao = 'abaixo'
WHERE imc >= 17 AND imc < 18.5

UPDATE @tabela
SET condicao = 'normal'
WHERE imc >= 18.5 AND imc < 25

UPDATE @tabela
SET condicao = 'acima'
WHERE imc >= 25 AND imc < 30

UPDATE @tabela
SET condicao = 'obesidade I'
WHERE imc >= 30 AND imc < 35

UPDATE @tabela
SET condicao = 'obesidade II'
WHERE imc >= 35 AND imc < 40

UPDATE @tabela
SET condicao = 'obesidade III'
WHERE imc >= 40

RETURN
END

SELECT * FROM fn_tableimc()