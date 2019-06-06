# About the project

## Inspiration
To start our brainstorming, we first decided our hack would be something actually useful and of high impact to users. We also did not want to create something that we know has been done before. Mental health is a big topic right now at RIT and across the country, so we started with ideas that can help cheer someone up, hence the name of our application. We had been interested in the opportunities that the use of artificial intelligence (AI) could bring. With access to Google Cloud Platform, we began looking through tools we could use relating to AI. We decided on Google Cloud Vision, since we are also writing a research paper on computer vision for a class.

## What it does
Our application is a web-app that is designed to cheer you up. It does this by presenting images from some of Reddit's top subs for stress-relieving images. The application utilizes the user's webcam to determine if the picture presented made the user happier. If it did, similar images will show up in the future.

## How we built it
We started with Google Cloud Vision to determine the reaction of the user to the image displayed on the screen. The image from the user's webcam gets sent through the API for processing, at which point we can determine if the user showed happiness. As for the images, a tool was used to pull images off the top 1000 posts of r/aww and r/eyebleach on Reddit, and we discarded any files that were not .jpg or .png. Google Cloud the labeled the images, and the top three labels for each picture were used to categorize them. After viewing each image, the application determines the level of happiness of the user. The higher the likelihood of joy, the greater the probability of seeing images in that category again. Additional probabilities are used to reduce the likelihood of seeing the same picture again.

## Challenges we ran into
Setting up Google Cloud Platform, Google Cloud Vision, and Maven took up a significant portion of our time. Getting the application to deploy on our domain from Domain.com took a lot of work, with errors dealing with Google Credentials, as well as errors with the original code to read the user's webcam feed. There was a steep learning curve to getting all the APIs to work correctly together with all the correct dependencies. The Google Cloud Vision face detection struggles with detecting the two of the other three emotions other than joy: sorrow and anger. The remaining emotion, surprise, was not of much use to the application. Joy works very well with the user smiling, especially showing teeth, so for the basis of rating pictures/categories, only joy was used.

## Accomplishments that we're proud of
We are largely proud of just setting up Google Cloud Platform with our domain from Domain.com with all the APIs. Beyond that, we take pride in being able to receive feed from the user's webcam and determining their level of joy. Lastly, our process of adjusting probabilities to determine what types of pictures the user should see next took a lot of communication, as there was no one correct way to do it.

## What we learned
In the end, we got a small introduction to machine learning and AI, more specifically computer vision. Our skills with web-development were reinforced, and we learned a lot about how to use Google APIs, along with the others.

## What's next for Cheer Me Up
In the future, Cheer Me Up may become better at determining what the user likes and doesn't like, either through facial detection, or simply prompting the user to select how he/she feels about the image.

## Built With
java, google-cloud-vision, maven, json, mozilla-media-stream, google-app-engine, javascript, html, html5, ajax, css, jquery

## Try it out
https://www.cheermeup.me


# Google App Engine setup

appengine-standard-archetype
============================

This is a generated App Engine Standard Java application from the appengine-standard-archetype archetype.

See the [Google App Engine standard environment documentation][ae-docs] for more
detailed instructions.

[ae-docs]: https://cloud.google.com/appengine/docs/java/


* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) (at least 3.5)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud)

## Setup

    gcloud init
    gcloud auth application-default login

## Maven
### Running locally

    mvn appengine:run

### Deploying

    mvn appengine:deploy

## Testing

    mvn verify

As you add / modify the source code (`src/main/java/...`) it's very useful to add
[unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)

## Updating to latest Artifacts

An easy way to keep your projects up to date is to use the maven [Versions plugin][versions-plugin].

    mvn versions:display-plugin-updates
    mvn versions:display-dependency-updates
    mvn versions:use-latest-versions

Note - Be careful when changing `javax.servlet` as App Engine Standard uses 3.1 for Java 8, and 2.5
for Java 7.

Our usual process is to test, update the versions, then test again before committing back.

[plugin]: http://www.mojohaus.org/versions-maven-plugin/
