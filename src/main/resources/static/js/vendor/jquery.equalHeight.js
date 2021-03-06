// make sure the $ is pointing to JQuery and not some other library
    (function($){
        // add a new method to JQuery

        $.fn.equalHeight = function() {
           // find the tallest height in the collection
           // that was passed in (.column)
            var tallest = 0;
            this.each(function(){
                var thisHeight = $(this).height();
                if( thisHeight > tallest)
                    tallest = thisHeight;
            });

            // set each items height to use the tallest value found
            this.each(function(){
                $(this).height(tallest);
            });
        }
    })(jQuery);