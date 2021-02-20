#!/bin/bash
APP_NAME=cube-project.jar

pids=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}'`
for pid in $pids
do
    echo stop $APP_NAME java process: $pid
    kill -9 $pid
done

nr_pids=`ps -ef | grep $APP_NAME | grep -v grep | wc -l`
while [ $nr_pids -gt 0 ]
do
    nr_pids=`ps -ef | grep $APP_NAME | grep -v grep | wc -l`
done