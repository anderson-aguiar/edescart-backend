package com.anderson.edescart.projection;

public interface UserDetailsProjection {

	String getUsername();

	String getPassword();

	Long getRoleId();

	String getAuthority();
}
