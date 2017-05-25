package com.h3c.cloudoc.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lisijie on 2017/5/19.
 */
@Entity
@Table(name = "tbl_user")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @JsonProperty(value = "uuid")
	private String uuid;

	@NotNull
    @Column(name = "name")
    @JsonProperty(value = "name")
	private String name;

	@NotNull
    @Column(name = "password")
    @JsonProperty(value = "password")
	private String password;

	@NotNull
    @Column(name = "email")
    @JsonProperty(value = "email")
	private String email;

    public UserEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity entity = (UserEntity) o;

        if (!uuid.equals(entity.uuid)) return false;
        if (!name.equals(entity.name)) return false;
        if (!password.equals(entity.password)) return false;
        return email.equals(entity.email);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
