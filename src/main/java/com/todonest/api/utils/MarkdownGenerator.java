package com.todonest.api.utils;

import com.todonest.api.entity.Project;
import com.todonest.api.entity.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class MarkdownGenerator {

    public static String toMarkdown(Project project) {
        StringBuilder markdownContent = new StringBuilder();

        // Project title
        markdownContent.append("# ").append(project.getTitle()).append("\n\n");

        // Summary
        markdownContent.append("Summary: ")
                .append(getCompletedTodos(project).size())
                .append("/")
                .append(project.getTodos().size())
                .append(" todos completed.\n\n");

        // Pending todos
        markdownContent.append("### Pending\n\n");
        List<Todo> pendingTodos = project.getTodos()
                .stream()
                .filter(todo -> todo.getStatus().equals("open"))
                .toList();
        if (!pendingTodos.isEmpty()) {
            pendingTodos.forEach(todo -> markdownContent.append("- [ ] ").append(todo.getDescription()).append("\n"));
        } else {
            markdownContent.append("*No pending todos.*\n");
        }

        markdownContent.append("\n"); // Add newline after pending todos

        // Completed todos
        markdownContent.append("### Completed\n\n");
        List<Todo> completedTodosList = getCompletedTodos(project);
        if (!completedTodosList.isEmpty()) {
            completedTodosList.forEach(todo -> markdownContent.append("- [x] ").append(todo.getDescription()).append("\n"));
        } else {
            markdownContent.append("*No completed todos.*\n");
        }

        return markdownContent.toString();
    }

    private static List<Todo> getCompletedTodos(Project project) {
        List<Todo> projectTodos = project.getTodos();
        return projectTodos.stream()
                .filter(todo -> "completed".equals(todo.getStatus()))
                .collect(Collectors.toList());
    }
}

