package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.*;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.repository.RoleRepository;
import com.jepepper.sellingApp.service.interfaces.IClientService;
import com.jepepper.sellingApp.service.utils.Cast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public long getUserIdByUserName(String username) {
        Client client = clientRepo.findByUsername(username);
        Long userId = client.getId();
        return userId;
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
        userRole.setClient(client);

        // SETTING EMBEDDED USER_ROLE_PK
        UserRolePK userRolePK = new UserRolePK();
        userRolePK.setRoleId(role.getId());
        userRolePK.setClientId(client.getId());
        userRole.setId(userRolePK);

        // ADDING USER_ROLE TO CLIENT
        client.addToRoles(userRole);
    }
    @Override
    public Client getClient(String clientName) {
        return clientRepo.findByUsername(clientName);
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return  purchaseRepo.save(purchase);
    }
    @Override
    public List<Client> getAll(int page) {
        return Cast.castList(Client.class,clientRepo.findAll(PageRequest.of(page,20)));
    }
    @Override
    public void deleteUser(long userId) {
        clientRepo.deleteById(userId);
    }

    @Override
    public void changePasswordToClient(String clientName, String clientOldPassword, String newClienPassword) throws Exception {
        Optional<Client> clientOptional = Optional.ofNullable(clientRepo.findByUsername(clientName));
        if(clientOptional.isEmpty()){
            throw new UsernameNotFoundException("USER NOT FOUND IN DB");
        }
        Client client =  clientOptional.get();
        String password = client.getPassword();
        if(clientOldPassword != password){
            throw new Exception("That's not the proper password");
        }
        client.setPassword(newClienPassword);
        clientRepo.save(client);
    }

    @Override
    public void registryOfClient(String name, String userName, String email, String Password) {
        Client client = new Client(null,name,userName,email,Password,new ArrayList<>(),new ArrayList<>());
        addRoleToClient(client.getName(),"ROLE_USER");
        clientRepo.save(client);
    }
}
