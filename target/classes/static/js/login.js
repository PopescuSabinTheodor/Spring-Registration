$(document).ready(
		function(){
			$(".activated").hide();
			$("#recoverForm").hide();
			$("#loginForm").show();
			$(".recover-email").hide();
			$(".email-not-exist").hide();
			$('.recoverbtn').click(submitForm);
			function submitForm() {
				var email = { "email" : $("#recover-email").val() };
				if(validateEmail(email.email)) {
					$.ajax({
						type : "POST",
						url : "/recover",
						contentType : "application/json",
						data: JSON.stringify(email),
						error : function(data) {
						},
						success: function(data) {
							if(data.emailExists){
								$(".recover-email").show();
								$(".form-group").hide();
								$(".recoverBtn").hide();
							}else {
								$(".email-not-exist").show();
							}
						}
					});
				}else {
					$(".error").hide();
					$("#error-email-recover").text(
							"Please enter a valid email address.")
							.show();
				}

			}
			function validateEmail(email) {
				var re = /\S+@\S+\.\S+/;
				return re.test(email);
			}
			if (window.location.href.indexOf("activated") != -1) {
				$(".activated").show();
			}else if (window.location.href.indexOf("recover") != -1) {
				$("#loginForm").hide();
				$("#recoverForm").show();
			}
			$('.form-group input').on(
					'focus blur',
					function(e) {
						$(this).parents('.form-group').toggleClass('active',
								(e.type === 'focus' || this.value.length > 0));
					}).trigger('blur');
	});