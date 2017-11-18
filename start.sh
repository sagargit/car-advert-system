#!/bin/bash
echo '-- Cleaning existing target --'
rm -rf target/
echo '-- Start Packaging Car App --'
sbt dist
cd target/universal/
echo '-- Unzipping distribution --'
unzip car-advert-system-1.0.0 -d car-advert-system-1.0.0
echo '-- Now staring application --'
nohup echo "Jenkins test $(date)" > $WORKSPACE/app.log 2>&1
echo '-- Car advert system has been started successfully --'