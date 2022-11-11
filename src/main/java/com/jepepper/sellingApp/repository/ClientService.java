package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.Role;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.repository.RoleRepository;
import com.jepepper.sellingApp.service.interfaces.IClientService;
import com.jepepper.sellingApp.service.interfaces.IPurchaseService;
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

    private final List<PurchaseProduct> products = new ArrayList<>();
    private final PurchaseRepository purchaseRepo;

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
        return  purchaseRepo.save(purchase);
    }
}
