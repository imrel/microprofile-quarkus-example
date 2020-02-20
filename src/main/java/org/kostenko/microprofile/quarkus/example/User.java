package org.kostenko.microprofile.quarkus.example;

/**
 *
 * @author kostenko
 */
public class User {

    private String name;
    private String jwt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    
}
