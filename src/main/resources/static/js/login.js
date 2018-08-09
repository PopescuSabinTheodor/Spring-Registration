$(document).ready(
		function(){
			$(".activated").hide();
			if (window.location.href.indexOf("activated") != -1) {
				$(".activated").show();
			}
		
	});