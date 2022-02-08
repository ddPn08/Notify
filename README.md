# Nofity 📤
サーバーで起きたいろいろな出来事をDiscordのWebHookで通知します。  
Paper(Paper派生), Waterfall, Velocityで使用できます。  

# Usage
`config.yml` の`webhook` の値を送信したいURLに変更してください。  

```yaml
webhook: 'https://discord.com/api/webhooks/example'
```

# Futures
## InformNewPlayer
新しいプレイヤーが参加した際に通知をします。  
プレイヤーのデータは`InformNewPlayer/cache.yml`に保存されます。リセットしたい場合はこのファイルを削除してください。  

### 今後更に機能を追加予定.. たぶん...