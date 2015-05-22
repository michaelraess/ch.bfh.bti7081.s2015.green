package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import javax.persistence.Entity;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User {

    private Long id;
 
    private String name;

	private String password;

	private String eMail;
    
 
    public User() {}
 
    public User(String name, String password, String eMail) {
        this.name = name;
        this.password = password;
        this.eMail = eMail;
    }
     
 
    public User(String name) {
        this.name = name;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
}
