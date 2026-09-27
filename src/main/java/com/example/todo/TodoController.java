package com.example.todo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Server-rendered HTML pages (Thymeleaf). */
@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", repository.findAllByOrderByCompletedAscIdDesc());
        return "index";
    }

    @PostMapping("/todos")
    public String add(@RequestParam String title) {
        if (StringUtils.hasText(title)) {
            repository.save(new Todo(title.strip()));
        }
        return "redirect:/";
    }

    @PostMapping("/todos/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        repository.findById(id).ifPresent(todo -> {
            todo.setCompleted(!todo.isCompleted());
            repository.save(todo);
        });
        return "redirect:/";
    }

    @PostMapping("/todos/{id}/delete")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
