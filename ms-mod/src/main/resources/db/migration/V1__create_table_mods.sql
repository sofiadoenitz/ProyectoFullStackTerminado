CREATE TABLE db_mods (

    id INT AUTO_INCREMENT PRIMARY KEY,

    titulo VARCHAR(100),
    descripcion VARCHAR(255),
    creador VARCHAR(100),
    version VARCHAR(50),
    aprobado BOOLEAN
);