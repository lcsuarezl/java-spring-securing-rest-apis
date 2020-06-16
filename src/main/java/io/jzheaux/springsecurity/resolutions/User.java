package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


@Entity(name="users")
public class User implements Serializable {

    @Id
    private UUID id;

    @Column(name="username", unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private boolean enabled=true;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<UserAuthority> userAuthorities = new ArrayList<>();

    public User() {
    }

    public User(UUID uuid, String userName, String password) {
        this.id=uuid;
        this.userName = userName;
        this.password = password;
    }

    public User(User user){
        this.id= user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.userAuthorities = user.getUserAuthorities();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Collection<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public void grantAuthority(String authority){
        UserAuthority userAuthority = new UserAuthority(authority, this);
        this.userAuthorities.add(userAuthority);
    }
}
