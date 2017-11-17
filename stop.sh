echo '-- Going to kill the nohup bg process ---'
kill $(cat target/universal/save_pid.txt);
rm target/universal/save_pid.txt;