package main;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "records")
public class Record {

	@Id
	private String id;
	private String content;
	private String origin;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime recordTimestamp;
	private String serial;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public LocalDateTime getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(LocalDateTime recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}
