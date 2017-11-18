echo '-- Going to kill the nohup bg process ---'
sudo kill `cat target/universal/save_pid.txt` && sleep 5;
sudo rm target/universal/save_pid.txt;
