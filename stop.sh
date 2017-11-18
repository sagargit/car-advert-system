echo '-- Going to kill the nohup bg process ---'
sudo chmod -R 757 save_pid.pid
sudo pkill -F save_pid.pid && sleep 5

