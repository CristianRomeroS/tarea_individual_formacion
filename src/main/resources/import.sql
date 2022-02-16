INSERT INTO Comunidades (NOMBRE) VALUES("Madrid");
INSERT INTO Comunidades (NOMBRE) VALUES("Catalunya");
INSERT INTO Comunidades (NOMBRE) VALUES("Galicia");
INSERT INTO Comunidades (NOMBRE) VALUES("Pais vasco");
INSERT INTO Comunidades (NOMBRE) VALUES("Andalucia");
INSERT INTO Comunidades (NOMBRE) VALUES("Castilla y Leon");
INSERT INTO Comunidades (NOMBRE) VALUES("Castilla la Mancha");

INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Cristian","Romero Siacara","Cristian@gmail.com",698745231,28025,"calle sol",1,"X643455p");
INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Raul","Rodrigues Santos","Raul@gmail.com",692745231,28045,"calle luna",2,"X643455p");
INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Juan","Jules roldan","Juan@gmail.com",693745231,28035,"calle Mar",3,"X643455p");
INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Pepe","Pecas piedra","pepe@gmail.com",698745231,28025,"calle pez",4,"X643455p");
INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Samuel","Sylas San","samuel@gmail.com",698745231,28025,"calle caracol",1,"X643455p");
INSERT INTO alumnos (NOMBRE,apellido,email,telefono,codigo_postal,direccion,comunidad_id,dni) VALUES("Jhonatan","Reyes santos","jhonatan@gmail.com",698745231,28025,"calle perro",1,"X643455p");


INSERT INTO Usuarios (username,password,enabled) VALUES("rolando","$2a$10$ZXW5e8l6tMxyoQNrYWD9dePcz8A0/SOAfGt/XIMTPwskWiuypky8W",1);
INSERT INTO Usuarios (username,password,enabled) VALUES("cristian","$2a$10$ZXW5e8l6tMxyoQNrYWD9dePcz8A0/SOAfGt/XIMTPwskWiuypky8W",1);
INSERT INTO Usuarios (username,password,enabled) VALUES("Rosa","$2a$10$ZXW5e8l6tMxyoQNrYWD9dePcz8A0/SOAfGt/XIMTPwskWiuypky8W",1);


INSERT INTO roles (nombre) VALUES("ROLE_USER");
INSERT INTO roles (nombre) VALUES("ROLE_ADMIN");

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);
