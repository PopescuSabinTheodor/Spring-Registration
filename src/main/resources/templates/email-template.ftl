<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
        
        .verifyBtn {
 			background: #6498fe;
  			border-radius: 2px;
  			color: #fff;
  			cursor: pointer;
  			font-weight: bold;
  			margin: 5px 0;
  			text-decoration: none;
  			padding: 8px;
  			transition: all 0.15s ease-in-out 0s;
  			box-shadow: 0 1px 2px 0px rgba(0,0,0,0.16), 0 1px 2px 0px rgba(0,0,0,0.23);
		}
		#signature > a {
			color: white;
		}
		.hello {
			padding-bottom: 0.5em;
		}
		.button {
			display: flex;
			justify-content: center;
		}
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td align="center" bgcolor="#6498fe" style="padding: 40px 0 30px 0;">
            </td>
        </tr>
        <tr>
            <td bgcolor="#f4f4f4" style="padding: 40px 30px 40px 30px;">
                <p class="hello">Hello ${name},</p>
                <p>In order to activate your account, click on the button below. </p>
                <div class="button"><a href="http://localhost:8082/${link}/${hash}/" class="verifyBtn">Verify</a></div>
                <p>If you are having problems verifying your email, please contact us at mailoficial@domeniu.ro </p>
            </td>
        </tr>
        <tr>
            <td bgcolor="#6498fe" style="padding: 30px 30px 30px 30px;">
                <p id="signature">${signature}</p>
            </td>
        </tr>
    </table>

</body>
</html>