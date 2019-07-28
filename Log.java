package com.pelatro.training.project.dto;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
public class Log {
	
	String word;
	@Id
	Timestamp time;
	String status;
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
}
