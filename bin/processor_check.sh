# crontab里加上进程自动拉起任务，做个简单的监控
# */1 * * * * source /etc/profile;sh /apprun/${YOUR_APP_NAME}/bin/processor_check.sh
# processor_check.sh脚本内容如下

#!/bin/bash
APP_NAME=cube-project.jar
WORK_PATH=$(cd `dirname $0`; pwd)

pids=`ps -ef | grep $APP_NAME | grep -v grep | wc -l`
if [ "$pids" -le 0 ]
then
        echo "start $APP_NAME processor ......"
        cd $WORK_PATH
		cd ..
        nohup sh bin/start.sh >/dev/null 2>&1 &
        sleep 5
        pids=`ps -ef | grep $APP_NAME | grep -v grep | wc -l`
        if [ "$pids" -gt 0 ]
        then 
                echo "$APP_NAME processor started"
        else
                echo "Failed to start $APP_NAME processor"
        fi
else
        echo "$APP_NAME processor is running ......"
fi