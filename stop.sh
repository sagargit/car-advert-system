echo '-- Going to kill the nohup bg process ---'
sudo chmod -R 757 save_pid.pid
sudo kill -9 `cat target/universal/save_pid.pid`

