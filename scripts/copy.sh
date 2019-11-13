#!/usr/bin/env bash

echo 'Copy files...'

scp -i ~/.ssh/key-Stockholm.pem -r  \
  dist \
  ubuntu@13.53.111.152:/home/ubuntu/

ssh -i ~/.ssh/key-Stockholm.pem ubuntu@13.53.111.152 <<EOF

sudo mv dist/* /var/www/html/

EOF


echo 'Bye'
