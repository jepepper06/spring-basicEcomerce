package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.Role;

import java.util.List;

public interface IClientService {
    Client saveUser(Client client);
    Role saveRole(Role role);
    void addRoleToClient(String clientName, String roleName);
    Client getClient(String clientName);
    List<Client> getClients();
    Purchase savePurchase(Purchase purchase);

}
