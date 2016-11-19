package com.invoicing.dao.impl;


import com.invoicing.dao.InvoiceDao;
import com.invoicing.model.InvoiceType;
import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Invoice> getMonthlyHousing(long customerId, int month) {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("type", InvoiceType.HOUSING.getValue());
        //params.put("monthStart", getSelectedMonthStart(month));
        //params.put("monthEnd", getSelectedMonthEnd(month));
        params.put("month", month);

        //String sql = "SELECT * FROM invoice WHERE customer_Id=:customer_id AND type=:type AND invoice_date >= :monthStart AND invoice_date <= :monthEnd";

        String sql = "SELECT * FROM invoice WHERE customer_Id=:customer_id AND type=:type AND EXTRACT(MONTH FROM invoice_date) = :month";
        List<Invoice> result = namedParameterJdbcTemplate.query(sql, params, new InvoiceMapper());
        return result;
    }

    @Override
    public List<Invoice> getMonthlyShopping(long customerId, int month) {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("type", InvoiceType.SHOPPING.getValue());
        params.put("month", month);
        //params.put("monthStart", getSelectedMonthStart(month));
        //params.put("monthEnd", getSelectedMonthEnd(month));
        //String sql = "SELECT * FROM invoice WHERE customer_id=:customer_id AND type=:type  AND invoice_date >= :monthStart AND invoice_date <= :monthEnd";
        String sql = "SELECT * FROM invoice WHERE customer_id=:customer_id AND type=:type AND EXTRACT(MONTH FROM invoice_date) = :month";
        List<Invoice> result = namedParameterJdbcTemplate.query(sql, params, new InvoiceMapper());
        return result;
    }

    @Override
    public List<Invoice> getAll(long customerId, long addressId) {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("address_id", addressId);
        String sql = "SELECT * FROM invoice WHERE customer_id=:customer_id and address_id=:address_id";
        List<Invoice> result = namedParameterJdbcTemplate.query(sql, params, new InvoiceMapper());
        return result;
    }

    @Override
    public List<Invoice> getAll(long customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        String sql = "SELECT * FROM invoice WHERE customer_id=:customer_id";
        List<Invoice> result = namedParameterJdbcTemplate.query(sql, params, new InvoiceMapper());
        return result;
    }

    @Override
    @Transactional
    public void create(InvoiceDto dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", dto.getCustomerId());
        params.put("address_id", dto.getAddressId());
        params.put("total_amount", dto.getTotalAmount());
        params.put("vat_amount", dto.getVatAmount());
        params.put("amount", dto.getAmount());
        params.put("period_description", dto.getPeriodDescription());
        params.put("end_date", dto.getEndDate());
        params.put("invoice_date", dto.getInvoiceDate());
        params.put("type", dto.getInvoiceType().getValue());
        params.put("payment_due_date", dto.getPaymentDueDate());
        params.put("start_date", dto.getStartDate());

        String sql = "INSERT INTO invoice (customer_id, address_id, total_amount, vat_amount, amount, period_description, type, invoice_date) " +
                "VALUES(:customer_id, :address_id, :total_amount, :vat_amount, :amount, :period_description, :type, :invoice_date)";
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void generateInvoice(long customerId) {
        Map<String, Object> params = new HashMap<>();

        GregorianCalendar timeNow = new GregorianCalendar();

        params.put("customer_id", customerId);
        params.put("invoice_date", timeNow.getTime());
        params.put("payment_due_date", getPaymentDueDate());
        params.put("month_start", getMonthStart());
        params.put("month_end", getMonthEnd());


        String sql = "INSERT  into invoice (customer_id, address_id, vat_amount, amount, total_amount, type, invoice_date, payment_due_date, start_date, end_date)" +
                "select customer_id, address_id, sum(vat_amount), sum(amount), sum(total_amount), service_type, :invoice_date, :payment_due_date, :month_start, :month_end from services " +
                "GROUP BY address_id, customer_id, service_type HAVING customer_id = :customer_id";
        namedParameterJdbcTemplate.update(sql, params);

        namedParameterJdbcTemplate.update("DELETE FROM SERVICES WHERE customer_id = :customer_id", params);
    }

    private Date getMonthStart() {
        Calendar monthStartDate = Calendar.getInstance();
        monthStartDate.set(monthStartDate.get(Calendar.YEAR), monthStartDate.get(Calendar.MONTH), monthStartDate.getActualMinimum(Calendar.DAY_OF_MONTH));

        return monthStartDate.getTime();
    }

    private Date getMonthEnd() {
        Calendar monthStartDate = Calendar.getInstance();
        monthStartDate.set(monthStartDate.get(Calendar.YEAR), monthStartDate.get(Calendar.MONTH), monthStartDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        return monthStartDate.getTime();
    }

    private Date getPaymentDueDate() {
        Calendar paymentDueDate = Calendar.getInstance();
        paymentDueDate.add(Calendar.DAY_OF_YEAR, 7);
        return paymentDueDate.getTime();
    }

    private static final class InvoiceMapper implements RowMapper<Invoice> {

        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Invoice invoice = new Invoice();
            invoice.setCustomerId(rs.getLong("customer_id"));
            invoice.setAddressId(rs.getLong("address_id"));
            invoice.setInvoiceType(InvoiceType.fromString(rs.getString("type")));
            invoice.setInvoiceDate(rs.getDate("invoice_date"));
            invoice.setPaymentDueDate(rs.getDate("payment_due_date"));
            invoice.setInvoiceNumber(rs.getLong("number"));
            invoice.setStartDate(rs.getDate("start_date"));
            invoice.setEndDate(rs.getDate("end_date"));
            invoice.setPeriodDescription(rs.getString("period_description"));
            invoice.setAmount(rs.getBigDecimal("amount"));
            invoice.setVatAmount(rs.getBigDecimal("vat_amount"));
            invoice.setTotalAmount(rs.getBigDecimal("total_amount"));

            return invoice;
        }
    }
}
