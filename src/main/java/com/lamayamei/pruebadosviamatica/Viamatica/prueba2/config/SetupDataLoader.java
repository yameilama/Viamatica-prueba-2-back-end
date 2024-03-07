package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.config;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.*;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.DataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.ZoneId;


@Component
public class SetupDataLoader implements CommandLineRunner {
    private final DataLoaderService dataLoaderService;

    @Autowired
    public SetupDataLoader(DataLoaderService dataLoaderService){
        this.dataLoaderService = dataLoaderService;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate startDateLocal = LocalDate.parse("2024-04-01", formatter);
    LocalDate endDateLocal = LocalDate.parse("2027-04-01", formatter);
    Date startDate = Date.from(startDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Override
    public void run(String... args) throws Exception {

        AttentionType contratarServicio = dataLoaderService.createAttentionTypeIfNotFound(
                "TICKET01",
                "Contratar servicio de internet"
        );
        AttentionType pagos = dataLoaderService.createAttentionTypeIfNotFound(
                "TICKET02",
                "Pagos"
        );
        AttentionType cambioDeServicioContratado = dataLoaderService.createAttentionTypeIfNotFound(
                "TICKET03",
                "Cambio de Servicio Contratado"
        );
        AttentionType cambioDeFormaDePago = dataLoaderService.createAttentionTypeIfNotFound(
                "TICKET04",
                "Cambio de Forma de Pago"
        );
        AttentionType cancelacionContrato = dataLoaderService.createAttentionTypeIfNotFound(
                "TICKET05",
                "Cancelación de Contrato de Servicio"
        );


        AttentionStatus generadoStatus = dataLoaderService.createAttentionStatusIfNotFound("A01", "Generado");
        AttentionStatus atendidoStatus = dataLoaderService.createAttentionStatusIfNotFound("A02", "Atendido");
        AttentionStatus canceladoStatus = dataLoaderService.createAttentionStatusIfNotFound("A03","Cancelado");
        AttentionStatus otroStatus = dataLoaderService.createAttentionStatusIfNotFound("A04","Otro");

        Rol rolAdministrador = dataLoaderService.createRolIfNotFound("ADMINISTRADOR");
        Rol rolGestor = dataLoaderService.createRolIfNotFound("GESTOR");
        Rol rolCajero = dataLoaderService.createRolIfNotFound("CAJERO");
        Rol rolCliente = dataLoaderService.createRolIfNotFound("CLIENTE");
        User adminUser = dataLoaderService.createUserIfNotFound(
                "admin",
                "admin@gmail.com",
                "admin",
                rolAdministrador
        );
        User gestorUser = dataLoaderService.createUserIfNotFound(
                "yameilama",
                "yamei@hotmail.com",
                "yameilama",
                rolGestor
        );

        PaymentMethod paymentMethod = dataLoaderService.createPaymentMethodIfNotFound(
          "VISA"
        );

        StatusContract statusContractVigente = dataLoaderService.createStatusContractIfNotFound(
                "VIGENTE"
        );

        StatusContract statusContractSustituido = dataLoaderService.createStatusContractIfNotFound(
                "SUSTITUIDO"
        );

        StatusContract statusContractCancelado = dataLoaderService.createStatusContractIfNotFound(
                "CANCELADO"
        );

        Product product = dataLoaderService.createProductIfNotFound(
          "SERVICIO 1",
                "Servicio para una persona",
                "20.00",
                "50mbps"
        );

        Client client = dataLoaderService.createClientIfNotFound(
          "Yamei",
          "Lama",
          "0923008569",
          "yamei@gmail.com",
          "Contrasena123",
          "0987509690",
          "norte",
          "norte norte",
                rolCliente,
                "yamei"
        );

        Contract contract = dataLoaderService.createContractIfNotFound(
                "0923008569",
                startDate,
                endDate,
                product,
                statusContractVigente,
                paymentMethod

        );
    }
}
