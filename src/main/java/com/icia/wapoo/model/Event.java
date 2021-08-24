package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	private int eventId;
	private String regDate;
	private String updateDate;
	private String dueDate;
	private String status;
	private String body;
	private String title;
	private int provider_id;
}
