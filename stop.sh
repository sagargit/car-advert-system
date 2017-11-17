echo '-- Going to kill the nohup bg process ---'
kill `cat target/universal/save_pid.txt` && sleep 5;