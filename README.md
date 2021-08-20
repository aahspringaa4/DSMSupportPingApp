# DSM 팀 프로젝트

## DSMSupportPingApp

DSM 팀 프로젝트로 만들게 된 앱입니다.

### Introduction
---
이 앱은 파티를 만들어 도움(공부, 게임, 운동 등도 가능)을 주고 받을 수 있는 앱입니다.
1. 회원가입을 하여 로그인을 해서 들어갈 수 있습니다.
2. 로그인해서 들어가면 가장 먼저 다른 사람들이 올린 게시글을 볼 수 있습니다.
3. 파티를 생성 할 수 있습니다..
4. 다른 사람 파티에 참여 할 수 있습니다.
5. 본인글만 볼 수 있는 마이페이지가 있습니다.
6. 마이페이지에서는 수정 삭제를 할 수 있습니다.

### Develope Environment
---
+ Android Studio
+ IntelliJ
+ PostMan
 
### Libraries
---
#### Api
Retrofit 
+ 유저 관리 (로그인 및 회원 가입) Post, Get
+ 게시물 관리 (게시물 등록 및 확인) Post, Get
+ 마이페이지 관리 (본인 게시물 확인 및 수정, 삭제) Get, Patch, Delete
+ 파티 관리 (파티 참여 및 파티 탈퇴) Post, Delete

#### 대표적인 tool
Gson : Json의 자바 오브젝트의 직력화, 역직렬화를 해줌
RecyclerView : 메모리 사용량을 최소화하면서 UI에 많은 양의 데이터를 표시함
