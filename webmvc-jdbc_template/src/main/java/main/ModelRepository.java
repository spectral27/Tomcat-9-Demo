package main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ModelRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private RowMapper<Model> rowMapper = (rs, rowNum) -> {
		Model model = new Model();
		model.setId(rs.getString("id"));
		model.setYear(rs.getInt("year"));
		model.setModel(rs.getString("model"));
		model.setNumber(rs.getInt("number"));
		model.setFinalPos(rs.getString("finalpos"));
		model.setEvent(rs.getString("event"));
		model.setCarClass(rs.getString("class"));
		
		String driversJson = rs.getString("drivers");
		List<String> drivers = new ArrayList<>();
		
		try {
			drivers = objectMapper.readValue(driversJson, new TypeReference<List<String>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.setDrivers(drivers);
		
		return model;
	};
	
	public List<Model> selectAllModels() {
		return jdbcTemplate.query("select * from models", rowMapper);
	}
	
	public Model selectModel(String id) {
		return jdbcTemplate.queryForObject("select * from models where id = ?", rowMapper, id);
	}
	
	public int insertModel(Model model) {
		String driversJson = "";
		
		try {
			driversJson = objectMapper.writeValueAsString(model.getDrivers());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jdbcTemplate.update("insert into models values (?, ?, ?, ?, ?, ?, ?, ?)", 
				UUID.randomUUID().toString().substring(0, 8),
				model.getYear(),
				model.getModel(),
				model.getNumber(),
				model.getFinalPos(),
				model.getEvent(),
				model.getCarClass(),
				driversJson);
	}
	
	public int updateModel(String id, Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("update models set ");
		sb.append("year = ?, ");
		sb.append("model = ?, ");
		sb.append("number = ?, ");
		sb.append("finalpos = ?, ");
		sb.append("event = ?, ");
		sb.append("class = ?, ");
		sb.append("drivers = ? ");
		sb.append("where id = ?");

		String updateQuery = sb.toString();
		
		String driversJson = "";
		
		try {
			driversJson = objectMapper.writeValueAsString(model.getDrivers());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jdbcTemplate.update(updateQuery,
				model.getYear(),
				model.getModel(),
				model.getNumber(),
				model.getFinalPos(),
				model.getEvent(),
				model.getCarClass(),
				driversJson,
				id);
	}
	
	public int deleteModel(String id) {
		return jdbcTemplate.update("delete from models where id = ?", id);
	}

}
