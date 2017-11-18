echo '-- Going to kill the nohup bg process ---'
sudo kill $( sudo lsof -i:9005 -t )

