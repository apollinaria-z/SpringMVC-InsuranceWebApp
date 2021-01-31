package by.zolotaya.app.dao.impl;

import by.zolotaya.app.DbConnection;
import by.zolotaya.app.dao.DAOException;
import by.zolotaya.app.dao.IClientDAO;
import by.zolotaya.app.models.Client;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDAO implements IClientDAO {
    enum SQLClient {
        GET("SELECT cl.id, cl.name, cl.surname, cl.passportId FROM client AS cl WHERE cl.id = (?)"),
        INSERT("INSERT INTO client (id, name, surname, passportId) VALUES (DEFAULT, (?), (?), (?))"),
        UPDATE("UPDATE client SET name=(?), surname=(?), passportId=(?) WHERE id=(?)"),
        DELETE("DELETE FROM client WHERE id = (?)"),
        GETLIST("SELECT * FROM client");
        String QUERY;
        SQLClient(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    public List<Client> getAllClients() throws DAOException {
        List<Client> clientList = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLClient.GETLIST.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Client newClient = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId")
                );
                clientList.add(newClient);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientList;
    }

    public Client getClientById(int id) throws DAOException {
        Client client = new Client();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLClient.GET.QUERY)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setPassportId(rs.getString("passportId"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return client;
    }

    public void addClient(Client client) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLClient.INSERT.QUERY)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPassportId());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateClient(int id, Client client) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLClient.UPDATE.QUERY)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPassportId());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLClient.DELETE.QUERY)) {
           statement.setInt(1, id);
           statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        }

}
