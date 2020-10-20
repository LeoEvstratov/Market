// https://datatables.net/

$(document).ready( function () {
    var table = $('.table').DataTable({
        "ajax": {
            "url": "/market/api/products",
            "dataSrc": "" //,
//	      "type": "POST"
        },
//	      "processing": true,
//        "serverSide": true,
        "columns": [
            { "data": "id" },
            { "data": "title" },
            { "data": "price" },
            {
                "data": "id",
                "render": function (data, type, full, meta) {
                    return '<button type="button" class="btn btn-primary add-item-btn" link="' + data + '">Купить</button>';
                }
            }
        ],
        "initComplete": function(settings, json) {
            $('.add-item-btn').on('click', function(event) {
                var btnLink = $(this).attr('link');
                console.log(btnLink);
                $.ajax({
                    url: "/market/api/addToCart/"+btnLink,
                    type: "GET",
                    // data: {
                    //     "id": btnLink
                    // }
                }).done(function() {
                    alert("Товар добавлен в корзину");
                });
            });
        }
    })
});