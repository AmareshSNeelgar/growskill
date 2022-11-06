package com.platform.growskill.dao;

import com.platform.growskill.entity.PaymentHistory;
import com.platform.growskill.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.awt.desktop.QuitResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class PaymentHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PaymentHistory> getLatestPaymentByStudentId(Long studentId,Long courseId){

        String qry = "select ph from PaymentHistory ph where ph.studentId = :studentId and ph.courseId = :courseId order by ph.createdDate desc offset 0 limit 1";
        MapSqlParameterSource qryParam =   new MapSqlParameterSource();
        qryParam.addValue("studentId",studentId);
        qryParam.addValue("courseId",courseId);

        RowMapper<PaymentHistory> rowMapper = new RowMapper<PaymentHistory>() {
            @Override
            public PaymentHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

                PaymentHistory rowData = new PaymentHistory();

                rowData.paymentId = rs.getLong("payment_id");
                rowData.courseId  = rs.getLong("course_id");
                rowData.studentId  = rs.getLong("student_id");
                rowData.status = PaymentStatus.valueOf(rs.getString("status"));
                rowData.amount = rs.getDouble("amount");
                rowData.referenceId = rs.getString("referenceId");
                rowData.createdDate = ZonedDateTime.of(rs.getTimestamp("created_date").toLocalDateTime(), ZoneId.of("UTC"));
                rowData.updatedDate = ZonedDateTime.of(rs.getTimestamp("updated_date").toLocalDateTime(), ZoneId.of("UTC"));
                return   rowData;
            }
        };

        return jdbcTemplate.query(qry, rowMapper);
    }
}
