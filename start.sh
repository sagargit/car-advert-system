#!/bin/bash
echo '-- Cleaning existing target --'
rm -rf target/
echo '-- Start Packaging Rew3-API --'
sbt dist
cd target/universal/
echo '-- Unzipping distribution --'
unzip car-advert-system-1.0.0 -d car-advert-system-1.0.0
echo '-- Now staring application --'
nohup car-advert-system-1.0.0/car-advert-system-1.0.0/bin/car-advert-system -Dreload=true -J-server -J-Xmx2048m -J-Xmx2048M -Dconfig.resource=application.conf -Dhttp.port=9005 > /dev/null 2>&1 & echo $! > RUNNING_PID
echo '-- Rew3-Api has been started successfully --'