package com.rivaldy.shki.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.rivaldy.shki.domain.Lookup;
import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.dto.LookupDto;
import com.rivaldy.shki.shared.ApplicationFactory;

public class LookupBO {
	
	private static LookupBO instance = null;
	
	public static synchronized LookupBO getInstance() {
		if(instance==null) instance = new LookupBO();
		return instance;
	}
	
	public boolean checkRequest(Document request) {
		boolean result = false;
		String[] arrKey = {"code", "description", "name", "priority", "type", "status"};
		List<String> listArr = Arrays.asList(arrKey);
		Set<String> setReq = request.keySet();
		List<String> listSet = new ArrayList<String>();
		listSet.addAll(setReq);
		Collections.sort(listArr);
		Collections.sort(listSet);
		result = listArr.equals(listSet);
		return result;
	}
	
	public Lookup addLookup(Document request) {
		Lookup lookup = new Lookup();
		@SuppressWarnings("unchecked")
		List<Lookup> list = (List<Lookup>) request.get("list");
		String uniq = ApplicationFactory.unique();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(uniq)) {
				uniq = ApplicationFactory.unique();
			}
		}
		lookup.setCode((String) request.get("code"));
		lookup.setDescription((String) request.get("description"));
		lookup.setId(uniq);
		lookup.setName((String) request.get("name"));
		lookup.setPriority((Integer) request.get("priority"));
		lookup.setStatus((Status) request.get("status"));
		lookup.setType((String) request.get("type"));
		request.clear();
		return lookup;
	}
	
	public LookupDto responseDto(Lookup data) {
		LookupDto dto = new LookupDto();
		dto.setCode(data.getCode());
		dto.setDescription(data.getDescription());
		dto.setId(data.getId());
		dto.setName(data.getName());
		dto.setPriority(data.getPriority());
//		dto.setStatus(StatusBO.getInstance().responseDto(data.getStatus()));
		dto.setStatus(data.getStatus().getName());
		dto.setType(data.getType());
		return dto;
	}
	
	public List<LookupDto> responseDtoList(List<Lookup> data) {
		List<LookupDto> dtos = new ArrayList<LookupDto>();
		LookupDto dto = null;
		for (int i = 0; i < data.size(); i++) {
			dto = new LookupDto();
			dto.setCode(data.get(i).getCode());
			dto.setDescription(data.get(i).getDescription());
			dto.setId(data.get(i).getId());
			dto.setName(data.get(i).getName());
			dto.setPriority(data.get(i).getPriority());
//			dto.setStatus(StatusBO.getInstance().responseDto(data.get(i).getStatus()));
			dto.setStatus(data.get(i).getStatus().getName());
			dto.setType(data.get(i).getType());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Document responseDocListData(List<Lookup> data) {
		Document dataRes = new Document();
		List<LookupDto> dtos = data.size()>0 ? responseDtoList(data) : null;
		dataRes.put("responseCode", dtos==null ? "05" : "00");
		dataRes.put("responseMessage", dtos==null ? "Data Not Found" : "Data Found");
		dataRes.put("data", dtos);
		return dataRes;
	}

	public Lookup updateLookup(Lookup lookup, Document request) {
		lookup.setCode((String) request.get("code"));
		lookup.setDescription((String) request.get("description"));
		lookup.setName((String) request.get("name"));
		lookup.setType((String) request.get("type"));
		lookup.setPriority((Integer) request.get("priority"));
		request.clear();
		return lookup;
	}
	
}
