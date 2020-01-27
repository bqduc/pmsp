<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
 
<body>
    <p>Dear ${firstName} ${lastName},</p>
    <p>Sending Email using Spring Boot with <b>FreeMarker template !!!</b></p>
    <p>Thanks</p>
    <p>${signature}</p>
    <p>${location}</p>
    charset="utf-8"
    
   {contact},

Thank you for contacting me about Java Book.  Per our discussion I have attached
a sales order for {contact.address1} {contact.address2} {contact.city} {contact.state} {contact.zip}.
Please sign where indicated on the last page and fax the entire sales order to me at {faxNumber}.
Once I have the signed sales order, I will place your order and get your stuff shipped.

Thank you for choosing YYYYOrg. 

Regards,

${userContact.firstName} ${userContact.lastName}
${userContact.companyName}
${userContact.registeredDate?string["dd.MM.yyyy, HH:mm"]}
${userContact.stateProvince}


<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top" bgcolor="#838383"
				style="background-color: #838383;"><br> <br>
				<table width="600" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" bgcolor="#d3be6c"
							style="background-color: #d3be6c; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
							
							<div style="font-size: 48px; color:blue;">
								<b>Java Techie</b>
							</div>
							
							<div style="font-size: 24px; color: #555100;">
								<br> Sending Email using Spring Boot with <b>FreeMarker
									template !!! <br>
							</div>
							<div>
								<br> Java Techie is a channel where we create and publish
								videos on<br> 'how to' about latest technology trends like<br>
								spring ,spring boot ,hibernate , web services and micro service
								<br> ""This channel is created to share the knowledge and
								to gain the knowledge"<br>
								<br>"Sharing the knowledge is biggest learning" <br> <br>
								<br> <br> <b>${firstName}</b><br>${location}<br>
								<br>
							</div>
						</td>
					</tr>
				</table> <br> <br></td>
		</tr>
	</table>
  
</body>
 
</html>