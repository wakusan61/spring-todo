# spring-todo

## 環境構築手順

[こちら](docs/SetupDevleperEmviroment.md)を参照してください。

## ディレクトリ構成

```
src/
├── main/
│   ├── java/com/example/todo/
│   │   ├── controller/   // REST API コントローラー
│   │   ├── service/      // ビジネスロジック
│   │   ├── repository/   // データベース操作 (MyBatis)
│   │   ├── model/        // エンティティ
│   │   └── config/       // 設定クラス
│   ├── resources/
│   │   ├── application.yml // アプリケーション設定
│   │   └── mapper/        // MyBatis マッパーXML
│   └── test/
│       └── java/com/example/todo/
```

## Spring-Todo プロンプト

https://chatgpt.com/g/g-n7Rs0IK86-grimoire

```markdown
# 依頼

- Spring のキャッチアップをしたいので、Todo アプリを作る #ハンズオン を考えてください。
- 私はコーディング歴 15 年の Web エンジニアです。そのため中級者がキャッチアップしやすいレベルでお願いします。

# 役割

- あなたは、プロの Web エンジニアのコーチです。

# ハンズオン

- 指定した ##技術スタック を全て活用した Todo アプリのハンズオンです。

## 技術スタック

全て最新のバージョンを指定して下さい。
LTS バージョンが最新ではない場合は、LTS のバージョンを指定してください。

- Java
- Spring
- Mybatis
- PostgresSQL
- springdoc-openapi

## 開発環境

- IDEA
- PostgreSQL(Docker)
- rancher desktop

## テスト

- JUnit
- DBUnit
- TestContainer
-

## CD

- GitHubActions

## AWS

- ECS
- CloudWatch

# 出力

- マークダウン形式にしてください。
- 開発環境構築、プロジェクトセットアップ、Todo アプリとテスト実装、CI の整備、CD の整備のような順番でお願いします。
```
