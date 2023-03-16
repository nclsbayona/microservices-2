#!/bin/bash

cd MicroserviciosApp;
sh run.sh &
sleep 30s;

cd ../Divider;
sh run.sh 8000 &
sh run.sh 8001 &

cd ../Multiplier;
sh run.sh 8002 &
sh run.sh 8003 &

cd ../Substractor;
sh run.sh 8004 &
sh run.sh 8005 &

cd ../Sumator;
sh run.sh 8006 &
sh run.sh 8007 &

cd ../Cliente;
sh run.sh;
