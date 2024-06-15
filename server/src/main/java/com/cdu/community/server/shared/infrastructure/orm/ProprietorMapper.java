package com.cdu.community.server.shared.infrastructure.orm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.community.server.shared.domain.entity.Proprietor;
import org.apache.ibatis.annotations.Select;

public interface ProprietorMapper extends BaseMapper<Proprietor> {

    @Select("select * from proprietor p where p.name=#{proprietorName} ")
    Proprietor getProprietorByName(String proprietorName);
}
