"use strict";

$('#save').on('click', function() {
    const book = JSON.stringify({
        title: $('#title').val(),
        author: { name: $('#name').val(), surname: $('#surname').val() },
        genre: { name: $('#genre').val() }
    });

    $.ajax({
        url: '/api/book',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: book,
        success: function(book) {
            location.href = '/book/' + book.id;
        },
        error: function() {
            $('#message').text('Book is not added!');
        }
    });
});