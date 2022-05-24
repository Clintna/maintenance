//package com.bhhyy.aircraft.maintenance.staff.service.impl;
//
//import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
//import com.bhhyy.aircraft.maintenance.staff.entity.AccountUser;
//import com.bhhyy.aircraft.maintenance.staff.service.StUserInfoService;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//
//	@Resource
//	private StUserInfoService stUserInfoService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		StUserInfo stUserInfo = stUserInfoService.getByUserName(username);
//		if (stUserInfo == null) {
//			throw new UsernameNotFoundException("用户名或密码不正确");
//		}
//		return new AccountUser(stUserInfo.getUserId(), stUserInfo.getUserName(), stUserInfo.getPassword(), getUserAuthority(stUserInfo.getUserId()));
//	}
//
////	/**
////	 * 获取用户权限信息（角色、菜单权限）
////	 * @param userId
////	 * @return
////	 */
////	public List<GrantedAuthority> getUserAuthority(String userId){
////
////		StUserInfo stUserInfo = stUserInfoService.getByUserId(userId);
////
////		// 角色(ROLE_admin)、菜单操作权限 sys:user:list
////		String authority = sysUserService.getUserAuthorityInfo(userId);  // ROLE_admin,ROLE_normal,sys:user:list,....
////
////		return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
////	}
//}
