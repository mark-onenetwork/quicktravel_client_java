[ ![Download](https://api.bintray.com/packages/sealink/maven/quicktravel_client/images/download.svg) ](https://bintray.com/sealink/maven/quicktravel_client/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/sealink/quicktravel_client_java/badge.svg?branch=master)](https://coveralls.io/github/sealink/quicktravel_client_java?branch=master)
[![Build Status](https://travis-ci.org/sealink/quicktravel_client_java.svg?branch=master)](https://travis-ci.org/sealink/quicktravel_client_java)

QuickTravel API library is a common use library developed primarily for use in the SeaLink Android
application suite, including Ticketing, Companion and Albert.

#### Authentication

A Bearer token can be retrieve from the Auth system. Once the token has been
returned you can then pass it to the QuickTravel API using the token interceptor.

```
    BearerTokenInterceptor interceptor = new BearerTokenInterceptor();
    interceptor.setToken("");
    new OkHttpClient.Builder().addInterceptor(interceptor).build();
```

#### Deployment

Deployment is handled via gradle and travis if you follow the correct git conventions.

If you are creating a new minor or major release than you would do the following.

- Create a new release branch, based upon the major and min

```concept
git checkout -b release/0.1
```

- Update the changelog with the correct version number (in this case 0.1.0)

```concept
git commit -m "Release 0.1.0"
git tag 0.1.0
git push origin master --tags
```

A point release would simply be a tag on this branch and you would follow the previous procedure.

#### Project Builds and Structure

- Gradle is the build tool of choice and all things related are contained within the project.

Some example build tasks include

```concept
./gradlew build
./gradlew test
./gradlew versionInfo
```

- Project is maven compatible however we only publish to Bintray so you will need to manually configure
  the repository, full instructions can be found at the following site
  https://bintray.com/sealink/maven/quicktravel_client
- TravisCI is used to test / build and publish new versions.
- JUnit is used for integration and unit level testing.

#### Development

If you want to use this library directly in an Android Studio project, you can
make the following changes to the gradle build

Edit `settings.gradle` and create a new project dependency.

```
include ':Dependency'
project(':Dependency').projectDir = new File('~user/src/quicktravel_client/')
```

Edit `app/build.gradle`
Replace the original dependency, i.e.

```
implementation 'au.com.sealink.quicktravel.client:quicktravel_client:x.x.x'
```

with

```
  compile project(':Dependency')
```

Resync the gradle process.

The quicktravel_client project should now be a direct dependency and any changes made should be immediately effective, if not you may be required to restart Android Studio.

