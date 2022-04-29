package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeeRowMapper implements RowMapper<Employee> {
    private static final Logger LOGGER = Logger.getLogger(EmployeeRowMapper.class.getName());

    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee employee;
        try {
            FullName fullName = new FullName(resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("middlename"));
            Position position = Position.valueOf(resultSet.getString("position"));
            LocalDate hired = resultSet.getDate("hiredate").toLocalDate();
            BigInteger id = resultSet.getBigDecimal("id").toBigInteger();
            employee = new Employee(id, fullName, position,
                    hired, resultSet.getBigDecimal("salary"));
        } catch (SQLException sqlEx) {
            LOGGER.log(Level.SEVERE, "Exception: ", sqlEx);
            throw sqlEx;
        }
        return employee;
    }
}
