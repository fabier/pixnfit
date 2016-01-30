# pixnfit
PixnFit project made with Grails

**PLEASE READ CAREFULLY, THERE ARE THINGS TO DO BEFORE LAUNCHING APPLICATION**


# Things to do BEFORE launching app
## Configuration
You need to create a file in <code>/grails-app/conf</code> named <code>passwords.properties</code>

**YOU MUST CHANGE THE VALUES BEFORE STARTING**
<br/>
This file must contain the following keys.

> passwords.properties

```
# Admin user
admin.username=My Name // This is NOT the real admin name, change it !
admin.email=email@somesite.com // <= This is NOT the real admin email, change it !
admin.password=5Fn7Mcs1dU9i1s9u1H69t // <= This is NOT the real password, change it !

# Email account to send emails
grails.mail.username=noreply@somesite.com // <= This is NOT the real email account, change it !
grails.mail.password=CW9M4R92OGH75sny533cT // <= This is NOT the real password, change it !

# Database password
dataSource.username=someuser // <= This is NOT the real username, change it !
dataSource.password=4g2ws366gM875MQB478EU // <= This is NOT the real password, change it !
```
