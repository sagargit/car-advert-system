echo '-- Going to kill the nohup bg process ---'
kill `cat target/universal/RUNNING_PID` && sleep 5;