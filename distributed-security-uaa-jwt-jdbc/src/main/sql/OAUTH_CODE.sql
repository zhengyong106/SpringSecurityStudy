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

 Date: 08/06/2020 16:25:42
*/


-- ----------------------------
-- Table structure for OAUTH_CODE
-- ----------------------------
DROP TABLE "TEST"."OAUTH_CODE";
CREATE TABLE "TEST"."OAUTH_CODE" (
  "CODE" VARCHAR2(255 BYTE),
  "AUTHENTICATION" LONG
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
-- Records of OAUTH_CODE
-- ----------------------------
