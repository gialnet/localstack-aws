#!/bin/bash

# Verificar si la cola ya existe para evitar recrearla
if ! awslocal sqs get-queue-url --queue-name mi-cola-test > /dev/null 2>&1; then
    echo "Creating SQS queue: mi-cola-test"
    awslocal sqs create-queue --queue-name mi-cola-test
    
    # Enviar un mensaje de prueba inicial a la cola solo si es nueva
    echo "Sending initial test message to the queue"
    awslocal sqs send-message --queue-url http://localhost:4566/000000000000/mi-cola-test --message-body '{"id":"123", "data":"Initial test message from LocalStack"}'
    
    echo "SQS queue created successfully!"
else
    echo "SQS queue 'mi-cola-test' already exists, skipping creation"
fi
