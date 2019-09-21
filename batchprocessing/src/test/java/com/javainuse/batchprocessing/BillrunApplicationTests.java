package com.javainuse.batchprocessing;

import com.javainuse.batchprocessing.model.Bill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class BillrunApplicationTests {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup()  {
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Test
    public void testJobResults() {
        testResult();
    }

    private void testResult() {
        List<Bill> billStatements = this.jdbcTemplate.query("select id, " +
                        "first_name, last_name, minutes, data_usage, bill_amount " +
                        "FROM bill_statements ORDER BY id",
                (rs, rowNum) -> new Bill(rs.getLong("id"),
                        rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
                        rs.getLong("DATA_USAGE"), rs.getLong("MINUTES"),
                        rs.getDouble("bill_amount")));
        Assert.assertEquals(5, billStatements.size());

        Bill billStatement = billStatements.get(0);
        Assert.assertEquals(6, billStatement.getBillAmount(), 1e-15);
        Assert.assertEquals("jane", billStatement.getFirstName());
        Assert.assertEquals("doe", billStatement.getLastName());
        Assert.assertEquals(new Long(1), billStatement.getId());
        Assert.assertEquals(new Long(500), billStatement.getMinutes());
        Assert.assertEquals(new Long(1000), billStatement.getDataUsage());
    }
}