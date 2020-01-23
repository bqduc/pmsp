<html> 
<head></head>
 
<body>
    <p>Dear ${firstName} ${lastName},</p>
    <p>Sending Email using Spring Boot with <b>FreeMarker template !!!</b></p>
    <p>Thanks</p>
    <p>${signature}</p>
    <p>${location}</p>
    
    
   {contact},

Thank you for contacting me about Java Book.  Per our discussion I have attached
a sales order for {contact.address1} {contact.address2} {contact.city} {contact.state} {contact.zip}.
Please sign where indicated on the last page and fax the entire sales order to me at {faxNumber}.
Once I have the signed sales order, I will place your order and get your stuff shipped.

Thank you for choosing YYYYOrg. 

Regards,

{user.userFirstName} {user.userLastName}
{user.userOffice}
{user.userPhone}
{user.userEmail}
  
</body>
 
</html>