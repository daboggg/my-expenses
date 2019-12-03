#!/usr/bin/env bash

echo 'Copy files...'

scp -i ~/.ssh/key-Stockholm.pem -r  \
  dist \
  ubuntu@13.53.111.152:/home/ubuntu/

scp -i ~/.ssh/key-Stockholm.pem \
  target/my-expenses-0.0.1-SNAPSHOT.jar \
  ubuntu@13.53.111.152:/home/ubuntu/

echo 'Restart server...'

ssh -i ~/.ssh/key-Stockholm.pem ubuntu@13.53.111.152 <<EOF

pgrep java |xargs kill -9
nohup java -jar my-expenses-0.0.1-SNAPSHOT.jar > log.txt &

sudo rm -r /var/www/html/*

sudo mv dist/* /var/www/html/

EOF

echo 'Bye'
