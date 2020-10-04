package com.busyvacation.aboard.config;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

    private String ID;
	private String PASSWORD;
    private String NAME;
    private String IMG;
	private String AUTHORITY;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean ENABLED;
	private int R_ID;
	private String ROLE;
	private String GENDER;
	private String BIRTH;
	private String NICKNAME;
	
	

	public String getNICKNAME() {
		return NICKNAME;
	}

	public void setNICKNAME(String nICKNAME) {
		NICKNAME = nICKNAME;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String role) {
		ROLE = role;
	}

	public int getR_ID() {
		return R_ID;
	}

	public void setR_ID(int r_id) {
		R_ID = r_id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	public String getUsername() {
		return ID;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ENABLED;
	}

	public String getName() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
    }
    public String getImg(){
        return IMG;
    }
    public void setIMG(String img){
        IMG = img;
    }
    public void setID(String id){
        ID = id;
    }
    public void setPASSWORD(String password){
        PASSWORD = password;
    }
    public void setAUTHORITY(String authority){
    	AUTHORITY = authority;
    }

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public String getBIRTH() {
		return BIRTH;
	}

	public void setBIRTH(String bIRTH) {
		BIRTH = bIRTH;
	}


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		System.out.println(authorities);
		this.authorities = authorities;
	}
}