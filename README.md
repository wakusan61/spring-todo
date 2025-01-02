# Spring Todo アプリ

- [環境構築手順](docs/SetupDevleperEmviroment.md)
- [アーキテクチャ](docs/Architecture.md)
- [テーブル定義](docs/TableDefinition.md)

## ディレクトリ構成

`mapper` `model` , `resourece/mapper`は MyBatis Generator でテーブル定義から自動生成します。

```
src/
├── main/
│   ├── java/wakusan61/spring/todo/
│   │   ├── common/       // 各レイヤー（Controller、Service、Repositoryなど）で共通的に使用されるユーティリティクラスや定数などを格納します。
│   │   ├── config/       // Spring の設定クラス（@Configuration アノテーションが付与されたクラス）を格納します。例えば、MyBatis の設定や Jackson の設定などが含まれます。
│   │   ├── controller/   // REST API のエンドポイントを定義し、リクエストを受け付けてレスポンスを返します。
│   │   ├── converter/    // DTO（Data Transfer Object）と Entity の間の変換を行うクラスを格納します。
│   │   ├── dto/          // Controller と Service 間でデータをやり取りするためのオブジェクトを格納します。
│   │   ├── handler/      // 例外処理やその他のハンドリングロジックを格納します。
│   │   ├── mapper/       // MyBatis のマッパーインターフェースを格納します。これらは通常、自動生成されます。
│   │   ├── model/        // データベースのテーブルに対応する Entity クラスを格納します。これらは通常、自動生成されます。
│   │   ├── serializer/   // JSON と Java オブジェクト間のシリアライズ・デシリアライズを行うクラスを格納します。
│   │   └── service/      // ビジネスロジックを実装するクラスを格納します。
│   ├── resources/
│   │   ├── application.yml // アプリケーション設定
│   │   └── mapper/        // MyBatis マッパーXML ※ 自動生成
│   └── test/
│       └── java/wakusan61/spring/todo/ // テストコード
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

### 注意点

- Mybatis Generator の実行は、テーブルを定義した後に実行してください。
- 対象のテーブルを `generatorConfig.xml` に追加してください。
- Entity クラスに Enum を設定した場合は上書きされてしまうため、再生成時に注意してください。

## OpenAPI 

Springdoc は Spring MVC のアノテーション（例: @RestController, @RequestMapping, @GetMapping）を解析し、API ドキュメントを自動生成します。

アプリケーションを起動すると、以下のエンドポイントが有効になります：

1. Swagger UI:http://localhost:8080/swagger-ui.html
2. OpenAPI ドキュメント (JSON):http://localhost:8080/v3/api-docs

## 生成AIによるハンズオン作成

Spring Boot を学習する際に、生成AIにハンズオンを作ってもらいながら学習しました。
以下のプロンプトと、GitHubのリポジトリを参照してもらいながら、 `Chat-GPT 4o` `Claude 3.5 Sonet` `GitHub Copilot` `Gemini 2.0 Flash Experiment` などのAIを使って、ハンズオンを作成してもらいました。

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
3. 「中級エンジニアが理解できる説明レベル」を意識し、初心者向けの基本説明は省略しても構いません。

# 【最終出力の要望】
- セクションごとに見出し（## ）を使い、コードブロックは ```java などで記載してください。
- 注意点や補足があれば、**Tips**などの見出しで追加してください。

```
