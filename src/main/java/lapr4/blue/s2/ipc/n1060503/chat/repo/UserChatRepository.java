/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.repo;

import eapli.framework.persistence.repositories.Repository;
import lapr4.blue.s2.ipc.n1060503.chat.profile.UserChatProfile;

/**
 *
 * @author Pedro Fernandes
 */
public interface UserChatRepository extends Repository<UserChatProfile, Long>{
    
    void getUserChatProfile();
    
    boolean update(UserChatProfile ucp);
    
}
