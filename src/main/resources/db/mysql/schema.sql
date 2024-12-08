--
--	DROP OF SECONDARY TABLES
--

DROP TABLE IF EXISTS visitas_medicas_aud;

DROP TABLE IF EXISTS vacunas_aud;

DROP TABLE IF EXISTS usuario_pacientes_aud;

DROP TABLE IF EXISTS usuarios_aud;

DROP TABLE IF EXISTS signos_vitales_pacientes_aud;

DROP TABLE IF EXISTS signos_vitales_customs_aud;

DROP TABLE IF EXISTS recetas_aud;

DROP TABLE IF EXISTS rango_edades_aud;

DROP TABLE IF EXISTS profesionales_aud;

DROP TABLE IF EXISTS prescripciones_aud;

DROP TABLE IF EXISTS historias_medicamentos_aud;

DROP TABLE IF EXISTS grupos_familiares_aud;

DROP TABLE IF EXISTS estudios_aud;

DROP TABLE IF EXISTS especialidades_aud;

DROP TABLE IF EXISTS direcciones_aud;

DROP TABLE IF EXISTS diagnosticos_aud;

DROP TABLE IF EXISTS contactos_aud;

DROP TABLE IF EXISTS instituciones_salud_aud;

DROP TABLE IF EXISTS calendarios_edades_vacunas_aud;

DROP TABLE IF EXISTS calendarios_aud;

DROP TABLE IF EXISTS historias_medicas_aud;

DROP TABLE IF EXISTS centros_salud_aud;

DROP TABLE IF EXISTS my_revision_entity;

--
--	DROP OF TABLES
--

DROP TABLE IF EXISTS direcciones_usuarios;

DROP TABLE IF EXISTS direcciones_profesionales;

DROP TABLE IF EXISTS contactos_usuarios;

DROP TABLE IF EXISTS contactos_profesionales;

DROP TABLE IF EXISTS contactos_instituciones_salud;

DROP TABLE IF EXISTS contactos;

DROP TABLE IF EXISTS argentina_estudios;

DROP TABLE IF EXISTS argentina_recetas;

DROP TABLE IF EXISTS recetas;

DROP TABLE IF EXISTS estudios;

DROP TABLE IF EXISTS prescripciones;

DROP TABLE IF EXISTS adjuntos;

DROP TABLE IF EXISTS visitas_medicas;

DROP TABLE IF EXISTS diagnosticos;

DROP TABLE IF EXISTS usuario_profesionales;

DROP TABLE if EXISTS profesional_especialidades;

DROP TABLE if EXISTS instituciones_profesionales;

DROP TABLE if EXISTS especialidades;

DROP TABLE IF EXISTS profesionales;

DROP TABLE IF EXISTS instituciones_salud;

DROP TABLE IF EXISTS direcciones_instituciones_salud;

DROP TABLE IF EXISTS direcciones;

DROP TABLE IF EXISTS calendarios_edades_vacunas;

DROP TABLE IF EXISTS vacunas_tipo_calendarios;

DROP TABLE IF EXISTS vacunas_edades;

DROP TABLE IF EXISTS vacunas_aplicadas;

DROP TABLE IF EXISTS vacunas;

DROP TABLE IF EXISTS edades_tipo_calendarios;

DROP TABLE IF EXISTS calendarios_edades;

DROP TABLE IF EXISTS rango_edades;

DROP TABLE IF EXISTS calendario_infantes;

DROP TABLE IF EXISTS calendario_adultos;

DROP TABLE IF EXISTS calendario_embarazadas;

DROP TABLE IF EXISTS calendario_personales_salud;

DROP TABLE IF EXISTS calendarios;

DROP TABLE IF EXISTS tipo_calendarios;

DROP TABLE IF EXISTS historias_medicamentos;

DROP TABLE IF EXISTS medicamentos_recordatorios;

DROP TABLE IF EXISTS medicamentos_productos;

DROP TABLE IF EXISTS medicamentos;

DROP TABLE IF EXISTS historias_familiares;

DROP TABLE IF EXISTS signos_vitales_pacientes;

DROP TABLE IF EXISTS signos_vitales_customs;

DROP TABLE IF EXISTS tipos_signos_vitales;

DROP TABLE IF EXISTS turnos;

DROP TABLE IF EXISTS historias_medicas;

DROP TABLE IF EXISTS usuario_pacientes;

DROP TABLE IF EXISTS admins_grupos_familiares;

DROP TABLE IF EXISTS usuarios_grupos_familiares;

DROP TABLE IF EXISTS grupos_notificaciones;

DROP TABLE IF EXISTS grupos_familiares;

DROP TABLE IF EXISTS autorizaciones_composiciones;

DROP TABLE IF EXISTS grupos_permisos;

DROP TABLE IF EXISTS roles_grupos;

DROP TABLE IF EXISTS roles_permisos;

DROP TABLE IF EXISTS grupos;

DROP TABLE IF EXISTS permisos;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS autorizaciones;

DROP TABLE IF EXISTS verificaciones_cuentas;

DROP TABLE IF EXISTS usuarios_autorizaciones_componentes;

DROP TABLE IF EXISTS usuarios;

DROP TABLE IF EXISTS autorizaciones_componentes;

--
-- CREATE OF TABLES
--

CREATE TABLE autorizaciones_componentes(
	acp_id bigint NOT NULL AUTO_INCREMENT,
    acp_tipo_componente VARCHAR(255) NOT NULL,
    acp_codigo VARCHAR(45) NOT NULL,
    acp_descripcion VARCHAR(255) NOT NULL,
    acp_activo tinyint(1) DEFAULT NULL,
    PRIMARY KEY (acp_id)
);

CREATE TABLE grupos(
	acp_id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (acp_id),
    CONSTRAINT acp_grp_acp_fk FOREIGN KEY (acp_id) REFERENCES autorizaciones_componentes (acp_id)
);

CREATE TABLE permisos(
	acp_id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (acp_id),
    CONSTRAINT acp_prm_acp_fk FOREIGN KEY (acp_id) REFERENCES autorizaciones_componentes (acp_id)
);

CREATE TABLE roles(
	acp_id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (acp_id),
    CONSTRAINT acp_rol_acp_fk FOREIGN KEY (acp_id) REFERENCES autorizaciones_componentes (acp_id)
);

CREATE TABLE grupos_permisos(
	gpr_id bigint NOT NULL AUTO_INCREMENT,
    gpr_grp_id bigint NOT NULL,
    gpr_prm_id bigint NOT NULL,
    PRIMARY KEY (gpr_id),
    CONSTRAINT gpr_grp_fk FOREIGN KEY (gpr_grp_id) REFERENCES grupos (acp_id),
    CONSTRAINT gpr_prm_fk FOREIGN KEY (gpr_prm_id) REFERENCES permisos (acp_id)
);

CREATE TABLE roles_grupos(
	rgp_id bigint NOT NULL AUTO_INCREMENT,
    rgp_rol_id bigint NOT NULL,
    rgp_grp_id bigint NOT NULL,
    PRIMARY KEY (rgp_id),
    CONSTRAINT rgp_rol_fk FOREIGN KEY (rgp_rol_id) REFERENCES roles (acp_id),
    CONSTRAINT rgp_grp_fk FOREIGN KEY (rgp_grp_id) REFERENCES grupos (acp_id)
);

CREATE TABLE roles_permisos(
	rpr_id bigint NOT NULL AUTO_INCREMENT,
    rpr_rol_id bigint NOT NULL,
    rpr_prm_id bigint NOT NULL,
    PRIMARY KEY (rpr_id),
    CONSTRAINT rpr_rol_fk FOREIGN KEY (rpr_rol_id) REFERENCES roles (acp_id),
    CONSTRAINT rpr_prm_fk FOREIGN KEY (rpr_prm_id) REFERENCES permisos (acp_id)
);

CREATE TABLE autorizaciones(
	acp_id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (acp_id),
    CONSTRAINT aut_acp_fk FOREIGN KEY (acp_id) REFERENCES autorizaciones_componentes (acp_id)
);

CREATE TABLE autorizaciones_composiciones(
	acm_id bigint NOT NULL AUTO_INCREMENT,
    acm_aut_id bigint NOT NULL,
    acm_acp_id bigint NOT NULL,
    PRIMARY KEY (acm_id),
    CONSTRAINT acm_acp_fk FOREIGN KEY (acm_acp_id) REFERENCES autorizaciones_componentes (acp_id),
    CONSTRAINT acm_aut_fk FOREIGN KEY (acm_aut_id) REFERENCES autorizaciones (acp_id)
);

CREATE TABLE usuarios(
	usr_id bigint NOT NULL AUTO_INCREMENT,
    usr_img_perfil varchar(255) NULL,
    usr_documento bigint NULL,
	usr_nombre varchar(255) NOT NULL,
	usr_apellido varchar(255) NOT NULL,
	usr_fecha_nacimiento bigint NOT NULL,
	usr_genero VARCHAR(50) NOT NULL,
	usr_mail varchar(255) NOT NULL,
	usr_password varchar(255) NOT NULL,
	usr_tipo_usuario varchar(255) NOT NULL,
	usr_activo tinyint(1) DEFAULT NULL,
	PRIMARY KEY (usr_id)
);

CREATE TABLE usuarios_autorizaciones_componentes (
	uac_id bigint NOT NULL AUTO_INCREMENT,
    uac_usr_id bigint NOT NULL,
    uac_acp_id bigint NOT NULL,
    PRIMARY KEY (uac_id),
    CONSTRAINT uac_acp_fk FOREIGN KEY (uac_acp_id) REFERENCES autorizaciones_componentes (acp_id),
    CONSTRAINT uac_usr_fk FOREIGN KEY (uac_usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE usuario_pacientes(
	usr_id bigint NOT NULL,
	PRIMARY KEY (usr_id),
	CONSTRAINT usr_pte_usr_fk FOREIGN KEY (usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE usuario_profesionales(
	usr_id bigint NOT NULL,
	usr_pfl_matricula varchar(255) DEFAULT NULL,
	PRIMARY KEY (usr_id),
	CONSTRAINT usr_pfl_usr_fk FOREIGN KEY (usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE verificaciones_cuentas(
	vfc_id bigint NOT NULL AUTO_INCREMENT,
	vfc_codigo varchar(10) NOT NULL,
	vfc_fecha_generado bigint NOT NULL,
	vfc_fecha_validado bigint DEFAULT NULL,
	vfc_usr_id bigint NOT NULL,
	PRIMARY KEY (vfc_id),
	CONSTRAINT vfc_usr_fk FOREIGN KEY (vfc_usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE grupos_familiares(
	gfm_id bigint NOT NULL AUTO_INCREMENT,
    gfm_nombre varchar(75) NOT NULL,
    gfm_codigo varchar(5) NOT NULL,
    gfm_fecha_creado bigint NOT NULL,
    gfm_activo tinyint(1) NOT NULL,
    PRIMARY KEY(gfm_id)
);

CREATE TABLE historias_medicas(
	hta_id bigint NOT NULL AUTO_INCREMENT,
	hta_usr_id bigint NOT NULL,
	PRIMARY KEY(hta_id),
	CONSTRAINT hta_usr_fk FOREIGN KEY (hta_usr_id) REFERENCES usuario_pacientes (usr_id)
);

CREATE TABLE historias_familiares(
	htf_id bigint NOT NULL AUTO_INCREMENT,
    htf_hta_id bigint NOT NULL,
    htf_gfm_id bigint NOT NULL,
    PRIMARY KEY(htf_id),
    CONSTRAINT htf_hta_fk FOREIGN KEY (htf_hta_id) REFERENCES historias_medicas (hta_id),
    CONSTRAINT htf_gfm_fk FOREIGN KEY (htf_gfm_id) REFERENCES grupos_familiares (gfm_id)
);

CREATE TABLE admins_grupos_familiares(
	adg_id bigint NOT NULL AUTO_INCREMENT,
    adg_gfm_id bigint NOT NULL,
    adg_usr_id bigint NOT NULL,
    PRIMARY KEY (adg_id),
    CONSTRAINT adg_usr_fk FOREIGN KEY (adg_usr_id) REFERENCES usuarios (usr_id),
    CONSTRAINT adg_gfm_fk FOREIGN KEY (adg_gfm_id) REFERENCES grupos_familiares (gfm_id)
);

CREATE TABLE usuarios_grupos_familiares(
	ugf_id bigint NOT NULL AUTO_INCREMENT,
    ugf_gfm_id bigint NOT NULL,
    ugf_usr_id bigint NOT NULL,
    PRIMARY KEY (ugf_id),
    CONSTRAINT ugf_usr_fk FOREIGN KEY (ugf_usr_id) REFERENCES usuarios (usr_id),
    CONSTRAINT ugf_gfm_fk FOREIGN KEY (ugf_gfm_id) REFERENCES grupos_familiares (gfm_id)
);

CREATE TABLE grupos_notificaciones (
	gnt_id bigint NOT NULL AUTO_INCREMENT,
	gnt_aceptada tinyint(1) NOT NULL,
	gnt_fecha bigint NOT NULL,
	gnt_gfm_id bigint NOT NULL,
	gnt_usr_id bigint NOT NULL,
	PRIMARY KEY (gnt_id),
    CONSTRAINT gnt_usr_fk FOREIGN KEY (gnt_usr_id) REFERENCES usuarios (usr_id),
    CONSTRAINT gnt_gfm_fk FOREIGN KEY (gnt_gfm_id) REFERENCES grupos_familiares (gfm_id)
);

CREATE TABLE direcciones(
	dir_id bigint NOT NULL AUTO_INCREMENT,
	dir_direccion varchar(255) DEFAULT NULL,
    dir_provincia varchar(255) DEFAULT NULL,
    dir_localidad varchar(255) DEFAULT NULL,
    dir_barrio varchar(255) DEFAULT NULL,
	dir_piso varchar(50) DEFAULT NULL,
	dir_departamento varchar(2) DEFAULT NULL,
	dir_referencia varchar(255) DEFAULT NULL,
    dir_tipo_entidad varchar(255) NOT NULL,
	PRIMARY KEY(dir_id)
);

CREATE TABLE direcciones_instituciones_salud(
	dir_id bigint NOT NULL,
	PRIMARY KEY (dir_id),
    CONSTRAINT dit_dir_fk FOREIGN KEY (dir_id) REFERENCES direcciones (dir_id)
);

CREATE TABLE instituciones_salud(
	itc_id bigint NOT NULL AUTO_INCREMENT,
    itc_nombre varchar(255) NOT NULL,
    itc_hta_id bigint NOT NULL,
    itc_dir_id bigint NOT NULL,
    itc_activo tinyint(1) NOT NULL,
    PRIMARY KEY(itc_id),
    CONSTRAINT itc_hta_fk FOREIGN KEY (itc_hta_id) REFERENCES historias_medicas (hta_id),
    CONSTRAINT itc_dir_fk FOREIGN KEY (itc_dir_id) REFERENCES direcciones_instituciones_salud (dir_id)
);

CREATE TABLE profesionales(
	pfl_id bigint NOT NULL AUTO_INCREMENT,
    pfl_nombre varchar(255) NOT NULL,
    pfl_tipo_matricula varchar(2) NOT NULL,
    pfl_matricula bigint NOT NULL,
    pfl_hta_id bigint NOT NULL,
    pfl_activo tinyint(1) NOT NULL,
    PRIMARY KEY (pfl_id),
    CONSTRAINT pfl_hta_fk FOREIGN KEY (pfl_hta_id) REFERENCES historias_medicas (hta_id)
);

CREATE TABLE especialidades(
	epc_id bigint NOT NULL AUTO_INCREMENT,
    epc_nombre varchar(255) NOT NULL,
    PRIMARY KEY(epc_id)
);

CREATE TABLE profesional_especialidades(
	pep_id bigint NOT NULL AUTO_INCREMENT,
    pep_pfl_id bigint NOT NULL,
    pep_epc_id bigint NOT NULL,
    PRIMARY KEY(pep_id),
    CONSTRAINT pep_pfl_fk FOREIGN KEY (pep_pfl_id) REFERENCES profesionales (pfl_id),
    CONSTRAINT pep_epc_fk FOREIGN KEY (pep_epc_id) REFERENCES especialidades (epc_id)
);

CREATE TABLE instituciones_profesionales(
	ipf_id bigint NOT NULL AUTO_INCREMENT,
    ipf_pfl_id bigint NOT NULL,
    ipf_itc_id bigint NOT NULL,
    PRIMARY KEY(ipf_id),
    CONSTRAINT ipf_pfl_fk FOREIGN KEY (ipf_pfl_id) REFERENCES profesionales (pfl_id),
    CONSTRAINT ipf_itc_fk FOREIGN KEY (ipf_itc_id) REFERENCES instituciones_salud (itc_id)
);

CREATE TABLE direcciones_usuarios(
	dir_id bigint NOT NULL,
    dir_usr_id bigint NOT NULL,
	PRIMARY KEY (dir_id),
    CONSTRAINT dus_dir_fk FOREIGN KEY (dir_id) REFERENCES direcciones (dir_id),
	CONSTRAINT dir_usr_fk FOREIGN KEY (dir_usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE direcciones_profesionales(
	dir_id bigint NOT NULL,
    dir_pfl_id bigint NOT NULL,
	PRIMARY KEY (dir_id),
    CONSTRAINT dpl_dir_fk FOREIGN KEY (dir_id) REFERENCES direcciones (dir_id),
	CONSTRAINT dir_pfl_fk FOREIGN KEY (dir_pfl_id) REFERENCES profesionales (pfl_id)
);

CREATE TABLE diagnosticos(
	dgt_id bigint NOT NULL AUTO_INCREMENT,
    dgt_nombre varchar(255) DEFAULT NULL,
    PRIMARY KEY(dgt_id)
);

CREATE TABLE turnos(
	trn_id bigint NOT NULL AUTO_INCREMENT,
	trn_fecha_inicio bigint NOT NULL,
	trn_direccion varchar(255) NOT NULL,
	trn_profesional varchar(255) DEFAULT NULL,
	trn_especialidad varchar(255) DEFAULT NULL,
	trn_institucion varchar(255) DEFAULT NULL,
	trn_motivo varchar(255) DEFAULT NULL,
	trn_google_id varchar(255) DEFAULT NULL,
	trn_activo tinyint(1) NOT NULL,
	trn_hta_id bigint NOT NULL,
	PRIMARY KEY(trn_id),
    CONSTRAINT trn_hta_fk FOREIGN KEY (trn_hta_id) REFERENCES historias_medicas (hta_id)
);

CREATE TABLE visitas_medicas(
	vta_id bigint NOT NULL AUTO_INCREMENT,
	vta_fecha bigint NOT NULL,
	vta_indicaciones varchar(255) NULL,
	vta_virtual tinyint(1) NOT NULL,
	vta_hta_id bigint NOT NULL,
    vta_itc_id bigint NULL,
    vta_pfl_id bigint NOT NULL,
    vta_epc_id bigint NOT NULL,
    vta_dgt_id bigint NOT NULL,
    vta_activo tinyint(1) NOT NULL,
	PRIMARY KEY(vta_id),
	CONSTRAINT vta_hta_fk FOREIGN KEY (vta_hta_id) REFERENCES historias_medicas(hta_id),
    CONSTRAINT vta_itc_fk FOREIGN KEY (vta_itc_id) REFERENCES instituciones_salud(itc_id),
    CONSTRAINT vta_pfl_fk FOREIGN KEY (vta_pfl_id) REFERENCES profesionales(pfl_id),
    CONSTRAINT vta_epc_fk FOREIGN KEY (vta_epc_id) REFERENCES especialidades(epc_id),
    CONSTRAINT vta_dgt_fk FOREIGN KEY (vta_dgt_id) REFERENCES diagnosticos(dgt_id)
);

CREATE TABLE prescripciones(
	pcp_id bigint NOT NULL AUTO_INCREMENT,
	pcp_pais_prescripcion varchar(255) DEFAULT NULL,
	pcp_vta_id bigint NOT NULL,
	PRIMARY KEY (pcp_id),
	CONSTRAINT pcp_vta_fk FOREIGN KEY (pcp_vta_id) REFERENCES visitas_medicas(vta_id)
);

CREATE TABLE recetas (
	rta_id bigint NOT NULL AUTO_INCREMENT,
    rta_url varchar(255) NOT NULL,
    rta_descripcion varchar(255) DEFAULT NULL,
    rta_fecha bigint NOT NULL,
	rta_pcp_id bigint NOT NULL,
	PRIMARY KEY(rta_id),
	CONSTRAINT rta_pcp_fk FOREIGN KEY (rta_pcp_id) REFERENCES prescripciones(pcp_id)
);

CREATE TABLE estudios(
	esd_id bigint NOT NULL AUTO_INCREMENT,
    esd_url varchar(255) NOT NULL,
    esd_descripcion varchar(255) DEFAULT NULL,
    esd_fecha bigint NOT NULL,
	esd_pcp_id bigint NOT NULL,
	PRIMARY KEY(esd_id),
	CONSTRAINT esd_pcp_fk FOREIGN KEY (esd_pcp_id) REFERENCES prescripciones(pcp_id)
);

CREATE TABLE argentina_recetas(
	rta_id bigint NOT NULL,
	rta_tipo_receta varchar(255) DEFAULT NULL,
	PRIMARY KEY(rta_id),
	CONSTRAINT arg_rta_fk FOREIGN KEY (rta_id) REFERENCES recetas(rta_id)
);

CREATE TABLE argentina_estudios(
	esd_id bigint NOT NULL,
	esd_tipo_estudio varchar(255) DEFAULT NULL,
	PRIMARY KEY(esd_id),
	CONSTRAINT arg_esd_fk FOREIGN KEY (esd_id) REFERENCES estudios(esd_id)
);

CREATE TABLE adjuntos(
	adj_id bigint NOT NULL AUTO_INCREMENT,
    adj_uri varchar(255) NOT NULL,
    PRIMARY KEY(adj_id)
);

CREATE TABLE contactos(
	ctc_id bigint NOT NULL AUTO_INCREMENT,
	ctc_mail_alternativo varchar(255) DEFAULT NULL,
	ctc_telefono varchar(255) DEFAULT NULL,
    ctc_tipo_entidad varchar(255) NOT NULL,
	PRIMARY KEY(ctc_id)
);

CREATE TABLE contactos_usuarios(
	ctc_id bigint NOT NULL,
    ctc_usr_id bigint NOT NULL,
    PRIMARY KEY(ctc_id),
    CONSTRAINT cus_ctc_fk FOREIGN KEY (ctc_id) REFERENCES contactos (ctc_id),
    CONSTRAINT ctc_usr_fk FOREIGN KEY (ctc_usr_id) REFERENCES usuarios (usr_id)
);

CREATE TABLE contactos_profesionales(
	ctc_id bigint NOT NULL,
    ctc_pfl_id bigint NOT NULL,
    PRIMARY KEY(ctc_id),
    CONSTRAINT cpl_ctc_fk FOREIGN KEY (ctc_id) REFERENCES contactos (ctc_id),
    CONSTRAINT ctc_pfl_fk FOREIGN KEY (ctc_pfl_id) REFERENCES profesionales (pfl_id)
);

CREATE TABLE contactos_instituciones_salud(
	cic_id bigint NOT NULL,
    cic_cis_id bigint NOT NULL,
    PRIMARY KEY(cic_id),
    CONSTRAINT cic_ctc_fk FOREIGN KEY (cic_id) REFERENCES contactos (ctc_id),
    CONSTRAINT cic_cis_fk FOREIGN KEY (cic_cis_id) REFERENCES instituciones_salud (itc_id)
);

CREATE TABLE tipo_calendarios(
	tcl_id varchar(50) NOT NULL,
    PRIMARY KEY (tcl_id)
);

CREATE TABLE calendarios(
	cld_id bigint NOT NULL AUTO_INCREMENT,
	cld_tcl_id VARCHAR(50),
    cld_hta_id bigint NOT NULL,
	PRIMARY KEY (cld_id),
    CONSTRAINT cld_tcl_fk FOREIGN KEY (cld_tcl_id) REFERENCES tipo_calendarios (tcl_id),
    CONSTRAINT cld_hta_fk FOREIGN KEY (cld_hta_id) REFERENCES historias_medicas (hta_id)
);

CREATE TABLE calendario_infantes(
	cld_id bigint NOT NULL,
	PRIMARY KEY (cld_id),
	CONSTRAINT cld_cif_fk FOREIGN KEY (cld_id) REFERENCES calendarios (cld_id)
);

CREATE TABLE calendario_adultos(
	cld_id bigint NOT NULL,
	PRIMARY KEY (cld_id),
	CONSTRAINT cld_cad_fk FOREIGN KEY (cld_id) REFERENCES calendarios (cld_id)
);

CREATE TABLE calendario_embarazadas(
	cld_id bigint NOT NULL,
	PRIMARY KEY (cld_id),
	CONSTRAINT cld_ceb_fk FOREIGN KEY (cld_id) REFERENCES calendarios (cld_id)
);

CREATE TABLE calendario_personales_salud(
	cld_id bigint NOT NULL,
	PRIMARY KEY (cld_id),
	CONSTRAINT cld_cps_fk FOREIGN KEY (cld_id) REFERENCES calendarios (cld_id)
);

CREATE TABLE vacunas(
	vcn_id bigint NOT NULL AUTO_INCREMENT,
	vcn_nombre VARCHAR(255) NOT NULL,
	vcn_descripcion VARCHAR(255) NOT NULL,
	vcn_obligatoria tinyint(1) NOT NULL,
    vcn_cantidad_dosis int NULL,
	PRIMARY KEY (vcn_id)
);

CREATE TABLE vacunas_aplicadas(
	vcp_id bigint NOT NULL AUTO_INCREMENT,
	vcp_numero_dosis int NOT NULL,
    vcp_fecha bigint NOT NULL,
    vcp_aplicada varchar(25) NOT NULL,
    vcp_vcn_id bigint NOT NULL,
    PRIMARY KEY(vcp_id),
    CONSTRAINT vcp_vcn_fk FOREIGN KEY (vcp_vcn_id) REFERENCES vacunas (vcn_id)
);

CREATE TABLE vacunas_tipo_calendarios(
	vtc_id bigint NOT NULL AUTO_INCREMENT,
    vtc_tcl_id varchar(50) NOT NULL,
    vtc_vcn_id bigint NOT NULL,
    PRIMARY KEY(vtc_id),
    CONSTRAINT vtc_tcl_fk FOREIGN KEY (vtc_tcl_id) REFERENCES tipo_calendarios (tcl_id),
    CONSTRAINT vtc_vcn_fk FOREIGN KEY (vtc_vcn_id) REFERENCES vacunas (vcn_id)
);

CREATE TABLE rango_edades(
	ede_id bigint NOT NULL AUTO_INCREMENT,
	ede_nombre VARCHAR(255) NOT NULL,
	PRIMARY KEY (ede_id)
);

CREATE TABLE vacunas_edades(
	vcd_id bigint NOT NULL AUTO_INCREMENT,
    vcd_vcn_id bigint NOT NULL,
    vcd_ede_id bigint NOT NULL,
    PRIMARY KEY(vcd_id),
    CONSTRAINT vcd_vcn_fk FOREIGN KEY (vcd_vcn_id) REFERENCES vacunas (vcn_id),
	CONSTRAINT vcd_ede_fk FOREIGN KEY (vcd_ede_id) REFERENCES rango_edades (ede_id)
);

CREATE TABLE calendarios_edades(
	cle_id bigint NOT NULL AUTO_INCREMENT,
    cle_cld_id bigint NOT NULL,
    cle_ede_id bigint NOT NULL,
    PRIMARY KEY(cle_id),
    CONSTRAINT cle_cld_fk FOREIGN KEY (cle_cld_id) REFERENCES calendarios (cld_id),
	CONSTRAINT cle_ede_fk FOREIGN KEY (cle_ede_id) REFERENCES rango_edades (ede_id)
);

CREATE TABLE edades_tipo_calendarios(
	etc_id bigint NOT NULL AUTO_INCREMENT,
    etc_tcl_id VARCHAR(50) NOT NULL,
    etc_ede_id bigint NOT NULL,
    PRIMARY KEY (etc_id),
    CONSTRAINT etc_tcl_fk FOREIGN KEY (etc_tcl_id) REFERENCES tipo_calendarios (tcl_id),
	CONSTRAINT etc_ede_fk FOREIGN KEY (etc_ede_id) REFERENCES rango_edades (ede_id)
);

CREATE TABLE calendarios_edades_vacunas(
	cev_id bigint NOT NULL AUTO_INCREMENT,
    cev_cld_id bigint NOT NULL,
    cev_ede_id bigint NOT NULL,
    cev_vcp_id bigint NOT NULL,
    PRIMARY KEY (cev_id),
    CONSTRAINT cev_cld_fk FOREIGN KEY (cev_cld_id) REFERENCES calendarios (cld_id),
	CONSTRAINT cev_ede_fk FOREIGN KEY (cev_ede_id) REFERENCES rango_edades (ede_id),
    CONSTRAINT cev_vcp_fk FOREIGN KEY (cev_vcp_id) REFERENCES vacunas_aplicadas (vcp_id)
);

CREATE TABLE medicamentos(
	mdc_id bigint NOT NULL AUTO_INCREMENT,
	mdc_nombre VARCHAR(355) NOT NULL,
	PRIMARY KEY (mdc_id)
);

CREATE TABLE medicamentos_productos(
	mpt_id bigint NOT NULL AUTO_INCREMENT,
	mpt_nombre VARCHAR(355) NOT NULL,
	mpt_mdc_id bigint NOT NULL,
	PRIMARY KEY (mpt_id),
	CONSTRAINT mpt_mdc_fk FOREIGN KEY (mpt_mdc_id) REFERENCES medicamentos (mdc_id)
);

CREATE TABLE medicamentos_recordatorios(
	mrc_id bigint NOT NULL AUTO_INCREMENT,
    mrc_fecha_inicio bigint NOT NULL,
    mrc_fecha_final bigint NULL,
    mrc_cronico tinyint(1) NULL,
    mrc_frecuencia int NOT NULL,
    mrc_tipo_frecuencia VARCHAR(15) NOT NULL,
    mrc_dosis int NOT NULL,
    mrc_reposicion int NULL,
	mrc_google_id varchar(255) DEFAULT NULL,
    PRIMARY KEY (mrc_id)
);

CREATE TABLE historias_medicamentos(
	htm_id bigint NOT NULL AUTO_INCREMENT,
    htm_cantidad int NOT NULL,
    htm_comentarios VARCHAR(255) NULL,
    htm_presentacion VARCHAR(10) NOT NULL,
	htm_hta_id bigint NOT NULL,
	htm_mpt_id bigint NOT NULL,
    htm_mrc_id bigint NULL,
	PRIMARY KEY (htm_id),
	CONSTRAINT htm_mpt_fk FOREIGN KEY (htm_mpt_id) REFERENCES medicamentos_productos (mpt_id),
    CONSTRAINT htm_mrc_fk FOREIGN KEY (htm_mrc_id) REFERENCES medicamentos_recordatorios (mrc_id),
	CONSTRAINT htm_hta_fk FOREIGN KEY (htm_hta_id) REFERENCES historias_medicas (hta_id)
);

CREATE TABLE tipos_signos_vitales(
	tsv_id bigint NOT NULL AUTO_INCREMENT,
    tsv_nombre VARCHAR(50) NOT NULL,
    tsv_medida VARCHAR(50) DEFAULT NULL,
    tsv_cantidad_valores int NOT NULL,
    PRIMARY KEY (tsv_id)
);

CREATE TABLE signos_vitales_customs(
	svc_id bigint NOT NULL AUTO_INCREMENT,
    svc_minimo decimal(6,2) NOT NULL,
    svc_maximo decimal(6,2) NOT NULL,
	svc_segundo_minimo decimal(6,2),
    svc_segundo_maximo decimal(6,2),
	svc_tsv_id bigint NOT NULL,
    svc_hta_id bigint NOT NULL,
    PRIMARY KEY(svc_id),
    CONSTRAINT svc_tsv_fk FOREIGN KEY (svc_tsv_id) REFERENCES tipos_signos_vitales (tsv_id),
    CONSTRAINT svc_hta_fk FOREIGN KEY (svc_hta_id) REFERENCES historias_medicas (hta_id)
);

CREATE TABLE signos_vitales_pacientes(
	svp_id bigint NOT NULL AUTO_INCREMENT,
    svp_valor decimal(6,2)  NOT NULL,
	svp_segundo_valor decimal(6,2),
    svp_fecha_ingresado bigint NOT NULL,
    svp_comentario varchar(255) NOT NULL,
    svp_minimo decimal(6,2) NOT NULL,
    svp_maximo decimal(6,2) NOT NULL,
	svp_segundo_minimo decimal(6,2),
    svp_segundo_maximo decimal(6,2),
    svp_hta_id bigint NOT NULL,
    svp_svc_id bigint NOT NULL,
    PRIMARY KEY (svp_id),
    CONSTRAINT svp_svc_fk FOREIGN KEY (svp_svc_id) REFERENCES signos_vitales_customs (svc_id),
    CONSTRAINT svp_hta_fk FOREIGN KEY (svp_hta_id) REFERENCES historias_medicas (hta_id)
);

--
-- CREATE OF SECONDARY TABLES
--

CREATE TABLE my_revision_entity (
	id integer not null,
	timestamp bigint not null,
	modifier_user varchar(255),
	primary key (id)
);

CREATE TABLE calendarios_aud (
	cld_id bigint not null,
	rev integer not null,
	cld_tcl_id varchar(31) not null,
	revtype tinyint,
	cld_hta_id bigint,
	primary key (cld_id, rev),
	CONSTRAINT cld_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE calendarios_edades_vacunas_aud (
	cev_id bigint not null,
	rev integer not null,
	revtype tinyint,
	cev_cld_id bigint,
	cev_ede_id bigint,
	cev_vcn_id bigint,
    cev_aplicada integer,
    cev_fecha_aplicada bigint,
    cev_numero_dosis integer,
	primary key (cev_id, rev),
    CONSTRAINT cev_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE instituciones_salud_aud (
	itc_id bigint not null,
	rev integer not null,
	revtype tinyint,
	itc_nombre varchar(255),
	itc_hta_id bigint,
	primary key (itc_id, rev),
    CONSTRAINT itc_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE contactos_aud (
	ctc_id bigint not null,
	rev integer not null,
	revtype tinyint,
	ctc_mail_alternativo varchar(255),
	ctc_telefono varchar(255),
	ctc_tipo_entidad varchar(255),
	primary key (ctc_id, rev),
    CONSTRAINT ctc_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE diagnosticos_aud (
	dgt_id bigint not null,
	rev integer not null,
	revtype tinyint,
	dgt_nombre varchar(255),
	primary key (dgt_id, rev),
    CONSTRAINT dgt_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE direcciones_aud (
	dir_id bigint not null,
	rev integer not null,
	revtype tinyint,
	dir_departamento varchar(255),
	dir_direccion varchar(255),
    dir_provincia varchar(255),
    dir_localidad varchar(255),
    dir_barrio varchar(255),
	dir_piso varchar(255),
	dir_referencia varchar(255),
	dir_tipo_entidad varchar(255),
	primary key (dir_id, rev),
    CONSTRAINT dir_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE especialidades_aud (
	epc_id bigint not null,
	rev integer not null,
	revtype tinyint,
	epc_nombre varchar(255),
	primary key (epc_id, rev),
    CONSTRAINT epc_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE estudios_aud (
	esd_id bigint not null,
	rev integer not null,
	revtype tinyint,
	esd_pcp_id bigint,
    esd_url varchar(255),
    esd_descripcion varchar(255),
    esd_fecha bigint,
	primary key (esd_id, rev),
    CONSTRAINT esd_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE grupos_familiares_aud (
	gfm_id bigint not null,
	rev integer not null,
	revtype tinyint,
	gfm_codigo varchar(255),
	gfm_fecha_creado bigint,
	gfm_nombre varchar(255),
	primary key (gfm_id, rev),
    CONSTRAINT gfm_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE historias_medicamentos_aud (
	htm_id bigint not null,
	rev integer not null,
	revtype tinyint,
	htm_cantidad integer,
    htm_comentarios VARCHAR(255),
    htm_presentacion VARCHAR(10),
	htm_hta_id bigint,
    htm_mrc_id bigint,
	primary key (htm_id, rev),
    CONSTRAINT htm_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE historias_medicas_aud (
	hta_id bigint not null,
	rev integer not null,
	revtype tinyint,
	primary key (hta_id, rev),
    CONSTRAINT hta_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE prescripciones_aud (
	pcp_id bigint not null,
	rev integer not null,
	revtype tinyint,
	pcp_descripcion varchar(255),
	pcp_pais_prescripcion varchar(255),
	pcp_vta_id bigint,
	primary key (pcp_id, rev),
	CONSTRAINT pcp_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE profesionales_aud (
	pfl_id bigint not null,
	rev integer not null,
	revtype tinyint,
	pfl_nombre varchar(255),
    pfl_tipo_matricula varchar(2) NOT NULL,
    pfl_matricula bigint NOT NULL,
	pfl_hta_id bigint NOT NULL,
	primary key (pfl_id, rev),
    CONSTRAINT pfl_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE rango_edades_aud (
	ede_id bigint not null,
	rev integer not null,
	revtype tinyint,
	ede_nombre varchar(255),
	primary key (ede_id, rev),
    CONSTRAINT ede_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE recetas_aud (
	rta_id bigint not null,
	rev integer not null,
	revtype tinyint,
	rta_pcp_id bigint,
    rta_url varchar(255),
    rta_descripcion varchar(255),
    rta_fecha bigint,
	primary key (rta_id, rev),
    CONSTRAINT rta_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE signos_vitales_customs_aud (
	svc_id bigint not null,
	rev integer not null,
	revtype tinyint,
	svc_maximo decimal(6,2),
	svc_minimo decimal(6,2),
    svc_segundo_minimo decimal(6,2),
    svc_segundo_maximo decimal(6,2),
	svc_hta_id bigint,
	primary key (svc_id, rev),
    CONSTRAINT svc_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE signos_vitales_pacientes_aud (
	svp_id bigint not null,
	rev integer not null,
	revtype tinyint,
	svp_comentario varchar(255),
	svp_fecha_ingresado bigint,
	svp_valor decimal(6,2),
    svp_segundo_valor decimal(6,2),
    svp_minimo decimal(6,2),
    svp_maximo decimal(6,2),
    svp_segundo_minimo decimal(6,2),
    svp_segundo_maximo decimal(6,2),
	svp_hta_id bigint,
	primary key (svp_id, rev),
    CONSTRAINT svp_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE usuarios_aud (
	usr_id bigint not null,
    usr_img_perfil varchar(255),
    usr_documento bigint,
	rev integer not null,
	usr_tipo_usuario varchar(31) not null,
	revtype tinyint,
	usr_apellido varchar(255),
	usr_fecha_nacimiento bigint,
	usr_genero varchar(255),
	usr_mail varchar(255),
	usr_nombre varchar(255),
	usr_password varchar(255),
	primary key (usr_id, rev),
    CONSTRAINT usr_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE vacunas_aud (
	vcn_id bigint not null,
	rev integer not null,
	revtype tinyint,
	vcn_descripcion varchar(255),
	vcn_nombre varchar(255),
	vcn_obligatoria bit,
    vcn_cantidad_dosis integer,
	primary key (vcn_id, rev),
    CONSTRAINT vcn_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);

CREATE TABLE visitas_medicas_aud (
	vta_id bigint not null,
	rev integer not null,
	revtype tinyint,
	vta_virtual bit,
	vta_fecha bigint,
    vta_indicaciones varchar(255),
	vta_dgt_id bigint not null,
	vta_hta_id bigint not null,
	primary key (vta_id, rev),
    CONSTRAINT vta_rev_fk FOREIGN KEY (rev) REFERENCES my_revision_entity (id)
);