package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Todo;
import com.example.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	//全件取得したtodoをtodoに入れ、top.htmlで表示
	@GetMapping("")
	public String top(Model model) {
		
		model.addAttribute("todo", todoService.selectAll());
		return "todos/top";
	}
	@GetMapping("new")//top→newボタン
	public String newTodo(Model model, @ModelAttribute Todo todo) {
		return "todos/new";
	}
	@PostMapping("new")//fromから作成された画面
	public String create(@Validated @ModelAttribute Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todos/new";
		}
		todoService.insert(todo);
		return "redirect:/todos";//一覧に戻る
	}
	@GetMapping("{id}")//一件分のデータの中身を確認
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("todo", todoService.selectOne(id));
		return "todos/show";
	}
	@GetMapping("{id}/change")//編集画面にいくまでの画面
	public String chage(@PathVariable Long id, Model model) {
		model.addAttribute("todo", todoService.selectOne(id));
		return "todos/change";
	}
	@PutMapping("put/{id}")//更新画面
	public String updata(Todo todo) {
		todoService.updata(todo);
		return "redirect:/todos";
	}
	@DeleteMapping("{id}/delete")//消去画面
	public String dast(@PathVariable Long id) {
		todoService.delete(id);
		return "redirect:/todos";
	}
	
}
