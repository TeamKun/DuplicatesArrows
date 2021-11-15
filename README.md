# DuplicatesArrows

矢が拡散するプラグイン

## 動作環境

- Minecraft 1.16.5
- PaperMC 1.16.5

## コマンド

- duparrow
    - addPlayer <Player>: 拡散射撃対象プレイヤーを指定する。@a、@r可。
    - removePlayer <Player>: 拡散射撃対象プレイヤーから除外する。@a可。
    - config
        - check: Config設定値を確認する。
        - reset: Config設定値を初期化する。拡散射撃対象プレイヤーも初期化される。
        - multiple <Number>: 拡散射撃数を指定する。1以上の整数。デフォルトは100。
        - speed <Number>: 射撃スピード倍率を指定する。0より大きい数。デフォルトは1。
        - spread <Number>: 拡散射撃範囲を指定する。0より大きい数。デフォルトは10。