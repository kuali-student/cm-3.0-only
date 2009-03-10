// LU LU RelationType
INSERT INTO KSLU_LULU_RELTN_TYPE (ID, NAME, DESCR, REV_NAME, REV_DESC, EFF_DT, EXPIR_DT, VERSIONIND) values ('luLuType.type1', 'bob', 'my desc', 'rev name', 'rev desc', '2008-01-01 00:00:00.0', '2010-01-01 00:00:00.0', 0)
/
INSERT INTO KSLU_LULU_RELTN_TYPE (ID, NAME, DESCR, REV_NAME, REV_DESC, EFF_DT, EXPIR_DT, VERSIONIND) values ('luLuType.type2', 'santiago', 'my desc', 'rev name', 'rev desc', '2008-01-01 00:00:00.0', '2010-01-01 00:00:00.0', 0)
/
INSERT INTO KSLU_LULU_RELTN_TYPE (ID, NAME, DESCR, REV_NAME, REV_DESC, EFF_DT, EXPIR_DT, VERSIONIND) values ('luLuType.type3', 'manolin', 'my desc', 'rev name', 'rev desc', '2008-01-01 00:00:00.0', '2010-01-01 00:00:00.0', 0)
/

// LU Type
INSERT INTO KSLU_LUTYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('luType.shell.course', 'A Shell Course', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Course')
/
INSERT INTO KSLU_LUTYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('luType.shell.program', 'A Shell Program', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Program')
/


// CluInstructor
INSERT INTO KSLU_CLU_INSTR (ID, ORG_ID, PERS_ID) VALUES ('INSTR-1', 'ORG-1', 'PersonID')
/
INSERT INTO KSLU_CLU_INSTR (ID, ORG_ID, PERS_ID) VALUES ('INSTR-2', 'ORG-2', 'PersonID')
/

// CluPublishing
INSERT INTO KSLU_CLU_PUBL (ID, END_CYCLE, START_CYCLE, ST, TYPE, PRI_INSTR_ID) VALUES ('PUBL-1', 'Fall', 'Spring', 'State', 'Type', 'INSTR-1')
/
INSERT INTO KSLU_CLU_PUBL (ID, END_CYCLE, START_CYCLE, ST, TYPE, PRI_INSTR_ID) VALUES ('PUBL-2', 'Fall', 'Summer', 'State', 'Type', 'INSTR-2')
/

// RichText
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-1', '<p>Desc</p>', 'Desc')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-2', '<p>Marketing Desc</p>', 'Marketing Desc')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-3', '<p>Desc2</p>', 'Desc2')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-4', '<p>Marketing Desc2</p>', 'Marketing Desc2')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-5', '<p>Core CluSet</p>', 'Core CluSet')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-6', '<p>CoreEnglish CluSet</p>', 'CoreEnglish CluSet')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-7', '<p>First year science CluSet</p>', 'First year science CluSet')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-8', '<p>First year arts CluSet</p>', 'First year arts CluSet')
/

// CluIdentifier
INSERT INTO KSLU_CLU_IDENT (ID, CD, DIV, LVL, LNG_NAME, SHRT_NAME, ST, TYPE, VARTN) VALUES ('IDENT-1', 'Code', 'Division', 'Level', 'Long Name', 'Shortname', 'State', 'Type', 'Variation')
/
INSERT INTO KSLU_CLU_IDENT (ID, CD, DIV, LVL, LNG_NAME, SHRT_NAME, ST, TYPE, VARTN) VALUES ('IDENT-2', 'Code', 'Division', 'Level', 'Long Name', 'Shortname', 'State', 'Type', 'Variation')
/

// CluCredit
INSERT INTO KSLU_CLU_CR (ID, INSTR_UNIT, MAX_ALOW_INACV_ATP, MAX_ALOW_INACV_TMQ, MAX_TM_RSLT_RCGZ_ATP, MAX_TM_RSLT_RCGZ_TMQ, MAX_TM_TO_COMP_ATP, MAX_TM_TO_COMP_TMQ, MAX_TOT_UNIT, MIN_TM_TO_COMP_ATP, MIN_TM_TO_COMP_TMQ, MIN_TOT_UNIT, REPEAT_CNT, REPEAT_TM_ATP, REPEAT_TM_TMQ, REPEAT_UNIT) VALUES ('CR-1', 0, 'ATP-INACT-1', 0, 'ATP-RECOG-1', 0, 'ATP-MAXCOMPLETE-1', 0, 0, 'ATP-MINCOMPLETE-1', 0, 0, 'Repeat Count', 'ATP-REPEAT-1', 0, 'Repeat Units')
/
INSERT INTO KSLU_CLU_CR (ID, INSTR_UNIT, MAX_ALOW_INACV_ATP, MAX_ALOW_INACV_TMQ, MAX_TM_RSLT_RCGZ_ATP, MAX_TM_RSLT_RCGZ_TMQ, MAX_TM_TO_COMP_ATP, MAX_TM_TO_COMP_TMQ, MAX_TOT_UNIT, MIN_TM_TO_COMP_ATP, MIN_TM_TO_COMP_TMQ, MIN_TOT_UNIT, REPEAT_CNT, REPEAT_TM_ATP, REPEAT_TM_TMQ, REPEAT_UNIT) VALUES ('CR-2', 0, 'ATP-INACT-2', 0, 'ATP-RECOG-2', 0, 'ATP-MAXCOMPLETE-2', 0, 0, 'ATP-MINCOMPLETE-2', 0, 0, 'Repeat Count', 'ATP-REPEAT-2', 0, 'Repeat Units')
/

// CluAccounting
INSERT INTO KSLU_CLU_ACCT (ID) VALUES ('ACCT-1')
/
INSERT INTO KSLU_CLU_ACCT (ID) VALUES ('ACCT-2')
/

// CluFee
INSERT INTO KSLU_CLU_FEE (ID) VALUES ('FEE-1')
/
INSERT INTO KSLU_CLU_FEE (ID) VALUES ('FEE-2')
/

// Clu
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'STATE1', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-1', 'CR-1', 'RICHTEXT-1', 'FEE-1', 'luType.shell.course', 'RICHTEXT-2', 'IDENT-1', 'INSTR-1', 'PUBL-1')
/
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'STATE2', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-2', 'CR-2', 'RICHTEXT-2', 'FEE-2', 'luType.shell.program', 'RICHTEXT-2', 'IDENT-2', 'INSTR-2', 'PUBL-2')
/
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-3', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'STATE2', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-2', 'CR-2', 'RICHTEXT-2', 'FEE-2', 'luType.shell.course', 'RICHTEXT-2', 'IDENT-2', 'INSTR-2', 'PUBL-2')
/
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-4', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'STATE1', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-1', 'CR-1', 'RICHTEXT-1', 'FEE-1', 'luType.shell.course', 'RICHTEXT-2', 'IDENT-1', 'INSTR-1', 'PUBL-1')
/


//Lui
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-1', 'CLU-1', 'ATP-1', 'MENG 329 section 101', 50, '2000-01-01 00:00:00.0', '2000-06-01 00:00:00.0', 'Approved', 1)
/
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-2', 'CLU-1', 'ATP-2', 'MENG 329 section 102', 75, '2000-08-25 00:00:00.0', '2000-12-31 00:00:00.0', 'Approved', 1)
/
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-3', 'CLU-1', 'ATP-2', 'MENG 329 section 112', 75, '2000-08-25 00:00:00.0', '2000-12-31 00:00:00.0', 'Activated', 1)
/
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-4', 'CLU-2', 'ATP-1', 'BENG 471 section 101', 75, '2000-01-01 00:00:00.0', '2000-06-01 00:00:00.0', 'Retired', 1)
/

// CluAtpTypeKey
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-1', 'atpType.semester.fall')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-2', 'atpType.semester.spring')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-3', 'atpType.semester.summer')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-4', 'atpType.quarter.fall')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-5', 'atpType.quarter.winter')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-6', 'atpType.quarter.spring')
/
insert into KSLU_CLU_ATP_TYPE_KEY (ID, ATP_TYPE_KEY) values ('ATP-7', 'atpType.quarter.summer')
/

// CluSet
INSERT INTO KSLU_CLU_SET (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, RT_DESCR_ID, CRIT_SET) VALUES ('CLUSET-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, '2003-01-01 00:00:00.0', '2004-01-01 00:00:00.0', 'Core Cluset Name', 'RICHTEXT-5', 0)
/
INSERT INTO KSLU_CLU_SET (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, RT_DESCR_ID, CRIT_SET) VALUES ('CLUSET-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, '2003-01-01 00:00:00.0', '2004-01-01 00:00:00.0', 'Core English Cluset Name', 'RICHTEXT-6', 0)
/
INSERT INTO KSLU_CLU_SET (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, RT_DESCR_ID, CRIT_SET) VALUES ('CLUSET-3', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, '2003-01-01 00:00:00.0', '2004-01-01 00:00:00.0', 'First year science CluSet', 'RICHTEXT-7', 0)
/
INSERT INTO KSLU_CLU_SET (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, RT_DESCR_ID, CRIT_SET) VALUES ('CLUSET-4', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, '2003-01-01 00:00:00.0', '2004-01-01 00:00:00.0', 'First year arts CluSet', 'RICHTEXT-8', 0)
/

// Clu <-> CluSet join
INSERT INTO KSLU_CLU_SET_JN_CLU (CLU_SET_ID, CLU_ID) VALUES ('CLUSET-2', 'CLU-1')
/
INSERT INTO KSLU_CLU_SET_JN_CLU (CLU_SET_ID, CLU_ID) VALUES ('CLUSET-2', 'CLU-3')
/
INSERT INTO KSLU_CLU_SET_JN_CLU (CLU_SET_ID, CLU_ID) VALUES ('CLUSET-3', 'CLU-2')
/
INSERT INTO KSLU_CLU_SET_JN_CLU (CLU_SET_ID, CLU_ID) VALUES ('CLUSET-4', 'CLU-2')
/
INSERT INTO KSLU_CLU_SET_JN_CLU (CLU_SET_ID, CLU_ID) VALUES ('CLUSET-4', 'CLU-3')
/

// CluSet <-> CluSet join
INSERT INTO KSLU_CLU_SET_JN_CLU_SET (CLU_SET_PARENT_ID, CLU_SET_CHILD_ID) VALUES ('CLUSET-2', 'CLUSET-3')
/
INSERT INTO KSLU_CLU_SET_JN_CLU_SET (CLU_SET_PARENT_ID, CLU_SET_CHILD_ID) VALUES ('CLUSET-1', 'CLUSET-2')
/
INSERT INTO KSLU_CLU_SET_JN_CLU_SET (CLU_SET_PARENT_ID, CLU_SET_CHILD_ID) VALUES ('CLUSET-2', 'CLUSET-4')
/


//STATEMENT TYPES
INSERT INTO KSLU_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.createCourseAcademicReadiness', 'Rules used in the evaluation of a person''s academic readiness for enrollment in an LU.', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Overall Academic Readiness Rules')
INSERT INTO KSLU_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.prereqAcademicReadiness', 'Pre req rules used in the evaluation of a person''s academic readiness for enrollment in an LU.', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Academic Readiness Pre Reqs')
INSERT INTO KSLU_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.coreqAcademicReadiness', 'Co req used in the evaluation of a person''s academic readiness for enrollment in an LU.', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Academic Readiness Co Reqs')

// REQUIREMENT TYPES
INSERT INTO KSLU_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList', 'Student must have completed all of <courses>', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Course completed')
INSERT INTO KSLU_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.grdCondCourseList', 'Student must have completed <Course> with a minimum grade of <Grade> ', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Course completed with minimum specified grade')
INSERT INTO KSLU_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.gradecheck', 'Minimum overall GPA of <value>', '2000-01-01 00:00:00.0', '2001-11-30 00:00:00.0', 'Minimum overall GPA')

// STMT_TYPE <-> REQ_COM_TYPE
INSERT INTO KSLU_STY_JN_RQTY (LU_STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.courseList')
INSERT INTO KSLU_STY_JN_RQTY (LU_STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.grdCondCourseList')
INSERT INTO KSLU_STY_JN_RQTY (LU_STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.gradecheck')

// STMT_TYPE <-> STMT_TYPE
INSERT INTO KSLU_STY_JN_LUSTY (LU_STMT_TYPE_ID, CHLD_LU_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.prereqAcademicReadiness')
INSERT INTO KSLU_STY_JN_LUSTY (LU_STMT_TYPE_ID, CHLD_LU_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.coreqAcademicReadiness')

// LU_STMT
INSERT INTO KSLU_STMT (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, NAME, DESCR, ST, OPERATOR, PARENT_LU_STMT_ID,LU_STMT_TYPE_ID) VALUES ('STMT-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0',1,'STMT 1','Statement 1','ACTIVE','AND', null ,'kuali.luStatementType.createCourseAcademicReadiness')
INSERT INTO KSLU_STMT (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, NAME, DESCR, ST, OPERATOR, PARENT_LU_STMT_ID,LU_STMT_TYPE_ID) VALUES ('STMT-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0',1,'STMT 2','Statement 2','ACTIVE','AND','STMT-1','kuali.luStatementType.prereqAcademicReadiness')

// REQ COM
INSERT INTO KSLU_REQ_COM (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, DESCR, ST, EFF_DT, EXPIR_DT, REQ_COM_TYPE_ID) VALUES ('REQCOMP-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0',1,'Required Component 1','ACTIVE','2001-01-01 00:00:00.0','2002-01-01 00:00:00.0','kuali.reqCompType.courseList')
INSERT INTO KSLU_REQ_COM (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, DESCR, ST, EFF_DT, EXPIR_DT, REQ_COM_TYPE_ID) VALUES ('REQCOMP-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0',1,'Required Component 2','ACTIVE','2001-01-01 00:00:00.0','2002-01-01 00:00:00.0','kuali.reqCompType.grdCondCourseList')
INSERT INTO KSLU_REQ_COM (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, DESCR, ST, EFF_DT, EXPIR_DT, REQ_COM_TYPE_ID) VALUES ('REQCOMP-3', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0',1,'Required Component 3','ACTIVE','2001-01-01 00:00:00.0','2002-01-01 00:00:00.0','kuali.reqCompType.gradecheck')

// CLU <-> LU STMT
INSERT INTO KSLU_CLU_JN_LU_STMT (CLU_ID, LU_STMT_ID) VALUES ('CLU-1','STMT-1')

// LU STMT <-> REQ COM                 
INSERT INTO KSLU_STMT_JN_REQ_COM (REQ_COM_ID, LU_STMT_ID) VALUES ('REQCOMP-1','STMT-2')                 
INSERT INTO KSLU_STMT_JN_REQ_COM (REQ_COM_ID, LU_STMT_ID) VALUES ('REQCOMP-2','STMT-2')                 
INSERT INTO KSLU_STMT_JN_REQ_COM (REQ_COM_ID, LU_STMT_ID) VALUES ('REQCOMP-3','STMT-2')    

// CluCluRelation
INSERT INTO KSLU_CLUCLU_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, CLU_RELTN_REQ, EFF_DT, EXPIR_DT, ST, CLU_ID, LU_RELTN_TYPE_ID, RELATED_CLU_ID) VALUES ('CLUCLUREL-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 1, '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active', 'CLU-1', 'luLuType.type1', 'CLU-2')
INSERT INTO KSLU_CLUCLU_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, CLU_RELTN_REQ, EFF_DT, EXPIR_DT, ST, CLU_ID, LU_RELTN_TYPE_ID, RELATED_CLU_ID) VALUES ('CLUCLUREL-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 1, '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active', 'CLU-1', 'luLuType.type1', 'CLU-3')

// CluCluRelation
INSERT INTO KSLU_LUILUI_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LUI_ID,RELATED_LUI_ID,LULU_RELTN_TYPE_ID, EFF_DT, EXPIR_DT, ST) VALUES ('LUILUIREL-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'LUI-1', 'LUI-2','luLuType.type1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
INSERT INTO KSLU_LUILUI_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LUI_ID,RELATED_LUI_ID,LULU_RELTN_TYPE_ID, EFF_DT, EXPIR_DT, ST) VALUES ('LUILUIREL-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'LUI-1', 'LUI-3','luLuType.type1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
INSERT INTO KSLU_LUILUI_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LUI_ID,RELATED_LUI_ID,LULU_RELTN_TYPE_ID, EFF_DT, EXPIR_DT, ST) VALUES ('LUILUIREL-3', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'LUI-2', 'LUI-4','luLuType.type1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')

// LuDocRelation Type
INSERT INTO KSLU_LU_DOC_RELTN_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('luDocRelationType.doctype1', 'Doc Type 1', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'DT1')
INSERT INTO KSLU_LU_DOC_RELTN_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('luDocRelationType.doctype2', 'Doc Type 2', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'DT2')

// LuDocRelation
INSERT INTO KSLU_LU_DOC_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LU_DOC_RELTN_TYPE_ID, CLU_ID, DOC_ID, TITLE, EFF_DT, EXPIR_DT, ST) VALUES ('LUDOCREL-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'luDocRelationType.doctype1', 'CLU-1', 'DOC-1', 'TITLE1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
INSERT INTO KSLU_LU_DOC_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LU_DOC_RELTN_TYPE_ID, CLU_ID, DOC_ID, TITLE, EFF_DT, EXPIR_DT, ST) VALUES ('LUDOCREL-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'luDocRelationType.doctype1', 'CLU-1', 'DOC-2', 'TITLE1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
INSERT INTO KSLU_LU_DOC_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LU_DOC_RELTN_TYPE_ID, CLU_ID, DOC_ID, TITLE, EFF_DT, EXPIR_DT, ST) VALUES ('LUDOCREL-3', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'luDocRelationType.doctype1', 'CLU-2', 'DOC-1', 'TITLE1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
INSERT INTO KSLU_LU_DOC_RELTN (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, LU_DOC_RELTN_TYPE_ID, CLU_ID, DOC_ID, TITLE, EFF_DT, EXPIR_DT, ST) VALUES ('LUDOCREL-4', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 0, 'luDocRelationType.doctype2', 'CLU-2', 'DOC-3', 'TITLE1', '2003-01-01 00:00:00.0', '2100-01-01 00:00:00.0', 'Active')
