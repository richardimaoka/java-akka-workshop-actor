ワークショップではチケット販売サイトをテーマに簡単なakkaアプリケーションを作成します。

主な機能
- チケットの予約・抽選販売
- チケットの先着販売

特徴
- APIサーバーをAkkaで実装
- API: Akka HTTP
- Event Storming
- CQRS
- Clustering

段階的に少しずつ複雑さを挙げていきます。最初は単純なアクターのみのアプリケーション、もしくはアクターすら使わないデータベースとAPIのみのアプリケーションを。
そこからアクターの詳細なモデリングと実装、Event Sourcingの導入、Clusteringの利用、最終的にはAWS上で走らせる準備もする予定です(AWS無理かも)。

ワークショップでは時間がないので、参加者にはコーディングをしてもらうつもりはなく、git checkoutでどんどん次のgit commitへと遷移してもらい、
アプリケーションを動かしながらログやソースコードを見てもらって動作を掴んでもらいます。
