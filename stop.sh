echo '-- Going to kill the nohup bg process ---'
test -f target/universal/RUNNING_PID && kill `cat target/universal/RUNNING_PID` && sleep 5;
rm target/universal/RUNNING_PID;