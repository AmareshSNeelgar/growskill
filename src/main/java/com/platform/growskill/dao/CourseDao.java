package com.platform.growskill.dao;


import com.platform.growskill.dto.CourseFilterDto;
import com.platform.growskill.entity.Course;
import com.platform.growskill.enums.CourseStatus;
import com.platform.growskill.enums.CourseType;
import com.platform.growskill.util.ZonedDateTimeAttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static final  RowMapper<Course> rowMapper = new RowMapper<Course>() {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

            Course rowData = new Course();

            rowData.id = rs.getLong("course_id");
            rowData.name  = rs.getString("name");
            rowData.type = CourseType.valueOf(rs.getString("type"));
            rowData.isDownload = rs.getBoolean("isDownload");
            rowData.status = CourseStatus.valueOf(rs.getString("status"));
            rowData.startDate = ZonedDateTime.of(rs.getTimestamp("start_date").toLocalDateTime(), ZoneId.of("UTC"));
            rowData.endDate = ZonedDateTime.of(rs.getTimestamp("end_date").toLocalDateTime(), ZoneId.of("UTC"));
            rowData.fees = rs.getDouble("fee");
            rowData.createdDate = ZonedDateTime.of(rs.getTimestamp("created_date").toLocalDateTime(), ZoneId.of("UTC"));
            rowData.updatedDate = ZonedDateTime.of(rs.getTimestamp("updated_date").toLocalDateTime(), ZoneId.of("UTC"));
            return   rowData;
        }
    };

    public List<Course> getCourse(CourseFilterDto body){
          String qry  = "select * from   course c ";
        MapSqlParameterSource qryParam =   new MapSqlParameterSource();
          String whereQry  = null;
          if(body.status != null) {
              String param = " c.status = :status";
              qryParam.addValue("status", body.status);
              if (whereQry != null) whereQry.concat("and").concat(param);
              else whereQry.concat(param);
          }

          if(body.type != null) {
              String param = " c.type = :type";
              qryParam.addValue("status", body.status);
              if (whereQry != null) whereQry.concat("and").concat(param);
              else whereQry.concat(param);
          }

        String nQry ;
        if(whereQry != null){
            nQry =   qry.concat("Where").concat(whereQry);
        }else {
            nQry = qry;
        }

         return jdbcTemplate.query(nQry, rowMapper);

    }

}
