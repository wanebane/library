package com.rivaldy.shki.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.Document;

import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.dto.StatusDto;
import com.rivaldy.shki.shared.ApplicationFactory;

public class StatusBO {
	
	private static StatusBO instance = null;
	
	public static synchronized StatusBO getInstance() {
		if(instance==null) instance = new StatusBO();
		return instance;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public boolean checkRequest(Document request) {
		boolean result = false;
		String[] arrKey = {"code", "description", "name", "type"};
		List<String> listArr = Arrays.asList(arrKey);
		Set<String> setReq = request.keySet();
		List<String> listSet = new ArrayList<String>();
		listSet.addAll(setReq);
		Collections.sort(listArr);
		Collections.sort(listSet);
		result = listArr.equals(listSet);
		return result;
	}
	
	public Status addStatus(Document request) {
		Status status = new Status();
		@SuppressWarnings("unchecked")
		List<Status> list = (List<Status>) request.get("list");
		String uniq = ApplicationFactory.unique();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(uniq)) {
				uniq = ApplicationFactory.unique();
			}
		}
		status.setId(uniq);
		status.setCode((String) request.get("code"));
		status.setDescription((String) request.get("description"));
		status.setName((String) request.get("name"));
		status.setType((String) request.get("type"));
		request.clear();
		return status;
	}
	
	public Document responseMapListData(List<Status> data) {
		Document dataRes = new Document();
		List<StatusDto> dtos = data.size()>0 ? responseDtoList(data) : null;
		dataRes.put("responseCode", dtos==null? "05" : "00");
		dataRes.put("responseMessage", dtos==null?"Data tidak ada" : "Data ada nih");
		dataRes.put("data", dtos);
		return dataRes;
	}
	
	public StatusDto responseDto(Status data) {
		StatusDto dto = new StatusDto();
		dto.setCode(data.getCode());
		dto.setDescription(data.getDescription());
		dto.setId(data.getId().toString());
		dto.setName(data.getName());
		dto.setType(data.getType());
		return dto;
	}
	
	public List<StatusDto> responseDtoList(List<Status> data) {
		List<StatusDto> dtos = new ArrayList<StatusDto>();
		StatusDto dto = null;
		for (int i = 0; i < data.size(); i++) {
			dto = new StatusDto();
			dto.setCode(data.get(i).getCode());
			dto.setDescription(data.get(i).getDescription());
			dto.setId(data.get(i).getId().toString());
			dto.setName(data.get(i).getName());
			dto.setType(data.get(i).getType());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Status updateStatus(Status status, Document request) {
		status.setCode((String) request.get("code"));
		status.setDescription((String) request.get("description"));
		status.setName((String) request.get("name"));
		status.setType((String) request.get("type"));
		return status;
	}	
}
