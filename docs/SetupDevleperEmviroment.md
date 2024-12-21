# 開発環境セットアップ

## Rancher Desktop インストール

```shell
brew install --cask rancher
```

- Finderから Rancher Desktopを起動する。
- Setting > Docker Engine で　`dokerd` で起動していることを確認
- docker cli が有効であるか確認。エラーの場合はターミナルを再起動して確認する。
```shell
docker version
```

## Postgresql のクライアントのみインストール

```shell
brew install libpq
```

```shell
# zshの設定ファイルにコマンド追加
echo 'export PATH="$(brew --prefix libpq)/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

パスが通っていることを確認
```shell
psql --version
```

## postgreSQL Docker 起動

```shell
docker compose up -d
```

## PostgreSQL の接続確認

```shell
psql -h localhost -p 15432 -U postgres
```
- パスワードに `postgres` を入力
- 接続できたら `quit` で終了する。
