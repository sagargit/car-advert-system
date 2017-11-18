echo '-- Going to kill the nohup bg process ---'
sudo kill -9 `cat target/universal/save_pid.txt` && sleep 5;
