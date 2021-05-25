package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.TodoMapper;
import com.example.model.Todo;

@Service
public class TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
	
	//全件取得
	@Transactional
	public List<Todo> selectAll(){
		return todoMapper.selectAll();
	}
	
	//一件取得
	@Transactional
	public Todo selectOne(Long id) {
		return todoMapper.selectOne(id);
	}
	//登録
	@Transactional
	public void insert(Todo todo) {
		todoMapper.insert(todo);
	}
	
	//更新
	public int updata(Todo todo) {
		return todoMapper.update(todo);
	}
	//消去
	@Transactional
	public void delete(Long id) {
		todoMapper.delete(id);
	}
}
