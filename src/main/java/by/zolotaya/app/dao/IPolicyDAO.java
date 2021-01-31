package by.zolotaya.app.dao;

import by.zolotaya.app.models.Policy;

import java.util.List;

public interface IPolicyDAO {

    List<Policy> getAllPolicies() throws DAOException;
    Policy getPolicyById(int id) throws DAOException;
    void createPolicy(Policy policy) throws DAOException;
    void updatePolicy(int id, Policy policy) throws DAOException;
    void deletePolicy(int id) throws DAOException;
}
