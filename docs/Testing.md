# テストアーキテクチャ

このドキュメントでは、このプロジェクトにおけるテスト戦略と実装について説明します。

## テスト戦略

このプロジェクトでは、以下の種類のテストを実施しています。

*   **単体テスト (Unit Test):** 個々のクラスやメソッドの動作を独立して検証します。Spring Boot のコンテキストはロードしません。
*   **結合テスト (Integration Test):** 複数のコンポーネントが連携して動作するかどうかを検証します。必要に応じて Spring Boot のコンテキストをロードします。

## テストフレームワーク

このプロジェクトでは、以下のテストフレームワークを使用しています。

*   **JUnit Jupiter (JUnit 5):** Java の単体テストおよび結合テストのための標準的なフレームワークです。
*   **Mockito:** モックオブジェクトを作成するためのフレームワークです。単体テストにおいて、依存オブジェクトの振る舞いを制御するために使用します。
*   **JaCoCo:** コードカバレッジを測定するためのツールです。テストの網羅性を評価するために使用します。

## テストの実行

テストは Gradle の `test` タスクを使用して実行します。

```bash
./gradlew test
```

テスト結果のレポートは `build/reports/tests/test/index.html` に出力されます。

## JaCoCo カバレッジレポート

コードカバレッジレポートは `build/reports/jacoco/test/html/index.html` に出力されます。

## Gradle 設定 (`build.gradle`)

テストと JaCoCo に関する Gradle の設定は以下のとおりです。

```groovy
plugins {
    id 'jacoco'
}

tasks.named('test') {
    useJUnitPlatform()
    finalizedBy jacocoTestReport
}

jacoco {
    toolVersion = "0.8.10" // 最新の JaCoCo バージョンを指定 (適宜更新)
}

jacocoTestReport {
    reports {
        xml.required = true
        html.required = true
    }
}
```

この設定は、プロジェクトのルートディレクトリにある `build.gradle` ファイルに記述されています。

- `plugins { id 'jacoco' }`: JaCoCo プラグインを適用します。
- `tasks.named('test') { ... }`: `test` タスクの設定を行います。
    - `useJUnitPlatform()`: JUnit Platform を使用してテストを実行します。
    - `finalizedBy jacocoTestReport`: テスト実行後に JaCoCo レポートを生成します。
- `jacoco { ... }`: JaCoCo の全体的な設定を行います。`toolVersion` で JaCoCo のバージョンを指定します。
- `jacocoTestReport { ... }`: JaCoCo レポートの設定を行います。`reports` でレポートの形式 (XML, HTML) を指定します。

## GitHub Actions 設定 (`.github/workflows/main.yml`)

GitHub Actions を使用して、プッシュ時とプルリクエスト時に自動的にテストを実行し、JaCoCo レポートを生成するように設定します。

```yaml
name: Java CI with Gradle

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

permissions:
  contents: read

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew build
    - name: Test with Gradle and Generate Jacoco Report
      run: ./gradlew test jacocoTestReport
    - name: Upload JaCoCo coverage report
      uses: actions/upload-artifact@v3
      with:
        name: jacoco-report
        path: build/reports/jacoco/test/html
```

この設定は、リポジトリの `.github/workflows/main.yml` ファイルに記述されています。

- `on`: ワークフローの実行トリガーを指定します。プッシュ時とプルリクエスト時に実行するように設定しています。
- `jobs.build.steps`: ワークフローのステップを定義します。
    - `actions/checkout@v3`: リポジトリをチェックアウトします。
    - `actions/setup-java@v3`: JDK 21 をセットアップします。
    - `chmod +x gradlew`: `gradlew` に実行権限を与えます。
    - `./gradlew build`: ビルドを実行します。
    - `./gradlew test jacocoTestReport`: テストを実行し、jacocoレポートを生成します。
    - `actions/upload-artifact@v3`: JaCoCo レポートをアーティファクトとしてアップロードします。

この設定により、GitHub 上でテストとカバレッジレポートの確認が可能になります。