# ANDROID MVVM BASE [![Build Status](https://www.bitrise.io/app/d2294837d63eb335/status.svg?token=caesXeFNDW63wtFf-9tw_w&branch=master)](https://www.bitrise.io/app/d2294837d63eb335#) 

Library with Android code base for MVVM architecture with DataBinding.

## Installation

#### Download
[ ![Download](https://api.bintray.com/packages/anapsil83/maven-android/android-mvvm-base/images/download.svg) ](https://bintray.com/anapsil83/maven-android/android-mvvm-base/_latestVersion)

#### Gradle

* Add it in your root build.gradle:
```groovy 
allprojects {
   repositories {
       maven { url  "https://dl.bintray.com/anapsil83/maven-android" }
   }
}
``` 

* Add the dependency:
```groovy
implementation 'net.anapsil:android-mvvm-base:0.1.0'
```

#### Maven

* Add the bintray repository to your maven file:

```xml
<repository>
    <id>bintray-anapsil83-maven-android</id>
    <url>https://dl.bintray.com/anapsil83/maven-android</url>
</repository>
```

* Add the dependency in the form
```xml
<dependency>
  <groupId>net.anapsil</groupId>
  <artifactId>android-mvvm-base</artifactId>
  <version>0.1.0</version>
</dependency>
```

License
-------

    Copyright (C) 2018 - Ana Paula da Silva

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.