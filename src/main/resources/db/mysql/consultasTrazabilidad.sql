use sys;
select * from usuarios;
select * from usuario_pacientes;
select * from verificaciones_cuentas;

SELECT * FROM direcciones;
SELECT * FROM usuario_pacientes;

SELECT * FROM autorizaciones;
select * from historias_medicas;

-- Composicion de calendario de vacunacion con patron Builder

SELECT * FROM calendarios;                -- > Relacion del tipo de calendario con la historia medica.
SELECT * FROM tipo_calendarios;           -- > Adulto | Embrazada | Infante
SELECT * FROM calendario_adultos;         -- > Identifica tipo para luego crear los calendarios correspondientes
SELECT * FROM calendario_embarazadas;     -- > Identifica tipo para luego crear los calendarios correspondientes 
SELECT * FROM calendario_infantes;        -- > Identifica tipo para luego crear los calendarios correspondientes 
SELECT * FROM rango_edades;               -- > Indicar la vacuna que corresponde por rango de edad
SELECT * FROM calendarios_edades;         -- > Relaciono tipo de calendario con los posibles rangos de edades que puede tener
SELECT * FROM vacunas;                    -- > Brinda los datos de la vacuna con su respectiva descripcion
SELECT * FROM calendarios_edades_vacunas; -- > Relacion entre el tipo de calendario + paciente + vacuna

-- Profesionales | Estos profesionales son los que da de alta el usuario paciente

SELECT * FROM profesionales;              -- > Almacenamos los datos que se ingresan en el alta
SELECT * FROM profesional_especialidades; -- > Relacion entre profesionales y especialidades
SELECT * FROM especialidades;             -- > Listado de especialidades provisto por el sistema (no manipulable por el usuario paciente)
SELECT * FROM direcciones_profesionales;  -- > Relacion entre profesionales y direcciones
SELECT * FROM contactos_profesionales;    -- > Relacion entre profesionales y telefonos / correos electronicos

-- Centros de salud | Estos centros son los que da de alta el usuario paciente

SELECT * FROM centros_salud;              -- > Almacenamos los datos que se ingresan en el alta
SELECT * FROM direcciones_centros_salud;  -- > Relacion entre centros y direcciones
SELECT * FROM contactos_centros_salud;    -- > Relacion entre centros y telefonos / correos electronicos

-- Diagnosticos se manejan a nivel dominio, sin manipulacion del usuario

SELECT * FROM diagnosticos;

-- Visita medica
/*
Fui al consultorio y me prescribieron
	--> A travez de una "Receta" te prescribo un { Medicamento, Otros }
	--> A travez de una "Orden" te mando a hacer un Estudio de tipo { Laboratorio, Imagenes, Otros }
    --> A travez de una "Orden" me dan un resultado.
*/

SELECT * FROM visitas_medicas;           -- > 
SELECT * FROM prescripciones;            -- > Posee una lista de recetas
SELECT * FROM recetas;                   -- > Relacion entre el tipo de receta y la prescripcion
SELECT * FROM argentina_recetas;         -- > Es un hijo de recetas 
SELECT * FROM estudios;                  -- > 
SELECT * FROM argentina_estudios;        -- > 
SELECT * FROM adjuntos;                  -- > 

-- Medicacion Habitual

SELECT * FROM medicamentos;              -- > 
SELECT * FROM medicamentos_productos;    -- > 
SELECT * FROM historias_medicamentos;    -- > Seria medicacion habitual

/*
Seria conveniente retirar el atributo cantidad y nombre y crear una tabla especifica para manejar los recordatorios
Tambien agregar atributo comentarios en historias_medicamentos
*/








