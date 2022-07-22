$(function () {
    $('#load-more').on('click', function () {

        $.ajax({
            url: '/event/loadMore',
            type: 'GET',
            success: function (response) {
                if(response.length > 1200) {
                    console.log(response.length);
                    $('#showMore-list').append(response);
                }
            }
        });
    });
});
