package by.zolotaya.app;

import by.zolotaya.app.dao.impl.ClientDAO;
import by.zolotaya.app.dao.impl.PolicyDAO;
import by.zolotaya.app.models.Client;
import by.zolotaya.app.models.Coverage;
import by.zolotaya.app.models.Policy;

public class Main {
    public static void main(String[] args) {
        PolicyDAO policyDAO = new PolicyDAO();
        System.out.println(policyDAO.getPolicyById(2).getCoverage());
        ClientDAO clientDAO = new ClientDAO();
        System.out.println(clientDAO.getClientById(230).getSurname());


    }
}
