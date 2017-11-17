test -f target/universal/RUNNING_PID && kill `cat target/universal/RUNNING_PID` && sleep 5;
rm target/universal/RUNNING_PID;