function jQuery(selector) {

	if(selector.startsWith('<')) {
		var tagName = selector.substring(1, selector.length - 1);
		var el = [document.createElement(tagName)];

	} else {
		var el = document.querySelectorAll(selector);
	}

	el.html = function(value) {
		if(arguments.length == 0) {
			return this[0].innerHTML;
		} 
		for(var e of el) {
			e.innerHTML = value;
		}
		return this;
	};
	
	el.val = function(value) {
		if(arguments.length == 0) {
			return this[0].value;
		} 
		for(var e of el) {
			e.value = value;
		}
		return this;
	};

	el.addClass = function(value) {
		if(arguments.length == 0) {
			return this[0].innerHTML;
		} 
		for(var e of el) {
			e.className = e.className + " " + value;
		}
		return this;
	};

	el.css = function(name, value) {
		if(arguments.length == 1) {
			return this[0].style[name];
		}
		for(let e of el) {
			e.style[name] = value;
		}
		return this;
	}

	el.on = function(event, p1, p2) {
		if(arguments.length < 2) {
			return;
		}

		// 값이 2개 넘어온다면, 두번째 파라미터는 리스너이다.
		if(arguments.length == 2) {
			for(var e of el) {
				e.addEventListener(event, p1);
			}
		} else {
			// 값이 3개 넘어온다면, 세번째 파라미터가 리스너이다.
			for(var element of el) {
				element.addEventListener(event, (e) => {
					// 부모의 자식중에서 파라미터에서 지정한 셀럭터일때만
					// 리스너를 호출한다.
					// 1) 현재 부모에 자식중, 셀렉터에 소속되어 있는 태그만 찾는다.
					var targets = element.querySelectorAll(p1);
					// 2) 이벤트가 발생한 태그가 셀렉터에서 지정한 태그일 경우에만
					// 리스너를 호출한다.
					for(let target of targets) {
						console.log(target);
						if(e.target == target) {
							p2(e);
						}
					}
				});
			}
		}
		return this;
	}

	el.click = function(listener) {
		if(arguments.length < 1) {
			return;
		}

		this.on('click', listener);
		return this;
	}

	el.append = function(childs) {
		if(arguments.length < 1) {
			return;
		}

		for(var e of el) {
			for(var child of childs) {
				e.appendChild(child);
			}
		}
		return this;
	}

	el.appendTo = function(parents) {
		if(arguments.length < 1) {
			return;
		}

		for(var e of parents) {
			for(var child of el) {
				e.appendChild(child);
			}
		}
		return this;
	}
	return el;
};

jQuery.ajax = function(url, settings) {
	var defaults = {
		method : 'GET',
		dataType : 'html',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
	};

	if(settings == undefined) {
		settings = defaults;
	}
	
	if(settings.method == undefined) {
		settings.method = 'GET';
	}

	if(settings.dataType == undefined) {
		settings.dataType = 'html';
	}

	if(settings.contentType == undefined) {
		settings.contentType = 'application/x-www-form-urlencoded; charset=UTF-8';
	}
	
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
            	if(settings.success) {
            		settings.success(xhr.responseText);
            	}
            } else {
            	if(settings.error) {
            		settings.error();
            	}
            }
        }
    };
    
    xhr.open(settings.method, url, true);

    if (settings.method == 'GET') {
      xhr.send();
    } else {
      xhr.setRequestHeader("Content-Type", settings.contentType);
      if (settings.data) {
        var qs = "";
        for (var propName in settings.data) {
          if (qs.length > 0) {
            qs += "&";
          }
          qs += propName + "=" + settings.data[propName]; 
        }
      }
      console.log(qs);
      xhr.send(qs);
    }
  };

jQuery.get = function(url, cb) {
	  jQuery.ajax(url, {success: cb});
	};

	var $ = jQuery;







