package com.cdu.community.server.shared.infrastructure.orm;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/2 下午5:20
 */
@Component
@Slf4j
public class MyBatisPlusFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入时自动填充....");
        // 插入时自动填充
        strictFillStrategy(metaObject, "createdTime", LocalDateTime::now);
        strictFillStrategy(metaObject, "updatedTime", LocalDateTime::now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时自动填充....");
        // 更新时自动填充
        strictFillStrategy(metaObject, "updatedTime", LocalDateTime::now);
    }
}
