Need the simple extension to delete the database file(the application will create the file itself).

I use the following endpoints from riot game:

/lol/summoner/v4/summoners/by-name/{summonerName}

#### I have implemented the DISTINCTION level.



### Put API Keys(Before you run the program)

Go to resources and there will be a `myconfig.txt`, put your details as following:

For example in `myconfig.txt`:

Your-riot-api-key

Your-twillio-Account-Sid

Your-twillio-authToken

The-phone-number-you-send-information

The-phone-number-you-receive-information

(Note: the phone number should be like +15028225286)

### How to run the application:

gradle run --args="offline online"

or

gradle run --args="online offline"

or

gradle run --args="offline offline"

or

gradle run --args="online online"

with the first controlling the input API, and the second controlling the output API.





### TDD:

new feature: getSummonerByName

RED:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/66507915720ebb9a482ad9e387e65fa6551c8016

GREEN:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/d4e259cbe05bf6d0f607e304b1dd67a95cb9799c



New feature: all the features except the getSummonerByName

RED:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/2c29875e6c7b0943bb6e45f019cd61b58bc3d7dc

GREEN:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/5c6d7312f0f327f87d787096c9871da1c697b0f1



Refactor of RiotGameMaker:

RED:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/7909b7638b5dff1a1b2e425870a2dc0674f9ce0a

GREEN: (wrong commit message, should be GREEN)

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/468144304b39ae274fd93b0611313aef54030c9e



Refactor of RiotGameMaker with test still passing:

Refactor:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/0c52c082d066d678a0cc6171369668049d320d61



Refactor of RiotGameMaker:

RED:

Link:https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/023ecfc9e0474f0889bfdc3a791a4696b86d6441

GREEN:

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/ae9c18ed19ed6ae4440bb7fcb8d8117abda8bafe



#### Big refactor: only keep the `getSummonerByName` and `getLeagueEntriesBySummonerId`. Test can still pass.

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/f84737e244974cf24fd07f9223b5e9f95e0c3a4e



Add twill output:

RED: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/ce7d699541239a3ea80ba763693f33e19387ca5e

GREEN: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/25ace8c397ec1bd9bc6d480c0fc5f72ede71fff0



#### Done with input and output api.

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/3bca5ad5d7608ffbb4dfd98238ce766aca99aabc



#### Add local data base:

RED:(wrong commit message, should be RED)

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/c1e911be790171498651880434ad22302058fc22

GREEN:

Link:https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/6792099502c33489f5abcec9161a535dacc816bd

Add new methods(updateUserInfo and updateLeagueEntry)

RED:https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/70355cb3bfdc51b9721529307d78d49ea0e6df6d

GREEN: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/0a7eb980ec25608e74417d2b542cb57e7b9643ad

#### realised that no need to test data base, so delete the test cases

#### Database done(successfully interact with UI):

Link: https://github.sydney.edu.au/ywan3184/SCD2_2021_Exam/commit/fa9bb4bef04796b3872a301fe673cd299920b450



#### Only keep getSummonerByName. Since that is what pre-work asks.





### Exam

Because the only files I change are `SummonerInfoController.java` which is for GUI and `SummonerInfo.fxml` which is also for GUI, no models are changed so there are also no new tests.

