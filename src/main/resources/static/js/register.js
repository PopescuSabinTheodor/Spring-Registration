$(document).ready(
		function() {
			$('.registerbtn').click(submitForm);
			$('.form-group input').on(
					'focus blur',
					function(e) {
						$(this).parents('.form-group').toggleClass('active',
								(e.type === 'focus' || this.value.length > 0));
					}).trigger('blur');
			function submitForm() {
				var userInfo = getUserInfo();
				// Validate empty fields

				if (validateInput(userInfo)) {
					$.ajax({
						type : "GET",
						dataType : "json",
						url : "/users/" + encodeURIComponent(userInfo.email),
						success : checkUser
					});
				}

			}
			function validateEmail(email) {
				var re = /\S+@\S+\.\S+/;
				return re.test(email);
			}

			function getUserInfo() {
				var fName = $('#first-name').val();
				var lName = $('#last-name').val();
				var email = $('#email').val();
				var pwd1 = $('#pwd1').val();
				var pwd2 = $('#pwd2').val();
				return {
					"firstName" : $('#first-name').val(),
					"lastName" : $('#last-name').val(),
					"email" : $('#email').val(),
					"password" : $('#pwd1').val(),
					"passwordVerify" : $('#pwd2').val()
				};
			}

			function validateInput(userInfo) {
				if (userInfo.firstName.length < 5) {
					$("#error-first-name").text(
							"First name should be at least 5 characters long.")
							.show();
					return false;
				} else if (userInfo.lastName.length < 5) {
					$(".error").hide();
					$("#error-last-name").text(
							"Last name should be at least 5 characters long.")
							.show();
					return false;
				} else if (!validateEmail(userInfo.email)) {
					$(".error").hide();
					$("#error-email").text(
							"Please enter a valid email address.")
							.show();
					return false;
				} else if (userInfo.password.length < 8) {
					$(".error").hide();
					$("#error-password").text(
							"The password must be at least 8 characters long")
							.show();
					return false;
				} else if (userInfo.password != userInfo.passwordVerify) {
					$(".error").hide();
					$("#error-passwordVerify").text(
							"The passwords must match")
							.show();
					return false;
				} else {
					$(".error").hide();
					return true;
				}
			}

			function checkUser(data) {
				var emailNotValid = data.emailExists;
				if (emailNotValid) {
					alert("Email already taken")
				} else {
					console.log("serializeee... ", $('form').serialize());
					$.ajax({
						type : "POST",
						url : "/registered",
						contentType : "application/json",
						data : JSON.stringify(getUserInfo()),
						error : function(data) {
							console.log("iha", data); // Optional
						},
						success : function(data) {
							alert("A verification email has been sent at", JSON
									.stringify(data));
							console.log(data);
						}

					});
				}

			}
		});
