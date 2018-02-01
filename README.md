# instagram-reminder
**Instagram reminder** is a linebot, which sends new media while Instagram user you followed posts new media  
<img src="https://i.imgur.com/yoSJAOe.jpg" alt="img_1" width="30%" height="30%">
<img src="https://i.imgur.com/anxrMIT.jpg" alt="img_2" width="30%" height="30%">  
Note that **since it's impossible to access private IG users data(because you don't really follow that user), you can only follow public IG users in this bot.**

# HOW TO USE #
First of all, scan the QR code to add bot  
<img src="https://i.imgur.com/OMNOZVu.jpg" alt="img_3">
> ### Commands  
> * *bot list*  
List all IG users you followed.
> * *bot follow [id]*  
Add an IG user to your follow list. [id] is IG user name.  
Currently you can only follow **10** users.
> * *bot unfollow [id]*  
Remove an IG user from your follow list. [id] is IG user name.
> * *bot help*  
Show all commands
> * *bot on*  
Turn on bot notification
> * *bot off*  
Turn off bot notification
> * *bot status*  
Show current bot status

# HOW TO RUN PROJECT #
This project is build in following environment:  
> **Java version: Java 8**  
> **Framwork: Spring Boot 1.5.8 + line-bot-api 1.10**  
> **Server platform: [Heroku cloud application platform](https://www.heroku.com/)**  
> **Database: PostgreSQL 9.4.1212**  

If you want to run this project, follow these steps:  

### 1. Import database
There are 2 tables which record user and IG users' latest media. Import them to your own database. [table script](./src/main/webapp/dbScript)  
  
### 2. Create a channel for your bot:  
Follow this [tutorials](https://developers.line.me/en/docs/messaging-api/building-bot) to create bot.  
You'll see bot's **access token** and **channel secrete** at setting page, these will be used at following steps.  
<img src="https://i.imgur.com/grj27NE.png" alt="img_4" width="60%" height="60%">    

To receive request from the Line platform, the webhook must be enabled.  
Enabled webhook and set webhook url at setting page.  
<img src="https://i.imgur.com/oHMhhRo.png" alt="img_5" width="60%" height="60%">
  
### 3. Create an instagram account & receive access token
Create an instagram account, and follow this [tutorial](https://www.instagram.com/developer/authentication/) step by setp to get **access token**.  
Be sure that token has the *public_content* permission.
  
### 4. Modify application.yml
> * Paste linebot's **access token** and **channel secret** at [application.yml](./src/main/resources/application.yml).  
> * Set **handler.path** depend on your server url
  
### 5. Modify application.properties
> * Paste database setting at [application.properties](./src/main/resources/application.properties), including *driver*, *url*, *username*, and *password*
> * Paste **instagram access token** as well
  
### 6. Deploy project to server  
Since the webhook URL must use HTTPS, I deploy it on Heroku.  
If you want to deploy server on other place, be sure to use HTTPS.  
  
