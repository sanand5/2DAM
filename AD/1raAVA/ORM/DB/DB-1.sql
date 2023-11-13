-- Crear la tabla Alumnos
CREATE TABLE Persona (
  PER_ID INT PRIMARY KEY AUTO_INCREMENT,
  PER_NAME1 VARCHAR(255) NOT NULL,
  PER_NAME2 VARCHAR(255) NULL,
  PER_SURNAME1 VARCHAR(255) NOT NULL,
  PER_SURNAME2 VARCHAR(255) NULL,
  PER_FECHA DATE NOT NULL
);

CREATE TABLE Alumnos (
  -- Si realemente la esta columan la coje de la tabla Personas hace falta que sea Autoincrement?
  ALM_PER_ID INT PRIMARY KEY AUTO_INCREMENT,
  ALM_NIA INT NOT NULL,
<<<<<<< HEAD:10813358.sql
  FOREIGN KEY (ALM_ID) REFERENCES Persona(PER_ID)
);
=======
  FOREIGN KEY (ALM_PER_ID) REFERENCES Persona(PER_ID)
  );
>>>>>>> 2f8d7127ed1dcf1b4f73a67caa7497c871d88ed1:AD/1raAVA/ORM/DB/DB-1.sql

-- Crear la tabla Moduls
CREATE TABLE Modulos (
  MOD_ID INT PRIMARY KEY AUTO_INCREMENT,
  MOD_NAME VARCHAR(255) NOT NULL
);
-- Crear la tabla Matricules
CREATE TABLE Matriculas (
  MAT_ID INT PRIMARY KEY AUTO_INCREMENT,
  MAT_ALM_ID INT NOT NULL,
  MAT_MOD_ID INT NOT NULL,
  MAT_NOTAS TEXT,

  FOREIGN KEY (MAT_ALM_ID) REFERENCES Alumnos(ALM_PER_ID),
  FOREIGN KEY (MAT_MOD_ID) REFERENCES Modulos(MOD_ID)
);