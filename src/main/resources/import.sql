INSERT INTO clientes (nombre, apellido, email, create_at) VALUES ('María', 'Sanchez', 'maria@correo.es', '2019-11-11');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');

INSERT INTO creadores (id, nombre) VALUES (1, 'Stan Lee');
INSERT INTO creadores (id, nombre) VALUES (2, 'Jack Kirby');

INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Los Vengadores', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Capitán América', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Thor', 12, 12, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Spiderman', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Daredevil', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('IronMan', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Capitán América - Thor', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Los Nuevos Vengadores', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('La Visión y la Bruja Escarlata', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Secret Wars', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Secret Wars II', 12, 12, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Los 4 Fantásticos', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Clásicos Marvel', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Marvel Heroes', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('What if?', 130, 120, '2021-01-24', 1);
INSERT INTO colecciones (nombre, num_totales, num_disponibles, create_at, creador_id) VALUES('Hulk', 130, 120, '2021-01-24', 1);

INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('juan', '$2a$10$y.obikPLlNFJkG6cH3EQYugA7Tnh0lGw1AgBwrPW5gpgfST2MCkN2', 1, 'Juan', 'Díaz', 'juan@mail.com')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$pNxlTutkJCaGIZXI16hFjON.sZH0LM.CZFasrAIH.HB0uD8t.kf4C', 1, 'Admin', 'Admin', 'admin@mail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES(2, 2);