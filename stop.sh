echo '-- Going to kill the nohup bg process ---'
cd target/universal/
kill `cat save_pid.txt`;