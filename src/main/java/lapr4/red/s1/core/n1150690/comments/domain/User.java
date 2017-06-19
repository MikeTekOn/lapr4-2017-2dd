/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the system user.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class User implements Serializable {

    /**
     * The name of the user.
     */
    private String usersName;

    /**
     * Creates a user using the current name of the logged session.
     */
    public User() {
        this.usersName = System.getProperty("user.name");
    }
    
    public User(String name){
        this.usersName = name;
    }

    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String name() {
        return this.usersName;
    }

    /**
     * EDIT:
     * @author Miguel Silva (1150901) Sprint 2 - I've added the equals method
     * necessary for my user story.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        // field comparison
        return Objects.equals(usersName, user.name());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.usersName);
        return hash;
    }
}
