# ANDROID MVVM BASE [![Build Status](https://www.bitrise.io/app/d2294837d63eb335/status.svg?token=caesXeFNDW63wtFf-9tw_w&branch=master)](https://www.bitrise.io/app/d2294837d63eb335#)  [![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14) 

Library with Android code base for MVVM architecture with DataBinding and Dagger2 for dependency injection.

## Installation

[ ![Latest version](https://api.bintray.com/packages/anapsil83/maven-android/android-mvvm-base/images/download.svg) ](https://bintray.com/anapsil83/maven-android/android-mvvm-base/_latestVersion)

#### Gradle

```groovy
implementation 'net.anapsil.android.mvvm:library:0.1.1'
```

#### Maven

```xml
<dependency>
    <groupId>net.anapsil.android.mvvm</groupId>
    <artifactId>library</artifactId>
    <version>0.1.1</version>
</dependency>
```
## How to use

#### Dependency injection
Create your AppModule class extending BaseModule and add **@Module** annotation. 
Include all necessary methods to provide your dependencies.

```java
@Module
public abstract class AppModule extends BaseModule {
    private static final String BASE_URL = "https://gateway.marvel.com/";
  
    @Binds
    abstract Application bindApplication(DemoApplication application);
  
    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
  
    @Singleton
    @Provides
    static MarvelApi provideApi(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }
}
```

Create your AppComponent class and add **@Component** annotation and set the modules AndroidSupportInjectionModule and AppModule.
Also include your application modules.

```java
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<DemoApplication> {
  
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DemoApplication> {
    }
}
```

#### Application
Create your custom application extending net.anapsil.mvvmbase.App.class.

```java
public class DemoApplication extends App {
  
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
```

#### Activities


#### Fragments


#### Adapters


## Testing

## Next Steps

* Add Android Architecture Components (LiveData and ViewModel)
* Add Kotlin support

License
-------

    Copyright (C) 2018 - Ana Paula da Silva

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License inputStream distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.