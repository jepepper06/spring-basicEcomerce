package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.*;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.repository.RoleRepository;
import com.jepepper.sellingApp.service.interfaces.IClientService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Data
public class ClientService implements IClientService{
    private final ClientRepository clientRepo;
    private final RoleRepository roleRepo;
    private final PurchaseRepository purchaseRepo;
    private final PurchaseProductRepository purchaseProductRepo;

    @Override
    public Client saveUser(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToClient(String clientName, String roleName) {
        Client client = clientRepo.findByUsername(clientName);
        Role role = roleRepo.findByName(roleName);

        // SETTING USER_ROLE
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(client);

        // SETTING EMBEDDED USER_ROLE_PK
        UserRolePK userRolePK = userRole.getId();
        userRolePK.setRoleId(role.getId());
        userRolePK.setUserId(client.getId());
        userRole.setId(userRolePK);

        // ADDING USER_ROLE TO CLIENT
        client.getRoles().add(userRole);
    }

    @Override
    public Client getClient(String clientName) {
        Client client = clientRepo.findByUsername(clientName);
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = clientRepo.findAll();
        return clients;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        purchase.getProducts().stream().map(purchaseProductRepo::save);
        return  purchaseRepo.save(purchase);
    }
}
