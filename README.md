# GithubUserSearchApp

## List Github users and search them by username

### This is a Kotlin Project.
User needs to fetch user details from API given below.

The screen has a bottom navigation with two tabs - ***"Event"*** and ***"Schedule"***

-*On typing the user name the github search API is invoked which displays the results*

-*On click of each item in the user list, respective github profile is opened in Browser.*

***The Schedule Tab:***  

-*Displays Tomorrow Schedule list with Date sorted in Ascending order.*

-*The Schedule screen is refreshed every 30 seconds.*

-*If tomorrow schedule is unavailable, default text message is displayed "Tomorrow Data Unavailable!!".*

***Android Components used:***

*Development Language* - Kotlin

*Architecture* - MVVM

*Dependency Injection* - Dagger Hilt

*Networking Libraries* - Retrofit

*Asynchronous calls* - LiveData, Coroutines

*UI* - ConstraintLayout

*API For User List* - https://api.github.com/users

*API For User Search List* - https://api.github.com/search/users
