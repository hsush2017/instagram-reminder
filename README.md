# instagram-reminder
An linebot that will post new media when your interested Instagram user has new post
--------------------------------------------------------------------------------------

This is a JAVA based linebot server handling user commands. To make it work, you have to do some work:

1. Create a database & import tables:
These tables record user id and Instagram users' latest media code. Import them to your own database. (table script are in [dbScript folder](./src/main/webapp/dbScript)

2. Create a linebot & enable webhook:
You can find these process [here](https://developers.line.me/en/docs/messaging-api/building-bot), there are many tutorials can help you. 
You should get linebot's channel access token and channel secret. And you have to set Webhook URL in bot's setting page, so that all requests will redirect to your server. 

3. Modify [application.yml](./src/main/resources/application.yml)</br>
Copy linebot's access token and secret on it.</br>
</br>
4. Modify [application.properties](./src/main/resources/application.properties)</br>
Copy your database setting on it, including driver, url, username, and password</br>
</br>
5. Deploy project to server</br>
Since the webhook URL must use HTTPS and have an SSL certificate issued by an authorized certificate authority (CA), I deploy it on a cloud platform service called [Heroku](https://www.heroku.com/). If you want to deploy server on other place, be sure to use HTTPS and SSL.</br>
  
