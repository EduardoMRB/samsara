#!/bin/bash

# /opt/ingestion-api/config/config.edn
#
# KAFKA_PORT_9092_TCP_ADDR: REQUIRED: (one or more)
# RIEMANN_PORT_5555_TCP_ADDR: OPTIONAL

export OUTPUT_TOPIC=${OUTPUT_TOPIC}
export BROKER_LIST=${BROKER_LIST}
export TRACKING_ENABLED=${TRACKING_ENABLED:-false}
export RIEMANN=${RIEMANN}

synapse /opt/ingestion-api/config/config.edn.tmpl

exec /usr/bin/supervisord -c /etc/supervisor/supervisord.conf
