package com.rivaldy.shki.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.rivaldy.shki.domain.Book;
import com.rivaldy.shki.domain.Lookup;
import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.dto.BookDto;
import com.rivaldy.shki.shared.ApplicationFactory;

public class BookBO {

	private static BookBO instance = null;
	
	public static synchronized BookBO getInstance() {
		if(instance==null) instance = new BookBO();
		return instance;
	}
	
	public boolean checkRequest(Document request) {
		boolean result = false;
		String[] arrKey = {"title", "author", "type", "status", "description", "code"};
		List<String> listArr = Arrays.asList(arrKey);
		Set<String> setReq = request.keySet();
		List<String> listSet = new ArrayList<String>();
		listSet.addAll(setReq);
		Collections.sort(listArr);
		Collections.sort(listSet);
		result = listArr.equals(listSet);
		return result;
	}
	
	public Book addBook(Document request) {
		Book book = new Book();
		@SuppressWarnings("unchecked")
		List<Book> list = (List<Book>) request.get("list");
		String uniq = ApplicationFactory.unique();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(uniq)) {
				uniq = ApplicationFactory.unique();
			}
		}
		book.setCode((String) request.get("code"));
		book.setAuthor((String) request.get("author"));
		book.setDescription((String) request.get("description"));
		book.setId(uniq);
		book.setStatus((Status) request.get("status"));
		book.setTitle((String) request.get("title"));
		book.setType((Lookup) request.get("type"));
		request.clear();
		return book;
	}
	
	public BookDto responseDto(Book data) {
		BookDto dto = new BookDto();
		dto.setAuthor(data.getAuthor());
		dto.setDescription(data.getDescription());
		dto.setId(data.getId());
		dto.setCode(data.getCode());
		dto.setStatus(data.getStatus().getName());
		dto.setTitle(data.getTitle());
		dto.setType(data.getType().getName());
		return dto;
	}
	
	public List<BookDto> responseDtoList(List<Book> data) {
		List<BookDto> dtos = new ArrayList<BookDto>();
		BookDto dto = null;
		for (int i = 0; i < data.size(); i++) {
			dto = new BookDto();
			dto.setCode(data.get(i).getCode());
			dto.setAuthor(data.get(i).getAuthor());
			dto.setDescription(data.get(i).getDescription());
			dto.setId(data.get(i).getId());
			dto.setStatus(data.get(i).getStatus().getName());
			dto.setTitle(data.get(i).getTitle());
			dto.setType(data.get(i).getType().getName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Document responseDocListData(List<Book> data) {
		Document dataRes = new Document();
		List<BookDto> dtos = data.size()>0 ? responseDtoList(data) : null;
		dataRes.put("responseCode", dtos==null ? "05" : "00");
		dataRes.put("responseMessage", dtos==null ? "Data Not Found" : "Data Found");
		dataRes.put("data", dtos);
		return dataRes;
	}
	
	public Book updateBook(Book book, Document request) {
		book.setCode((String) request.get("code"));
		book.setType((Lookup) request.get("type"));;
		book.setAuthor((String) request.get("author"));
		book.setDescription((String) request.get("description"));
		book.setTitle((String) request.get("title"));
		request.clear();
		return book;
	}
}
