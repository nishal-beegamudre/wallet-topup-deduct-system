package com.payment.system.topup.Entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="user")
public class User implements Serializable,UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	@JsonProperty("id")
	private Long id;
	
	@Column(name="name")
	@JsonProperty("name")
	private String name;
	
	@Column(name="balance")
	@JsonProperty("balance")
	private float balance;
	
    @Column(name="email", unique = true, length = 100, nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name="password",nullable = false)
    @JsonProperty("password")
    private String password;
	
	
	@Column(name="created_date")
	@JsonProperty("createdDate")
	@TimeZoneStorage(TimeZoneStorageType.NATIVE)
	private ZonedDateTime createdDate;
	
	
	@Column(name="last_modified_date")
	@JsonProperty("lastModifiedDate")
	@TimeZoneStorage(TimeZoneStorageType.NATIVE)
	private ZonedDateTime lastModifiedDate;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}


	public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

}
