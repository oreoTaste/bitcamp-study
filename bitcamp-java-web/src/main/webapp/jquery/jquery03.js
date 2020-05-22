function jQuery(selector) {

	var e = document.querySelector(selector);

	e.html = function(value) {

		if(arguments.length == 0) {
			return this.innerHTML;
		} else {
			this.innerHTML = value;
		}
	};

	return e;
}

var $ = jQuery;