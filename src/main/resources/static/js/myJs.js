$(document).ready(function () {
    $('.collapse')
        .on('shown.bs.collapse', function() {
            $(this)
                .parent()
                .find(".fa-sort-desc")
                .removeClass("fa-sort-desc")
                .addClass("fa-sort-asc");
        })
        .on('hidden.bs.collapse', function() {
            $(this)
                .parent()
                .find(".fa-sort-asc")
                .removeClass("fa-sort-asc")
                .addClass("fa-sort-desc");
        });
});