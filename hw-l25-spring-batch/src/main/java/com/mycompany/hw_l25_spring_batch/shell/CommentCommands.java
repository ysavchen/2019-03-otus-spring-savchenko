package com.mycompany.hw_l25_spring_batch.shell;

import com.mycompany.hw_l25_spring_batch.domain.Comment;
import com.mycompany.hw_l25_spring_batch.service.CommentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentDbService dbService;

    @ShellMethod(value = "Find comments by book_id", key = {"fcbi", "find-comments-by-book-id"})
    public String findCommentsByBookId(String id) {
        var comments = dbService.getCommentsByBookId(id);
        if (comments.isEmpty()) {
            return "No comments with bookId = " + id + " found";
        }

        var bookTitle = comments.get(0).getBook().getTitle();
        return "Comments for book '" + bookTitle + "': \n" +
                comments.stream()
                        .map(Comment::getContent)
                        .collect(joining("\n"));
    }

    @ShellMethod(value = "Add comment by book_id", key = {"acbi", "add-comment-by-book-id"})
    public String addCommentByBookId(String id, String comment) {
        String commentId = dbService.addCommentByBookId(id, new Comment(comment));
        if (commentId != null && !commentId.isEmpty()) {
            return "Comment is added with id = " + commentId;
        }
        return "Book with id = " + id + " is not found";
    }

    @ShellMethod(value = "Delete comment by book_id", key = {"dcbi", "delete-comment-by-book-id"})
    public String deleteCommentByBookId(String id, String comment) {
        dbService.deleteCommentByBookId(id, new Comment(comment));
        return "Comment is is deleted from book (id = " + id + ")";
    }
}
