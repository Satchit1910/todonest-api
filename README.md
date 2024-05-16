# TodoNest API

API repository for TodoNest, a project and todo management app.

This project is written using Spring Boot.

I have hosted the full-stack app using Vercel and render.com. Check it out [here](https://todonest-v1.vercel.app/).

### Run Locally

Clone the project

```bash
git clone https://github.com/Satchit1910/todonest-api
```

Uncomment the first line from **application.properties**.

Add **env.properties** file in the route */src/main/resources* , i.e. in the same directory as application.properties.

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



