#!/bin/bash
JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8001,server=y,suspend=n "
JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false "
JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom  -server -Xms512M -Xmx512M -Xss256K -XX:MetaspaceSize=16M -XX:MaxMetaspaceSize=128M  -XX:MaxDirectMemorySize=1g -XX:SurvivorRatio=8 -XX:+UseG1GC -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof"
WORK_PATH=$(cd `dirname $0`; pwd)
cd $WORK_PATH
cd ..
$JAVA_HOME/bin/java $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_OPTS -jar cube-project.jar
