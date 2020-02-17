package com.mycompany.hw_l09_spring_orm_jpa.shell;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;
import com.mycompany.hw_l09_spring_orm_jpa.service.CommentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentDbService dbService;

    @ShellMethod(value = "Find comments by book_id", key = {"fci", "find-comments-by-book-id"})
    public String findCommentsByBookId(long id) {
        var comments = dbService.getCommentsByBookId(id);
        if (comments.isEmpty()) {
            return "No comments (bookId = " + id + ") found";
        }

        var bookTitle = comments.get(0).book().title();
        return "Comments for book (" + bookTitle + "): \n" +
                comments.stream()
                        .map(Comment::content)
                        .collect(joining("\n"));
    }

    @ShellMethod(value = "Add comment by book_id", key = {"aci", "add-comment-by-book-id"})
    public String addCommentByBookId(long bookId, String comment) {

        return "";
    }

    @ShellMethod(value = "Delete comment by book_id", key = {"dci", "delete-comment-by-book-id"})
    public String deleteCommentByBookId(long bookId, String comment) {
        return "";
    }
}
