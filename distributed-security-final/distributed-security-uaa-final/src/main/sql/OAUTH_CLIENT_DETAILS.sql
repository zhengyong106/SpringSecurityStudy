/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.109
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.1.109:1521
 Source Schema         : TEST

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 08/06/2020 16:25:35
*/


-- ----------------------------
-- Table structure for OAUTH_CLIENT_DETAILS
-- ----------------------------
DROP TABLE "TEST"."OAUTH_CLIENT_DETAILS";
CREATE TABLE "TEST"."OAUTH_CLIENT_DETAILS" (
  "CLIENT_ID" VARCHAR2(128 BYTE) NOT NULL,
  "RESOURCE_IDS" VARCHAR2(256 BYTE),
  "CLIENT_SECRET" VARCHAR2(256 BYTE),
  "SCOPE" VARCHAR2(256 BYTE),
  "AUTHORIZED_GRANT_TYPES" VARCHAR2(256 BYTE),
  "WEB_SERVER_REDIRECT_URI" VARCHAR2(256 BYTE),
  "AUTHORITIES" VARCHAR2(256 BYTE),
  "ACCESS_TOKEN_VALIDITY" NUMBER(11,0),
  "REFRESH_TOKEN_VALIDITY" NUMBER(11,0),
  "ADDITIONAL_INFORMATION" LONG,
  "AUTOAPPROVE" VARCHAR2(256 BYTE)
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of OAUTH_CLIENT_DETAILS
-- ----------------------------
INSERT INTO "TEST"."OAUTH_CLIENT_DETAILS" VALUES ('c1', 'res1', '$2a$10$lzklM/jf.Lf1Ot70jwicQ.9GuD40XCF.5FCMGOwpQZoo4QoqQqQDq', 'all', '[authorization_code, password,client_credentials,implicit,refresh_token]', 'http://www.baidu.com', NULL, NULL, NULL, NULL, 'false');

-- ----------------------------
-- Primary Key structure for table OAUTH_CLIENT_DETAILS
-- ----------------------------
ALTER TABLE "TEST"."OAUTH_CLIENT_DETAILS" ADD CONSTRAINT "SYS_C0037778" PRIMARY KEY ("CLIENT_ID");

-- ----------------------------
-- Checks structure for table OAUTH_CLIENT_DETAILS
-- ----------------------------
ALTER TABLE "TEST"."OAUTH_CLIENT_DETAILS" ADD CONSTRAINT "SYS_C0037777" CHECK ("CLIENT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
