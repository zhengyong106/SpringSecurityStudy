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

 Date: 08/06/2020 16:25:48
*/


-- ----------------------------
-- Table structure for USERS
-- ----------------------------
DROP TABLE "TEST"."USERS";
CREATE TABLE "TEST"."USERS" (
  "USERNAME" VARCHAR2(32 BYTE),
  "PASSWORD" VARCHAR2(1024 BYTE),
  "ENABLED" NUMBER(1,0),
  "ROLES" VARCHAR2(255 BYTE)
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
-- Records of USERS
-- ----------------------------
INSERT INTO "TEST"."USERS" VALUES ('admin', '$2a$10$zRyHVQQ7TrmyMcuTltsGce6KMe5/MzxpVSq5BofgClWIXqzGlQyV6', '1', '[admin]');
INSERT INTO "TEST"."USERS" VALUES ('user', '$2a$10$ijYTAJZdOYtMhcNukfC5uehpUGYbx4mE3zGB6mZNRDhGK3vxW8szK', '1', '[user]');
