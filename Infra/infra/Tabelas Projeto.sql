DROP SEQUENCE s_modulo_01
/

CREATE SEQUENCE s_modulo_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_programa_01
/

CREATE SEQUENCE s_programa_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_prog_tp_acesso_01
/

CREATE SEQUENCE s_prog_tp_acesso_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_tp_acesso_01
/

CREATE SEQUENCE s_tp_acesso_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE S_USUARIO_01
/

CREATE SEQUENCE s_usuario_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_usu_acesso_01
/

CREATE SEQUENCE s_usu_acesso_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_usu_acesso_erro_01
/

CREATE SEQUENCE s_usu_acesso_erro_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_usu_acesso_log_01
/

CREATE SEQUENCE s_usu_acesso_log_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_usu_prog_01
/

CREATE SEQUENCE s_usu_prog_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_COBRANCA_01
/

CREATE SEQUENCE S_COBRANCA_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_aluno_01
/

CREATE SEQUENCE S_ALUNO_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE S_ALUNO_COBRANCA_01
/

CREATE SEQUENCE S_ALUNO_COBRANCA_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_ESTADO_01
/

CREATE SEQUENCE S_ESTADO_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_PAIS_01
/

CREATE SEQUENCE S_PAIS_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_CURSO_01
/

CREATE SEQUENCE S_CURSO_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

DROP SEQUENCE s_STATUS_COBRANCA_01
/

CREATE SEQUENCE S_STATUS_COBRANCA_01
  INCREMENT BY 1
  START WITH 1
  MINVALUE 1
  MAXVALUE 999999999
  NOCYCLE
  NOORDER
  NOCACHE
/

/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     14/08/2017 10:37:53                          */
/*==============================================================*/


/*==============================================================*/
/* Table: SEG_MODULO                                            */
/*==============================================================*/
create table SEG_MODULO 
(
   MODU_ID              numeric              not null,
   MODU_NOM             varchar2(100)         not null,
   MODU_DSC             varchar2(250),
   MODU_ICONE           varchar2(150),
   MODU_CLASS           varchar2(150),
   MODU_NUM_ORDEM       numeric
);

comment on table SEG_MODULO is
'Tabela de Cadastro de Modulo (Sistema)';

comment on column SEG_MODULO.MODU_ID is
'Chave primária da tabela módulo.';

comment on column SEG_MODULO.MODU_NOM is
'Nome do modulo (sistema)';

comment on column SEG_MODULO.MODU_DSC is
'Descrição do modulo (sistema)';

comment on column SEG_MODULO.MODU_ICONE is
'Icone do menu';

comment on column SEG_MODULO.MODU_CLASS is
'Class de Menu';

comment on column SEG_MODULO.MODU_NUM_ORDEM is
'Número de ordem de Apresentação do Menu';

alter table SEG_MODULO
   add constraint MODU_PK primary key (MODU_ID);

alter table SEG_MODULO
   add constraint MODU_UK unique (MODU_NOM);

/*==============================================================*/
/* Table: SEG_PROGRAMA                                          */
/*==============================================================*/
create table SEG_PROGRAMA 
(
   PROG_ID              numeric              not null,
   PROG_NOM             varchar2(100)         not null,
   PROG_DSC             varchar2(250)         not null,
   PROG_FORM            varchar2(100)         not null,
   PROG_IND_STS         numeric(1)           default 1 not null,
   MODU_ID              numeric,
   PROG_DIRETORIO       varchar2(150),
   PROG_ICONE           varchar2(150),
   PROG_IND_VISIVEL     numeric(1)           not null,
   PROG_NUM_ORDEM       numeric
);

comment on table SEG_PROGRAMA is
'Tabela  Para Cadastro de Telas de Sistema';

comment on column SEG_PROGRAMA.PROG_ID is
'Chave primária da tabela programa.';

comment on column SEG_PROGRAMA.PROG_NOM is
'Nome do programa (tela).';

comment on column SEG_PROGRAMA.PROG_DSC is
'Descrição de programa.';

comment on column SEG_PROGRAMA.PROG_FORM is
'Nome do formulário do C+';

comment on column SEG_PROGRAMA.PROG_IND_STS is
'Indicador de status da tela.';

comment on column SEG_PROGRAMA.MODU_ID is
'Chave primária da tabela módulo.';

comment on column SEG_PROGRAMA.PROG_DIRETORIO is
'Diretório do arquivo';

comment on column SEG_PROGRAMA.PROG_ICONE is
'Icone do programa';

comment on column SEG_PROGRAMA.PROG_IND_VISIVEL is
'Indicador se o programa é visivel';

comment on column SEG_PROGRAMA.PROG_NUM_ORDEM is
'Número de Ordem de Menu';

alter table SEG_PROGRAMA
   add constraint PROG_CK01 check (PROG_IND_STS in (1,0));

alter table SEG_PROGRAMA
   add constraint PROG_CK02 check (PROG_IND_VISIVEL in (1,0));

alter table SEG_PROGRAMA
   add constraint PROG_PK primary key (PROG_ID);

alter table SEG_PROGRAMA
   add constraint PROG_UK unique (PROG_NOM);

/*==============================================================*/
/* Table: SEG_PROGRAMA_TIPO_ACESSO                              */
/*==============================================================*/
create table SEG_PROGRAMA_TIPO_ACESSO 
(
   PTAC_ID              numeric              not null,
   PROG_ID              numeric,
   TACE_ID              numeric,
   PTAC_IND_CONSULTA    numeric(1)           default 1 not null,
   PTAC_IND_EDITA       numeric(1)           default 1 not null,
   PTAC_IND_INSERE      numeric(1)           default 1 not null,
   PTAC_IND_EXCLUI      numeric(1)           default 1 not null
);

comment on table SEG_PROGRAMA_TIPO_ACESSO is
'Tabela associativa programa por tipo de acesso.
';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PTAC_ID is
'Número identificador de programa tipo acesso';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PROG_ID is
'Chave primária da tabela programa.';

comment on column SEG_PROGRAMA_TIPO_ACESSO.TACE_ID is
'Chave primaria da tabela de tipo acesso.';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PTAC_IND_CONSULTA is
'Indicador de acesso a consulta.';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PTAC_IND_EDITA is
'Indicador de acesso a edição.';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PTAC_IND_INSERE is
'Indicador de acesso inserção.';

comment on column SEG_PROGRAMA_TIPO_ACESSO.PTAC_IND_EXCLUI is
'Indicador de acesso a exclusão.';

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PTAC_CK01 check (PTAC_IND_CONSULTA in (1,0));

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PTAC_CK02 check (PTAC_IND_EDITA in (1,0));

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PTAC_CK03 check (PTAC_IND_INSERE in (1,0));

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PTAC_CK04 check (PTAC_IND_EXCLUI in (1,0));

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PTAC_PK primary key (PTAC_ID);

/*==============================================================*/
/* Table: SEG_TIPO_ACESSO                                       */
/*==============================================================*/
create table SEG_TIPO_ACESSO 
(
   TACE_ID              numeric              not null,
   TACE_NOM_ACESSO      varchar2(50)          not null,
   TACE_DESC            varchar2(100)
);

comment on table SEG_TIPO_ACESSO is
'Tabela de cadastro de tipo de acesso.';

comment on column SEG_TIPO_ACESSO.TACE_ID is
'Número identificador de tipo de acesso.';

comment on column SEG_TIPO_ACESSO.TACE_NOM_ACESSO is
'Nome do tipo de acesso.';

comment on column SEG_TIPO_ACESSO.TACE_DESC is
'Descrição de tipo de acesso';

alter table SEG_TIPO_ACESSO
   add constraint TACE_PK primary key (TACE_ID);

alter table SEG_TIPO_ACESSO
   add constraint TACE_UK unique (TACE_NOM_ACESSO);

/*==============================================================*/
/* Table: SEG_USUARIO                                           */
/*==============================================================*/
create table SEG_USUARIO 
(
   USUA_ID              numeric               not null,
   TACE_ID              numeric               not null,
   USUA_NOME            varchar2(255)         not null,
   USUA_DAT_NASCIMENTO  DATE                  not null,
   USUA_LOGIN           varchar2(50)          not null,
   USUA_SENHA           varchar2(50)          not null,
   USUA_EMAIL           varchar2(100)         not null,
   USUA_DAT_TROCA_SENHA date,
   USUA_IND_STS         numeric(1)           default 1 not null
);

comment on table SEG_USUARIO is
'Tabela de Cadastro de Usuário';

comment on column SEG_USUARIO.USUA_ID is
'Chave unica da tabela de usuário.';

comment on column SEG_USUARIO.TACE_ID is
'Chave primaria da tabela de tipo acesso.';

comment on column SEG_USUARIO.USUA_SENHA is
'Senha do usuário';

comment on column SEG_USUARIO.USUA_EMAIL is
'Email do usuário';

comment on column SEG_USUARIO.USUA_DAT_TROCA_SENHA is
'Data da ultima troca de senha';

comment on column SEG_USUARIO.USUA_IND_STS is
'Indicador de Status';

alter table SEG_USUARIO
   add constraint USUA_CK01 check (USUA_IND_STS in (1,0));

alter table SEG_USUARIO
   add constraint USUA_PK primary key (USUA_ID);

alter table SEG_USUARIO
   add constraint USUA_UK unique (USUA_EMAIL);

/*==============================================================*/
/* Table: SEG_USUARIO_ACESSO                                    */
/*==============================================================*/
create table SEG_USUARIO_ACESSO 
(
   UACE_ID              numeric              not null,
   USUA_ID              numeric              not null,
   UACE_SESSAO          varchar2(50)          not null,
   UACE_IP              varchar2(50)          not null,
   UACE_SO              varchar2(50)          not null,
   UACE_NAVEGADOR       varchar2(50)          not null,
   UACE_NAVEGADOR_VERSAO varchar2(50)          not null,
   UACE_DAT             date             not null
);

comment on table SEG_USUARIO_ACESSO is
'Tabela de acessos do usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_ID is
'Chave primária da tabela de usuário acesso';

comment on column SEG_USUARIO_ACESSO.USUA_ID is
'Chave unica da tabela de usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_SESSAO is
'Sessão do usuário';

comment on column SEG_USUARIO_ACESSO.UACE_IP is
'Endereço Ip do acesso do usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_SO is
'Sistema operacional do acesso do usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_NAVEGADOR is
'Navegador do acesso do usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_NAVEGADOR_VERSAO is
'Versão do navegador do acesso do usuário.';

comment on column SEG_USUARIO_ACESSO.UACE_DAT is
'Data de acesso';

alter table SEG_USUARIO_ACESSO
   add constraint UACE_PK primary key (UACE_ID);

/*==============================================================*/
/* Table: SEG_USUARIO_ACESSO_ERRO                               */
/*==============================================================*/
create table SEG_USUARIO_ACESSO_ERRO 
(
   UAER_ID              numeric              not null,
   UACE_ID              numeric,
   UAER_ID_AUTO         numeric,
   UAER_DATA            date             not null,
   UAER_LOCAL           varchar2(250),
   UAER_SOURCE          varchar2(300),
   UAER_MESSAGE         varchar2(2000),
   UAER_TRACE           varchar2(2000)
);

comment on table SEG_USUARIO_ACESSO_ERRO is
'Tabela de usuário acesso erro.';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_ID is
'Número identificador de acesso erro';

comment on column SEG_USUARIO_ACESSO_ERRO.UACE_ID is
'Chave primária da tabela de usuário acesso';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_ID_AUTO is
'Número identificador de acesso erro auto relacionamento';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_DATA is
'Data de erro acesso';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_LOCAL is
'Local do acesso erro';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_SOURCE is
'Source acesso erro';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_MESSAGE is
'Mensagem de erro do acesso erro';

comment on column SEG_USUARIO_ACESSO_ERRO.UAER_TRACE is
'Trace acesso erro';

alter table SEG_USUARIO_ACESSO_ERRO
   add constraint UAER_PK primary key (UAER_ID);

/*==============================================================*/
/* Table: SEG_USUARIO_ACESSO_LOG                                */
/*==============================================================*/
create table SEG_USUARIO_ACESSO_LOG 
(
   UALO_ID              numeric              not null,
   PROG_ID              numeric,
   UACE_ID              numeric,
   UALO_DAT             date             not null
);

comment on table SEG_USUARIO_ACESSO_LOG is
'Tabela para guardar logs de acesso de usuário.';

comment on column SEG_USUARIO_ACESSO_LOG.UALO_ID is
'Chave primeira da tabela usuário log.';

comment on column SEG_USUARIO_ACESSO_LOG.PROG_ID is
'Chave primária da tabela programa.';

comment on column SEG_USUARIO_ACESSO_LOG.UACE_ID is
'Chave primária da tabela de usuário acesso';

comment on column SEG_USUARIO_ACESSO_LOG.UALO_DAT is
'Data de inserção de log';

alter table SEG_USUARIO_ACESSO_LOG
   add constraint ULOG_PK primary key (UALO_ID);

/*==============================================================*/
/* Table: SEG_USUARIO_PROGRAMA                                  */
/*==============================================================*/
create table SEG_USUARIO_PROGRAMA 
(
   UPRO_ID              numeric              not null,
   USUA_ID              numeric,
   PROG_ID              numeric,
   UPRO_IND_CONSULTA    numeric(1)           default 1 not null,
   UPRO_IND_EDITA       numeric(1)           default 1 not null,
   UPRO_IND_INSERE      numeric(1)           default 1 not null,
   UPRO_IND_EXCLUI      numeric(1)           default 1 not null
);

comment on table SEG_USUARIO_PROGRAMA is
'Tabela para gravar acessos de usuário.';

comment on column SEG_USUARIO_PROGRAMA.UPRO_ID is
'Chave primaria da tabela de usário modulo programa .';

comment on column SEG_USUARIO_PROGRAMA.USUA_ID is
'Chave unica da tabela de usuário.';

comment on column SEG_USUARIO_PROGRAMA.PROG_ID is
'Chave primária da tabela programa.';

comment on column SEG_USUARIO_PROGRAMA.UPRO_IND_CONSULTA is
'Indicador de acesso a consulta.';

comment on column SEG_USUARIO_PROGRAMA.UPRO_IND_EDITA is
'Indicador de acesso a edição.';

comment on column SEG_USUARIO_PROGRAMA.UPRO_IND_INSERE is
'Indicador de acesso inserção.';

comment on column SEG_USUARIO_PROGRAMA.UPRO_IND_EXCLUI is
'Indicador de acesso a exclusão.';

alter table SEG_USUARIO_PROGRAMA
   add constraint UPRO_CK01 check (UPRO_IND_CONSULTA in (1,0));

alter table SEG_USUARIO_PROGRAMA
   add constraint UPRO_CK02 check (UPRO_IND_EDITA in (1,0));

alter table SEG_USUARIO_PROGRAMA
   add constraint UPRO_CK04 check (UPRO_IND_INSERE in (1,0));

alter table SEG_USUARIO_PROGRAMA
   add constraint UPRO_CK05 check (UPRO_IND_EXCLUI in (1,0));

alter table SEG_USUARIO_PROGRAMA
   add constraint UMPR_PK primary key (UPRO_ID);

alter table SEG_USUARIO_PROGRAMA
   add constraint UPRO_UK unique (USUA_ID, PROG_ID);

alter table SEG_PROGRAMA
   add constraint MODU_PROG_FK foreign key (MODU_ID)
      references SEG_MODULO (MODU_ID);

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint PROG_PTAC_FK foreign key (PROG_ID)
      references SEG_PROGRAMA (PROG_ID);

alter table SEG_PROGRAMA_TIPO_ACESSO
   add constraint TACE_PTAC_FK foreign key (TACE_ID)
      references SEG_TIPO_ACESSO (TACE_ID);

alter table SEG_USUARIO
   add constraint TACE_USUA_FK foreign key (TACE_ID)
      references SEG_TIPO_ACESSO (TACE_ID);

alter table SEG_USUARIO_ACESSO
   add constraint USUA_UACE_FK foreign key (USUA_ID)
      references SEG_USUARIO (USUA_ID);

alter table SEG_USUARIO_ACESSO_ERRO
   add constraint UACE_UAER_FK foreign key (UACE_ID)
      references SEG_USUARIO_ACESSO (UACE_ID);

alter table SEG_USUARIO_ACESSO_ERRO
   add constraint UAER_UAER_FK foreign key (UAER_ID_AUTO)
      references SEG_USUARIO_ACESSO_ERRO (UAER_ID);

alter table SEG_USUARIO_ACESSO_LOG
   add constraint PROG_UALO_FK foreign key (PROG_ID)
      references SEG_PROGRAMA (PROG_ID);

alter table SEG_USUARIO_ACESSO_LOG
   add constraint UACE_UALO_FK foreign key (UACE_ID)
      references SEG_USUARIO_ACESSO (UACE_ID);

alter table SEG_USUARIO_PROGRAMA
   add constraint PROG_UPRO_FK foreign key (PROG_ID)
      references SEG_PROGRAMA (PROG_ID);

alter table SEG_USUARIO_PROGRAMA
   add constraint USUA_UMPR_FK foreign key (USUA_ID)
      references SEG_USUARIO (USUA_ID);
      
/*==============================================================*/
/* Table: TAB_CURSO                                             */
/*==============================================================*/
DROP table TAB_CURSO 
/

create table TAB_CURSO 
(
   CURS_ID              numeric              not null,
   CURS_NOM             VARCHAR2(100)        not null,
   CURS_SIGLA           VARCHAR2(003)        not null
);

alter table TAB_CURSO
   add constraint CURS_PK primary key (CURS_ID);
   
/*==============================================================*/
/* Table: TBG_PAIS                                              */
/*==============================================================*/
DROP table TBG_PAIS 
/

create table TBG_PAIS 
(
   PAIS_ID              numeric              not null,
   PAIS_NOM             VARCHAR2(100)        not null,
   PAIS_SIGLA           VARCHAR2(002)        not null
);

alter table TBG_PAIS
   add constraint PAIS_PK primary key (PAIS_ID);

/*==============================================================*/
/* Table: TBG_ESTADO                                            */
/*==============================================================*/
DROP table TBG_ESTADO 
/
create table TBG_ESTADO 
(
   ESTA_ID              numeric              not null,
   PAIS_ID              numeric              not null,
   ESTA_NOM             VARCHAR2(100)        not null,
   ESTA_SIGLA           VARCHAR2(002)        not null
);

alter table TBG_ESTADO
   add constraint ESTA_PK primary key (ESTA_ID);
   
alter table TBG_ESTADO
   add constraint ESTA_PAIS_FK foreign key (PAIS_ID)
      references TBG_PAIS (PAIS_ID);
      
/*==============================================================*/
/* Table: TAB_ALUNO                                            */
/*==============================================================*/
DROP table TAB_ALUNO 
/
create table TAB_ALUNO 
(
   ALUN_ID              numeric              not null,
   USUA_ID              numeric              not null,
   ALUN_CPF             varchar2(11)         not null,
   ALUN_NOM             varchar2(255)        not null,
   ALUN_DAT_NASC        DATE                 not null,
   ALUN_END             varchar2(255)        not null,
   ALUN_END_NR          number               not null,
   ALUN_END_COMPL       VARCHAR2(255)        not null,
   ALUN_BAIRRO          varchar2(100)        not null,
   ALUN_CIDADE          varchar2(100)        not null,
   ALUN_TEL_RESIDENCIAL varchar2(11)                 ,
   ALUN_TEL_CELULAR     varchar2(11)                 ,
   ESTA_ID              numeric              not null,
   PAIS_ID              numeric              not null,
   CURS_ID              numeric              not null
);

alter table TAB_ALUNO
   add constraint ALUN_PK primary key (ALUN_ID);

alter table TAB_ALUNO
   add constraint ALUN_ESTA_FK foreign key (ESTA_ID)
      references TBG_ESTADO (ESTA_ID);

alter table TAB_ALUNO
   add constraint ALUN_PAIS_FK foreign key (PAIS_ID)
      references TBG_PAIS (PAIS_ID);

alter table TAB_ALUNO
   add constraint ALUN_CURS_FK foreign key (CURS_ID)
      references TAB_CURSO (CURS_ID);
      
/*==============================================================*/
/* Table: TAB_STATUS_COBRANCA                                            */
/*==============================================================*/
DROP TABLE TAB_STATUS_COBRANCA
/

create table TAB_STATUS_COBRANCA 
(
   STCO_ID                 numeric              not null,
   STCO_NOM                varchar2(100)        not null,
   STCO_IND_VER_PAG        number(1,0)         DEFAULT 1 not null,
   STCO_IND_GER_NOV_COB    number(1,0)       DEFAULT 0 not null
);

alter table TAB_STATUS_COBRANCA
   add constraint STCO_PK primary key (STCO_ID);
   
/*==============================================================*/
/* Table: TAB_ALUNO_COBRANCA                                            */
/*==============================================================*/
DROP TABLE TAB_ALUNO_COBRANCA
/

create table TAB_ALUNO_COBRANCA 
(
   ALCO_ID              numeric              not null,
   ALUN_ID              numeric              not null,
   STCO_ID              number               not null,
   ALCO_MES_REF         number               not null,
   ALCO_DAT_VENC        DATE                 not null,
   COBN_ID              numeric              
);

alter table TAB_ALUNO_COBRANCA
   add constraint ALCO_PK primary key (ALCO_ID);
   
alter table TAB_ALUNO_COBRANCA
   add constraint ALCO_ALUN_FK foreign key (ALUN_ID)
      references TAB_ALUNO (ALUN_ID);

alter table TAB_ALUNO_COBRANCA
   add constraint ALCO_STCO_FK foreign key (STCO_ID)
      references TAB_STATUS_COBRANCA (STCO_ID);


/*==============================================================*/
/* Table: CFT_CONTROLE_COBRANCA                                            */
/*==============================================================*/
DROP TABLE CFT_CONTROLE_COBRANCA
/

create table CFT_CONTROLE_COBRANCA 
(
   COBN_ID              numeric              not null,
   COBN_DAT_VENC        DATE                 not null,
   COBN_DAT_PAG         DATE                 ,
   COBN_NOM             VARCHAR2(255)       not null,
   COBN_END_COB         VARCHAR2(500)       not null,
   COBN_VAL_COB         number              not null,
   COBN_JUROS           NUMBER              DEFAULT 0 ,
   COBN_MULTA           NUMBER              DEFAULT 0 ,
   COBN_CORRECAO        NUMBER              DEFAULT 0 
);

alter table CFT_CONTROLE_COBRANCA
   add constraint CNCO_PK primary key (COBN_ID);

      
alter table TAB_ALUNO_COBRANCA
   add constraint ALCO_CNCO_FK foreign key (COBN_ID)
      references CFT_CONTROLE_COBRANCA (COBN_ID);
      
