#!/bin/bash
echo '-- Cleaning existing target --'
rm -rf target/
echo '-- Start Packaging Car App --'
sbt dist
cd target/universal/
echo '-- Unzipping distribution --'
unzip car-advert-system-1.0.0 -d car-advert-system-1.0.0
echo '-- Now staring application --'
nohup car-advert-system-1.0.0/car-advert-system-1.0.0/bin/car-advert-system -Dreload=true -J-server -J-Xmx2048m -J-Xmx2048M -Dhttp.port=9005 > $WORKSPACE/app.log 2>&1 &
echo $! > save_pid.txt
echo '-- Car advert system has been started successfully --'