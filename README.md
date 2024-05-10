
# TodoNest API

API repository for TodoNest, a project and todo management app.

I have hosted the full-stack app using AWS services. Check it out [here](http://todonest-ui.s3-website-us-east-1.amazonaws.com).

This project is written using **Spring Boot**. <br>
I have opted for a **PostgreSQL** DB to store the data.

### Run Locally

Clone the project

```bash
git clone https://github.com/Satchit1910/todonest-api
```
Add **env.properties** file in the route */src/main/resources* , i.e. in the same directory as application.properties. <br>
You have to add the credentials to your PostgreSQL DB and also a GitHub PAT in this file.

Format for env.properties: <br>
`DB_DATABASE` = database name <br>
`DB_PORT` = port <br>
`DB_HOSTNAME` = hostname <br>
`DB_USERNAME` = username <br>
`DB_PASSWORD` = password <br>
`GH_PAT` = GitHub Personal Access Token <br>

[Here's how you can create your own PAT from your Github.](https://docs.github.com/en/enterprise-server@3.9/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)
Just make sure you select only the **gist** option while you grant scopes to your PAT.

and that is all!

Open the project in a suitable IDE, I use intelliJ.
Then run it!



