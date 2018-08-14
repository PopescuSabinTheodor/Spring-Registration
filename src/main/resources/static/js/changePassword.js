$(document).ready(
		function(){
			$("#feedback").hide();
			$('.changebtn').click(submitForm);
			var url = new URL(window.location.href);
			var updateKey = url.searchParams.get("updateKeyString");
			function submitForm() {
				if ($('#password').val().length < 8) {
					$("#error-password").text(
							"The password must be at least 8 characters long")
							.show();
				}else if ($('#password').val() != $('#passwordVerify').val()) {
					$(".error").hide();
					$("#error-passwordVerify").text(
					"The passwords must match")
					.show();
				}else {
					$(".error").hide();
					var updateData = postData();
					console.log("post data ", updateData);
					$.ajax({
						type : "POST",
						url : "/recoverPassword",
						contentType : "application/json",
						data: JSON.stringify(updateData),
						error : function(data) {
							$(".form-group").hide();
							$(".changeBtn").hide();
							$(".feedback").text("An unexpected error occured.")
						},
						success: function(data) {
							$(".form-group").hide();
							$(".changeBtn").hide();
							if(data.passwordUpdated) {
								$(".feedback").text("Your password has been successfully changed.").show();
							}else {
								$(".feedback").text("Your link may have expired.")
								$("#feedback").show();
							}
						}
					});
				}
			}

			$('.form-group input').on(
					'focus blur',
					function(e) {
						$(this).parents('.form-group').toggleClass('active',
								(e.type === 'focus' || this.value.length > 0));
					}).trigger('blur');
			function postData() {
				return {
					"password" : $('#password').val(),
					"updateKey" : updateKey
				};
			}
			
		});