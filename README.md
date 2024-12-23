# Spring Todo アプリ

- [環境構築手順](docs/SetupDevleperEmviroment.md)
- [アーキテクチャ](docs/Architecture.md)
- [テーブル定義](docs/TableDefinition.md)

## ディレクトリ構成

`mapper` `model` , `resourece/mapper`は MyBatis Generator でテーブル定義から自動生成します。

```
src/
├── main/
│   ├── java/com/example/todo/
│   │   ├── controller/   // REST API コントローラー
│   │   ├── service/      // ビジネスロジック
│   │   ├── mapper/       // データベース操作 (MyBatis) ※ 自動生成
│   │   ├── model/        // エンティティ ※ 自動生成
│   │   └── config/       // 設定クラス
│   ├── resources/
│   │   ├── application.yml // アプリケーション設定
│   │   └── mapper/        // MyBatis マッパーXML ※ 自動生成
│   └── test/
│       └── java/com/example/todo/
```

## MyBatis Generator

MyBatis Generator は、データベースのテーブル定義から、エンティティクラス、マッパーインターフェース、マッパーXML を自動生成します。

`generatorConfig.xml` にテーブル定義と生成するクラスの設定を記述し、以下のコマンドで生成します。

### コマンド実行

```bash
./gradlew mbGenerator
```

### IDEA で実行

メニューから [実行] > [実行構成の編集] から Gradle タスクを追加し、`mbGenerator` を設定します。
作成したGradleタスクを実行してください。

## OpenAPI 

Springdoc は Spring MVC のアノテーション（例: @RestController, @RequestMapping, @GetMapping）を解析し、API ドキュメントを自動生成します。

アプリケーションを起動すると、以下のエンドポイントが有効になります：

1. Swagger UI:http://localhost:8080/swagger-ui.html
2. OpenAPI ドキュメント (JSON):http://localhost:8080/v3/api-docs

## Spring-Todo プロンプト

https://chatgpt.com/g/g-n7Rs0IK86-grimoire/c/67665809-2544-8004-98ad-0e6e581f3ac7

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
