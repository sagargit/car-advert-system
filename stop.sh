echo '-- Going to kill the nohup bg process ---'
sudo test -f target/universal/save_pid.txt && kill `cat target/universal/save_pid.txt` && sleep 5;
sudo rm target/universal/save_pid.txt;
