echo '-- Going to kill the nohup bg process ---'
test -f target/universal/save_pid.txt && kill `cat target/universal/save_pid.txt` && sleep 5;