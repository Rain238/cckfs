package com.rain.cckfs.mapper;

import com.rain.cckfs.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: LightRain
 * @Description: UserMapper接口
 * @DateTime: 2023-04-01 16:36
 * @Version：1.0
 **/
@Mapper
public interface CrabCastleKingFinancialSystemUserMapper {
    @Select("select id,username,password from users where username=#{username}")
    User getUserName(String username);
}
