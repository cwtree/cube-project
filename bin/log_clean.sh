# 把下面语句编辑到crontab里，定时清理日志，每天凌晨1点清理日志，保留7天日志

0 1 * * * find /apprun/cube-project/logs -mtime +7 -name "*.log.*" -exec rm -rf {} \;