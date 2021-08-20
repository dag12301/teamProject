package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	int eventId;
	String regDate;
	String updateDate;
	String dueDate;
	String status;
	String body;
	String title;
	int provider_id;
}
