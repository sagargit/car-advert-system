echo '-- Going to kill the nohup bg process ---'
sudo chmod -R 757 target/universal/save_pid.pid
sudo pkill -F target/universal/save_pid.pid && sleep 5

