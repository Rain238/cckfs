package com.rain.cckfs.service;

import com.rain.cckfs.mapper.CrabCastleKingFinancialSystemUserMapper;
import com.rain.cckfs.pojo.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: LightRain
 * @Description: 螃蟹城堡之王金融系统安全服务实体Impl
 * @DateTime: 2023-04-01 16:28
 * @Version：1.0
 **/
@Service
public class CrabCastleKingFinancialSystemSecurityServiceEntityImpl implements UserDetailsService {
    private final CrabCastleKingFinancialSystemUserMapper mapper;
    public CrabCastleKingFinancialSystemSecurityServiceEntityImpl(CrabCastleKingFinancialSystemUserMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserName(username);
        if (user != null && user.getUsername() != null && user.getPassword() != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        }
        //验证失败
        return new org.springframework.security.core.userdetails.User("t", "$2a$10$kiS2bbJHUiDINf466LuMae2kpoC0/iGvbmqNR7w7eiQhaEUlgw6Nq", AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
