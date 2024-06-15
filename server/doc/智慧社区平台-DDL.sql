DROP TABLE IF EXISTS meter_type;
CREATE TABLE meter_type(
                           `id` INT AUTO_INCREMENT COMMENT '主键' ,
                           `code` VARCHAR(255) NOT NULL  COMMENT '类别代码' ,
                           `name` VARCHAR(255) NOT NULL  COMMENT '类别名称' ,
                           `charge_project_id` BIGINT NOT NULL  COMMENT '收费项目id' ,
                           `remark` VARCHAR(255)   COMMENT '备注' ,
                           `created_time` DATETIME   COMMENT '创建时间' ,
                           `updated_time` DATETIME   COMMENT '更新时间' ,
                           PRIMARY KEY (id)
)  COMMENT = '表计类别';


DROP TABLE IF EXISTS meter;
CREATE TABLE meter(
                      `id` INT AUTO_INCREMENT COMMENT '主键' ,
                      `created_time` DATETIME   COMMENT '创建时间' ,
                      `updated_time` DATETIME   COMMENT '更新时间' ,
                      `room_id` BIGINT NOT NULL  COMMENT '房间id' ,
                      `meter_type_id` BIGINT NOT NULL  COMMENT '表计类别id' ,
                      `num` VARCHAR(255) NOT NULL  COMMENT '表号' ,
                      `read_num` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '装表读数' ,
                      `rate` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '倍率' ,
                      `range` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '量程' ,
                      `status` VARCHAR(255) NOT NULL  COMMENT '表计运行状态;1运行/0报停' ,
                      `install_time` DATETIME NOT NULL  COMMENT '安装日期' ,
                      `stop_time` DATETIME   COMMENT '报停日期' ,
                      `meter_reader` VARCHAR(255)  DEFAULT 'swh' COMMENT '抄表员' ,
                      `remark` VARCHAR(255)   COMMENT '备注' ,
                      PRIMARY KEY (id)
)  COMMENT = '表计';

DROP TABLE IF EXISTS user;
CREATE TABLE user(
                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                     `name` VARCHAR(255) NOT NULL  COMMENT '用户名' ,
                     `password` VARCHAR(255) NOT NULL  COMMENT '密码' ,
                     `updated_time` DATETIME   COMMENT '更新时间' ,
                     `created_time` DATETIME   COMMENT '创建时间' ,
                     PRIMARY KEY (id,name,password)
)  COMMENT = '用户';

DROP TABLE IF EXISTS room_statistics;
CREATE TABLE room_statistics(
                                `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                `created_time` DATETIME   COMMENT '创建时间' ,
                                `updated_time` DATETIME   COMMENT '更新时间' ,
                                `room_id` BIGINT   COMMENT '房间id' ,
                                `building_id` BIGINT   COMMENT '楼栋id' ,
                                `proprietor_id` BIGINT   COMMENT '业主id' ,
                                `proprietor_name` VARCHAR(255)   COMMENT '业主姓名' ,
                                `room_code` VARCHAR(255)   COMMENT '房间号' ,
                                PRIMARY KEY (id)
)  COMMENT = '房间统计';

DROP TABLE IF EXISTS proprietor;
CREATE TABLE proprietor(
                           `id` INT AUTO_INCREMENT COMMENT '主键' ,
                           `name` VARCHAR(255) NOT NULL  COMMENT '业主名称' ,
                           `created_time` DATETIME   COMMENT '创建时间' ,
                           `updated_time` DATETIME   COMMENT '更新时间' ,
                           `phone` VARCHAR(255) NOT NULL  COMMENT '联系手机号' ,
                           `amount` DECIMAL(24,2)  DEFAULT 0.00 COMMENT '预收金金额' ,
                           PRIMARY KEY (id)
)  COMMENT = '业主';

DROP TABLE IF EXISTS proprietor;
CREATE TABLE proprietor(
                           `id` INT AUTO_INCREMENT COMMENT '主键' ,
                           `name` VARCHAR(255) NOT NULL  COMMENT '业主名称' ,
                           `created_time` DATETIME   COMMENT '创建时间' ,
                           `updated_time` DATETIME   COMMENT '更新时间' ,
                           `phone` VARCHAR(255) NOT NULL  COMMENT '联系手机号' ,
                           PRIMARY KEY (id)
)  COMMENT = '业主';

DROP TABLE IF EXISTS meter_reading_record;
CREATE TABLE meter_reading_record(
                                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                     `created_time` DATETIME   COMMENT '创建时间' ,
                                     `updated_time` DATETIME   COMMENT '更新时间' ,
                                     `room_id` BIGINT NOT NULL  COMMENT '房间id' ,
                                     `meter_type_id` BIGINT NOT NULL  COMMENT '表计类别id' ,
                                     `num` VARCHAR(255) NOT NULL  COMMENT '表号' ,
                                     `start_charge_time` DATETIME NOT NULL  COMMENT '计费起始' ,
                                     `end_charge_time` DATETIME NOT NULL  COMMENT '计费截止' ,
                                     `start_num` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '起数' ,
                                     `end_num` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '止数' ,
                                     `rate` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '倍率' ,
                                     `amount` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '数量' ,
                                     `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '抄表状态;0-已录入 1-待收费 2-已收费' ,
                                     `record_time` DATETIME NOT NULL  COMMENT '录单时间' ,
                                     `record_type` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '抄表方式;0-手工 1-其它' ,
                                     `serial_number` VARCHAR(255)   COMMENT '抄表流水号' ,
                                     `record_user_id` BIGINT   COMMENT '抄表人id' ,
                                     `input_mode` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '录入方式;0-按止数录入 1-按用了录入' ,
                                     PRIMARY KEY (id)
)  COMMENT = '抄表记录';


DROP TABLE IF EXISTS room;
CREATE TABLE room(
                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                     `created_time` DATETIME   COMMENT '创建时间' ,
                     `updated_time` DATETIME   COMMENT '更新时间' ,
                     `code` VARCHAR(255) NOT NULL  COMMENT '房间编号' ,
                     `building_id` BIGINT NOT NULL  COMMENT '楼栋id' ,
                     `floor` INT NOT NULL  COMMENT '所属楼层' ,
                     `bind_time` DATETIME   COMMENT '绑定时间' ,
                     `first_pay_time` DATETIME   COMMENT '首次缴费时间' ,
                     `area` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '建筑面积' ,
                     `usage_area` DECIMAL(24,2)  DEFAULT 0.00 COMMENT '使用面积' ,
                     `proprietor_id` BIGINT   COMMENT '业主id' ,
                     `edifice_id` BIGINT   COMMENT '大厦/小区id' ,
                     PRIMARY KEY (id)
)  COMMENT = '房间';


DROP TABLE IF EXISTS table_change_record;
CREATE TABLE table_change_record(
                                    `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                    `created_time` DATETIME   COMMENT '创建时间' ,
                                    `updated_time` DATETIME   COMMENT '更新时间' ,
                                    `room_id` BIGINT NOT NULL  COMMENT '房间号' ,
                                    `meter_type_id` BIGINT NOT NULL  COMMENT '表计类别id' ,
                                    `old_num` VARCHAR(255) NOT NULL  COMMENT '原表号' ,
                                    `old_end_num` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '原表止数' ,
                                    `old_usage` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '原表用量' ,
                                    `new_num` VARCHAR(255) NOT NULL  COMMENT '新表号' ,
                                    `new_start_num` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '新表起数' ,
                                    `install_staff` VARCHAR(255) NOT NULL DEFAULT 'swh' COMMENT '安装人员' ,
                                    `record_user_id` BIGINT NOT NULL  COMMENT '登记人id' ,
                                    PRIMARY KEY (id)
)  COMMENT = '换表记录';

DROP TABLE IF EXISTS user;
CREATE TABLE user(
                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                     `name` VARCHAR(255) NOT NULL  COMMENT '用户名' ,
                     `password` VARCHAR(255) NOT NULL  COMMENT '密码' ,
                     `updated_time` DATETIME   COMMENT '更新时间' ,
                     `created_time` DATETIME   COMMENT '创建时间' ,
                     PRIMARY KEY (id,name,password)
)  COMMENT = '用户';

DROP TABLE IF EXISTS building;
CREATE TABLE building(
                         `id` INT AUTO_INCREMENT COMMENT '主键' ,
                         `created_time` DATETIME   COMMENT '创建时间' ,
                         `updated_time` DATETIME   COMMENT '更新时间' ,
                         `name` VARCHAR(255) NOT NULL  COMMENT '楼栋名称' ,
                         `edifice_id` BIGINT NOT NULL  COMMENT '所属大厦/小区id' ,
                         `eId` BIGINT NOT NULL  COMMENT '所属单元id' ,
                         PRIMARY KEY (id)
)  COMMENT = '楼栋';


DROP TABLE IF EXISTS proprietor;
CREATE TABLE proprietor(
                           `id` INT AUTO_INCREMENT COMMENT '主键' ,
                           `name` VARCHAR(255) NOT NULL  COMMENT '业主名称' ,
                           `created_time` DATETIME   COMMENT '创建时间' ,
                           `updated_time` DATETIME   COMMENT '更新时间' ,
                           `phone` VARCHAR(255) NOT NULL  COMMENT '联系手机号' ,
                           `amount` DECIMAL(24,2)  DEFAULT 0.00 COMMENT '预收金金额' ,
                           PRIMARY KEY (id)
)  COMMENT = '业主';

DROP TABLE IF EXISTS room;
CREATE TABLE room(
                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                     `created_time` DATETIME   COMMENT '创建时间' ,
                     `updated_time` DATETIME   COMMENT '更新时间' ,
                     `code` VARCHAR(255) NOT NULL  COMMENT '房间编号' ,
                     `building_id` BIGINT NOT NULL  COMMENT '楼栋id' ,
                     `floor` INT NOT NULL  COMMENT '所属楼层' ,
                     `bind_time` DATETIME   COMMENT '绑定时间' ,
                     `first_pay_time` DATETIME   COMMENT '首次缴费时间' ,
                     `area` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '建筑面积' ,
                     `usage_area` DECIMAL(24,2)  DEFAULT 0.00 COMMENT '使用面积' ,
                     `proprietor_id` BIGINT   COMMENT '业主id' ,
                     PRIMARY KEY (id)
)  COMMENT = '房间';


DROP TABLE IF EXISTS building;
CREATE TABLE building(
                         `id` INT AUTO_INCREMENT COMMENT '主键' ,
                         `created_time` DATETIME   COMMENT '创建时间' ,
                         `updated_time` DATETIME   COMMENT '更新时间' ,
                         `name` VARCHAR(255) NOT NULL  COMMENT '楼栋名称' ,
                         `edifice_id` BIGINT NOT NULL  COMMENT '所属大厦/小区id' ,
                         `eId` BIGINT NOT NULL  COMMENT '所属单元Id' ,
                         PRIMARY KEY (id)
)  COMMENT = '楼栋';

DROP TABLE IF EXISTS edifice;
CREATE TABLE edifice(
                        `id` INT AUTO_INCREMENT COMMENT '主键' ,
                        `created_time` DATETIME   COMMENT '创建时间' ,
                        `updated_time` DATETIME   COMMENT '更新时间' ,
                        `name` VARCHAR(255)   COMMENT '大厦/小区名称' ,
                        PRIMARY KEY (id)
)  COMMENT = '大厦/小区';

DROP TABLE IF EXISTS charge_project;
CREATE TABLE charge_project(
                               `id` INT AUTO_INCREMENT COMMENT '主键' ,
                               `created_time` DATETIME   COMMENT '创建时间' ,
                               `updated_time` DATETIME   COMMENT '更新时间' ,
                               `code` VARCHAR(255) NOT NULL  COMMENT '收费编码' ,
                               `billing_type` TINYINT(1) NOT NULL  COMMENT '收费方式;0-周期性 1-临时性' ,
                               `charge_type` TINYINT(1) NOT NULL  COMMENT '收费类型;0-普通 1-押金 2-预收金' ,
                               `name` VARCHAR(255) NOT NULL  COMMENT '收费名称' ,
                               `unit_cost` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '计算单价' ,
                               `unit_period` TINYINT(1) NOT NULL  COMMENT '周期单位;0-日 1-月' ,
                               `computing_accuracy` TINYINT(1) NOT NULL  COMMENT '计算精度;0-元(0.00) 1-角(0.10) 2-分(0.01)' ,
                               `trade_off` TINYINT(1) NOT NULL  COMMENT '取舍方式;0-四舍五入 1-直接舍弃 2-直接进位' ,
                               `formula_mode` TINYINT(1) NOT NULL  COMMENT '计算方式;0-建面(单价*建筑面积) 1-使用(单价*使用面积) 2-定额(单价) 3-公式' ,
                               `breach_amount` DECIMAL(24,2)   COMMENT '违约金额;违约金额占比' ,
                               `breach_start` INT   COMMENT '违约开始;违约金开始天数' ,
                               PRIMARY KEY (id)
)  COMMENT = '收费项目';

DROP TABLE IF EXISTS charge_room;
CREATE TABLE charge_room(
                            `id` INT AUTO_INCREMENT COMMENT '主键' ,
                            `created_time` DATETIME   COMMENT '创建时间' ,
                            `updated_time` DATETIME   COMMENT '更新时间' ,
                            `room_id` BIGINT NOT NULL  COMMENT '房间id' ,
                            `charge_project_id` BIGINT NOT NULL  COMMENT '收费项目id' ,
                            `charge_start_time` DATETIME   COMMENT '计费开始日期' ,
                            `charge_end_time` DATETIME   COMMENT '计费结束日期' ,
                            `ladder_cost` TINYINT(1)   COMMENT '阶梯单价' ,
                            `breach_rate` DECIMAL(24,2)   COMMENT '违约金率' ,
                            `breach_start` INT   COMMENT '违约开始天数' ,
                            PRIMARY KEY (id)
)  COMMENT = '房间收费';


DROP TABLE IF EXISTS building_element;
CREATE TABLE building_element(
                                 `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                 `created_time` DATETIME   COMMENT '创建时间' ,
                                 `updated_time` DATETIME   COMMENT '更新时间' ,
                                 `name` VARCHAR(255) NOT NULL  COMMENT '单元名称' ,
                                 `edifice_id` BIGINT   COMMENT '所属小区id' ,
                                 PRIMARY KEY (id)
)  COMMENT = '单元';


DROP TABLE IF EXISTS charge_manage;
CREATE TABLE charge_manage(
                              `id` INT AUTO_INCREMENT COMMENT '主键' ,
                              `created_time` DATETIME   COMMENT '创建时间' ,
                              `updated_time` DATETIME   COMMENT '更新时间' ,
                              `receive_manage_id` BIGINT   COMMENT '应收管理id' ,
                              `deferred_amonut` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '缓交金额' ,
                              `derate_amount` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '减免金额' ,
                              `breach_amount` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '违约金额' ,
                              `discount_amount` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额' ,
                              `read_income_amount` DECIMAL(24,2) NOT NULL DEFAULT 0.00 COMMENT '实收金额' ,
                              `remark` VARCHAR(255)   COMMENT '备注' ,
                              PRIMARY KEY (id)
)  COMMENT = '收费管理';

DROP TABLE IF EXISTS receive_manage;
CREATE TABLE receive_manage(
                               `id` INT AUTO_INCREMENT COMMENT '主键' ,
                               `created_time` DATETIME   COMMENT '创建时间' ,
                               `updated_time` DATETIME   COMMENT '更新时间' ,
                               `room_id` BIGINT NOT NULL  COMMENT '房间号id' ,
                               `charge_project_id` BIGINT NOT NULL  COMMENT '收费项目id' ,
                               `start_time` DATETIME NOT NULL  COMMENT '起始日期' ,
                               `end_time` DATETIME NOT NULL  COMMENT '截至日期' ,
                               `amount` DECIMAL(24,2) NOT NULL  COMMENT '数量;0.00' ,
                               `unit` DECIMAL(24,2) NOT NULL  COMMENT '单价;0.00' ,
                               `rate` DECIMAL(24,2) NOT NULL  COMMENT '倍率;0.00' ,
                               `receive_amount` DECIMAL(24,2) NOT NULL  COMMENT '应收金额;0.00' ,
                               `record_time` DATETIME NOT NULL  COMMENT '录入时间' ,
                               `record_user_id` BIGINT NOT NULL  COMMENT '录入人id' ,
                               `status` TINYINT(1) NOT NULL  COMMENT '审核状态;0-待审核 1-已审核' ,
                               `judge_user` VARCHAR(255) NOT NULL DEFAULT 'swh' COMMENT '审核人' ,
                               PRIMARY KEY (id)
)  COMMENT = '应收管理';

DROP TABLE IF EXISTS prev_charge;
CREATE TABLE prev_charge(
                            `id` INT AUTO_INCREMENT COMMENT '主键' ,
                            `created_time` DATETIME   COMMENT '创建时间' ,
                            `updated_time` DATETIME   COMMENT '更新时间' ,
                            `code` VARCHAR(255) NOT NULL  COMMENT '收费流水号' ,
                            `room_id` BIGINT NOT NULL  COMMENT '房间id' ,
                            `proprietor_id` BIGINT NOT NULL  COMMENT '业主id' ,
                            `charge_project_id` BIGINT NOT NULL  COMMENT '收费项目_id' ,
                            `read_income_amount` DECIMAL(24,2) NOT NULL  COMMENT '实收金额;0.00' ,
                            `payment_method` TINYINT(1) NOT NULL  COMMENT '收款方式;0-现金 1-银行卡 2-微信 3-支付宝' ,
                            `budget_status` TINYINT(1) NOT NULL  COMMENT '收支状态;0-收入 1-支出' ,
                            `charge_remark` VARCHAR(255)   COMMENT '收款备注' ,
                            `charge_time` DATETIME   COMMENT '收费日期' ,
                            `charge_user_id` BIGINT   COMMENT '收款人_id' ,
                            `status` TINYINT(1) NOT NULL  COMMENT '归档状态;0-未归档 1-已归档' ,
                            `payment_status` TINYINT(1) NOT NULL  COMMENT '收费状态;0-未收费 1-已收费 2-已退费' ,
                            `archive_user_id` BIGINT   COMMENT '归档人id' ,
                            `archive_time` DATETIME   COMMENT '归档时间' ,
                            PRIMARY KEY (id)
)  COMMENT = '预收金/押金管理';

DROP TABLE IF EXISTS refund_record;
CREATE TABLE refund_record(
                              `id` INT AUTO_INCREMENT COMMENT '主键' ,
                              `created_time` DATETIME   COMMENT '创建时间' ,
                              `updated_time` DATETIME   COMMENT '更新时间' ,
                              `prev_charge_id` BIGINT NOT NULL  COMMENT '预收金id' ,
                              `refund_amount` DECIMAL(24,2) NOT NULL  COMMENT '退款金额;0.00' ,
                              `refund_method` TINYINT(1) NOT NULL  COMMENT '退款方式;0-现金 1-银行卡 2-微信 3-支付宝' ,
                              `refund_reason` VARCHAR(255) NOT NULL  COMMENT '退款原因' ,
                              `refund_time` DATETIME   COMMENT '退款日期' ,
                              PRIMARY KEY (id)
)  COMMENT = '退款记录';

DROP TABLE IF EXISTS revocation_record;
CREATE TABLE revocation_record(
                                  `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                  `created_time` DATETIME   COMMENT '创建时间' ,
                                  `updated_time` DATETIME   COMMENT '更新时间' ,
                                  `prev_charge_id` VARCHAR(255)   COMMENT '预收金id' ,
                                  `revocation_amount` DECIMAL(24,2) NOT NULL  COMMENT '撤销金额;0.00' ,
                                  `revocation_reason` VARCHAR(255) NOT NULL  COMMENT '撤销原因' ,
                                  `revocation_time` DATETIME NOT NULL  COMMENT '撤销时间' ,
                                  `revocation_user_id` BIGINT NOT NULL  COMMENT '撤销人id' ,
                                  PRIMARY KEY (id)
)  COMMENT = '撤销记录';

DROP TABLE IF EXISTS mitigate_record;
CREATE TABLE mitigate_record(
                                `id` INT AUTO_INCREMENT COMMENT '主键' ,
                                `created_time` DATETIME   COMMENT '创建时间' ,
                                `updated_time` DATETIME   COMMENT '更新时间' ,
                                `receive_manage_id` BIGINT NOT NULL  COMMENT '应收管理id' ,
                                `mitigate_amount` DECIMAL(24,2) NOT NULL  COMMENT '减免金额;0.00' ,
                                `record_status` TINYINT(1) NOT NULL  COMMENT '登记状态;0-未登记 1-已登记' ,
                                `record_time` DATETIME NOT NULL  COMMENT '登记时间' ,
                                `record_user_id` BIGINT NOT NULL  COMMENT '登记人id' ,
                                PRIMARY KEY (id)
)  COMMENT = '减免登记';

