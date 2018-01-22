package com.aotain.baobiao.service.security;

import com.aotain.baobiao.mapper.secmodel.SysUserMapper;
import com.aotain.baobiao.model.secmodel.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CusUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+username);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}
