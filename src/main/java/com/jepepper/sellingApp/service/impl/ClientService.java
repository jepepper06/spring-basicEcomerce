package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.*;
import com.jepepper.sellingApp.filter.CustomAuthorizationFilter;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.repository.RoleRepository;
import com.jepepper.sellingApp.service.interfaces.IClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Data
@Slf4j
@AllArgsConstructor
public class ClientService implements IClientService, UserDetailsService {
    private final ClientRepository clientRepo;
    private final RoleRepository roleRepo;
    private final PurchaseRepository purchaseRepo;
    private final PurchaseProductRepository purchaseProductRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepo.findByUsername(username);
        if(client == null){
            throw new UsernameNotFoundException("USER NOT FOUND IN DB");
        }else{
            log.info("USER FOUND IN DB");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        client.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName())));
        return
                new  org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                authorities);
    }

    @Override
    public Client saveUser(Client client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        return clientRepo.save(client);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void  addRoleToClient(String clientName, String roleName) {
        Client client = clientRepo.findByUsername(clientName);
        Role role = roleRepo.findByRoleName(roleName);

        // SETTING USER_ROLE
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(client);

        // SETTING EMBEDDED USER_ROLE_PK
        UserRolePK userRolePK = new UserRolePK();
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
