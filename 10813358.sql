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
  ALM_ID INT PRIMARY KEY AUTO_INCREMENT,
  ALM_NIA INT NOT NULL,
  FOREIGN KEY (ALM_ID) REFERENCES Persona(PER_ID)
);

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

  FOREIGN KEY (MAT_ALM_ID) REFERENCES Alumnos(ALM_ID),
  FOREIGN KEY (MAT_MOD_ID) REFERENCES Modulos(MOD_ID)
);