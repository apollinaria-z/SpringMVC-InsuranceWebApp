package by.zolotaya.app.dao;

import by.zolotaya.app.models.Client;

import java.util.List;

public interface IClientDAO {

    List<Client> getAllClients() throws DAOException;
    Client getClientById(int id) throws DAOException;
    void addClient(Client client) throws DAOException;
    void updateClient(int id, Client client) throws DAOException;
    void deleteClient(int id) throws DAOException;
}
