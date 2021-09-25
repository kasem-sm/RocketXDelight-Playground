# Delight-Playground

🎉 Native Android application built with Kotlin and Jetpack Compose. This app also illustrates the
usage of advance libraries such as Ktor, SqlDelight, Hilt etc.

---
[![Delight-Playground](https://img.shields.io/badge/APK-Delight--Playground-green)](https://github.com/kasem-sm/SpaceXDelight-Playground/blob/master/app/release/app-release.apk)

<img src="https://github.com/kasem-sm/SpaceXDelight-Playground/blob/master/art/1.jpg" width="25%"> -*- <img src="https://github.com/kasem-sm/SpaceXDelight-Playground/blob/master/art/2.jpg" width="25%">

### **SqlDelight**

- SqlDelight can be used for various platforms such as Android, iOS, web etc. The initial setup of
  SqlDelight is not as easy as Room but also not difficult.
- If you need to know more about SqlDelight, please read the documentation or [my article](https://github.com/kasem-sm/RocketXDelight-Playground#-medium-artcile).

### **Content of the app**

- We will initially fetch a list of rockets from [_*SpaceX
  Api*_](https://api.spacexdata.com/v3/rockets) using Ktor.
- We will
  then [cache the data](https://github.com/kasem-sm/SpaceXDelight-Playground/blob/master/app/src/main/java/kasem/sm/delightplayground/interactors/GetRocketsUseCase.kt)
  using SqlDelight and display it.

### Libraries used and it's source

- [SqlDelight](https://github.com/cashapp/sqldelight)
- [Ktor](https://github.com/ktorio/ktor)
- [Dagger-Hilt](https://github.com/google/dagger)
- [Coil](https://coil-kt.github.io/coil/compose/)
- [Swipe Refresh from Accompanist](https://google.github.io/accompanist/swiperefresh/)

### 🧾 **Medium article**

- [Link will be updated soon](https://medium.com/@kasem.)

### ***TODO***

- Implement dark mode toggle using Jetpack Datastore.

### ***How to contribute?***

- You can start a discussion and then open a PR, Let's learn together!

## License

```
Copyright 2020 Kasem S.M

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```