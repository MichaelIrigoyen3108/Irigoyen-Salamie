DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
NOMBRE VARCHAR(255) NOT NULL, APELLIDO VARCHAR(255) NOT NULL, MATRICULA INTEGER NOT NULL);

INSERT INTO ODONTOLOGOS(NOMBRE, APELLIDO, MATRICULA)VALUES('Juan','Perez',1234);
INSERT INTO ODONTOLOGOS(NOMBRE, APELLIDO, MATRICULA)VALUES('Pablo','Dominguez',123463456);