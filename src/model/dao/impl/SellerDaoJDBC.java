package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                            "FROM seller INNER JOIN department \n" +
                            "ON seller.DepartmentId = department.Id \n" +
                            "WHERE seller.Id = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt("DepartmentId"));
                department.setName(resultSet.getString("DepName"));

                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id"));
                seller.setName(resultSet.getString("Name"));
                seller.setEmail(resultSet.getString("Email"));
                seller.setBirthDate(resultSet.getDate("BirthDate"));
                seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
                seller.setDepartment(department);

                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
