package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.*;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DataLoaderService {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final StatusContractRepository statusContractRepository;
    private final AttentionTypeRepository attentionTypeRepository;
    private final AttentionStatusRepository attentionStatusRepository;


    @Autowired
    public DataLoaderService(RolRepository rolRepository, UserRepository userRepository,
                             ProductRepository productRepository,
                             ContractRepository contractRepository,
                             ClientRepository clientRepository,
                             StatusContractRepository statusContractRepository,
                             PaymentMethodRepository paymentMethodRepository,
                             PasswordEncoder passwordEncoder, AttentionTypeRepository attentionTypeRepository,
                             AttentionStatusRepository attentionStatusRepository

    ){
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
        this.statusContractRepository = statusContractRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.attentionTypeRepository = attentionTypeRepository;
        this.attentionStatusRepository = attentionStatusRepository;
    }


    @Transactional
    public Rol createRolIfNotFound(String roleName){
        return rolRepository.findByRolName(roleName).orElseGet(() -> {
            Rol newRol = new Rol();
            newRol.setRolName(roleName);
            return rolRepository.save(newRol);
        });
    }

    @Transactional
    public AttentionStatus createAttentionStatusIfNotFound(String code, String description) {
        return attentionStatusRepository.findByCode(code)
                .orElseGet(() -> {
                    AttentionStatus newStatus = new AttentionStatus();
                    newStatus.setDescription(description);
                    newStatus.setCode(code);
                    return attentionStatusRepository.save(newStatus);
                });
    }


    @Transactional
    public AttentionType createAttentionTypeIfNotFound(String code, String description){
        return attentionTypeRepository.findByCode(code).orElseGet(() -> {
           AttentionType newAttentionType = new AttentionType();
            newAttentionType.setCode(code);
            newAttentionType.setDescription(description);
           return attentionTypeRepository.save(newAttentionType);
        });
    }

    @Transactional
    public User createUserIfNotFound(String username, String email, String password, Rol role){
        return userRepository.findByUsername(username).orElseGet(() -> {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRol(role);
            return userRepository.save(newUser);
        });
    }

    @Transactional
    public Client createClientIfNotFound(String name, String lastName, String identification,
                                         String email, String password, String phoneNumber, String address, String addressReference, Rol rol, String username){
        return clientRepository.findByIdentification(identification).orElseGet(() -> {
           Client newClient = new Client();
           newClient.setName(name);
           newClient.setLastName(lastName);
           newClient.setEmail(email);
           newClient.setIdentification(identification);
           newClient.setPassword(passwordEncoder.encode(password));
           newClient.setAddressReference(addressReference);
           newClient.setPhoneNumber(phoneNumber);
           newClient.setAddress(address);
           newClient.setRol(rol);
           newClient.setUsername(username);
           return clientRepository.save(newClient);
        });
    }

    @Transactional
    public Product createProductIfNotFound(String productName, String productDescription, String price, String speed){
        return productRepository.findByProductName(productName).orElseGet(() -> {
           Product newProduct = new Product();
           newProduct.setProductName(productName);
           newProduct.setProductDescription(productDescription);
           newProduct.setPrice(price);
           newProduct.setSpeed(speed);
           return productRepository.save(newProduct);
        });
    }

    @Transactional
    public PaymentMethod createPaymentMethodIfNotFound(String description){
        return paymentMethodRepository.findByDescription(description).orElseGet(() -> {
           PaymentMethod newPaymentMethod = new PaymentMethod();
           newPaymentMethod.setDescription(description);
           return paymentMethodRepository.save(newPaymentMethod);
        });
    }

    @Transactional
    public StatusContract createStatusContractIfNotFound(String description){
        return statusContractRepository.findByDescription(description).orElseGet(() -> {
           StatusContract newStatusContract = new StatusContract();
           newStatusContract.setDescription(description);
           return statusContractRepository.save(newStatusContract);
        });
    }

    @Transactional
    public Contract createContractIfNotFound(String clientIdentification, Date startDate, Date endDate, Product product, StatusContract statusContract, PaymentMethod paymentMethod) {
        Client client = clientRepository.findByIdentification(clientIdentification)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con identificacion: " + clientIdentification));

        List<Contract> existingContracts = contractRepository.findByClient(client);

        if (existingContracts.isEmpty()) {
            Contract newContract = new Contract();
            newContract.setStartDate(startDate);
            newContract.setEndDate(endDate);
            newContract.setProduct(product);
            newContract.setStatusContract(statusContract);
            newContract.setClient(client);
            newContract.setPaymentMethod(paymentMethod);
            return contractRepository.save(newContract);
        } else {
            return existingContracts.get(0);
        }
    }

}
