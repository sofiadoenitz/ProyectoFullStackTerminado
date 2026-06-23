CREATE TABLE db_descuentos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    juego_id INT,
    descuento INT,
    oferta VARCHAR (100),
    fecha_inicio VARCHAR (50),
    fecha_fin VARCHAR (50)
);