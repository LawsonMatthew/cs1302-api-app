# Deadline

Modify this file to satisfy a submission requirement related to the project
deadline. Please keep this file organized using Markdown. If you click on
this file in your GitHub repository website, then you will see that the
Markdown is transformed into nice looking HTML.

## Part 1.1: App Description

> Please provide a firendly description of your app, including the
> the primary functions available to users of the app. Be sure to
> describe exactly what APIs you are using and how they are connected
> in a meaningful way.

> **Also, include the GitHub `https` URL to your repository.**

My SpaceXLaunchApp is designed to take user input via a selection
of the five launchpads that SpaceX owns for use in its launches. Upon selection,
the user is prompted to press the "Search" button at which point the app generates
an api request for the SpaceX api launchpad endpoint. The response is parsed into a class
object representing the launchpad response and the app displays relevant launchpad information
and provided image. At that point the user is prompted press the "GetWeather" button which
generates a request to the AccuWeather Locations api using the Locality variable from the SpaceX
launchpad response. The Locations api provides a location ID for the launchpad location, which
is then used to generate a request to the AccuWeather current conditions api. Then, the app
parses the current conditions response, pulling various details about the current weather conditions
at the launchpad's location, and displays several of these details to allow the user to analyze
ongoing conditions at one of SpaceX's launchpads.

Github URL: https://github.com/LawsonMatthew/cs1302-api-app.git

## Part 1.2: APIs

> For each RESTful JSON API that your app uses (at least two are required),
> include an example URL for a typical request made by your app. If you
> need to include additional notes (e.g., regarding API keys or rate
> limits), then you can do that below the URL/URI. Placeholders for this
> information are provided below. If your app uses more than two RESTful
> JSON APIs, then include them with similar formatting.

### API 1

```
https://api.spacexdata.com/v4/launchpads/:id
```

> The SpaceX launchpads api allows you to query for specific launcpads and their relevat information. In
    the SpaceXLaunch app, users select a launchpad which is matched with it's corresponding ID. This ID is
    passed to the above endpoint which returns relevant information for each launchpad, some of which
    is displayed to the user. The locality variable from the response is then passed to the AccuWeather api
    to identify a location Id.

### API 2

```
http://dataservice.accuweather.com/locations/v1/cities/autocomplete
http://dataservice.accuweather.com/currentconditions/v1/:LocationId
```

> The second api is the AccuWeather api, which requires an api included in one of the app class variables.
    The app first takes the "locality" string from the SpaceX api and passes it to the autocomplete search
    endpoint for the AccuWeather Locations api. This api returns a corresponding location key which is passed by
    the app to the AccuWeather Current Conditions api, which takes a location key and returns live weather data
    for the specified location.

## Part 2:

> What is something new and/or exciting that you learned from working
> on this project?

Something new I learned while completing this project was the vast amount of generally free
publicly available apis for any range of topics. What was particularly new was that even within each of these apis,
there were often several endpoints that you needed to query and pass responses between to truly access all of the
information you desired. This functionality is quite a bit different than what I expected coming from the gallery
project as the Itunes Search api was fairly easy to use and provided all the information required for that project. Playing
around with the apis, I realized that for basically free in most cases, there is some sort of api that can help you access
information on any topic which allows you to create apps or projects that are really only bound by you imagination in how
to use all the different information available publicly.

## Part 3: Retrospect

> If you could start the project over from scratch, what do
> you think might do differently and why?

If I started over from scratch I believe that I might start at the UI elements and UI componenet classes. This is what
I did for the gallery project and it seemed to work pretty well. For this project I tried something different and started
with the api response classes and finished with pulling all the information together to implement the UI parts. The UI parts
were sort of backseat in comparison to the api functionality on this project due to more api interactions, and I think
that working from api response classes then to UI ended up resulting in me writing a bunch of getters/setters for response
variables that I didnt even end up using in the end. I think having a plan or outline going in is super important, although
actually creating the UI elements solidifies the truly necessary pieces. I think moving from api response classes to UI in
this case resulted in me wandering about trying to play with the UI connecting the pieces which was a little confusing
at times. I am still not quite sure which approach I liked best, but this is something I am thinking about as I continue
programming and I intend on continuing to try slightly different approaches to find what I find to be most efficient from
a time and energy perspective so I don't get bogged down in certain parts for future projects.
