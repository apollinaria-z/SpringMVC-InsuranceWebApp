package by.zolotaya.app.dao.impl;

import by.zolotaya.app.DbConnection;
import by.zolotaya.app.dao.DAOException;
import by.zolotaya.app.dao.IPolicyDAO;
import by.zolotaya.app.models.Client;
import by.zolotaya.app.models.Coverage;
import by.zolotaya.app.models.Policy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PolicyDAO implements IPolicyDAO {

    enum SQLPolicy {
        GET("SELECT client.id, client.name, client.surname, client.passportId, " +
                    "policy.policyid, policy.clientid, policy.property, policy.coverage, policy.price " +
                    "FROM client JOIN policy ON client.id = policy.clientid WHERE policy.policyid = (?)"),
        INSERT("INSERT INTO policy (policyid, clientid, property, coverage, price) VALUES (DEFAULT, (?), (?), (?), (?))"),
        UPDATE("UPDATE policy SET property=(?), coverage=(?), price=(?) WHERE policyid=(?)"),
        DELETE("DELETE FROM policy WHERE policyid = (?)"),
        GETLIST("SELECT client.id, client.name, client.surname, client.passportId, " +
                        "policy.policyid, policy.clientid, policy.property, policy.coverage, policy.price " +
                        "FROM client JOIN policy ON client.id = policy.clientid"),
        UPDATEPRICE("UPDATE policy SET price = (?) WHERE policyid = (?)");
        String QUERY;
        SQLPolicy(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public List<Policy> getAllPolicies() throws DAOException {
       List<Policy> policies = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(PolicyDAO.SQLPolicy.GETLIST.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Client newClient = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId")
                );
                Policy newPolicy = new Policy(
                        rs.getInt("policyid"),
                        newClient,
                        rs.getString("property"),
                        Coverage.valueOf(rs.getString("coverage")),
                        rs.getInt("price")
                );
                policies.add(newPolicy);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public Policy getPolicyById(int id) throws DAOException {
        Policy policy = new Policy();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(PolicyDAO.SQLPolicy.GET.QUERY)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Client newClient = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId")
                );
                policy.setId(rs.getInt("policyid"));
                policy.setClient(newClient);
                policy.setCoverage(Coverage.valueOf(rs.getString("coverage")));
                policy.setProperty(rs.getString("property"));
                policy.setPrice(rs.getInt("price"));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return policy;
    }

    @Override
    public void createPolicy(Policy policy) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(PolicyDAO.SQLPolicy.INSERT.QUERY)) {
            statement.setInt(1, policy.getClient().getId());
            statement.setString(2, policy.getProperty());
            statement.setString(3, policy.getCoverage().toString());
            statement.setInt(4, policy.getPrice());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePolicy(int id, Policy policy) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(PolicyDAO.SQLPolicy.UPDATE.QUERY)) {
            statement.setString(1, policy.getProperty());
            statement.setString(2, policy.getCoverage().toString());
            statement.setInt(3, policy.getPrice());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletePolicy(int id) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(PolicyDAO.SQLPolicy.DELETE.QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
