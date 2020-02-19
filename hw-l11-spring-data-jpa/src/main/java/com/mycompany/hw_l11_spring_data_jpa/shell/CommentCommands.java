package com.mycompany.hw_l11_spring_data_jpa.shell;

import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import com.mycompany.hw_l11_spring_data_jpa.service.CommentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentDbService dbService;

    @ShellMethod(value = "Find comments by book_id", key = {"fcbi", "find-comments-by-book-id"})
    public String findCommentsByBookId(long id) {
        var comments = dbService.getCommentsByBookId(id);
        if (comments.isEmpty()) {
            return "No comments with bookId = " + id + " found";
        }

        var bookTitle = comments.get(0).book().title();
        return "Comments for book '" + bookTitle + "': \n" +
                comments.stream()
                        .map(Comment::content)
                        .collect(joining("\n"));
    }

    @ShellMethod(value = "Add comment by book_id", key = {"acbi", "add-comment-by-book-id"})
    public String addCommentByBookId(long id, String comment) {
        long commentId = dbService.addCommentByBookId(id, new Comment(comment));
        if (commentId != 0) {
            return "Comment is added with id = " + commentId;
        }
        return "Book with id = " + id + " is not found";
    }

    @ShellMethod(value = "Delete comment by book_id", key = {"dcbi", "delete-comment-by-book-id"})
    public String deleteCommentByBookId(long id, String comment) {
        dbService.deleteCommentByBookId(id, new Comment(comment));
        return "Comment is is deleted from book (id = " + id + ")";
    }
}
