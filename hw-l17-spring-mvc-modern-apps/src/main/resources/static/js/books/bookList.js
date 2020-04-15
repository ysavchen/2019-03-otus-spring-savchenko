$(function () {
    $.get('/api/book').done(function (books) {
        books.forEach(function (book) {
            $('tbody').append(`
                <tr>
                    <td class="dataField">${book.id}</td>
                    <td class="dataField">${book.title}</td>
                    <td class="dataField">${book.author.name} ${book.author.surname}</td>
                    <td class="dataField">${book.genre.name}</td>
                    <td><a id="edit" href="/book/update/${book.id}">Edit</a></td>
                </tr>
            `)
        });
    })
});