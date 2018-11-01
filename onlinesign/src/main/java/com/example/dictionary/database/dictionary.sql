
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字典编号',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上一级id',
  `key` int(11) NOT NULL DEFAULT 0 COMMENT '字典项key，默认为0，表示最顶层的类别',
  `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典项value',
  `sort` smallint(6) NOT NULL DEFAULT 0 COMMENT '排序',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `readonly` tinyint(1) NOT NULL DEFAULT 0 COMMENT '只读(从基础数据过来的不可修改)',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '字典状态 ，0=可见可用 ，1=不可见 被禁用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '修改人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------------------
-- 数据示例
-- 1	0	0	消息状态	0		0	0	2018-11-01 09:18:53		2018-11-01 09:18:57
-- 2	1	0	未读	    1		0	0	2018-11-01 09:19:35		2018-11-01 09:19:38
-- 3	1	1	已读	    2		0	0	2018-11-01 09:20:25		2018-11-01 09:20:29
-- ----------------------------------------
