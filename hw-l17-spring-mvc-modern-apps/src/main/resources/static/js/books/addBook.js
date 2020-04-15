"use strict";

$('#save').on('click', function() {
    const book = {
        title: $('#title').val(),
        author: { name: $('#name').val(), surname: $('#surname').val() },
        genre: { name: $('#genre').val() }
    };

    $.post('/api/book', book, function(data) {
        console.log(data);
        //location.href = '/book/${book.id}';
    });
});