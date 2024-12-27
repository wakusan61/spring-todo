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
# 【目的・背景】
- Spring のキャッチアップ用に Todo アプリのハンズオン資料を作りたい。
- 私（または受講者）はコーディング歴 15 年の Web エンジニアで、中級者がスムーズに学べるレベルを想定。

# 【あなたの役割】
- あなたはプロの Web エンジニア兼コーチです。
- 中級者エンジニア向けの Spring ハンズオンを作成するアシスタントとしてふるまってください。

# 【前提条件】
- 必ず最新のバージョンを採用。最新が LTS でない場合は LTS バージョンを採用してください。
- 使用する技術スタック:
    - Java
    - Spring
    - Mybatis
    - PostgreSQL
    - springdoc-openapi
    - gradle
- 開発環境:
    - IDEA
    - PostgreSQL (Docker)
    - rancher desktop
- テスト:
    - JUnit
    - DBUnit
    - TestContainer
- CI
    - GitHubActions
- CD
    - AWS ECS
    - CloudWatch
    

# 【出力形式】
- **マークダウン**で出力してください。
- 下記セクションごとに説明を行ってください。
    1. **概要**（目的と前提技術の紹介）
    2. **開発環境構築**（IDEA, Docker, rancher desktop セットアップ方法など）
    3. **プロジェクトセットアップ**（Java, Spring の初期設定 / build.gradle の設定）
    4. **Todo アプリ実装**（エンティティ定義, Mybatis 設定, Controller, Repository, Service）
    5. **API ドキュメント生成**（springdoc-openapi の設定方法と確認手順）
    6. **テスト実装**（JUnit, DBUnit, TestContainer の使い方）
    7. **CI（GitHubActions）整備**（ビルド, テスト, Lint など）
    8. **CD（AWS ECS, CloudWatch）整備**（デプロイ手順, ログ監視設定）

# 【ステップバイステップ要望】
1. 最初に、上記セクションをすべて箇条書きで示してください。
2. 続いて、各セクションの詳細な説明・サンプルコード・実行手順を順番に書いてください。
3. 分量は各セクションあたり 5〜10 行程度でコンパクトにまとめてください。
4. 「中級エンジニアが理解できる説明レベル」を意識し、初心者向けの基本説明は省略しても構いません。

# 【最終出力の要望】
- セクションごとに見出し（## ）を使い、コードブロックは ```java などで記載してください。
- 注意点や補足があれば、**Tips**などの見出しで追加してください。

```
