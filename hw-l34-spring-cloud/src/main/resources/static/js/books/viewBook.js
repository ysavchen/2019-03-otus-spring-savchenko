"use strict";

$('#save').on('click', function() {
    const bookId = $('#bookId').text();
    const book = JSON.stringify({
        id: bookId,
        title: $('#title').val()
    });

    $.ajax({
        url: '/api/book/' + bookId,
        type: 'PATCH',
        contentType: 'application/json; charset=utf-8',
        data: book,
        success: function(book) {
            $('#message').text('Title is updated!');
        },
        error: function() {
            $('#message').text('Title is not updated!');
        }
    });
});

$('#delete').on('click', function() {
    const bookId = $('#bookId').text();
    $.ajax({
        url: '/api/book/' + bookId,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(book) {
            location.href = '/';
        },
        error: function() {
            $('#message').text('Book is not deleted!');
        }
    });
});

$('#allBooks').on('click', function() {
    location.href = '/';
});