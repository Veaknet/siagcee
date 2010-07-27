/*
Created: 13/04/2010
Modified: 13/04/2010
Model: RE PostgreSQL 8.3
Database: PostgreSQL 8.3
*/

-- Create schemas section -------------------------------------------------

CREATE SCHEMA "pg_toast_temp_1" AUTHORIZATION "postgres"
;

CREATE SCHEMA "public" AUTHORIZATION "postgres"
;

-- Create functions section -------------------------------------------------

CREATE FUNCTION "public"."pldbg_get_target_info"("signature" "text","targettype" "char")
RETURNS "targetinfo"
 LANGUAGE c
 VOLATILE
 RETURNS NULL ON NULL INPUT
AS '$libdir/targetinfo'
;

CREATE FUNCTION "public"."plpgsql_oid_debug"("functionoid" "oid")
RETURNS "int4"
 LANGUAGE c
 VOLATILE
 RETURNS NULL ON NULL INPUT
AS '$libdir/plugins/plugin_debugger'
;

-- Create user data types section -------------------------------------------------

CREATE TYPE "public"."breakpoint" AS
 ( "func" oid, "linenumber" int4, "targetname" text )
;

CREATE TYPE "public"."frame" AS
 ( "level" int4, "targetname" text, "func" oid, "linenumber" int4, "args" text )
;

CREATE TYPE "public"."proxyinfo" AS
 ( "serverversionstr" text, "serverversionnum" int4, "proxyapiver" int4, "serverprocessid" int4 )
;

CREATE TYPE "public"."targetinfo" AS
 ( "target" oid, "schema" oid, "nargs" int4, "argtypes" oid, "targetname" name, "argmodes" char[], "argnames" text[], "targetlang" oid, "fqname" text, "returnsset" bool, "returntype" oid )
;

CREATE TYPE "public"."var" AS
 ( "name" text, "varclass" bpchar, "linenumber" int4, "isunique" bool, "isconst" bool, "isnotnull" bool, "dtype" oid, "value" text )
;

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "public"."seq_accesos_encuestados"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_administradores"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_estudios"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_instancia_objetos"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_instancia_preguntas"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_pool_objetos"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_pool_preguntas"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_pool_respuestas_posibles"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_respuestas"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_respuestas_temp"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "public"."seq_usuarios"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table public.accesos_encuestados

CREATE TABLE "public"."accesos_encuestados"(
 "id_accesos_encuestados" Bigint DEFAULT nextval('seq_accesos_encuestados'::regclass) NOT NULL,
 "id_instancia_objetos" Bigint NOT NULL,
 "id_usuarios" Bigint
)
WITH (OIDS=FALSE)
;

-- Add keys for table public.accesos_encuestados

ALTER TABLE "public"."accesos_encuestados" ADD CONSTRAINT "pk_accesos_encuestados" PRIMARY KEY ("id_accesos_encuestados")
;

-- Table public.administradores

CREATE TABLE "public"."administradores"(
 "id_administradores" Bigint DEFAULT nextval('seq_administradores'::regclass) NOT NULL,
 "usuario" Character varying(255) NOT NULL,
 "clave" Character varying(255) NOT NULL,
 "tipo_administrador" Character varying(255) DEFAULT 'regular'::character varying,
 "nombre" Character varying(255) DEFAULT ''::character varying,
 "email" Character varying(255) DEFAULT ''::character varying
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "public"."administradores"."tipo_administrador" IS 'regular
superadministrador'
;

-- Create indexes for table public.administradores

CREATE UNIQUE INDEX "unique_administradores" ON "public"."administradores" ("usuario")
;

-- Add keys for table public.administradores

ALTER TABLE "public"."administradores" ADD CONSTRAINT "pk_administradores" PRIMARY KEY ("id_administradores")
;

-- Table public.estudios

CREATE TABLE "public"."estudios"(
 "id_estudios" Bigint DEFAULT nextval('seq_estudios'::regclass) NOT NULL,
 "id_pool_objetos" Bigint NOT NULL,
 "codigo" Text NOT NULL,
 "creado_por" Bigint NOT NULL,
 "titulo" Character varying(1000) NOT NULL,
 "descripcion" Text
)
WITH (OIDS=FALSE)
;

-- Add keys for table public.estudios

ALTER TABLE "public"."estudios" ADD CONSTRAINT "pk_estudios" PRIMARY KEY ("id_estudios")
;

ALTER TABLE "public"."estudios" ADD CONSTRAINT "unique_estudio" UNIQUE ("id_pool_objetos","creado_por","titulo")
;

-- Table public.instancia_objetos

CREATE TABLE "public"."instancia_objetos"(
 "id_instancia_objetos" Bigint DEFAULT nextval('seq_instancia_objetos'::regclass) NOT NULL,
 "objeto" Character varying(1000) NOT NULL,
 "id_pool_objetos" Bigint NOT NULL,
 "creado_por" Bigint NOT NULL,
 "identificador_publico" Character varying(255) NOT NULL,
 "tipo_acceso" Integer DEFAULT 0,
 "fecha_creacion" Date DEFAULT ('now'::text)::date,
 "fecha_inicio" Date DEFAULT ('now'::text)::date,
 "fecha_cierre" Date DEFAULT ('now'::text)::date,
 "poblacion_asociada" Bigint
)
WITH (OIDS=FALSE)
;

-- Create indexes for table public.instancia_objetos

CREATE UNIQUE INDEX "unique_identificador_publico" ON "public"."instancia_objetos" ("identificador_publico")
;

-- Add keys for table public.instancia_objetos

ALTER TABLE "public"."instancia_objetos" ADD CONSTRAINT "pk_instancia_objetos" PRIMARY KEY ("id_instancia_objetos")
;

-- Table public.instancia_preguntas

CREATE TABLE "public"."instancia_preguntas"(
 "id_instancia_preguntas" Bigint DEFAULT nextval('seq_instancia_preguntas'::regclass) NOT NULL,
 "pregunta" Character varying(1000) NOT NULL,
 "id_pool_preguntas" Bigint NOT NULL,
 "creado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "id_pool_objetos" Bigint NOT NULL,
 "orden_pregunta" Integer DEFAULT 1,
 "solo_lectura" Boolean DEFAULT false,
 "id_estudios" Bigint,
 "campo_comunicacion_email" Boolean DEFAULT false NOT NULL,
 "campo_comunicacion_telefono" Boolean DEFAULT false NOT NULL,
 "campo_comunicacion_telefono2" Boolean DEFAULT false NOT NULL,
 "campo_identificador" Boolean DEFAULT false NOT NULL,
 "campo_clave_unico" Boolean DEFAULT false NOT NULL
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "public"."instancia_preguntas"."solo_lectura" IS 'determina si esta pregunta es posible asignarle una respuesta o es de solo lectura por parte del usuario'
;

-- Add keys for table public.instancia_preguntas

ALTER TABLE "public"."instancia_preguntas" ADD CONSTRAINT "pk_instancia_preguntas" PRIMARY KEY ("id_instancia_preguntas")
;

-- Table public.pool_objetos

CREATE TABLE "public"."pool_objetos"(
 "id_pool_objetos" Bigint DEFAULT nextval('seq_pool_objetos'::regclass) NOT NULL,
 "nombre_amistoso" Character varying(255) NOT NULL,
 "creado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "tipo_objeto" Integer DEFAULT 1 NOT NULL,
 "visible" Boolean DEFAULT true
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "public"."pool_objetos"."tipo_objeto" IS '1=censo
2=encuesta'
;

-- Create indexes for table public.pool_objetos

CREATE UNIQUE INDEX "unique_nombre" ON "public"."pool_objetos" ("nombre_amistoso")
;

-- Add keys for table public.pool_objetos

ALTER TABLE "public"."pool_objetos" ADD CONSTRAINT "pk_pool_objetos" PRIMARY KEY ("id_pool_objetos")
;

-- Table public.pool_preguntas

CREATE TABLE "public"."pool_preguntas"(
 "id_pool_preguntas" Bigint DEFAULT nextval('seq_pool_preguntas'::regclass) NOT NULL,
 "nombre_amistoso" Character varying(255) NOT NULL,
 "creado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "tipo_pregunta" Integer DEFAULT 0,
 "visible" Boolean DEFAULT true
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "public"."pool_preguntas"."tipo_pregunta" IS '1 pregunta cerrada seleccion simple
2 pregunta cerrada seleccion multiple
30 pregunta abierta texto
31 pregunta abierta numerica sin decimales
32 pregunta abierta numerica con decimales
33 pregunta abierta fecha
'
;

-- Create indexes for table public.pool_preguntas

CREATE UNIQUE INDEX "unique_pool_preguntas" ON "public"."pool_preguntas" ("nombre_amistoso")
;

-- Add keys for table public.pool_preguntas

ALTER TABLE "public"."pool_preguntas" ADD CONSTRAINT "pk_pool_preguntas" PRIMARY KEY ("id_pool_preguntas")
;

-- Table public.pool_respuestas_posibles

CREATE TABLE "public"."pool_respuestas_posibles"(
 "id_pool_respuestas_posibles" Bigint DEFAULT nextval('seq_pool_respuestas_posibles'::regclass) NOT NULL,
 "respuesta" Character varying(255) NOT NULL,
 "id_pool_preguntas" Bigint NOT NULL,
 "creado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "visibilidad" Smallint DEFAULT 1 NOT NULL
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "public"."pool_respuestas_posibles"."visibilidad" IS '1 publico
0 privado'
;

-- Add keys for table public.pool_respuestas_posibles

ALTER TABLE "public"."pool_respuestas_posibles" ADD CONSTRAINT "id_pool_respuestas_posibles" PRIMARY KEY ("id_pool_respuestas_posibles")
;

-- Table public.respuestas

CREATE TABLE "public"."respuestas"(
 "id_respuestas" Bigint DEFAULT nextval('seq_respuestas'::regclass) NOT NULL,
 "id_instancia_objetos" Bigint NOT NULL,
 "id_instancia_preguntas" Bigint NOT NULL,
 "respuesta_string" Text,
 "elaborado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "id_respuestas_posibles" Bigint DEFAULT (-1),
 "respuesta_int" Bigint,
 "respuesta_float" Double precision,
 "respuesta_date" Date
)
WITH (OIDS=FALSE)
;

-- Add keys for table public.respuestas

ALTER TABLE "public"."respuestas" ADD CONSTRAINT "pk_respuestas" PRIMARY KEY ("id_respuestas")
;

-- Table public.respuestas_temp

CREATE TABLE "public"."respuestas_temp"(
 "id_respuestas" Bigint DEFAULT nextval('seq_respuestas_temp'::regclass) NOT NULL,
 "id_instancia_objetos" Bigint NOT NULL,
 "id_instancia_preguntas" Bigint NOT NULL,
 "respuesta_string" Text,
 "elaborado_por" Bigint NOT NULL,
 "fecha_creacion" Date DEFAULT ('now'::text)::date NOT NULL,
 "id_respuestas_posibles" Bigint DEFAULT (-1),
 "respuesta_int" Integer,
 "respuesta_float" Double precision,
 "respuesta_date" Date
)
WITH (OIDS=FALSE)
;

-- Add keys for table public.respuestas_temp

ALTER TABLE "public"."respuestas_temp" ADD CONSTRAINT "pk_respuestas_temp" PRIMARY KEY ("id_respuestas")
;

-- Table public.usuarios

CREATE TABLE "public"."usuarios"(
 "id_usuarios" Bigint DEFAULT nextval('seq_usuarios'::regclass) NOT NULL,
 "campo_clave" Character varying(255) NOT NULL,
 "id_poblacion" Bigint NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table public.usuarios

ALTER TABLE "public"."usuarios" ADD CONSTRAINT "pk_usuarios" PRIMARY KEY ("id_usuarios")
;

ALTER TABLE "public"."usuarios" ADD CONSTRAINT "unico_usuario" UNIQUE ("campo_clave","id_poblacion")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "public"."estudios" ADD CONSTRAINT "fk_admin" FOREIGN KEY ("creado_por") REFERENCES "public"."administradores" ("id_administradores") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."pool_objetos" ADD CONSTRAINT "fk_admin" FOREIGN KEY ("creado_por") REFERENCES "public"."administradores" ("id_administradores") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."pool_preguntas" ADD CONSTRAINT "fk_admin" FOREIGN KEY ("creado_por") REFERENCES "public"."administradores" ("id_administradores") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."pool_respuestas_posibles" ADD CONSTRAINT "fk_admin" FOREIGN KEY ("creado_por") REFERENCES "public"."administradores" ("id_administradores") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."estudios" ADD CONSTRAINT "fk_estudios" FOREIGN KEY ("id_pool_objetos") REFERENCES "public"."pool_objetos" ("id_pool_objetos") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."instancia_objetos" ADD CONSTRAINT "fk_id_pool_objetos" FOREIGN KEY ("id_pool_objetos") REFERENCES "public"."pool_objetos" ("id_pool_objetos") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."pool_respuestas_posibles" ADD CONSTRAINT "fk_id_pool_preguntas" FOREIGN KEY ("id_pool_preguntas") REFERENCES "public"."pool_preguntas" ("id_pool_preguntas") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."respuestas" ADD CONSTRAINT "fk_instancia_objetos" FOREIGN KEY ("id_instancia_objetos") REFERENCES "public"."instancia_objetos" ("id_instancia_objetos") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."respuestas" ADD CONSTRAINT "fk_instancia_preguntas" FOREIGN KEY ("id_instancia_preguntas") REFERENCES "public"."instancia_preguntas" ("id_instancia_preguntas") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."instancia_preguntas" ADD CONSTRAINT "fk_pool_objetos" FOREIGN KEY ("id_pool_objetos") REFERENCES "public"."pool_objetos" ("id_pool_objetos") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."instancia_preguntas" ADD CONSTRAINT "fk_pool_preguntas" FOREIGN KEY ("id_pool_preguntas") REFERENCES "public"."pool_preguntas" ("id_pool_preguntas") ON DELETE NO ACTION ON UPDATE NO ACTION
;



-- Create roles section -------------------------------------------------

CREATE ROLE "postgres" LOGIN
;
CREATE ROLE "usr_siagcee" LOGIN
;

-- Grant permissions section -------------------------------------------------


