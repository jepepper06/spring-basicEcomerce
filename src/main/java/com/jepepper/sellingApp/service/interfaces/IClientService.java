package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.Role;

import java.util.List;

public interface IClientService {
    long getUserIdByUserName(String username);
    Client saveUser(Client client);
    Role saveRole(Role role);
    void addRoleToClient(String clientName, String roleName);
    Client getClient(String clientName);

  Purchase savePurchase(Purchase purchase);
    List<Client> getAll(int page);
    void deleteUser(long userId);

    void changePasswordToClient(String clientName, String clientOldPassword, String newClienPassword) throws Exception;

    void registryOfClient(String name, String userName,String email,String Password);
}
