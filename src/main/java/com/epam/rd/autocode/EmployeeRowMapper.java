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
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String MIDDLENAME = "middlename";
    public static final String POSITION = "position";
    public static final String HIREDATE = "hiredate";
    public static final String ID = "id";
    public static final String SALARY = "salary";
    public static final String EXCEPTION = "Exception: ";

    private static final Logger LOGGER = Logger.getLogger(EmployeeRowMapper.class.getName());

    @Override
    public Employee mapRow(ResultSet resultSet) {
        Employee employee = null;
        try {
            FullName fullName = new FullName(resultSet.getString(FIRSTNAME),
                    resultSet.getString(LASTNAME), resultSet.getString(MIDDLENAME));
            Position position = Position.valueOf(resultSet.getString(POSITION));
            LocalDate hired = resultSet.getDate(HIREDATE).toLocalDate();
            BigInteger id = resultSet.getBigDecimal(ID).toBigInteger();
            employee = new Employee(id, fullName, position,
                    hired, resultSet.getBigDecimal(SALARY));
        } catch (SQLException sqlEx) {
            LOGGER.log(Level.SEVERE, EXCEPTION, sqlEx);
        }
        return employee;
    }
}
