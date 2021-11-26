#!/bin/bash

function trap_ctrlc() {
	sudo docker-compose down
	exit 2
}

trap "trap_ctrlc" 2

clear &&
    cd ./app &&
    ./mvnw clean package &&
    cd .. &&
    sudo docker-compose build &&
    sudo docker-compose up
