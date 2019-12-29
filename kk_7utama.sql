/*
 Navicat Premium Data Transfer

 Source Server         : MYDB
 Source Server Type    : MySQL
 Source Server Version : 100121
 Source Host           : localhost:3306
 Source Schema         : kk_7utama

 Target Server Type    : MySQL
 Target Server Version : 100121
 File Encoding         : 65001

 Date: 10/07/2019 00:35:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dt_anggota
-- ----------------------------
DROP TABLE IF EXISTS `dt_anggota`;
CREATE TABLE `dt_anggota`  (
  `id` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tempatlahir` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tgllahir` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `alamat` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `notlp` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `divisi` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `simpananpokok` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tglmasuk` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_anggota
-- ----------------------------
INSERT INTO `dt_anggota` VALUES ('ID-0001', 'Bams', 'jakarta', '04 Juni 2019', 'Bogor', '0909090', 'PURCHASING', '150000', '04 Juli 2019');
INSERT INTO `dt_anggota` VALUES ('ID-0003', 'Pia', 'Pekalongan', '02 Juli 1999', 'Jogja', '0846365322', 'PELAPORAN', '100000', '07 Juli 2019');

-- ----------------------------
-- Table structure for dt_angsuran
-- ----------------------------
DROP TABLE IF EXISTS `dt_angsuran`;
CREATE TABLE `dt_angsuran`  (
  `idangsuran` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tgl` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `idpinjaman` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tglpinjaman` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `totalpinjaman` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `lamapinjaman` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `idanggota` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `angsuranke` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `angsuran` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jmlangsuran` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `totalangsuran` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sisaangsuran` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idangsuran`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_angsuran
-- ----------------------------
INSERT INTO `dt_angsuran` VALUES ('ID-0001', '06 Juli 2019', 'ID-0001', '06 Juli 2019', '2100000', '12', 'ID-0001', 'Bams', '1', '175000', '175000', '1925000', '11');
INSERT INTO `dt_angsuran` VALUES ('ID-0002', '06 Juli 2019', 'ID-0001', '06 Juli 2019', '2100000', '12', 'ID-0001', 'Bams', '2', '175000', '350000', '1750000', '10');
INSERT INTO `dt_angsuran` VALUES ('ID-0003', '09 Juli 2019', 'ID-0001', '06 Juli 2019', '2100000', '12', 'ID-0001', 'Bams', '3', '175000', '525000', '1575000', '9');

-- ----------------------------
-- Table structure for dt_divisi
-- ----------------------------
DROP TABLE IF EXISTS `dt_divisi`;
CREATE TABLE `dt_divisi`  (
  `kode` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`kode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_divisi
-- ----------------------------
INSERT INTO `dt_divisi` VALUES ('KODE-0001', 'PURCHASING');
INSERT INTO `dt_divisi` VALUES ('KODE-0002', 'PELAPORAN');

-- ----------------------------
-- Table structure for dt_pinjaman
-- ----------------------------
DROP TABLE IF EXISTS `dt_pinjaman`;
CREATE TABLE `dt_pinjaman`  (
  `idpinjaman` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tgl` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `idanggota` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jmlpinjaman` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `lamapinjaman` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `bunga` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `totalpinjaman` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `angsuran` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idpinjaman`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_pinjaman
-- ----------------------------
INSERT INTO `dt_pinjaman` VALUES ('ID-0001', '06 Juli 2019', 'ID-0001', 'Bams', '2000000', '12', '5', '2100000', '175000');

-- ----------------------------
-- Table structure for dt_simpanan
-- ----------------------------
DROP TABLE IF EXISTS `dt_simpanan`;
CREATE TABLE `dt_simpanan`  (
  `idsimpanan` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tgl` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `idanggota` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `simpananwajib` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `simpanansukarela` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idsimpanan`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_simpanan
-- ----------------------------
INSERT INTO `dt_simpanan` VALUES ('ID-0001', '05 Juli 2019', 'ID-0001', 'Bams', '50000', '1000000');
INSERT INTO `dt_simpanan` VALUES ('ID-0002', '05 Juli 2019', 'ID-0001', 'Bams', '50000', '200000');
INSERT INTO `dt_simpanan` VALUES ('ID-0003', '05 Juli 2019', 'ID-0002', 'heydi', '50000', '250000');

-- ----------------------------
-- Table structure for dt_user
-- ----------------------------
DROP TABLE IF EXISTS `dt_user`;
CREATE TABLE `dt_user`  (
  `username` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pass` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_user
-- ----------------------------
INSERT INTO `dt_user` VALUES ('admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
